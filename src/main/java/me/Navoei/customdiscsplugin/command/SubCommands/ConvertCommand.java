package me.Navoei.customdiscsplugin.command.SubCommands;

import me.Navoei.customdiscsplugin.CustomDiscs;
import me.Navoei.customdiscsplugin.command.SubCommand;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class ConvertCommand extends SubCommand {

    @Override
    public String getName() {
        return "convert";
    }

    @Override
    public String getDescription() { return ChatColor.GRAY + "\u041f\u0440\u0435\u043e\u0431\u0440\u0430\u0437\u0443\u0435\u0442 \u043f\u043b\u0430\u0441\u0442\u0438\u043d\u043a\u0443 \u0438\u0437 \u0441\u0442\u0430\u0440\u043e\u0433\u043e \u0444\u043e\u0440\u043c\u0430\u0442\u0430 \u0432 \u043d\u043e\u0432\u044b\u0439.";}

    @Override
    public String getSyntax() {
        return ChatColor.GREEN + "/customdisc convert";
    }

    @Override
    public void perform(Player player, String[] args) {

        if (!player.hasPermission("customdiscs.convert")) {
            player.sendMessage(ChatColor.RED + "\u0412\u044b \u043d\u0435 \u043c\u043e\u0436\u0435\u0442\u0435 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c \u044d\u0442\u0443 \u043a\u043e\u043c\u0430\u043d\u0434\u0443!");
            return;
        }

        if (isOldCustomMusicDisc(player.getInventory().getItemInMainHand())) {
            ItemMeta customDiscMeta = player.getInventory().getItemInMainHand().getItemMeta();

            Component soundFileNameComponent = customDiscMeta.lore().get(1).asComponent();
            String soundFileName = PlainTextComponentSerializer.plainText().serialize(soundFileNameComponent);
            List<Component> songName = customDiscMeta.lore();

            PersistentDataContainer data = customDiscMeta.getPersistentDataContainer();
            data.set(new NamespacedKey(CustomDiscs.getInstance(), "customdisc"), PersistentDataType.STRING, soundFileName);

            songName.remove(1);

            customDiscMeta.lore(songName);

            customDiscMeta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);

            player.getInventory().getItemInMainHand().setItemMeta(customDiscMeta);

            player.sendMessage(ChatColor.GREEN + "\u041f\u043b\u0430\u0441\u0442\u0438\u043d\u043a\u0430 \u0443\u0441\u043f\u0435\u0448\u043d\u043e \u043f\u0435\u0440\u0435\u0432\u0435\u0434\u0435\u043d\u0430 \u0432 \u043d\u043e\u0432\u044b\u0439 \u0444\u043e\u0440\u043c\u0430\u0442");

        } else {
            player.sendMessage(ChatColor.RED + "\u041d\u043e\u0432\u044b\u0439 \u0444\u043e\u0440\u043c\u0430\u0442 \u043d\u0435 \u043c\u043e\u0436\u0435\u0442 \u0431\u044b\u0442\u044c \u043f\u0440\u0438\u043c\u0435\u043d\u0451\u043d");
        }

    }

    private boolean isOldCustomMusicDisc (ItemStack item) {

        return item.hasItemFlag(ItemFlag.HIDE_ENCHANTS) && (
                item.getType().equals(Material.MUSIC_DISC_13) ||
                        item.getType().equals(Material.MUSIC_DISC_CAT) ||
                        item.getType().equals(Material.MUSIC_DISC_BLOCKS) ||
                        item.getType().equals(Material.MUSIC_DISC_CHIRP) ||
                        item.getType().equals(Material.MUSIC_DISC_FAR) ||
                        item.getType().equals(Material.MUSIC_DISC_MALL) ||
                        item.getType().equals(Material.MUSIC_DISC_MELLOHI) ||
                        item.getType().equals(Material.MUSIC_DISC_STAL) ||
                        item.getType().equals(Material.MUSIC_DISC_STRAD) ||
                        item.getType().equals(Material.MUSIC_DISC_WARD) ||
                        item.getType().equals(Material.MUSIC_DISC_11) ||
                        item.getType().equals(Material.MUSIC_DISC_WAIT) ||
                        item.getType().equals(Material.MUSIC_DISC_OTHERSIDE) ||
                        item.getType().equals(Material.MUSIC_DISC_5) ||
                        item.getType().equals(Material.MUSIC_DISC_PIGSTEP)
        );
    }
}
