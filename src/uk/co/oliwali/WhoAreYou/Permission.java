package uk.co.oliwali.WhoAreYou;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class Permission {
	
	private WhoAreYou plugin;
	private PermissionPlugin handler = PermissionPlugin.OP;
	private Plugin permissionPlugin;
	
	public Permission(WhoAreYou instance) {
		plugin = instance;
        Plugin permissions = plugin.getServer().getPluginManager().getPlugin("Permissions");
        
        if (permissions != null) {
        	permissionPlugin = permissions;
        	handler = PermissionPlugin.PERMISSIONS;
        	Util.sendMessage("info", "Using Permissions for user permissions");
        }
        else {
        	Util.sendMessage("info", "No permission handler detected, only ops can use commands");
        }
	}
	
	private boolean hasPermission(Player player, String node) {
		switch (handler) {
			case PERMISSIONS:
				return ((Permissions) permissionPlugin).getHandler().has(player, node);
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
		switch (handler) {
			case PERMISSIONS:
				PermissionHandler handler = ((Permissions) permissionPlugin).getHandler();
				String group = handler.getGroup(player.getWorld().getName(), player.getName());
				return handler.getGroupPrefix(player.getWorld().getName(), group);
			case OP:
				if (player.isOp())
					return "&c";
				return "&f";
		}
		return null;
	}
	
	private enum PermissionPlugin {
		PERMISSIONS,
		OP
	}

}
