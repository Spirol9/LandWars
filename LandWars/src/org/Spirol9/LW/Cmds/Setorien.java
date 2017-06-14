package org.Spirol9.LW.Cmds;

import org.Spirol9.LW.Configs.BaseConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Setorien implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof ConsoleCommandSender)
		{
			Bukkit.getLogger().warning("Command Can Only be Run By Player | LandWars");
		}

		if(sender instanceof Player) {

			Player p = (Player) sender;

			if(args.length == 1)
			{
				if(args[0].equalsIgnoreCase("xz")){
					BaseConfig.BaseConfig.set("orientation", "xz");
					BaseConfig.BaseSave();

					p.sendMessage("set orientation to xz");
				}
				else if(args[0].equalsIgnoreCase("zx")){
					BaseConfig.BaseConfig.set("orientation", "zx");
					BaseConfig.BaseSave();

					p.sendMessage("set orientation to zx");
				}
				return true;
			}
		}
		return false;
	}
}
