package uk.co.oliwali.WhoAreYou;

import org.bukkit.entity.Player;


public class WAUPermission {
	
	private WhoAreYou plugin;

	
	public WAUPermission(WhoAreYou instance) {
		plugin = instance;
	}
	
	private boolean hasPermission(Player player, String node) {
		return player.hasPermission(node);
	}
	
	public boolean list(Player player) {
		return hasPermission(player, "whoareyou.list");
	}
	public boolean player(Player player) {
		return hasPermission(player, "whoareyou.player");
	}
	public boolean world(Player player) {
		return hasPermission(player, "whoareyou.world");
	}
	
	public String getPrefix(Player player) {
		String prefix = "&f";
		prefix = plugin.chat.getGroupPrefix(player.getWorld(),getGroup(player));

		if (prefix.length() == 0)
			return "&f";
		return prefix;
	}
	
	public String getSuffix(Player player) {
		String prefix = "";
		prefix = plugin.chat.getGroupSuffix(player.getWorld(), getGroup(player));
		return prefix;
	}

	public String getGroup(Player playerInfo) {
		return plugin.permission.getPrimaryGroup(playerInfo);
	}
	

	


}
