package me.Navoei.customdiscsplugin;

import de.maxhenkel.voicechat.api.VoicechatServerApi;
import de.maxhenkel.voicechat.api.audiochannel.AudioChannel;
import de.maxhenkel.voicechat.api.audiochannel.AudioPlayer;
import de.maxhenkel.voicechat.api.audiochannel.LocationalAudioChannel;
import javazoom.spi.mpeg.sampled.convert.MpegFormatConversionProvider;
import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import javax.sound.sampled.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class PlayerManager {

    private final Map<UUID, Stoppable> playerMap;
    private final ExecutorService executorService;
    private static AudioFormat FORMAT = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 48000F, 16, 1, 2, 48000F, false);

    public PlayerManager() {
        this.playerMap = new ConcurrentHashMap<>();
        this.executorService = Executors.newSingleThreadExecutor(r -> {
            Thread thread = new Thread(r, "AudioPlayerThread");
            thread.setDaemon(true);
            return thread;
        });
    }

    public void playLocationalAudio(VoicechatServerApi api, Path soundFilePath, Player bukkitPlayer, Block block) {
        UUID id = UUID.nameUUIDFromBytes(block.getLocation().toString().getBytes());

        LocationalAudioChannel audioChannel = api.createLocationalAudioChannel(id, api.fromServerLevel(block.getWorld()), api.createPosition(block.getLocation().getX() + 0.5d, block.getLocation().getY() + 0.5d, block.getLocation().getZ() + 0.5d));

        if (audioChannel == null) return;

        audioChannel.setCategory(VoicePlugin.MUSIC_DISC_CATEGORY);
        audioChannel.setDistance(CustomDiscs.getInstance().musicDiscDistance);

        AtomicBoolean stopped = new AtomicBoolean();
        AtomicReference<de.maxhenkel.voicechat.api.audiochannel.AudioPlayer> player = new AtomicReference<>();

        playerMap.put(id, () -> {
            synchronized (stopped) {
                stopped.set(true);
                de.maxhenkel.voicechat.api.audiochannel.AudioPlayer audioPlayer = player.get();
                if (audioPlayer != null) {
                    audioPlayer.stopPlaying();
                }
            }
        });

        executorService.execute(() -> {
            de.maxhenkel.voicechat.api.audiochannel.AudioPlayer audioPlayer = playChannel(api, bukkitPlayer, audioChannel, block, soundFilePath);
            if (audioPlayer == null) {
                playerMap.remove(id);
                return;
            }

            audioPlayer.setOnStopped(() -> {

                Bukkit.getScheduler().runTask(CustomDiscs.getInstance(), () -> HopperManager.instance().itemJukeboxToHopper(block));

                playerMap.remove(id);
            });

            synchronized (stopped) {
                if (!stopped.get()) {
                    player.set(audioPlayer);
                } else {
                    audioPlayer.stopPlaying();
                }
            }
        });
    }

    @Nullable
    private de.maxhenkel.voicechat.api.audiochannel.AudioPlayer playChannel(VoicechatServerApi api, Player bukkitPlayer, AudioChannel audioChannel, Block block, Path soundFilePath) {
        try {
            short[] audio = readSoundFile(soundFilePath);
            AudioPlayer audioPlayer = api.createAudioPlayer(audioChannel, api.createEncoder(), audio);
            audioPlayer.startPlaying();
            return audioPlayer;
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getLogger().warning("\u0412\u043e\u0437\u043d\u0438\u043a\u043b\u0430 \u043e\u0448\u0438\u0431\u043a\u0430 \u0432: " + block.getLocation());
            if (bukkitPlayer != null) {
                bukkitPlayer.sendMessage(ChatColor.RED + "\u041f\u0440\u0438 \u043f\u043e\u043f\u044b\u0442\u043a\u0435 \u0432\u043e\u0441\u043f\u0440\u043e\u0438\u0437\u0432\u0435\u0434\u0435\u043d\u0438\u044f \u043f\u043b\u0430\u0441\u0442\u0438\u043d\u043a\u0438 \u043f\u0440\u043e\u0438\u0437\u043e\u0448\u043b\u0430 \u043e\u0448\u0438\u0431\u043a\u0430!");
            }
            return null;
        }
    }

    private static short[] readSoundFile(Path file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        return VoicePlugin.voicechatApi.getAudioConverter().bytesToShorts(convertFormat(file, FORMAT));
    }

    private static byte[] convertFormat(Path file, AudioFormat audioFormat) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream finalInputStream = null;

        if (getFileExtension(file.toFile().toString()).equals("wav")) {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file.toFile());
            finalInputStream = AudioSystem.getAudioInputStream(audioFormat, inputStream);
        } else if (getFileExtension(file.toFile().toString()).equals("mp3")) {

            AudioInputStream inputStream = new MpegAudioFileReader().getAudioInputStream(file.toFile());
            AudioFormat baseFormat = inputStream.getFormat();
            AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getFrameRate(), false);
            AudioInputStream convertedInputStream = new MpegFormatConversionProvider().getAudioInputStream(decodedFormat, inputStream);
            finalInputStream = AudioSystem.getAudioInputStream(audioFormat, convertedInputStream);

        }

        assert finalInputStream != null;

        return adjustVolume(finalInputStream.readAllBytes(), CustomDiscs.getInstance().musicDiscVolume);
    }

    private static byte[] adjustVolume(byte[] audioSamples, double volume) {

        if (volume > 1d || volume < 0d) {
            CustomDiscs.getInstance().getServer().getLogger().warning("\u041e\u0448\u0438\u0431\u043a\u0430: \u0413\u0440\u043e\u043c\u043a\u043e\u0441\u0442\u044c \u0432 \u043a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u0438 \u0434\u043e\u043b\u0436\u043d\u0430 \u0431\u044b\u0442\u044c \u043c\u0435\u0436\u0434\u0443 0 \u0438 1!");
            return null;
        }

        byte[] array = new byte[audioSamples.length];
        for (int i = 0; i < array.length; i+=2) {
            // convert byte pair to int
            short buf1 = audioSamples[i+1];
            short buf2 = audioSamples[i];

            buf1 = (short) ((buf1 & 0xff) << 8);
            buf2 = (short) (buf2 & 0xff);

            short res= (short) (buf1 | buf2);
            res = (short) (res * volume);

            // convert back
            array[i] = (byte) res;
            array[i+1] = (byte) (res >> 8);

        }
        return array;
    }


    public void stopLocationalAudio(Location blockLocation) {
        UUID id = UUID.nameUUIDFromBytes(blockLocation.toString().getBytes());
        Stoppable player = playerMap.get(id);
        if (player != null) {
            player.stop();
        }
        playerMap.remove(id);
    }

    public static float getLengthSeconds(Path file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        short[] audio = readSoundFile(file);
        return (float) audio.length / FORMAT.getSampleRate();
    }

    public boolean isAudioPlayerPlaying(Location blockLocation) {
        UUID id = UUID.nameUUIDFromBytes(blockLocation.toString().getBytes());
        return playerMap.containsKey(id);
    }

    private static String getFileExtension(String s) {
        int index = s.lastIndexOf(".");
        if (index > 0) {
            return s.substring(index + 1);
        } else {
            return "";
        }
    }

    private static PlayerManager instance;

    public static PlayerManager instance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    private interface Stoppable {
        void stop();
    }

}
