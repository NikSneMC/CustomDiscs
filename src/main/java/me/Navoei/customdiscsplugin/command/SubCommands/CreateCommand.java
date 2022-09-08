package me.Navoei.customdiscsplugin.command.SubCommands;

import me.Navoei.customdiscsplugin.CustomDiscs;
import me.Navoei.customdiscsplugin.command.SubCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CreateCommand extends SubCommand {

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return ChatColor.GRAY + "\u0421\u043e\u0437\u0434\u0430\u0435\u0442 \u043a\u0430\u0441\u0442\u043e\u043c\u043d\u0443\u044e \u043f\u043b\u0430\u0441\u0442\u0438\u043d\u043a\u0443.";
    }

    @Override
    public String getSyntax() {
        return ChatColor.GREEN + "\u041d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u043e\u0432! ( /customdisc create <\u0438\u043c\u044f \u0444\u0430\u0439\u043b\u0430> [\"\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435 \u043f\u043b\u0430\u0441\u0442\u0438\u043d\u043a\u0438\"] )";
    }

    @Override
    public void perform(Player player, String[] args) {
        if (isMusicDisc(player)) {
            if (args.length >= 3) {

                if (!player.hasPermission("customdiscs.create")) {
                    player.sendMessage(ChatColor.RED + "\u0412\u044b \u043d\u0435 \u043c\u043e\u0436\u0435\u0442\u0435 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u044d\u0442\u0443 \u043a\u043e\u043c\u0430\u043d\u0434\u0443!");
                    return;
                }

                // /cd create test.mp3 "test"
                //      [0]     [1]     [2]
                //Find file, if file not there then say "file not there"
                String songname = "";
                String filename = args[1];

                if (customName(readQuotes(args)).equalsIgnoreCase("")) {
                    player.sendMessage(ChatColor.RED + "\u0412\u044b \u0434\u043e\u043b\u0436\u043d\u044b \u0443\u043a\u0430\u0437\u0430\u0442\u044c \u043d\u0430\u0437\u0432\u0430\u043d\u0438\u0435 \u0432\u0430\u0448\u0435\u0439 \u043f\u043b\u0430\u0441\u0442\u0438\u043d\u043a\u0438!");
                    return;
                }

                File getDirectory = new File(CustomDiscs.getInstance().getDataFolder(), "musicdata");
                File songFile = new File(getDirectory.getPath(), filename);
                if (songFile.exists()) {
                    if (getFileExtension(filename).equals("wav") || getFileExtension(filename).equals("mp3")) {
                        songname = args[1];
                    } else {
                        player.sendMessage(ChatColor.RED + "\u0422\u043e\u043b\u044c\u043a\u043e \u0438\u0433\u0440\u043e\u043a\u0438 \u043c\u043e\u0433\u0443\u0442 \u0424\u0430\u0439\u043b \u043d\u0435 \u0432 \u0444\u043e\u0440\u043c\u0430\u0442\u0435 wav \u0438\u043b\u0438 mp3!");
                        return;
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "\u0424\u0430\u0439\u043b \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d!");
                    return;
                }

                //Sets the lore of the item to the quotes from the command.
                ItemStack disc = new ItemStack(player.getInventory().getItemInMainHand());
                ItemMeta meta = disc.getItemMeta();
                @Nullable List<Component> itemLore = new ArrayList<>();
                final TextComponent customLoreSong = Component.text()
                        .decoration(TextDecoration.ITALIC, false)
                        .content(customName(readQuotes(args)))
                        .color(NamedTextColor.GRAY)
                        .build();
                itemLore.add(customLoreSong);
                meta.addItemFlags(ItemFlag.values());
                meta.lore(itemLore);

                PersistentDataContainer data = meta.getPersistentDataContainer();
                data.set(new NamespacedKey(CustomDiscs.getInstance(), "customdisc"), PersistentDataType.STRING, filename);

                player.getInventory().getItemInMainHand().setItemMeta(meta);

                player.sendMessage("\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435 \u0432\u0430\u0448\u0435\u0433\u043e \u0444\u0430\u0439\u043b\u0430: " + ChatColor.GRAY + songname);
                player.sendMessage("\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435 \u0432\u0430\u0448\u0435\u0439 \u043f\u043b\u0430\u0441\u0442\u0438\u043d\u043a\u0438: " + ChatColor.GRAY + customName(readQuotes(args)));

            } else {
                player.sendMessage(ChatColor.RED + "\u041d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u0430\u0440\u0433\u0443\u043c\u0435\u043d\u0442\u043e\u0432! ( /customdisc create <\u0438\u043c\u044f \u0444\u0430\u0439\u043b\u0430> [\"\u041d\u0430\u0437\u0432\u0430\u043d\u0438\u0435 \u043f\u043b\u0430\u0441\u0442\u0438\u043d\u043a\u0438\"] )");
            }
        } else {
            player.sendMessage(ChatColor.RED + "\u0412\u044b \u043d\u0435 \u0434\u0435\u0440\u0436\u0438\u0442\u0435 \u043f\u043b\u0430\u0441\u0442\u0438\u043d\u043a\u0443 \u0432 \u043e\u0441\u043d\u043e\u0432\u043d\u043e\u0439 \u0440\u0443\u043a\u0435!");
        }
    }

    private String getFileExtension(String s) {
        int index = s.lastIndexOf(".");
        if (index > 0) {
            return s.substring(index + 1);
        } else {
            return "";
        }
    }

    private ArrayList<String> readQuotes(String[] args) {
        ArrayList<String> quotes = new ArrayList<>();
        String temp = "";
        boolean inQuotes = false;

        for (String s : args) {
            if (s.startsWith("\"") && s.endsWith("\"")) {
                temp += s.substring(1, s.length()-1);
                quotes.add(temp);
            } else if (s.startsWith("\"")) {
                temp += s.substring(1);
                quotes.add(temp);
                inQuotes = true;
            } else if (s.endsWith("\"")) {
                temp += s.substring(0, s.length()-1);
                quotes.add(temp);
                inQuotes = false;
            } else if (inQuotes) {
                temp += s;
                quotes.add(temp);
            }
            temp = "";
        }

        return quotes;
    }

    private String customName(ArrayList<String> q) {

        StringBuffer sb = new StringBuffer();

        for (String s : q) {
            sb.append(s);
            sb.append(" ");
        }

        if (sb.isEmpty()) {
            return sb.toString();
        } else {
            return sb.toString().substring(0, sb.length()-1);
        }
    }

    private boolean isMusicDisc(Player p) {

        return p.getInventory().getItemInMainHand().getType().equals(Material.MUSIC_DISC_13) ||
                p.getInventory().getItemInMainHand().getType().equals(Material.MUSIC_DISC_CAT) ||
                p.getInventory().getItemInMainHand().getType().equals(Material.MUSIC_DISC_BLOCKS) ||
                p.getInventory().getItemInMainHand().getType().equals(Material.MUSIC_DISC_CHIRP) ||
                p.getInventory().getItemInMainHand().getType().equals(Material.MUSIC_DISC_FAR) ||
                p.getInventory().getItemInMainHand().getType().equals(Material.MUSIC_DISC_MALL) ||
                p.getInventory().getItemInMainHand().getType().equals(Material.MUSIC_DISC_MELLOHI) ||
                p.getInventory().getItemInMainHand().getType().equals(Material.MUSIC_DISC_STAL) ||
                p.getInventory().getItemInMainHand().getType().equals(Material.MUSIC_DISC_STRAD) ||
                p.getInventory().getItemInMainHand().getType().equals(Material.MUSIC_DISC_WARD) ||
                p.getInventory().getItemInMainHand().getType().equals(Material.MUSIC_DISC_11) ||
                p.getInventory().getItemInMainHand().getType().equals(Material.MUSIC_DISC_WAIT) ||
                p.getInventory().getItemInMainHand().getType().equals(Material.MUSIC_DISC_OTHERSIDE) ||
                p.getInventory().getItemInMainHand().getType().equals(Material.MUSIC_DISC_5) ||
                p.getInventory().getItemInMainHand().getType().equals(Material.MUSIC_DISC_PIGSTEP);
    }

}
