package uk.co.oliwali.WhoAreYou;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Util {
	
	public static final Logger log = Logger.getLogger("Minecraft");
	
	public static void sendMessage(Player player, String msg) {
		msg = replaceColors(msg);
		int i;
		String part;
		String lastColor;
		for (String line : msg.split("`n")) {
			i = 0;
			while (i < line.length()) {
				if (i+93 <= line.length()) {
					part = line.substring(i, i+93);
					if (!line.substring(i+93, 1).equals(" "))
						part = part.substring(0, part.lastIndexOf(" "));
				}
				else
					part = line.substring(i);
				lastColor = getLastColor(part);
				player.sendMessage(lastColor + part);
				i = i + part.length();
			}
		}
	}
	
	public static void sendMessage(String level, String msg) {
		msg = "[" + WhoAreYou.name + "] " + msg;
		if (level == "info")
			log.info(msg);
		else
			log.severe(msg);
	}
	
	private static String getLastColor(String str) {
		int i = 0;
		String lastColor = ChatColor.WHITE.toString();
		while (i < str.length()-3) {
			for (ChatColor color: ChatColor.values()) {
				if (str.substring(i, i+3) == color.toString())
					lastColor = color.toString();
			}
			i = i+3;
		}
		return lastColor;
	}
	
    private static String replaceColors(String str) {
        str = str.replace("&c", ChatColor.RED.toString());
        str = str.replace("&4", ChatColor.DARK_RED.toString());
        str = str.replace("&e", ChatColor.YELLOW.toString());
        str = str.replace("&6", ChatColor.GOLD.toString());
        str = str.replace("&a", ChatColor.GREEN.toString());
        str = str.replace("&2", ChatColor.DARK_GREEN.toString());
        str = str.replace("&b", ChatColor.AQUA.toString());
        str = str.replace("&8", ChatColor.DARK_AQUA.toString());
        str = str.replace("&9", ChatColor.BLUE.toString());
        str = str.replace("&1", ChatColor.DARK_BLUE.toString());
        str = str.replace("&d", ChatColor.LIGHT_PURPLE.toString());
        str = str.replace("&5", ChatColor.DARK_PURPLE.toString());
        str = str.replace("&0", ChatColor.BLACK.toString());
        str = str.replace("&8", ChatColor.DARK_GRAY.toString());
        str = str.replace("&7", ChatColor.GRAY.toString());
        str = str.replace("&f", ChatColor.WHITE.toString());
        return str;
    }

}
