package com.tomi.sleepnoclearweather;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.TimeSkipEvent;

import net.md_5.bungee.api.ChatColor;

public class SleepListener implements Listener {
	
	static Boolean justSlept = false;
	
	@EventHandler
	public static void onPlayerBedEnter(PlayerBedEnterEvent event) {
		//Broadcasts who went to bed
		if (event.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK)
			Bukkit.getServer().broadcastMessage(ChatColor.GRAY + event.getPlayer().getDisplayName() + " is now sleeping...");
	}
	
	@EventHandler
	public static void onPlayerBedLeave(PlayerBedLeaveEvent event) {
		if (!justSlept)
			Bukkit.getServer().broadcastMessage(ChatColor.GRAY + event.getPlayer().getDisplayName() + " cancelled sleep");
	}
	
	@EventHandler
	public static void onTimeSkipEvent(TimeSkipEvent event) {
		//If the reason for skipping wasn't sleeping, return
		if (event.getSkipReason() != TimeSkipEvent.SkipReason.NIGHT_SKIP) return;
		justSlept = true; // Assuming this skipevent happened because someone slept
		Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "The night has been skipped");
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
