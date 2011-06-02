package com.scswc.shadrxninga.iop;


import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.ChatColor;
import org.bukkit.World;

public class iOPPlayerListener extends PlayerListener{
	
	
	
	public static String prefix;
	public static String nameColor;
	public static String msgColor;
	public static String suffix;
	public static String group;
	
private iOP plugin;
	
	public iOPPlayerListener(iOP plugin) {
        this.plugin = plugin;
    }
	
	public void onPlayerChat(PlayerChatEvent event) {
		Player ply = event.getPlayer();
		String plys = ply.getName();
		String dname = ply.getDisplayName();
		String msg = event.getMessage();
		World world = ply.getWorld();
		String world2 = world.toString();
if(ply.isOp()){
			prefix = iOP.load().getString(plys + ".prefix", iOP.opPrefix);
			prefix = prefix.replaceAll("&([0-9a-fA-F])","\u00A7$1");
			suffix = iOP.load().getString(plys + ".suffix", iOP.opSuffix);
			suffix = suffix.replaceAll("&([0-9a-fA-F])", "\u00A7$1");
			nameColor = iOP.load().getString(plys + ".name", iOP.opNameColor);
			nameColor = nameColor.replaceAll("&([0-9a-fA-F])","\u00A7$1");
			msgColor = iOP.load().getString(plys + ".msg", iOP.opMsgColor);
			msgColor = msgColor.replaceAll("&([0-9a-fA-F])","\u00A7$1");
		}else{
			prefix = iOP.load().getString(plys + ".prefix", iOP.playerPrefix);
			prefix = prefix.replaceAll("&([0-9a-fA-F])","\u00A7$1");
			suffix = iOP.load().getString(plys + ".suffix", iOP.playerSuffix);
			suffix = suffix.replaceAll("&([0-9a-fA-F])", "\u00A7$1");
			nameColor = iOP.load().getString(plys + ".name", iOP.playerMsgColor);
			nameColor = nameColor.replaceAll("&([0-9a-fA-F])","\u00A7$1");
			msgColor = iOP.load().getString(plys + ".msg", iOP.playerMsgColor);
			msgColor = msgColor.replaceAll("&([0-9a-fA-F])","\u00A7$1");
		}
		if(iOP.Default == false){
			event.setFormat(prefix + ChatColor.WHITE + nameColor + dname + ChatColor.WHITE + suffix +  ": " + msgColor + msg);
		}else{
			event.setFormat(prefix + ChatColor.WHITE + "<" + nameColor + dname + ChatColor.WHITE + "> " + suffix + msgColor +  msg);
	}


}
}


