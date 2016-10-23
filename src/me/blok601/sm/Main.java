package me.blok601.sm;

import me.blok601.sm.Commands.CutCleanCommand;
import me.blok601.sm.Commands.ScenarioGUICommand;
import me.blok601.sm.Commands.TimebombCommand;
import me.blok601.sm.ScenarioEvents.CutClean;
import me.blok601.sm.ScenarioEvents.InventoryClick;
import me.blok601.sm.ScenarioEvents.Timebomb;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	private static Plugin plugin;
	
	FileConfiguration config = getConfig();
	
	@Override
	public void onEnable(){
		getLogger().info("ScenarioManager Enabled!");
		config.options().copyDefaults(true);
		saveConfig();
		
		getCommand("cutclean").setExecutor(new CutCleanCommand(this));
		getServer().getPluginManager().registerEvents(new CutClean(this), this);
		
		getCommand("timebomb").setExecutor(new TimebombCommand(this));
		getServer().getPluginManager().registerEvents(new Timebomb(this), this);
		
		getCommand("scen").setExecutor(new ScenarioGUICommand(this));
		getServer().getPluginManager().registerEvents(new InventoryClick(this), this);
	}
	
	@Override
	public void onDisable(){
		getLogger().info("ScenarioManager Disabled!");
	}
	
	public static Plugin getPlugin(){
		return plugin;
	}

}
