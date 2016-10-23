package me.blok601.sm.Commands;

import me.blok601.sm.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TimebombCommand implements CommandExecutor{
	
	Main plugin;
	
	public TimebombCommand (Main plugin){
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(p.hasPermission("nightshade.host.scenarios")){
				if(!(args.length == 1)){
					p.sendMessage(ChatColor.RED + "Use the command like this! /timebomb <on/off>");
					return false;
				}else{
					if(args[0].equalsIgnoreCase("off")){
						plugin.getConfig().set("timebomb", false);
						plugin.saveConfig();
						p.sendMessage(ChatColor.AQUA + "[UHC] " + ChatColor.GOLD + "Disabled: " + ChatColor.RED + "Timebomb");
					}else if(args[0].equalsIgnoreCase("on")){
						plugin.getConfig().set("timebomb", true);
						plugin.saveConfig();
						p.sendMessage(ChatColor.AQUA + "[UHC] " + ChatColor.GOLD + "Enabled: " + ChatColor.GREEN + "Timebomb");
					}else{
						p.sendMessage(ChatColor.RED + "Use the command like this! /timebomb <on/off>");
						return false;
					}
				}
			}else{
				
			}
		}else{
			sender.sendMessage(ChatColor.RED + "Player only");
			return false;
		}
		return false;
	}
	
	

}
