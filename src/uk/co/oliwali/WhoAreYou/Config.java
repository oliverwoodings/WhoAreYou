package uk.co.oliwali.WhoAreYou;

import java.util.HashMap;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;


public class Config {
	
	private HashMap<String, String> aliases = new HashMap<String, String>();
	public WhoAreYou plugin;
	private FileConfiguration config;
	public boolean onlineMsg;
	public boolean tablist;

	public Config (WhoAreYou instance) {
		
		this.plugin = instance;
		this.config = plugin.getConfig();
		plugin.reloadConfig();
		
		//If there are no aliases yet
		for(World world : plugin.getServer().getWorlds()){
			config.addDefault("aliases."+ world.getName(), world.getName());
		}
		
		//Message players on join
		config.addDefault("msg-online-on-join", true);
		
		config.options().copyDefaults(true);
		
		//Load aliasess into hashmap
		for(World world : plugin.getServer().getWorlds()){
			aliases.put(world.getName(), config.getString("aliases."+world.getName()));
		}
		
		onlineMsg = config.getBoolean("msg-online-on-join", true);
		tablist = config.getBoolean("colour-tab-menu", true);
		
		//Attempt save
		plugin.saveConfig();

	}
	
	public String getAliasFromWorld(World world) {
		return aliases.get(world.getName());
	}
	
}