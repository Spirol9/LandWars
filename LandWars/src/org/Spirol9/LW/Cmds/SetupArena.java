package org.Spirol9.LW.Cmds;

import org.Spirol9.LW.Msgs.SetupMsgs;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class SetupArena implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof ConsoleCommandSender)
		{
			Bukkit.getLogger().warning("Command Can Only be Run By Player | LandWars");
		}

		if(sender instanceof Player) {

			Player p = (Player) sender;

			if(args.length == 2)
			{
				if(args[0].equalsIgnoreCase("create")){
					
				}
				else if(args[0].equalsIgnoreCase("delete")){
					
				}
				else{
					SetupMsgs.setupHelpCmds(p.getUniqueId());
				}
			}
			return true;
		}
		return false;
	}
}
