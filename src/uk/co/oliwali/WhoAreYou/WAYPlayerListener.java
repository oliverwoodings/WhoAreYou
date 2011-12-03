package uk.co.oliwali.WhoAreYou;

import org.bukkit.entity.Player;
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
		if (plugin.config.tablist) {
			Player player = event.getPlayer();
					player.setPlayerListName(plugin.permissions.getPrefix(player) + player.getName() + plugin.permissions.getSuffix(player));
		}
	}
	
	
}