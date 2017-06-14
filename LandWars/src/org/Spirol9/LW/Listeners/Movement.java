package org.Spirol9.LW.Listeners;

import org.Spirol9.LW.Configs.BaseConfig;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class Movement implements Listener {

  private static Plugin plugin;

  public Movement(Plugin plugin) {
    Movement.plugin = plugin;
  }


  @EventHandler
  public void movementEvent(PlayerMoveEvent e){

    Player p = e.getPlayer();

    ItemStack ih = p.getInventory().getItemInMainHand();

    Location loc = new Location(
        p.getWorld()
        ,p.getLocation().getBlockX()
        ,p.getLocation().getBlockY()-1
        ,p.getLocation().getBlockZ());

    Double ground = 0.0;
    Double ground2pdiff =0.0;

    Double boundh2 = BaseConfig.BaseConfig.getDouble("boundh2.Y");
    Double boundh1 = BaseConfig.BaseConfig.getDouble("boundh1.Y");

    if(boundh1<boundh2){

      ground = boundh1;
    }
    else if(boundh1>boundh2){

      ground = boundh2;
    }

    ground2pdiff = p.getLocation().getY() - ground;

    Material wool = Material.WOOL;

    if(BaseConfig.BaseConfig.getString("orientation").equalsIgnoreCase("xz")){

      for(int a=0; a<ground2pdiff; a++){

        if(ih.getType()==wool){
          if(ih.getDurability()==DyeColor.RED.getWoolData()){
            if(loc.subtract(0, a, 0).getBlock().getType()==Material.WOOL){
              if(loc.subtract(0, a, 0).getBlock().getData()==DyeColor.BLUE.getWoolData()){
                p.setVelocity(p.getLocation().getDirection().setX(-1).setY(+0.5));
              }
            }
          }else{
            if(ih.getDurability()==DyeColor.BLUE.getWoolData()){
              if(loc.subtract(0, a, 0).getBlock().getType()==Material.WOOL){
                if(loc.subtract(0, a, 0).getBlock().getData()==DyeColor.RED.getWoolData()){
                  p.setVelocity(p.getLocation().getDirection().setX(+1).setY(+0.5));
                }
              }
            }
          }
        }
      }
    }else if(BaseConfig.BaseConfig.getString("orientation").equalsIgnoreCase("zx")){

      for(int a=0; a<ground2pdiff; a++){

        if(ih.getType()==wool){
          if(ih.getDurability()==DyeColor.RED.getWoolData()){
            if(loc.subtract(0, a, 0).getBlock().getType()==Material.WOOL){
              if(loc.subtract(0, a, 0).getBlock().getData()==DyeColor.BLUE.getWoolData()){
                p.setVelocity(p.getLocation().getDirection().setZ(+1).setY(+0.5));
              }
            }
          }else{
            if(ih.getDurability()==DyeColor.BLUE.getWoolData()){
              if(loc.subtract(0, a, 0).getBlock().getType()==Material.WOOL){
                if(loc.subtract(0, a, 0).getBlock().getData()==DyeColor.RED.getWoolData()){
                  p.setVelocity(p.getLocation().getDirection().setZ(-1).setY(+0.5));
                }
              }
            }
          }
        }
      }
    }
  }
}
