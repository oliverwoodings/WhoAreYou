package uk.co.oliwali.WhoAreYou;

import java.util.HashMap;

import org.bukkit.World;
import org.bukkit.util.config.Configuration;

public class Config {
	
	HashMap<String, String> aliases = new HashMap<String, String>();
	WhoAreYou plugin;
	Configuration config;

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
		
		//Load aliasess into hashmap
		String[] worlds = (String[]) config.getKeys("aliases").toArray(new String[0]);
		for (String world : worlds)
			aliases.put(world, config.getString("aliases." + world));
		
		//Attempt save
		if (!config.save())
			Util.log.severe("Error while writing to config.yml");

	}
	
	public String getAliasFromWorld(World world) {
		return aliases.get(world.getName());
	}
	
}