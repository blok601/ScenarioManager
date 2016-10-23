package me.blok601.sm.Commands;

import java.util.ArrayList;
import java.util.List;

import me.blok601.sm.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ScenarioGUICommand implements CommandExecutor{
	
	Main plugin;
	
	public ScenarioGUICommand (Main plugin){
		this.plugin = plugin;
	}

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(p.hasPermission("nightshade.uhc.scenarios")){
				p.sendMessage(ChatColor.AQUA + "[UHC] " + ChatColor.GOLD + "Opening the ScenarioManager...");
				openGui(p);
			}else{
				return false;
			}
		}else{
			sender.sendMessage(ChatColor.RED + "Player only");
			return false;
		}
		return false;
	}
	
	private void openGui(Player p){
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GOLD + "Scenario Manager");
		
		if(plugin.getConfig().getBoolean("cutclean")){
			ItemStack green = new ItemStack(Material.WOOL, 1, (byte) 5);
			ItemMeta gm = green.getItemMeta();
			gm.setDisplayName(ChatColor.GREEN + "Cutclean: " + ChatColor.GOLD + "On");
			green.setItemMeta(gm);
			inv.addItem(green);
		}else{
			ItemStack red = new ItemStack(Material.WOOL, 1, (byte) 14);
			ItemMeta rm = red.getItemMeta();
			rm.setDisplayName(ChatColor.RED + "Cutclean: " + ChatColor.AQUA + "Off");
			red.setItemMeta(rm);
			inv.addItem(red);
		}
		
		if(plugin.getConfig().getBoolean("timebomb")){
			ItemStack green = new ItemStack(Material.WOOL, 1, (byte) 5);
			ItemMeta gm = green.getItemMeta();
			gm.setDisplayName(ChatColor.GREEN + "Timebomb: " + ChatColor.GOLD + "On");
			green.setItemMeta(gm);
			inv.addItem(green);
		}else{
			ItemStack red = new ItemStack(Material.WOOL, 1, (byte) 14);
			ItemMeta rm = red.getItemMeta();
			rm.setDisplayName(ChatColor.RED + "Timebomb: " + ChatColor.AQUA + "Off");
			red.setItemMeta(rm);
			inv.addItem(red);
		}
		
		ItemStack bucket = new ItemStack(Material.LAVA_BUCKET);
		ItemMeta bm = bucket.getItemMeta();
		List<String> lore = new ArrayList<String>();
		bm.setDisplayName(ChatColor.RED + "Turn off all scenarios!");
		lore.add(ChatColor.DARK_AQUA + "Click then when you are done hosting!");
		bm.setLore(lore);
		bucket.setItemMeta(bm);
		
		inv.setItem(53, bucket);
		
		p.openInventory(inv);
		
	}
	
	

}
