package com.tomi.sleepnoclearweather;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author tomi
 * Makes it so when you sleep it doesn't clear the weather (rain, snow etc.)
 * Also adds optional sleeping broadcast messages
 */
public class SleepNoClearWeather extends JavaPlugin {
	private FileConfiguration config = getConfig();
	
    //Fired when plugin is first enabled
    @Override
    public void onEnable() { 
    	//Registers an instance of the sleepListener class
    	getServer().getPluginManager().registerEvents(new SleepListener(), this);
    	
    	config.addDefault("broadcastMessages", true);
    	config.options().copyDefaults(true);
    	saveConfig();
    	
    	SleepListener.config = config;
    	SleepCommands.plugin = this;
    	
    	getCommand("reload").setExecutor(new SleepCommands());
    }
    
    //Fired when plugin is disabled
    @Override
    public void onDisable() { }

    public void reload() {
    	reloadConfig();
    	config = getConfig();
    	SleepListener.config = config;
    }
    
}
