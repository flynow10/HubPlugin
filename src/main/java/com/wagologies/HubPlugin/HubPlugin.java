package com.wagologies.HubPlugin;

import com.wagologies.HubPlugin.Commands.HubCommand;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class HubPlugin extends JavaPlugin {
    public ConfigReader configReader;
    public static HubPlugin instance;
    @Override
    public void onEnable() {
        instance = this;
        try {
            setupHubWorld();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Bukkit.getPluginManager().registerEvents(new HubListener(),this);
        getCommand("hub").setExecutor(new HubCommand());
    }

    private void setupHubWorld() throws Exception {
        configReader = new ConfigReader(this);
        configReader.AddDefaults();
        String hubWorldName = configReader.getHubWorldName();
        World hubWorld;
        if(new File(getServer().getWorldContainer(), hubWorldName).exists())
        {
            hubWorld = getServer().createWorld(new WorldCreator(hubWorldName));
        }
        else
        {
            throw new Exception("Hub world doesn't exist");
        }
        hubWorld.setPVP(false);
        hubWorld.setGameRuleValue("doDaylightCycle", "false");
        hubWorld.setTime(0);
        hubWorld.setWeatherDuration(0);
        hubWorld.setGameRuleValue("doMobSpawning", "false");
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static void TeleportToHub(Player player)
    {
        try {
            player.teleport(HubPlugin.instance.configReader.getHubSpawnLocation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
