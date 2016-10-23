package me.blok601.sm.ScenarioEvents;

import me.blok601.sm.Main;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;


public class CutClean implements Listener{
	
	Main plugin;
	
	public CutClean (Main plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e){
		if(plugin.getConfig().getBoolean("cutclean")){
			Player p = e.getPlayer();
			Block b = e.getBlock();
		    Location clone = new Location(b.getWorld(),  b.getLocation().getBlockX() + 0.5D, b.getLocation().getBlockY(),  b.getLocation().getBlockZ() + 0.5D);
			if(e.getBlock().getType() == Material.IRON_ORE){
				e.setCancelled(true);
				e.getBlock().setType(Material.AIR);
				p.getInventory().addItem(new ItemStack(Material.IRON_INGOT, 1));
				((ExperienceOrb)b.getWorld().spawn(clone, ExperienceOrb.class)).setExperience(3);
				
			}else if(e.getBlock().getType() == Material.GOLD_ORE){
				e.setCancelled(true);
				e.getBlock().setType(Material.AIR);
				p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 1));
				((ExperienceOrb)b.getWorld().spawn(clone, ExperienceOrb.class)).setExperience(3);
			}else{
				return;
			}
		}
	}
	
	@EventHandler
	public void onDeath(EntityDeathEvent e){
		Entity en = e.getEntity();
		if(en.getType() == EntityType.PIG){
			e.getDrops().clear();
			en.getLocation().getWorld().dropItemNaturally(en.getLocation().add(1, 0, 0), new ItemStack(Material.GRILLED_PORK, 3));
		}else if(en.getType() == EntityType.COW){
			e.getDrops().clear();
			en.getLocation().getWorld().dropItemNaturally(en.getLocation().add(1, 0, 0), new ItemStack(Material.COOKED_BEEF, 3));
			en.getLocation().getWorld().dropItemNaturally(en.getLocation().add(1, 0, 0), new ItemStack(Material.LEATHER, 1));
		}else if(en.getType() == EntityType.SHEEP){
			e.getDrops().clear();
			en.getLocation().getWorld().dropItemNaturally(en.getLocation().add(1, 0, 0), new ItemStack(Material.COOKED_BEEF, 3));
		}else if(en.getType() == EntityType.CHICKEN){
			e.getDrops().clear();
			en.getLocation().getWorld().dropItemNaturally(en.getLocation().add(1, 0, 0), new ItemStack(Material.COOKED_CHICKEN, 3));
			en.getLocation().getWorld().dropItemNaturally(en.getLocation().add(1, 0, 0), new ItemStack(Material.FEATHER, 2));
		}
	}

}
