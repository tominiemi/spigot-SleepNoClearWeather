package com.tomi.sleepnoclearweather;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SleepCommands implements CommandExecutor {
	protected static SleepNoClearWeather plugin;
	
    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // reload config
    	if (command.getName().equalsIgnoreCase("reload"))
    		plugin.reloadConfig();
    	
    	return true;
    }

}
