package com.scswc.shadrxninga.iop;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;




import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;


public class iOP extends JavaPlugin{
	
	private final iOPPlayerListener playerListener = new iOPPlayerListener(this);
	//Logging!!
	public static final Logger log = Logger.getLogger("Minecraft");
	
	Configuration config;
	
	
	// Config variables

	//OP Variables
	static String opNameColor;
	static String opMsgColor;
	static String opPrefix;
	static String opSuffix;
	//Player variables
	static String playerNameColor;
	static String playerMsgColor;
	static String playerPrefix;
	static String playerSuffix;
	//Others
	public static boolean Default;
	public static boolean coloredList;
	//Online List
	static String prefix;
	static String nameColor;
	static String suffix;
	
	
	
	
	@Override
	public void onDisable() {
		
		log.info("[iOP] v0.6 Stopping");
		
		
	}

	@Override
	public void onEnable() {
		reloadConfig();
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Event.Priority.Highest, this);
		log.info("[iOP] v0.6 Enabled");
		
		
		
	}
	
	 public void reloadConfig(){
		 load().load();	
		 //Load the OPs Name Colors and Prefixes
		 opNameColor = load().getString("OP-NameColor", "1");
		 opNameColor= opNameColor.replaceAll("&([0-9a-fA-F])","\u00A7$1");
		 opMsgColor = load().getString("OP-MsgColor", "1");
		 opMsgColor = opMsgColor.replaceAll("&([0-9a-fA-F])","\u00A7$1");
		 opPrefix = load().getString("OP-Prefix", "");
		 opPrefix = opPrefix.replaceAll("&([0-9a-fA-F])", "\u00A7$1");
		 opSuffix = load().getString("OP-Suffix", "");
		 opSuffix = opSuffix.replaceAll("&([0-9a-fA-F])", "\u00A7$1");

		 //Load the OPs Name Colors and Prefixes
		 playerNameColor = load().getString("Player-NameColor", "&f");
		 playerNameColor = playerNameColor.replaceAll("&([0-9a-fA-F])","\u00A7$1");
		 playerMsgColor = load().getString("Player-MsgColor", "&f");
		 playerMsgColor = playerMsgColor.replaceAll("&([0-9a-fA-F])","\u00A7$1");
		 playerPrefix = load().getString("Player-Prefix", "&f");
		 playerPrefix = playerPrefix.replaceAll("&([0-9a-fA-F])","\u00A7$1");
		 playerSuffix = load().getString("Player-Suffix", "");
		 playerSuffix = playerSuffix.replaceAll("&([0-9a-fA-F])", "\u00A7$1");
		 
		 Default = load().getBoolean("Normal Chat", true);
		 coloredList = load().getBoolean("Colored Online List", true);
		 //save
		 load().save();
	 }
		

	 private static final File myfile= new File("plugins/iOP/config.yml");
	 public static Configuration load() {
	         try {
	             Configuration PluginPropConfig = new Configuration(myfile);
	             PluginPropConfig.load();
	             return PluginPropConfig;

	         } catch (Exception e) {
	         }
	         return null;
	     }
	 public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(cmd.getName().equalsIgnoreCase("iop")){
			Player player = (Player) sender;
			if(player.isOp()){
		    reloadConfig();
		    player.sendMessage(ChatColor.AQUA + "iOP Config Reloaded");
			}else{
				player.sendMessage(ChatColor.RED + "You don't have permission");
			}
			
		}
	    	
	     if(coloredList == true){
	    		    	 
	    	 
	    	 
	    	 
	    	 
	    	if(cmd.getName().equalsIgnoreCase("list")){
	    		
	    		
	    		
	    		Player[] onlinePlayers = getServer().getOnlinePlayers();
	    		List<String> players = new ArrayList<String>();
	    		for(Player who : onlinePlayers) {
	    			String who2 = who.getName();
	    			if(who.isOp()){
						prefix = iOP.load().getString(who2 + ".prefix", iOP.opPrefix);
						prefix = prefix.replaceAll("&([0-9a-fA-F])","\u00A7$1");
						nameColor = iOP.load().getString(who2 + ".name", iOP.opNameColor);
						nameColor = nameColor.replaceAll("&([0-9a-fA-F])","\u00A7$1");
						suffix = iOP.load().getString(who2 + ".suffix", iOP.opSuffix);
						suffix = suffix.replaceAll("&([0-9a-fA-F])","\u00A7$1");

						}else{
						prefix = iOP.load().getString(who2 + ".prefix", iOP.playerPrefix);
						prefix = prefix.replaceAll("&([0-9a-fA-F])","\u00A7$1");
						nameColor = iOP.load().getString(who2 + ".name", iOP.playerMsgColor);
						nameColor = nameColor.replaceAll("&([0-9a-fA-F])","\u00A7$1");
						suffix = iOP.load().getString(who2 + ".suffix", iOP.playerSuffix);
						suffix = suffix.replaceAll("&([0-9a-fA-F])","\u00A7$1");

					}
	    			players.add(prefix + nameColor + who.getDisplayName()  + suffix + ChatColor.WHITE);
	    		}
	    		sender.sendMessage(players.toString());
	    	}
	     }
		return false;
	 }
}
	    	
	
