package org.Spirol9.LW.Listeners;

import org.Spirol9.LW.Configs.BaseConfig;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockBreak implements Listener {

  private static Plugin plugin;

  int redamount = 0;
  int blueamount = 0;
  Location redbounds1;
  Location redbounds2;
  Location bluebounds1; 
  Location bluebounds2; 

  public BlockBreak(Plugin plugin) {
    BlockBreak.plugin = plugin;
    locs();
  }

  public void locs(){
    try{
      Location redbound1 = new Location(
          Bukkit.getWorld(BaseConfig.BaseConfig.getString("Red.bound1.World"))
          ,BaseConfig.BaseConfig.getInt("Red.bound1.X")
          ,BaseConfig.BaseConfig.getInt("Red.bound1.Y")
          ,BaseConfig.BaseConfig.getInt("Red.bound1.Z"));
      Location redbound2 = new Location(
          Bukkit.getWorld(BaseConfig.BaseConfig.getString("Red.bound2.World"))
          ,BaseConfig.BaseConfig.getInt("Red.bound2.X")
          ,BaseConfig.BaseConfig.getInt("Red.bound2.Y")
          ,BaseConfig.BaseConfig.getInt("Red.bound2.Z"));
      Location bluebound1 = new Location(
          Bukkit.getWorld(BaseConfig.BaseConfig.getString("Blue.bound1.World"))
          ,BaseConfig.BaseConfig.getInt("Blue.bound1.X")
          ,BaseConfig.BaseConfig.getInt("Blue.bound1.Y")
          ,BaseConfig.BaseConfig.getInt("Blue.bound1.Z"));
      Location bluebound2 = new Location(
          Bukkit.getWorld(BaseConfig.BaseConfig.getString("Blue.bound2.World"))
          ,BaseConfig.BaseConfig.getInt("Blue.bound2.X")
          ,BaseConfig.BaseConfig.getInt("Blue.bound2.Y")
          ,BaseConfig.BaseConfig.getInt("Blue.bound2.Z"));

      if(redbound1!=null&&redbound2!=null&&bluebound1!=null&&bluebound1!=null){
        redbounds1 = redbound1;
        redbounds2 = redbound2;
        bluebounds1 = bluebound1;
        bluebounds2 = bluebound2;
      }
    }catch(NullPointerException npe){npe.getStackTrace();}
  }

  @EventHandler
  public void blockBreakEvent(BlockBreakEvent e){

    Player p = e.getPlayer();
    ItemStack ih = p.getInventory().getItemInMainHand();

    if(e.getBlock().getType()==Material.GRASS){}

    Material wool = Material.WOOL;
    if(BaseConfig.BaseConfig.getString("orientation").equalsIgnoreCase("xz")){
      if(ih.getType()==wool){

        Bukkit.broadcastMessage("xz");

        if(ih.getData().getData()==DyeColor.RED.getWoolData()){

          redamount = redamount-1;

          TeamKill(p,redamount,DyeColor.RED.getWoolData());

          bluebounds1.add(1, 0, 0);
          bluebounds2.add(1, 0, 0);
        }else{
          if(ih.getData().getData()==DyeColor.BLUE.getWoolData()){

            blueamount = blueamount -1;

            TeamKill(p,blueamount,DyeColor.BLUE.getWoolData());

            redbounds1.subtract(1, 0, 0);
            redbounds2.subtract(1, 0, 0);
          }
        }
      }
    }else if(BaseConfig.BaseConfig.getString("orientation").equalsIgnoreCase("zx")){
      if(ih.getType()==wool){

        Bukkit.broadcastMessage("zx");

        if(DyeColor.getByWoolData(ih.getData().getData())==DyeColor.BLUE){

          Bukkit.broadcastMessage("red");  


          redamount = redamount+1;

          Bukkit.broadcastMessage("RED: "+ redamount+ " | " + "BLUE: "+ blueamount);

          TeamKill2(p,redamount,DyeColor.RED);

          bluebounds1.subtract(0, 0, 1);
          bluebounds2.subtract(0, 0, 1);
        }else{
          if(DyeColor.getByWoolData(ih.getData().getData())==DyeColor.RED){

            Bukkit.broadcastMessage("blue");


            blueamount = blueamount+1;

            Bukkit.broadcastMessage("RED: "+ redamount+ " | " + "BLUE: "+ blueamount);

            TeamKill2(p,blueamount,DyeColor.BLUE);

            redbounds1.add(0, 0, 1);
            redbounds2.add(0, 0, 1);
          }
        }
      }
    }
  }

  public void TeamKill(Player p,int direction,byte color){

    int boundh2 = BaseConfig.BaseConfig.getInt("boundh2.Y");
    int boundh1 = BaseConfig.BaseConfig.getInt("boundh1.Y");

    int boundhdiff = -1;

    if(boundh1<boundh2){

      boundhdiff = boundh2 - boundh1;
    }
    else if(boundh1>boundh2){

      boundhdiff = boundh1 - boundh2;
    }else{
      p.sendMessage("height bounds not tall enough");
    }

    if(color==DyeColor.RED.getWoolData()){
      Location bound1loc = redbounds1;

      Material wool = Material.WOOL;

      int bound2 = redbounds2.getBlockZ();
      int bound1 = redbounds1.getBlockZ();

      ///////////////////////////////
      ////IF 2 IS GREATER THAN 1/////
      ///////////////////////////////

      if(bound1<bound2){
        int bounddiff = bound2 - bound1;

        for(int a=0; a<bounddiff+1; a++){
          for(int h=0; h<boundhdiff+1; h++){

            Location loc = new Location(
                bound1loc.getWorld(),
                bound1loc.getX()-direction,
                bound1loc.getY()-1+h,
                bound1loc.getZ()+a);

            if(loc.getBlock().getType()==Material.WOOL){
              loc.getBlock().setType(wool);
              loc.getBlock().setData(color);
            }
          }
        }
        ///////////////////////////////
        ////IF 1 IS GREATER THAN 2////
        ///////////////////////////////
      }else if(bound1>bound2){
        int bounddiff = bound1 - bound2;

        for(int a=0; a<bounddiff+1; a++){
          for(int h=0; h<boundhdiff+1; h++){

            Location loc = new Location(
                bound1loc.getWorld(),
                bound1loc.getX()-direction,
                bound1loc.getY()-1+h,
                bound1loc.getZ()-a);

            if(loc.getBlock().getType()==Material.WOOL){
              loc.getBlock().setType(wool);
              loc.getBlock().setData(color);
            }
          }
        }
      }else{
        p.sendMessage("Bounds not long enough");
      }
      //////////////////////////////////////
      //////////////////////////////////////
      ////////////////2NDTEAM///////////////
      //////////////////////////////////////
      //////////////////////////////////////
    }else if(color==DyeColor.BLUE.getWoolData()){
      Location bound1loc = bluebounds1;

      Material wool = Material.WOOL;

      int bound2 = bluebounds2.getBlockZ();
      int bound1 = bluebounds1.getBlockZ();

      ///////////////////////////////
      ////IF 2 IS GREATER THAN 1/////
      ///////////////////////////////

      if(bound1<bound2){
        int bounddiff = bound2 - bound1;

        for(int a=0; a<bounddiff+1; a++){
          for(int h=0; h<boundhdiff+1; h++){

            Location loc = new Location(
                bound1loc.getWorld(),
                bound1loc.getX()+direction,
                bound1loc.getY()-1+h,
                bound1loc.getZ()+a);

            if(loc.getBlock().getType()==Material.WOOL){
              loc.getBlock().setType(wool);
              loc.getBlock().setData(color);
            }
          }
        }
        ///////////////////////////////
        ////IF 1 IS GREATER THAN 2/////
        ///////////////////////////////
      }else if(bound1>bound2){
        int bounddiff = bound1 - bound2;

        for(int a=0; a<bounddiff+1; a++){
          for(int h=0; h<boundhdiff+1; h++){

            Location loc = new Location(
                bound1loc.getWorld(),
                bound1loc.getX()-direction,
                bound1loc.getY()-1+h,
                bound1loc.getZ()-a);

            if(loc.getBlock().getType()==Material.WOOL){
              loc.getBlock().setType(wool);
              loc.getBlock().setData(color);
            }
          }
        }
      }else{
        p.sendMessage("Bounds not long enough");
      }
    }
  }

  public void TeamKill2(Player p,int direction,DyeColor color){

    int boundh2 = BaseConfig.BaseConfig.getInt("boundh2.Y");
    int boundh1 = BaseConfig.BaseConfig.getInt("boundh1.Y");

    int boundhdiff = -1;

    if(boundh1<boundh2){

      boundhdiff = boundh2 - boundh1;
    }
    else if(boundh1>boundh2){

      boundhdiff = boundh1 - boundh2;
    }else{
      p.sendMessage("height bounds not tall enough");
    }

    if(color==DyeColor.RED){
      Location bound1loc = redbounds1;

      Material wool = Material.WOOL;

      int bound2 = redbounds2.getBlockX();
      int bound1 = redbounds1.getBlockX();
      int check2 = 0;
      int check1 = 0;

      if(String.valueOf(bound2).contains("-")){
        check2 = Integer.valueOf(String.valueOf(bound2).substring(1));
      }else{
        check2 = bound2;
      }
      if(String.valueOf(bound1).contains("-")){
        check1 = Integer.valueOf(String.valueOf(bound1).substring(1));
      }else{
        check1 = bound1;
      }

      ///////////////////////////////
      ////IF 2 IS GREATER THAN 1/////
      ///////////////////////////////

      if(check1<check2){
        int bounddiff = check2 - check1;

        Bukkit.broadcastMessage("red 1");

        for(int a=0; a<bounddiff+1; a++){
          for(int h=0; h<boundhdiff+1; h++){

            Location loc = new Location(
                bound1loc.getWorld(),
                bound1loc.getX()-a,
                bound1loc.getY()-1+h,
                bound1loc.getZ()-direction);
            Location loc2 = new Location(
                bound1loc.getWorld(),
                bound1loc.getX()+a,
                bound1loc.getY()-1+h,
                bound1loc.getZ()-direction);

            if(loc.getBlock().getType()==Material.WOOL){
              loc.getBlock().setType(wool);
              loc.getBlock().setData(color.getWoolData());
            }
            if(loc2.getBlock().getType()==Material.WOOL){
              loc2.getBlock().setType(wool);
              loc2.getBlock().setData(color.getWoolData());
            }
          }
        }
        ///////////////////////////////
        ////IF 1 IS GREATER THAN 2////
        ///////////////////////////////
      }else if(check1>check2){
        int bounddiff = check1 - check2;

        Bukkit.broadcastMessage("red 2");

        for(int a=0; a<bounddiff+1; a++){
          for(int h=0; h<boundhdiff+1; h++){

            Location loc = new Location(
                bound1loc.getWorld(),
                bound1loc.getX()+a,
                bound1loc.getY()-1+h,
                bound1loc.getZ()-direction);
            Location loc2 = new Location(
                bound1loc.getWorld(),
                bound1loc.getX()-a,
                bound1loc.getY()-1+h,
                bound1loc.getZ()-direction);

            if(loc.getBlock().getType()==Material.WOOL){
              loc.getBlock().setType(wool);
              loc.getBlock().setData(color.getWoolData());
            }
            if(loc2.getBlock().getType()==Material.WOOL){
              loc2.getBlock().setType(wool);
              loc2.getBlock().setData(color.getWoolData());
            }
          }
        }
      }else{
        p.sendMessage("Bounds not long enough");
      }
      //////////////////////////////////////
      //////////////////////////////////////
      ////////////////2NDTEAM///////////////
      //////////////////////////////////////
      //////////////////////////////////////
    }else if(color==DyeColor.BLUE){
      Location bound1loc = bluebounds1;

      Material wool = Material.WOOL;

      int bound2 = bluebounds2.getBlockX();
      int bound1 = bluebounds1.getBlockX();
      int check2 = 0;
      int check1 = 0;

      if(String.valueOf(bound2).contains("-")){
        check2 = Integer.valueOf(String.valueOf(bound2).substring(1));
      }else{
        check2 = bound2;
      }
      if(String.valueOf(bound1).contains("-")){
        check1 = Integer.valueOf(String.valueOf(bound1).substring(1));
      }else{
        check1 = bound1;
      }
      ///////////////////////////////
      ////IF 2 IS GREATER THAN 1/////
      ///////////////////////////////

      if(check1<check2){
        int bounddiff = check2 - check1;

        for(int a=0; a<bounddiff+1; a++){
          for(int h=0; h<boundhdiff+1; h++){

            Location loc = new Location(
                bound1loc.getWorld(),
                bound1loc.getX()-a,
                bound1loc.getY()-1+h,
                bound1loc.getZ()+direction-1);
            Location loc2 = new Location(
                bound1loc.getWorld(),
                bound1loc.getX()+a,
                bound1loc.getY()-1+h,
                bound1loc.getZ()+direction-1);


            if(loc.getBlock().getType()==Material.WOOL){
              loc.getBlock().setType(wool);
              loc.getBlock().setData(color.getWoolData());
            }
            if(loc2.getBlock().getType()==Material.WOOL){
              loc2.getBlock().setType(wool);
              loc2.getBlock().setData(color.getWoolData());
            }
          }
        }
        ///////////////////////////////
        ////IF 1 IS GREATER THAN 2/////
        ///////////////////////////////
      }else if(check1>check2){
        int bounddiff = check1 - check2;

        for(int a=0; a<bounddiff+1; a++){
          for(int h=0; h<boundhdiff+1; h++){

            Location loc = new Location(
                bound1loc.getWorld(),
                bound1loc.getX()+a,
                bound1loc.getY()-1+h,
                bound1loc.getZ()-direction+1);
            Location loc2 = new Location(
                bound1loc.getWorld(),
                bound1loc.getX()-a,
                bound1loc.getY()-1+h,
                bound1loc.getZ()-direction+1);

            if(loc.getBlock().getType()==Material.WOOL){
              loc.getBlock().setType(wool);
              loc.getBlock().setData(color.getWoolData());
            }
            if(loc2.getBlock().getType()==Material.WOOL){
              loc2.getBlock().setType(wool);
              loc2.getBlock().setData(color.getWoolData());
            }
          }
        }
      }else{
        p.sendMessage("Bounds not long enough");
      }
    }
  }
}
