package io.github.c9lul12btw.gui;

import io.github.c9lul12btw.Rank;
import io.github.c9lul12btw.Rank.TitleRank;
import io.github.c9lul12btw.RankTitleString;
import io.github.c9lul12btw.gui.GuiUtil.*;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class TitleGui extends Gui {

    private static final int[] SLOTS = { 10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33,
            34, 37, 38, 39, 40, 41, 42, 43 }, CORNER_SLOTS = { 0, 8, 45, 53 },
            LINES_SLOTS = { 1, 2, 3, 4, 5, 6, 7, 9, 18, 27, 36, 17, 26, 35, 44, 46, 47, 48, 50, 51, 52 };;
    private final ArrayList<GuiItem> items;
    private OfflinePlayer target;
    private GuiItem pane1, pane2;

    private TitleGui(Player p, int page, ArrayList<GuiItem> items, Gui previousGui, OfflinePlayer target) {
        super(p, "§2Title Selector", 54, page);
        this.items = items;
        this.target = target;
        final int size = items.size();
        this.fillSlots(SLOTS, page, items.toArray(new GuiItem[size]));
        this.setGuiLink(previousGui);
        border(page);
        this.setItem(46, getPreviousPage());
        this.setItem(49, getExit());
        this.setItem(52, getNextPage());
    }

    private void border(int page) {
        pane1 = getPane(3);
        pane2 = getPane(0);
        GuiItem[] panes1 = new GuiItem[CORNER_SLOTS.length];
        GuiItem[] panes2 = new GuiItem[LINES_SLOTS.length];
        Arrays.fill(panes1, pane1);
        Arrays.fill(panes2, pane2);
        this.fillSlots(CORNER_SLOTS, page, panes1);
        this.fillSlots(LINES_SLOTS, page, panes2);
    }

    public static boolean show(Player p, int page, ArrayList<GuiItem> items, Gui previousGui, OfflinePlayer target) {
        if (p == null || page < 1 || items == null) return false;
        Gui gui = new TitleGui(p, page, items, previousGui, target);
        gui.open();
        return true;
    }

    private static boolean show(TitleGui gui) {
        return show(gui.getPlayer(), gui.getPage(), gui.getItems(), gui, gui.getTarget());
    }

    /**
     * @return GuiItem to go to the next page, only used in reviewGui() method.
     */
    private GuiItem getNextPage() {
        int total = GuiUtil.getTotalPages(SLOTS, this.getPage(), items.size());
        if (this.getPage() >= total) return pane2;
        return new GuiItem.Builder().name("&aNext Page").material(Material.MAP)
                .lore("&7Page &e" + this.getPage() + "/" + total).click(() -> TitleGui.show(this)).build();
    }


    private ArrayList<GuiItem> getItems() {
        return items;
    }

    private OfflinePlayer getTarget() {
        return target;
    }

    private GuiItem getPane(int color) {
        return new GuiItem.Builder().material(Material.STAINED_GLASS_PANE).data(color).name(" ").hideFlags().build();
    }

    /**
     * @return GuiItem to go back to the previous page.
     */
    private GuiItem getPreviousPage() {
        int total = GuiUtil.getTotalPages(SLOTS, this.getPage(), items.size());
        if (this.getPage() < 2 || this.getGuiLink() == null) return pane2;
        return new GuiItem.Builder().name("&cPrevious Page").material(Material.MAP)
                .lore("&7Page &e" + this.getPage() + "/" + total).click(() -> this.getGuiLink().open()).build();
    }

    /**
     * @return GuiItem to exit the menu.
     */
    private GuiItem getExit() {
        return new GuiItem.Builder().material(Material.BARRIER).name("&cExit Menu")
                .click(() -> this.getPlayer().closeInventory()).build();
    }

    public static GuiItem getTitleItem(Player p, String titleId, String buildRankId, String staffRankId, FileConfiguration config) {
        final TitleRank titleRank = TitleRank.valueOf(titleId.toUpperCase());
        final String titlePrefix = titleRank.getPrefix();
        final String description = titleRank.getDescription();
        final String rankPrefix;
        final char colourData = titleRank.getColourData();
        int matData = 0;
        Material material = null;

        if (staffRankId.equalsIgnoreCase("none")) {
            rankPrefix = Rank.BuildRank.valueOf(buildRankId.toUpperCase()).getPrefix();
        } else {
            rankPrefix = Rank.StaffRank.valueOf(staffRankId.toUpperCase()).getPrefix();
        }

        if (p.hasPermission("prefix.title." + titleId)) {
            material = Material.CONCRETE;
            switch (colourData) {
                case '0':
                    matData = 15;
                    break;
                case '1':
                    matData = 11;
                    break;
                case '2':
                    matData = 13;
                    break;
                case '3':
                    matData = 9;
                    break;
                case '4':
                    matData = 14;
                    break;
                case '5':
                    matData = 10;
                    break;
                case '6':
                    matData = 1;
                    break;
                case '7':
                    matData = 7;
                    break;
                case '8':
                    matData = 8;
                    break;
                case '9':
                    matData = 11;
                    break;
                case 'a':
                    matData = 5;
                    break;
                case 'b':
                    matData = 3;
                    break;
                case 'c':
                    matData = 14;
                    break;
                case 'd':
                    matData = 6;
                    break;
                case 'e':
                    matData = 4;
                    break;
                case 'f':
                    matData = 0;
                    break;

            }
        }
        else material = Material.STONE;

        return new GuiItem.Builder()
                .name("§7Title " + titlePrefix + " §7Information:")
                .lore(getItemLore(p, p.hasPermission("prefix.title." + titleId), titlePrefix, description, rankPrefix))
                .click(() -> {
                    if (p.hasPermission("prefix.title." + titleId)) {
                        config.getConfigurationSection("users." + p.getName().toLowerCase()).set("current_title", titleId);
                        RankTitleString.getInstance().saveConfig();
                        p.sendMessage("§7[§eTITLE§7] Your active title has been set to " + titlePrefix + "§7.");
                    } else {
                        p.sendMessage("§7[§eTITLE§7] &cYou do not have access to that title.");
                    }
                })
                .material(material).data(matData)
                .build();
    }

    private static String[] getItemLore(Player p, boolean achieved, String titlePrefix, String description, String rankPrefix) {
        String isAchieved = !achieved ? "§cNo" : "§aYes";

        return new String[]{
                " ",
                "§7Unlocked: " + isAchieved,
                "§7" + description,
                " ",
                "§7[" + titlePrefix + " §7- " + rankPrefix + "§7] §f" + p.getDisplayName()
        };
    }
}
