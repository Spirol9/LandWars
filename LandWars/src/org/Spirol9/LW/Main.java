package org.Spirol9.LW;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.Spirol9.LW.Cmds.Setbounds;
import org.Spirol9.LW.Cmds.Setorien;
import org.Spirol9.LW.Cmds.SetupArena;
import org.Spirol9.LW.Configs.BaseConfig;
import org.Spirol9.LW.Listeners.ArrowHitBlock;
import org.Spirol9.LW.Listeners.BlockBreak;
import org.Spirol9.LW.Listeners.Movement;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  private Plugin plugin;

  File file;
  File Config;

  @Override
  public void onEnable(){
    plugin = this;  

    getConfigFileConfiguration("Config");
    
    Bukkit.getServer().getPluginManager().registerEvents(new BlockBreak(this),this);
    Bukkit.getServer().getPluginManager().registerEvents(new Movement(this),this);
    Bukkit.getServer().getPluginManager().registerEvents(new ArrowHitBlock(this),this);
    
    getCommand("LWS").setExecutor(new SetupArena());
    getCommand("setorien").setExecutor(new Setorien());
    getCommand("setbound").setExecutor(new Setbounds());
  }

  @Override
  public void onDisable(){

  }

  private FileConfiguration getConfigFileConfiguration(String fileName) {
    file = new File(getDataFolder(), fileName + ".yml");
    BaseConfig.newConfig = file;
    FileConfiguration fileConfiguration = new YamlConfiguration();
    BaseConfig.BaseConfig = fileConfiguration;

    try {
      fileConfiguration.load(file);

    } catch (IOException | InvalidConfigurationException e) {
      getLogger().info("Generating fresh configuration file: " + fileName + ".yml");
    }

    try {
      if (!file.exists()) {
        file.getParentFile().mkdirs();
        InputStream in = getResource(fileName + ".yml");
        OutputStream out = new FileOutputStream(file);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0)
          out.write(buf, 0, len);
        out.close();
        in.close();
      }
      fileConfiguration.load(file);
    } catch (IOException | InvalidConfigurationException ex) {
      getLogger().severe("Plugin unable to write configuration file " + fileName + ".yml!");
      getLogger().severe("Disabling...");
      getServer().getPluginManager().disablePlugin(this);
      ex.printStackTrace();
    }

    return fileConfiguration;
  }

}
