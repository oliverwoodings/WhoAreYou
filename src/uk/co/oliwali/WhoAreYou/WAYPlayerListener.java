package uk.co.oliwali.WhoAreYou;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;

public class WAYPlayerListener extends PlayerListener {
	
	public WhoAreYou plugin;

	public WAYPlayerListener(WhoAreYou instance) {
		plugin = instance;
	}
	
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (plugin.config.onlineMsg)
			plugin.who(event.getPlayer());
	}
	
}