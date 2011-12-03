package uk.co.oliwali.WhoAreYou;

import java.util.HashMap;

import org.bukkit.World;
import org.bukkit.util.config.Configuration;

public class Config {
	
	private HashMap<String, String> aliases = new HashMap<String, String>();
	public WhoAreYou plugin;
	private Configuration config;
	public boolean onlineMsg;
	public boolean tablist;

	public Config (WhoAreYou instance) {
		
		this.plugin = instance;
		this.config = plugin.getConfiguration();
		config.load();
		
		//If there are no aliases yet
		if (config.getKeys("aliases") == null) {
			World[] worlds = (World[]) plugin.getServer().getWorlds().toArray(new World[0]);
			for (World world : worlds)
				config.setProperty("aliases." + world.getName(), world.getName());
		}
		
		//Message players on join
		if (config.getProperty("msg-online-on-join") == null)
			config.setProperty("msg-online-on-join", true);
		
		//Load aliasess into hashmap
		String[] worlds = (String[]) config.getKeys("aliases").toArray(new String[0]);
		for (String world : worlds)
			aliases.put(world, config.getString("aliases." + world));
		
		onlineMsg = config.getBoolean("msg-online-on-join", true);
		tablist = config.getBoolean("colour-tab-menu", true);
		
		//Attempt save
		if (!config.save())
			Util.severe("Error while writing to config.yml");

	}
	
	public String getAliasFromWorld(World world) {
		return aliases.get(world.getName());
	}
	
}