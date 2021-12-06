package com.tomi.sleepnoclearweather;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class SleepCommands implements CommandExecutor {
	protected static SleepNoClearWeather plugin;
	
    //This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // reload config
    	if (command.getName().equalsIgnoreCase("reload")) {
    		plugin.reload();
    		if (sender instanceof Player) sender.sendMessage(ChatColor.GREEN + "SleepNoClearWeather config reloaded.");
    		plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "SleepNoClearWeather config reloaded.");
    	}
    	return true;
    }

}
