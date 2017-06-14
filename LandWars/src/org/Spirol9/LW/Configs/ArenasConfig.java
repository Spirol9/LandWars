package org.Spirol9.LW.Configs;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public abstract class ArenasConfig implements Plugin {
    
  public static File newConfig;
  public static FileConfiguration Arenas;

  public static void BaseSave() {
    try {
      Arenas.save(newConfig);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void Basereload() {
    try {
      Arenas.load(newConfig);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InvalidConfigurationException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
