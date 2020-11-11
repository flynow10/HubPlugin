package com.wagologies.HubPlugin;

import com.wagologies.HubPlugin.Commands.HubCommand;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

import java.io.File;
import java.util.List;

public class HubPlugin extends JavaPlugin {
    public ConfigReader configReader;
    public static HubPlugin instance;
    public List<Lobby> lobbys;
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
        hubWorld.setGameRuleValue("doDaylightCycle", "false");
        hubWorld.setTime(1000);
        hubWorld.setWeatherDuration(0);
        hubWorld.setGameRuleValue("doMobSpawning", "false");
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static void TeleportToHub(Player player)
    {
        player.getInventory().clear();
        player.setGameMode(GameMode.SURVIVAL);
        for (PotionEffect effect : player.getActivePotionEffects())
            player.removePotionEffect(effect.getType());
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);
        try {
            int chunkx = HubPlugin.instance.configReader.getHubSpawnLocation().getBlockX() >> 4;
            int chunkz = HubPlugin.instance.configReader.getHubSpawnLocation().getBlockZ() >> 4;
            HubPlugin.instance.configReader.getHubSpawnLocation().getWorld().loadChunk(chunkx,chunkz);
            player.teleport(HubPlugin.instance.configReader.getHubSpawnLocation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
