package io.github.c9lul12btw.cmds;

import io.github.c9lul12btw.RankTitleString;
import io.github.c9lul12btw.gui.TitleGui;
import io.github.c9lul12btw.gui.GuiUtil.GuiItem;
import io.github.c9lul12btw.utils.LoggerUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class TitleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            LoggerUtil.logMessage("&cYou must be a player to run this command!");
            return true;
        }
        Player player = (Player) sender;
        OfflinePlayer target;

        if (args.length == 0) {
            return true;
        }

        switch (args[0]) {
            case "select":
                if (args.length < 2) {
                    target = player;
                }
                else {
                    target = Bukkit.getOfflinePlayer(args[1]);
                }
                FileConfiguration config = RankTitleString.getInstance().getConfig();
                ArrayList<GuiItem> items = new ArrayList<>();
                for (String title : config.getConfigurationSection("users." + target.getName().toLowerCase() + ".titles").getKeys(false)) {
                    items.add(TitleGui.getTitleItem(
                            target.getPlayer(),
                            title,
                            config.getString("users." + target.getName().toLowerCase() + ".build_rank"),
                            config.getString("users." + target.getName().toLowerCase() + ".staff_rank"),
                            config)
                    );
                    LoggerUtil.logMessage(title + " - " + config.getBoolean("users." + target.getName().toLowerCase() + ".titles." + title));
                }
                return TitleGui.show(player, 1, items, null, target);

        }
        return true;
    }
}
