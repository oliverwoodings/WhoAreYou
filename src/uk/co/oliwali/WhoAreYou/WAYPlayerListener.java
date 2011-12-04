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
			String prefix = plugin.permissions.getPrefix(player);
			int lastcolor = prefix.lastIndexOf("&");

			String coloredname = "&" + prefix.charAt(lastcolor + 1) + player.getName();
					player.setPlayerListName(coloredname.replaceAll("(&([a-f0-9]))", "\u00A7$2"));
		}
	}
	
	
}