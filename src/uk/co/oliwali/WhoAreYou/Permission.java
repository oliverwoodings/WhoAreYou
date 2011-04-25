package uk.co.oliwali.WhoAreYou;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class Permission {
	
	private WhoAreYou plugin;
	private PermissionPlugin handler = PermissionPlugin.OP;
	private PermissionHandler permissionPlugin;
	
	public Permission(WhoAreYou instance) {
		plugin = instance;
        Plugin permissions = plugin.getServer().getPluginManager().getPlugin("Permissions");
        
        if (permissions != null) {
        	permissionPlugin = ((Permissions)permissions).getHandler();
        	handler = PermissionPlugin.PERMISSIONS;
        	Util.info("Using Permissions for user permissions");
        }
        else {
        	Util.info("No permission handler detected, only ops can use commands");
        }
	}
	
	private boolean hasPermission(Player player, String node) {
		switch (handler) {
			case PERMISSIONS:
				return permissionPlugin.has(player, node);
			case OP:
				return player.isOp();
		}
		return false;
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
		switch (handler) {
			case PERMISSIONS:
				prefix = permissionPlugin.getGroupPrefix(player.getWorld().getName(), getGroup(player));
				break;
			case OP:
				if (player.isOp())
					prefix = "&c";
				break;
		}
		if (prefix.length() == 0)
			return "&f";
		return prefix;
	}
	
	public String getGroup(Player player) {
		switch (handler) {
			case PERMISSIONS:
				return permissionPlugin.getGroup(player.getWorld().getName(), player.getName());
			case OP:
				if (player.isOp())
					return "op";
		}
		return "regular";
	}
	
	private enum PermissionPlugin {
		PERMISSIONS,
		OP
	}

}
