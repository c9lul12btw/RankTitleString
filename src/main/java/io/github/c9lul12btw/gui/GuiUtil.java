package io.github.c9lul12btw.gui;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Self-created GuiUtil.
 * Use the Builder subclass in the Gui class to build a Gui.
 * A Gui contains GuiItems, which can be created with builders aswell.
 * > Gui.Builder(Player p)
 * > GuiItem.Builder()
 * @author Robnoo02
 *
 */
public class GuiUtil implements Listener {

	/*
	 * PLEASE NOTE:
	 * This class works fine as it is now,
	 * but should be rewritten to make it easier
	 * to create and modify Gui's.
	 */

    private static final GuiManager INSTANCE = new GuiManager();

    public static class Gui {

        private final int invSize, page; // Inventory size (amount of squares) + page number
        private int totalPages;
        private String title;
        private final Inventory inv;
        private final Player player;
        private final GuiItem[] guiContents;
        private Gui gui;

        public Gui(final Player p, final String title, final int size, final int page) {
            this.invSize = size;
            this.title = toColor(title);
            this.inv = Bukkit.createInventory(null, this.invSize, this.title);
            this.player = p;
            this.guiContents = new GuiItem[size];
            this.page = page;
        }

        public int getPage() {
            return page;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public int getSize() {
            return invSize;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Inventory toInventory() {
            return inv;
        }

        public Player getPlayer() {
            return player;
        }

        private void fillInventory() {
            for (int i = 0; i < invSize; i++)
                if (guiContents[i] != null)
                    inv.setItem(i, guiContents[i].getItem());
        }

        public void open() {
            fillInventory();
            player.openInventory(inv);
            GuiManager.getInstance().addGuiLink(player, this);
        }

        public GuiItem getItem(int slot) {
            return guiContents[slot];
        }

        public Gui getGuiLink() {
            return gui;
        }

        public void setGuiLink(final Gui gui) {
            this.gui = gui;
        }

        public void setItem(int place, GuiItem item) {
            if(!(place >= 0 && place < 54))
                return;
            guiContents[place] = item;
        }

        public void fillSlots(int[] slots, int page, GuiItem... items) {
            if(items == null || slots == null || slots.length < 1)
                return;
            int perPage = slots.length;
            this.totalPages = items.length / perPage;
            if (items.length % perPage > 0)
                totalPages++;
            int count = 0;
            for(int i : slots) {
                if(i < 0 || i > 53)
                    continue;
                if(count >= items.length)
                    break;
                guiContents[i] = items[((page - 1) * perPage) + count++];
            }
        }
    }

    public static final class GuiItem {

        private final ItemStack itemStack;
        private final Material material;
        private final int amount, data;
        private final ArrayList<String> lore;
        private final String name, customSkull;
        private final Runnable leftClick, rightClick, middleClick;
        private final boolean glowing, hideFlags;
        private final Skull skullType;
        protected InventoryClickEvent e;

        private enum Skull {
            NONE, PLAYER, CUSTOM
        };

        public GuiItem(Builder builder) {
            this.itemStack = builder.item;
            this.material = builder.material;
            this.amount = builder.amount;
            this.name = builder.itemName;
            this.lore = builder.lore;
            this.customSkull = builder.customSkull;
            this.leftClick = builder.leftClick;
            this.rightClick = builder.rightClick;
            this.middleClick = builder.middleClick;
            this.glowing = builder.glowing;
            this.hideFlags = builder.hideFlags;
            this.skullType = builder.skullType;
            this.data = builder.data;
        }

        public static class Builder {
            private ItemStack item;
            private Material material = Material.STONE;
            private int amount = 1, data = 0;
            private ArrayList<String> lore;
            private String itemName, customSkull;
            private Runnable leftClick, rightClick, middleClick;
            private boolean glowing = false, hideFlags = false;
            private Skull skullType = Skull.NONE;
            public InventoryClickEvent e;

            public Builder material(Material mat) {
                this.material = mat;
                return this;
            }

            public Builder amount(int amount) {
                this.amount = amount;
                return this;
            }

            public Builder lore(ArrayList<String> lore) {
                this.lore = lore;
                return this;
            }

            public Builder data(int data) {
                this.data = data;
                return this;
            }

            public Builder lore(String... loreItems) {
                ArrayList<String> lore = new ArrayList<>();
                for (int i = 0; i < loreItems.length; i++) {
                    lore.add(toColor(loreItems[i]));
                }
                this.lore = lore;
                return this;
            }

            public Builder name(String name) {
                this.itemName = toColor(name);
                return this;
            }

            public Builder playerSkull(String playerName) {
                this.customSkull = playerName;
                this.skullType = Skull.PLAYER;
                this.material = Material.SKULL_ITEM;
                this.data = 3;
                return this;
            }

            public Builder customSkull(String url) {
                this.customSkull = url;
                this.skullType = Skull.CUSTOM;
                this.material = Material.SKULL_ITEM;
                return this;
            }

            public Builder leftClick(Runnable action) {
                this.leftClick = action;
                return this;
            }

            public Builder rightClick(Runnable action) {
                this.rightClick = action;
                return this;
            }

            public Builder middleClick(Runnable action) {
                this.middleClick = action;
                return this;
            }

            public Builder click(Runnable action) {
                this.leftClick = action;
                this.rightClick = action;
                this.middleClick = action;
                return this;
            }

            public Builder itemStack(ItemStack item) {
                this.item = item;
                return this;
            }

            public Builder glowing() {
                this.glowing = true;
                return this;
            }

            public Builder hideFlags() {
                this.hideFlags = true;
                return this;
            }

            public GuiItem build() {
                return new GuiItem(this);
            }

        }

        protected ItemStack getItem() {
            ItemStack item = new ItemStack(material, amount, (byte) data);
            if (itemStack != null)
                item = itemStack;
            if (skullType.equals(Skull.NONE))
                item.setItemMeta(defaultMeta(item));
            return item;
        }

        private ItemMeta defaultMeta(ItemStack item) {
            ItemMeta meta = item.getItemMeta();
            if (name != null)
                meta.setDisplayName(name);
            if (lore != null)
                meta.setLore(lore);
            if (glowing)
                meta.addEnchant(Enchantment.MENDING, 0, true);
            if (hideFlags)
                meta.addItemFlags(ItemFlag.values());
            meta.addItemFlags(ItemFlag.values());
            return meta;
        }

        public void rightClickAction(InventoryClickEvent e) {
            if (this.rightClick != null)
                this.rightClick.run();
        }

        public void leftClickAction(InventoryClickEvent e) {
            if (this.leftClick != null)
                this.leftClick.run();
        }

        public void scrollClickAction(InventoryClickEvent e) {
            if (this.middleClick != null)
                this.middleClick.run();
        }
    }

    public static class GuiManager {

        private HashMap<String, Gui> currentGuis = new HashMap<>();

        private GuiManager() {
        }

        public static GuiManager getInstance() {
            return INSTANCE;
        }

        public void addGuiLink(Player p, Gui gui) {
            this.currentGuis.put(p.getName(), gui);
        }

        public boolean hasOpenGui(Player p) {
            if (currentGuis.containsKey(p.getName())) {
                if (p.getOpenInventory().getTopInventory().getType().equals(InventoryType.CHEST))
                    return true;
                GuiManager.getInstance().removeGuiLink(p);
                return false;
            } else {
                return false;
            }
        }

        public Gui getGuiFor(Player p) {
            return hasOpenGui(p) ? currentGuis.get(p.getName()) : null;
        }

        public void removeGuiLink(Player p) {
            this.currentGuis.remove(p.getName());
        }

        public void openExistingGui(Player p, Gui gui) {
            removeGuiLink(p);
            gui.open();
        }

    }

    public static int getTotalPages(int[] slots, int page, int size) {
        if (slots == null)
            return 0;
        int totalPages = size / (slots.length);
        if (size % (slots.length) > 0)
            totalPages++;
        return totalPages;
    }

    private static String toColor(String input) {
        String output = input;
        output = (ChatColor.translateAlternateColorCodes('&', output));
        return output;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (GuiManager.getInstance().hasOpenGui(p))
            p.closeInventory();
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onInventoryClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();
        if (GuiManager.getInstance().hasOpenGui(p))
            GuiManager.getInstance().removeGuiLink(p);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player) e.getWhoClicked();
            if (e.getRawSlot() < e.getView().getTopInventory().getSize()) {
                int slot = e.getRawSlot();
                ClickType click = e.getClick();
                if (slot >= 0) {
                    if (GuiManager.getInstance().hasOpenGui(p)) {
                        e.setCancelled(true);
                        ItemStack mat = e.getCurrentItem();
                        if (mat != null && mat.getType() != Material.AIR) {
                            if (e.getInventory().getHolder() == null) {
                                Gui gui = GuiManager.getInstance().getGuiFor(p);
                                GuiItem item = gui.getItem(slot);
                                if (item != null)
                                    if (click.equals(ClickType.LEFT) || click.equals(ClickType.SHIFT_LEFT)
                                            || click.equals(ClickType.SHIFT_LEFT)) {
                                        item.leftClickAction(e);
                                    } else if (click.equals(ClickType.RIGHT) || click.equals(ClickType.SHIFT_RIGHT)
                                            || click.equals(ClickType.SHIFT_RIGHT)) {
                                        item.rightClickAction(e);
                                    } else if (click.equals(ClickType.MIDDLE)) {
                                        item.scrollClickAction(e);
                                    }
                            }
                        }
                    }
                }
            } else if (GuiManager.getInstance().hasOpenGui(p)) {
                e.setCancelled(true);
            }
        }

    }

}