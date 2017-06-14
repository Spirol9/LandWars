package org.Spirol9.LW.Msgs;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

public class SetupMsgs {

	private static Plugin plugin;

	public SetupMsgs(Plugin plugin) {
		SetupMsgs.plugin = plugin;
	}
	
	private static ChatColor italic = ChatColor.ITALIC;
	private static ChatColor bold = ChatColor.BOLD;
	private static ChatColor reset = ChatColor.RESET;
	private static ChatColor orange = ChatColor.GOLD;
	private static ChatColor grey  = ChatColor.GRAY;
	private static ChatColor white = ChatColor.GRAY;
	
	public static void setupHelpCmds(UUID u){
		OfflinePlayer p = Bukkit.getPlayer(u);
		
		p.getPlayer().sendMessage(orange+""+bold+"-------|Land Wars|-------");
		p.getPlayer().sendMessage(orange+""+italic+"/LWS Create (Arena Name)"+reset+white+" - "+grey+"Use command to create arena.");
		p.getPlayer().sendMessage(orange+""+italic+"/LWS SetOrient (Arena Name)"+reset+white+" - "+grey+"Use command to set the orient of the arena.");
		p.getPlayer().sendMessage(orange+""+italic+"/LWS SetBounds (Bound Type) (Arena Name)"+reset+white+" - "+grey+"Use command to set bounds of the arena.");
		p.getPlayer().sendMessage(orange+""+italic+"/LWS AddSpawn (Team Name) (#) (Arena Name)"+reset+white+" - "+grey+"Use command to add team spawn point (max of 3 min of 1).");
		p.getPlayer().sendMessage(orange+""+italic+"/LWS DelSpawn (Team Name) (#) (Arena Name)"+reset+white+" - "+grey+"Use command to remove team spawn point (max of 3).");
		p.getPlayer().sendMessage(orange+""+italic+"/LWS TeamCount (#) (Arena Name)"+reset+white+" - "+grey+"Use command to set how many players per-team.");
		p.getPlayer().sendMessage(orange+""+italic+"/LWS MinP (#) (Arena Name)"+reset+white+" - "+grey+"Use command to set how many players (Min Needed) in order to start Game.");
		p.getPlayer().sendMessage(orange+""+italic+"/LWS MaxP (#) (Arena Name)"+reset+white+" - "+grey+"Use command to set how many players (Max Needed) allowed in Game.");
		p.getPlayer().sendMessage(orange+""+italic+"/LWS LobbyTime (#Sec.) (Arena Name)"+reset+white+" - "+grey+"Use command to set lobby time. (In Sec.)");
		p.getPlayer().sendMessage(orange+""+italic+"/LWS LobyTimeMaxP (#Sec.) (Arena Name)"+reset+white+" - "+grey+"Use command to set lobby time if max players in lobby are reached.");
		p.getPlayer().sendMessage(orange+""+italic+"/LWS SetLobby (Arena Name)"+reset+white+" - "+grey+"Use command to set lobby location for arena.");
		p.getPlayer().sendMessage(orange+""+italic+"/LWS SetGameTime (Arena Name)"+reset+white+" - "+grey+"Use command to set game time.");
		p.getPlayer().sendMessage(orange+""+bold+"-------------------------");
	}
}
