package com.tomi.sleepnoclearweather;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.TimeSkipEvent;

import net.md_5.bungee.api.ChatColor;

public class SleepListener implements Listener {
	private static Boolean justSlept = false;
	protected static FileConfiguration config;
	
	/**
	 * Sends a message to everyone in the given world
	 * @param world World that you want to send a message to everyone in
	 * @param message What message is being sent
	 */
	public static void sendEveryoneInWorldMessage(World world, String message) {
		// Only if the config option is on
		if (config.getBoolean("broadcastMessages"))
			for (Player player : world.getPlayers()) player.sendMessage(message);
	}
	
	@EventHandler
	public static void onPlayerBedEnter(PlayerBedEnterEvent event) {
		//Broadcasts who went to bed
		if (event.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
			Player sleepPlayer = event.getPlayer();
			String message = ChatColor.GRAY + sleepPlayer.getDisplayName() + " is now sleeping...";
			sendEveryoneInWorldMessage(event.getPlayer().getWorld(), message);
		}
	}
	
	@EventHandler
	public static void onPlayerBedLeave(PlayerBedLeaveEvent event) {
		//Display cancel message only if sleep wasn't succesful (aka didn't "just sleep")
		if (!justSlept) {
			Player player = event.getPlayer();
			String message = ChatColor.GRAY + player.getDisplayName() + " cancelled sleep";
			sendEveryoneInWorldMessage(event.getPlayer().getWorld(), message);
		}
	}
	
	@EventHandler
	public static void onTimeSkipEvent(TimeSkipEvent event) {
		//If the reason for skipping wasn't sleeping, return
		if (event.getSkipReason() != TimeSkipEvent.SkipReason.NIGHT_SKIP) return;
		justSlept = true; // Assuming this skipevent happened because someone slept
		
		String message = ChatColor.GRAY + "The night has been skipped";
		sendEveryoneInWorldMessage(event.getWorld(), message);
	}
	
	@EventHandler
	public static void onWeatherChange(WeatherChangeEvent event) {
		//Rudimentary way of cancelling changing the weather
		if (justSlept) {
			event.setCancelled(true);
			justSlept = false;
		}
	}
	
}
