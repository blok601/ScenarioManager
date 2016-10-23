package me.blok601.sm.ScenarioEvents;

import me.blok601.sm.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class Timebomb implements Listener {

	Main plugin;

	public Timebomb(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if (plugin.getConfig().getBoolean("timebomb")) {
			final Player p = e.getEntity();
			Block b = p.getLocation().getBlock().getRelative(BlockFace.DOWN);
			final Location l = p.getLocation().clone();
			b.setType(Material.CHEST);

			Chest c = (Chest) b.getState();

			b = b.getRelative(BlockFace.NORTH);
			b.setType(Material.CHEST);

			for (ItemStack is : e.getDrops()) {
				if (is == null || is.getType() == Material.AIR) {
					continue;
				}

				c.getInventory().addItem(is);
			}
			ItemStack skull = new ItemStack(Material.SKULL_ITEM);
			skull.setDurability((short) 3);
			SkullMeta sm = (SkullMeta) skull.getItemMeta();
			sm.setOwner(p.getName());
			sm.setDisplayName(ChatColor.AQUA + p.getName());
			skull.setItemMeta(sm);

			c.getInventory().addItem(skull);

			e.getDrops().clear();

			new BukkitRunnable() {
				private int time = 31;

				@Override
				public void run() {
					time--;
					if (time == 0) {
						l.getWorld().createExplosion(l.add(0.5, 0.5, 0.5), 25, false);
						
						Bukkit.broadcastMessage(ChatColor.AQUA + "[Timebomb] " + ChatColor.GOLD + p.getName() + "'s corpse has exploded!");
						
						cancel();
						return;
					}
				}
			}.runTaskTimer(plugin, 0, 20);
		}
	}

}
