package com.wagologies.HubPlugin;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.permissions.Permission;

import java.util.Arrays;

public class HubListener implements Listener {
    private final World HUB_WORLD;
    public Material[] noInteractBlocks = new Material[]{
            Material.CHEST,
            Material.ENDER_CHEST,
            Material.BED_BLOCK,
            Material.BED,
            Material.TRAPPED_CHEST,
            Material.ACACIA_DOOR,
            Material.BIRCH_DOOR,
            Material.DARK_OAK_DOOR,
            Material.IRON_DOOR,
            Material.JUNGLE_DOOR,
            Material.SPRUCE_DOOR,
            Material.TRAP_DOOR,
            Material.WOOD_DOOR,
            Material.WOODEN_DOOR,
            Material.STONE_BUTTON,
            Material.WOOD_BUTTON
    };
    public HubListener()
    {
        HUB_WORLD = Bukkit.getWorld(HubPlugin.instance.configReader.getHubWorldName());
    }
    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event)
    {
        event.setJoinMessage("Welcome to the server!");
        try {
            event.getPlayer().teleport(HubPlugin.instance.configReader.getHubSpawnLocation());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @EventHandler
    public void PlayerBreakBlocks(BlockBreakEvent event)
    {
        if(event.getPlayer().getWorld() == HUB_WORLD)
        {
            if(!PlayerCanInteractWithHub(event.getPlayer()))
            {
                if(!event.isCancelled())
                    event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void PlayerPlaceBlock(BlockPlaceEvent event)
    {
        if(event.getPlayer().getWorld() == HUB_WORLD)
        {
            if(!PlayerCanInteractWithHub(event.getPlayer()))
            {
                if(!event.isCancelled())
                    event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void PlayerOpenChest(PlayerInteractEvent event)
    {
        if(event.getPlayer().getWorld() == HUB_WORLD)
        {
            if(!PlayerCanInteractWithHub(event.getPlayer()))
            {
                if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
                {
                    if(Arrays.asList(noInteractBlocks).contains(event.getClickedBlock().getType()))
                    {
                        if(!event.isCancelled())
                            event.setCancelled(true);
                    }
                }
            }
        }
    }
    @EventHandler
    public void HungerDeplete(FoodLevelChangeEvent event)
    {
        if(event.getEntity().getWorld() == HUB_WORLD)
        {
            if(!event.isCancelled())
                event.setCancelled(true);
        }
    }
    @EventHandler
    public void EntityDamage(EntityDamageEvent event)
    {
        if(event.getCause() == EntityDamageEvent.DamageCause.VOID || event.getCause() == EntityDamageEvent.DamageCause.SUICIDE)
            return;
        if(event.getEntity() instanceof Player)
        {
            if(event.getEntity().getWorld() == HUB_WORLD)
            {
                if(!event.isCancelled())
                    event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void WeatherChange(WeatherChangeEvent event)
    {
        if(event.getWorld() == HUB_WORLD)
        {
            if(!event.isCancelled())
                event.setCancelled(true);
        }
    }
    public boolean PlayerCanInteractWithHub(Player player)
    {
        return (player.isOp() || player.hasPermission("hubPlugin.canInteract")) && player.getGameMode() == GameMode.CREATIVE;
    }
}
