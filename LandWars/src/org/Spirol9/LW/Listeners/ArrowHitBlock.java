package org.Spirol9.LW.Listeners;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BlockIterator;

public class ArrowHitBlock implements Listener {

  private static Plugin plugin;

  public ArrowHitBlock(Plugin plugin) {
    ArrowHitBlock.plugin = plugin;
  }

  @EventHandler
  public void arrowHitWool(ProjectileHitEvent e) {

    Entity entity = e.getEntity();

    if (entity instanceof Arrow) {
      BlockIterator iterator = new BlockIterator(entity.getWorld(), entity.getLocation().toVector(), entity.getVelocity().normalize(), 0, 4);
      Block hitBlock = null;

      while(iterator.hasNext()) {
        hitBlock = iterator.next();
        if(hitBlock.getTypeId()!=0) {
          break;
        }
      }

      if (hitBlock.getType()==Material.WOOL) {
        hitBlock.setTypeId(0);
      }

      entity.remove();
    }
  }
}
