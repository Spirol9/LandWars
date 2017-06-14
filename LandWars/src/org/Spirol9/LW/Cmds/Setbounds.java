package org.Spirol9.LW.Cmds;

import org.Spirol9.LW.Configs.BaseConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Setbounds implements CommandExecutor {

  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if(sender instanceof ConsoleCommandSender)
    {
      Bukkit.getLogger().warning("Command Can Only be Run By Player | LandWars");
    }

    if(sender instanceof Player) {

      Player p = (Player) sender;

      if(args.length == 2)
      {
        if(args[0].equalsIgnoreCase("red")){
          if(args[1].equalsIgnoreCase("1")){
            BaseConfig.BaseConfig.set("Red.bound1.World", p.getLocation().getWorld().getName());
            BaseConfig.BaseConfig.set("Red.bound1.X", p.getLocation().getBlockX());
            BaseConfig.BaseConfig.set("Red.bound1.Y", p.getLocation().getBlockY());
            BaseConfig.BaseConfig.set("Red.bound1.Z", p.getLocation().getBlockZ());
            BaseConfig.BaseSave();

            p.sendMessage("set red bound 1");
          }else if(args[1].equalsIgnoreCase("2")){
              BaseConfig.BaseConfig.set("Red.bound2.World", p.getLocation().getWorld().getName());
              BaseConfig.BaseConfig.set("Red.bound2.X", p.getLocation().getBlockX());
              BaseConfig.BaseConfig.set("Red.bound2.Y", p.getLocation().getBlockY());
              BaseConfig.BaseConfig.set("Red.bound2.Z", p.getLocation().getBlockZ());
              BaseConfig.BaseSave();

              p.sendMessage("set red bound 2");
            }
        }else if(args[0].equalsIgnoreCase("blue")){
          if(args[1].equalsIgnoreCase("1")){
            BaseConfig.BaseConfig.set("Blue.bound1.World", p.getLocation().getWorld().getName());
            BaseConfig.BaseConfig.set("Blue.bound1.X", p.getLocation().getBlockX());
            BaseConfig.BaseConfig.set("Blue.bound1.Y", p.getLocation().getBlockY());
            BaseConfig.BaseConfig.set("Blue.bound1.Z", p.getLocation().getBlockZ());
            BaseConfig.BaseSave();

            p.sendMessage("set blue bound 1");
          }else if(args[1].equalsIgnoreCase("2")){
              BaseConfig.BaseConfig.set("Blue.bound2.World", p.getLocation().getWorld().getName());
              BaseConfig.BaseConfig.set("Blue.bound2.X", p.getLocation().getBlockX());
              BaseConfig.BaseConfig.set("Blue.bound2.Y", p.getLocation().getBlockY());
              BaseConfig.BaseConfig.set("Blue.bound2.Z", p.getLocation().getBlockZ());
              BaseConfig.BaseSave();

              p.sendMessage("set blue bound 2");
          }
        }else if(args[0].equalsIgnoreCase("height")){
          if(args[1].equalsIgnoreCase("1")){
            BaseConfig.BaseConfig.set("boundh1.Y", p.getLocation().getBlockY());
            BaseConfig.BaseSave();

            p.sendMessage("set height bound 1");
          }else if(args[1].equalsIgnoreCase("2")){
              BaseConfig.BaseConfig.set("boundh2.Y", p.getLocation().getBlockY());
              BaseConfig.BaseSave();

              p.sendMessage("set height bound 2");
          }
        }
        return true;
      }
    }
    return false;
  }
}
