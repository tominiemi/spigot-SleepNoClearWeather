package com.tomi.sleepnoclearweather;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author tomi
 * Makes it so when you sleep it doesn't clear the weather (rain, snow etc.)
 */
public class SleepNoClearWeather extends JavaPlugin {
	
    // Fired when plugin is first enabled
    @Override
    public void onEnable() { 
    	//Registers an instance of the sleepListener class
    	getServer().getPluginManager().registerEvents(new SleepListener(), this);
    }
    
    // Fired when plugin is disabled
    @Override
    public void onDisable() { 
    	//no
    }
	
	
}
