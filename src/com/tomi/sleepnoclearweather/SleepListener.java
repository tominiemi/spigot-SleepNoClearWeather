package com.tomi.sleepnoclearweather;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.TimeSkipEvent;

import net.md_5.bungee.api.ChatColor;

public class SleepListener implements Listener {
	
	static Boolean justSlept = false;
	
	@EventHandler
	public static void onPlayerBedLeave(PlayerBedLeaveEvent event) {
		Player player = event.getPlayer();
		Bukkit.getServer().broadcastMessage(ChatColor.GRAY + player.getDisplayName() + " just slept");
		justSlept = true;
	}
	
	@EventHandler
	public static void onTimeSkipEvent(TimeSkipEvent event) {
		//If the reason for skipping wasn't sleeping, return
		if (event.getSkipReason() != TimeSkipEvent.SkipReason.NIGHT_SKIP) return;
		Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "Time has been skipped");

	}
	
	@EventHandler
	public static void onWeatherChange(WeatherChangeEvent event) {
		//Rudimentary way of cancelling weatherchange
		if (justSlept) {
			event.setCancelled(true);
			justSlept = false;
		}
	}
	
}
