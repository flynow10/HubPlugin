package com.wagologies.HubPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigReader {
    private final JavaPlugin plugin;
    private FileConfiguration config;
    public ConfigReader(JavaPlugin plugin)
    {
        this.plugin = plugin;
        config = plugin.getConfig();
    }
    public void AddDefaults()
    {
        plugin.saveDefaultConfig();
    }
    public FileConfiguration getConfig(){ return getConfig(); }
    public String getHubWorldName()
    {
        return config.getString("spawnpoint.world");
    }
    public Location getHubSpawnLocation() throws Exception {
        World spawnWorld = Bukkit.getWorld(getHubWorldName());
        if(spawnWorld != null)
        {
            return new Location(
                    spawnWorld,
                    config.getInt("spawnpoint.x")+0.5,
                    config.getInt("spawnpoint.y"),
                    config.getInt("spawnpoint.z")+0.5
                    );
        }
        else
        {
            throw new Exception("Spawn world " + getHubWorldName() +" could not be found");
        }
    }
    public Location getBedwarsSpawnLocation() throws Exception {
        World spawnWorld = Bukkit.getWorld(getHubWorldName());
        if(spawnWorld != null)
        {
            return new Location(
                    spawnWorld,
                    config.getInt("bedwarsLobbyPoint.x") + 0.5,
                    config.getInt("bedwarsLobbyPoint.y"),
                    config.getInt("bedwarsLobbyPoint.z") + 0.5
            );
        }
        else
        {
            throw new Exception("Spawn world " + getHubWorldName() +" could not be found");
        }
    }
}