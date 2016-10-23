package me.blok601.sm.ScenarioEvents;

import me.blok601.sm.Main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener{
	
	Main plugin;
	
	public InventoryClick (Main plugin){
		this.plugin = plugin;
	}
	
	
	@EventHandler
	public void onClick(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		ItemStack is = e.getCurrentItem();
		
		if(!ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase("Scenario Manager")){
			return;
		}
			e.setCancelled(true);
			if(is !=null && is.hasItemMeta() && is.getItemMeta().hasDisplayName()){
				if(is.getItemMeta().getDisplayName().contains(ChatColor.stripColor("Cutclean"))){
					if(plugin.getConfig().getBoolean("cutclean")){
						//Set to false
						plugin.getConfig().set("cutclean", false);
						plugin.saveConfig();
						p.closeInventory();
						p.sendMessage(ChatColor.AQUA + "[UHC] " + ChatColor.GOLD + "Disabled: " + ChatColor.RED + "CutClean");
					}else{
						//Set to true
						plugin.getConfig().set("cutclean", true);
						plugin.saveConfig();
						p.closeInventory();
						p.sendMessage(ChatColor.AQUA + "[UHC] " + ChatColor.GOLD + "Enabled: " + ChatColor.GREEN + "CutClean");
					}
			}else if(is.getItemMeta().getDisplayName().contains(ChatColor.stripColor("Timebomb"))){
				if(plugin.getConfig().getBoolean("timebomb")){
					//Set to false
					plugin.getConfig().set("timebomb", false);
					plugin.saveConfig();
					p.closeInventory();
					p.sendMessage(ChatColor.AQUA + "[UHC] " + ChatColor.GOLD + "Disabled: " + ChatColor.RED + "Timebomb");
				}else{
					plugin.getConfig().set("timebomb", true);
					plugin.saveConfig();
					p.closeInventory();
					p.sendMessage(ChatColor.AQUA + "[UHC] " + ChatColor.GOLD + "Enabled: " + ChatColor.GREEN + "Timebomb");
				}
			}else if(is.getItemMeta().getDisplayName().contains(ChatColor.stripColor("Turn"))){
				for (String key : plugin.getConfig().getKeys(false)){
					plugin.getConfig().set(key, false);
					plugin.saveConfig();
				}
				
				p.sendMessage(ChatColor.AQUA + "[UHC] " + ChatColor.GOLD + "Disabled all scenarios!");
				p.closeInventory();
			}
		}
	}

}
