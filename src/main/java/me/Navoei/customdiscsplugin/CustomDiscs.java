package me.Navoei.customdiscsplugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import de.maxhenkel.voicechat.api.BukkitVoicechatService;
import me.Navoei.customdiscsplugin.command.CommandManager;
import me.Navoei.customdiscsplugin.event.JukeBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Jukebox;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.io.File;
import java.util.Objects;

public final class CustomDiscs extends JavaPlugin {

    public static final String PLUGIN_ID = "CustomDiscs";
    public static final Logger LOGGER = LogManager.getLogger(PLUGIN_ID);
    static CustomDiscs instance;

    @Nullable
    private VoicePlugin voicechatPlugin;

    public float musicDiscDistance;
    public float musicDiscVolume;

    @Override
    public void onEnable() {

        CustomDiscs.instance = this;

        BukkitVoicechatService service = getServer().getServicesManager().load(BukkitVoicechatService.class);

        this.saveDefaultConfig();

        File musicData = new File(this.getDataFolder(), "musicdata");
        if (!(musicData.exists())) {
            musicData.mkdirs();
        }

        if (service != null) {
            voicechatPlugin = new VoicePlugin();
            service.registerPlugin(voicechatPlugin);
            // CustomDiscs
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "");
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD + " \u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2557   \u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2557   \u2588\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2557  \u2588\u2588\u2588\u2588\u2588\u2588\u2557");
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2551   \u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255d\u255a\u2550\u2550\u2588\u2588\u2554\u2550\u2550\u255d\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255d\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255d");
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "\u2588\u2588\u2551  \u255a\u2550\u255d\u2588\u2588\u2551   \u2588\u2588\u2551\u255a\u2588\u2588\u2588\u2588\u2588\u2557    \u2588\u2588\u2551   \u2588\u2588\u2551  \u2588\u2588\u2551\u2588\u2588\u2554\u2588\u2588\u2588\u2588\u2554\u2588\u2588\u2551\u2588\u2588\u2551  \u2588\u2588\u2551\u2588\u2588\u2551\u255a\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2551  \u255a\u2550\u255d\u255a\u2588\u2588\u2588\u2588\u2588\u2557");
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "\u2588\u2588\u2551  \u2588\u2588\u2557\u2588\u2588\u2551   \u2588\u2588\u2551 \u255a\u2550\u2550\u2550\u2588\u2588\u2557   \u2588\u2588\u2551   \u2588\u2588\u2551  \u2588\u2588\u2551\u2588\u2588\u2551\u255a\u2588\u2588\u2554\u255d\u2588\u2588\u2551\u2588\u2588\u2551  \u2588\u2588\u2551\u2588\u2588\u2551 \u255a\u2550\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2551  \u2588\u2588\u2557 \u255a\u2550\u2550\u2550\u2588\u2588\u2557");
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "\u255a\u2588\u2588\u2588\u2588\u2588\u2554\u255d\u255a\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255d\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255d   \u2588\u2588\u2551   \u255a\u2588\u2588\u2588\u2588\u2588\u2554\u255d\u2588\u2588\u2551 \u255a\u2550\u255d \u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255d\u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255d\u255a\u2588\u2588\u2588\u2588\u2588\u2554\u255d\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255d");
            getServer().getConsoleSender().sendMessage(ChatColor.GOLD + " \u255a\u2550\u2550\u2550\u2550\u255d  \u255a\u2550\u2550\u2550\u2550\u2550\u255d \u255a\u2550\u2550\u2550\u2550\u2550\u255d    \u255a\u2550\u255d    \u255a\u2550\u2550\u2550\u2550\u255d \u255a\u2550\u255d     \u255a\u2550\u255d\u255a\u2550\u2550\u2550\u2550\u2550\u255d \u255a\u2550\u255d\u255a\u2550\u2550\u2550\u2550\u2550\u255d  \u255a\u2550\u2550\u2550\u2550\u255d \u255a\u2550\u2550\u2550\u2550\u2550\u255d");
            // Translated
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + "\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2557  \u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2557  \u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2557      \u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2557");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + "\u255a\u2550\u2550\u2588\u2588\u2554\u2550\u2550\u255d\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2551\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255d\u2588\u2588\u2551     \u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u255a\u2550\u2550\u2588\u2588\u2554\u2550\u2550\u255d\u2588\u2588\u2554\u2550\u2550\u2550\u2550\u255d\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + "   \u2588\u2588\u2551   \u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255d\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2551\u2588\u2588\u2554\u2588\u2588\u2557\u2588\u2588\u2551\u255a\u2588\u2588\u2588\u2588\u2588\u2557 \u2588\u2588\u2551     \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2551   \u2588\u2588\u2551   \u2588\u2588\u2588\u2588\u2588\u2557  \u2588\u2588\u2551  \u2588\u2588\u2551");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + "   \u2588\u2588\u2551   \u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2551\u2588\u2588\u2551\u255a\u2588\u2588\u2588\u2588\u2551 \u255a\u2550\u2550\u2550\u2588\u2588\u2557\u2588\u2588\u2551     \u2588\u2588\u2554\u2550\u2550\u2588\u2588\u2551   \u2588\u2588\u2551   \u2588\u2588\u2554\u2550\u2550\u255d  \u2588\u2588\u2551  \u2588\u2588\u2551");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + "   \u2588\u2588\u2551   \u2588\u2588\u2551  \u2588\u2588\u2551\u2588\u2588\u2551  \u2588\u2588\u2551\u2588\u2588\u2551 \u255a\u2588\u2588\u2588\u2551\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255d\u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2551  \u2588\u2588\u2551   \u2588\u2588\u2551   \u2588\u2588\u2588\u2588\u2588\u2588\u2588\u2557\u2588\u2588\u2588\u2588\u2588\u2588\u2554\u255d");
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + "   \u255a\u2550\u255d   \u255a\u2550\u255d  \u255a\u2550\u255d\u255a\u2550\u255d  \u255a\u2550\u255d\u255a\u2550\u255d  \u255a\u2550\u2550\u255d\u255a\u2550\u2550\u2550\u2550\u2550\u255d \u255a\u2550\u2550\u2550\u2550\u2550\u2550\u255d\u255a\u2550\u255d  \u255a\u2550\u255d   \u255a\u2550\u255d   \u255a\u2550\u2550\u2550\u2550\u2550\u2550\u255d\u255a\u2550\u2550\u2550\u2550\u2550\u255d");
            // Board original
            getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + " ______________________________________________________________________________________");
            getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "| \u0410\u0432\u0442\u043e\u0440 \u043e\u0440\u0438\u0433\u0438\u043d\u0430\u043b\u044c\u043d\u043e\u0433\u043e \u043f\u043b\u0430\u0433\u0438\u043d\u0430:" + ChatColor.GOLD + " Navoei" + ChatColor.YELLOW + "                                                  |");
            getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "| \u041e\u0441\u043d\u043e\u0432\u0430\u043d\u043e \u043d\u0430 \u0432\u0435\u0440\u0441\u0438\u0438 \u043e\u0440\u0438\u0433\u0438\u043d\u0430\u043b\u044c\u043d\u043e\u0433\u043e \u043f\u043b\u0430\u0433\u0438\u043d\u0430" + ChatColor.GOLD + " 2.2.2" + ChatColor.YELLOW + "                                       |");
            getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "| GitHub \u043e\u0440\u0438\u0433\u0438\u043d\u0430\u043b\u044c\u043d\u043e\u0433\u043e \u043f\u043b\u0430\u0433\u0438\u043d\u0430:" + ChatColor.GOLD + " https://github.com/Navoei/CustomDiscs" + ChatColor.YELLOW + "                  |");
            getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "|______________________________________________________________________________________|");
            // Board Translated
            getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "| \u041f\u0435\u0440\u0435\u0432\u043e\u0434 \u043d\u0430 \u0440\u0443\u0441\u0441\u043a\u0438\u0439 \u0438 \u0443\u043b\u0443\u0447\u0448\u0435\u043d\u0438\u044f \u043f\u043b\u0430\u0433\u0438\u043d\u0430:" + ChatColor.DARK_PURPLE + " NikSne" + ChatColor.LIGHT_PURPLE + "                                       |");
            getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "| \u0412\u0435\u0440\u0441\u0438\u044f \u043f\u0435\u0440\u0435\u0432\u043e\u0434\u0430 \u0438 \u0443\u043b\u0443\u0447\u0448\u0435\u043d\u0438\u0439" + ChatColor.DARK_PURPLE + " 1.2" + ChatColor.LIGHT_PURPLE + "                                                      |");
            getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "| GitHub \u043f\u0435\u0440\u0435\u0432\u043e\u0434\u0430 \u0438 \u0443\u043b\u0443\u0447\u0448\u0435\u043d\u0438\u0439:" + ChatColor.DARK_PURPLE + " https://github.com/NikSneMC/CustomDiscs" + ChatColor.LIGHT_PURPLE + "                 |");
            getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "|______________________________________________________________________________________|");
            getServer().getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "");
            //"Successfully registered CustomDiscs plugin" message
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[CustomDiscs Translated] \u041f\u043b\u0430\u0433\u0438\u043d \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u0437\u0430\u0433\u0440\u0443\u0436\u0435\u043d!");
        } else {
            LOGGER.error("\u041d\u0435 \u0443\u0434\u0430\u043b\u043e\u0441\u044c \u0437\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044c \u043f\u043b\u0430\u0433\u0438\u043d customdiscs!");
        }

        getServer().getPluginManager().registerEvents(new JukeBox(), this);
        getServer().getPluginManager().registerEvents(new HopperManager(), this);
        getCommand("customdisc").setExecutor(new CommandManager());

        musicDiscDistance = getConfig().getInt("music-disc-distance");
        musicDiscVolume = Float.parseFloat(Objects.requireNonNull(getConfig().getString("music-disc-volume")));

        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

        protocolManager.addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.Play.Server.WORLD_EVENT) {
            @Override
            public void onPacketSending(PacketEvent event) {
                PacketContainer packet = event.getPacket();

                if (packet.getIntegers().read(0).toString().equals("1010")) {
                    Jukebox jukebox = (Jukebox) packet.getBlockPositionModifier().read(0).toLocation(event.getPlayer().getWorld()).getBlock().getState();

                    if (!jukebox.getRecord().hasItemMeta()) return;

                    if (jukebox.getRecord().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(CustomDiscs.getInstance(), "customdisc"), PersistentDataType.STRING)) {
                        event.setCancelled(true);
                    }

                }
            }
        });

    }

    @Override
    public void onDisable() {
        if (voicechatPlugin != null) {
            getServer().getServicesManager().unregister(voicechatPlugin);
            getServer().getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "[CustomDiscs Translated] \u041f\u043b\u0430\u0433\u0438\u043d customdiscs \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u0432\u044b\u0433\u0440\u0443\u0436\u0435\u043d!");
        }
    }

    public static CustomDiscs getInstance() {
        return instance;
    }
}
