package com.tomi.sleepnoclearweather;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class SleepListener implements Listener {
	
	Player player;
	World world;
	
	int stormDuration = 0;
	
	@EventHandler
	public void onPlayerBedEnter(PlayerBedEnterEvent event) {
		//if sleep is not ok then return
		if (event.getBedEnterResult() != PlayerBedEnterEvent.BedEnterResult.OK) return;
		
		player = event.getPlayer();
		player.sendMessage("you have entered a bed"); //debug message
		world = player.getWorld();
		
		//if raining or snowing, get how long it's going to rain or snow
		if (world.hasStorm()) stormDuration = world.getWeatherDuration();
		player.sendMessage("amount of weathertime remaining: " + stormDuration); //debug message
		
	}
	
	@EventHandler
	public void onPlayerBedLeave(PlayerBedLeaveEvent event) {
		player = event.getPlayer();
		world = player.getWorld();
		
		if (event.isCancelled()) {
			player.sendMessage("you left bed"); //debug message
			return;
		}
			
		player.sendMessage("you had nice nap");//debug message
		if (stormDuration > 0) {
			world.setStorm(true);
			world.setWeatherDuration(stormDuration);
		}
		
	}
	
}
