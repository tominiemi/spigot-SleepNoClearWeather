package com.tomi.sleepnoclearweather;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class SleepListener implements Listener {
	
	@EventHandler
	public static void onPlayerBedEnter(PlayerBedEnterEvent event) {
		//do something
		event.getPlayer().sendMessage("you have entered a bed");
	}
	
	
}
