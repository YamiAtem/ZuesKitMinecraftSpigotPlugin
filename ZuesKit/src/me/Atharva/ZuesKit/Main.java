package me.Atharva.ZuesKit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.addRecipe(ZuesKitAct());
	}

	@Override
	public void onDisable() {

	}
	
	public ShapedRecipe ZuesKitAct() {

		ItemStack item = new ItemStack(Material.END_CRYSTAL);
		ItemMeta meta = item.getItemMeta();

		meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "ZUES KIT");
		meta.addEnchant(Enchantment.DURABILITY, 1, true);

		item.setItemMeta(meta);

		NamespacedKey key = new NamespacedKey(this, "zues_kit");

		ShapedRecipe recipe = new ShapedRecipe(key, item);

		recipe.shape("LIA", "IBI", "GI ");

		recipe.setIngredient('B', Material.NETHERITE_BLOCK);
		recipe.setIngredient('I', Material.NETHERITE_INGOT);
		recipe.setIngredient('L', Material.LAVA_BUCKET);
		recipe.setIngredient('A', Material.GOLDEN_APPLE);
		recipe.setIngredient('G', Material.ENCHANTED_GOLDEN_APPLE);
		
		return recipe;
	}
	
	private ItemStack[] ZuesKit() {
		ItemStack item = new ItemStack(Material.NETHERITE_HELMET);
		ItemMeta meta = item.getItemMeta();

		meta.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "ZUES HELMET");
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 20, true);
		meta.addEnchant(Enchantment.PROTECTION_FIRE, 20, true);
		meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 20, true);
		meta.addEnchant(Enchantment.PROTECTION_PROJECTILE, 20, true);
		meta.addEnchant(Enchantment.DURABILITY, 20, true);
		meta.addEnchant(Enchantment.WATER_WORKER, 20, true);
		meta.addEnchant(Enchantment.OXYGEN, 20, true);

		item.setItemMeta(meta);

		ItemStack item2 = new ItemStack(Material.NETHERITE_CHESTPLATE);
		ItemMeta meta2 = item.getItemMeta();

		meta2.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "ZUES CHESTPLATE");
		meta2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 20, true);
		meta2.addEnchant(Enchantment.PROTECTION_FIRE, 20, true);
		meta2.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 20, true);
		meta2.addEnchant(Enchantment.PROTECTION_PROJECTILE, 20, true);
		meta2.addEnchant(Enchantment.THORNS, 20, true);
		meta2.addEnchant(Enchantment.DURABILITY, 20, true);

		item2.setItemMeta(meta2);

		ItemStack item3 = new ItemStack(Material.NETHERITE_LEGGINGS);
		ItemMeta meta3 = item.getItemMeta();

		meta3.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "ZUES LEGGINGS");
		meta3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 20, true);
		meta3.addEnchant(Enchantment.PROTECTION_FIRE, 20, true);
		meta3.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 20, true);
		meta3.addEnchant(Enchantment.PROTECTION_PROJECTILE, 20, true);
		meta3.addEnchant(Enchantment.THORNS, 20, true);
		meta3.addEnchant(Enchantment.DURABILITY, 20, true);

		item3.setItemMeta(meta3);

		ItemStack item4 = new ItemStack(Material.NETHERITE_BOOTS);
		ItemMeta meta4 = item.getItemMeta();

		meta4.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "ZUES BOOTS");
		meta4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 20, true);
		meta4.addEnchant(Enchantment.PROTECTION_FIRE, 20, true);
		meta4.addEnchant(Enchantment.PROTECTION_EXPLOSIONS, 20, true);
		meta4.addEnchant(Enchantment.PROTECTION_PROJECTILE, 20, true);
		meta4.addEnchant(Enchantment.DURABILITY, 20, true);
		meta4.addEnchant(Enchantment.DEPTH_STRIDER, 20, true);
		meta4.addEnchant(Enchantment.PROTECTION_FALL, 20, true);

		item4.setItemMeta(meta4);

		ItemStack[] items = { item, item2, item3, item4 };
		return items;
	}
	
	private void dropChest(Player player, ItemStack[] items) {
		Location chest = null;
		if (player.getFacing() == BlockFace.NORTH)
			chest = player.getLocation().add(0, 0, -1);

		if (player.getFacing() == BlockFace.SOUTH)
			chest = player.getLocation().add(0, 0, 1);

		if (player.getFacing() == BlockFace.EAST)
			chest = player.getLocation().add(1, 0, 0);

		if (player.getFacing() == BlockFace.WEST)
			chest = player.getLocation().add(-1, 0, 0);

		if (chest.getBlock().getType() != Material.AIR) {
			player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "CANNOT PLACE CHEST HERE");
			return;
		}

		Block block = chest.getBlock();
		block.setType(Material.CHEST);

		Chest c = (Chest) block.getState();
		c.getInventory().addItem(items);
	}
	
	@EventHandler
	public void onClicked(PlayerInteractEvent event) {
		if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.END_CRYSTAL)) {
			if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
				Player player = (Player) event.getPlayer();

				if (event.getAction() == Action.RIGHT_CLICK_AIR) {
					this.dropChest(player, ZuesKit());
					player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
				}
			}
		}
	}
}
