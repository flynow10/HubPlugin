package com.wagologies.HubPlugin;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Lobby {
    public String name;
    public Class game;
    public Location spawnLocation;
    public List<Player> players;
    public World world;
    int MaxPlayers;

    public Lobby(String name, Location spawnLocation, Class game, World world)
    {
        this.name = name;
        this.spawnLocation = spawnLocation;
        this.game = game;
        this.world = world;
        try {
            MaxPlayers = (Integer) game.getMethod("GetMaxTeams").invoke(null);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    public void AddPlayer(Player player)
    {
        players.add(player);
        player.teleport(spawnLocation);
    }
    public void StartGame()
    {
        List<List<Player>> teams = new ArrayList<>();
        int i = 0;
        for (Player player : players) {
            teams.get(i).add(player);
            i++;
            if(i > MaxPlayers)
            {
                i = 0;
            }
        }
        try {
            Constructor c = game.getConstructor(List.class, World.class);
            Game game = (Game) c.newInstance(teams, world);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public boolean PlayerInLobby(Player player)
    {
        if(players.contains(player))
            return true;
        return false;
    }
}
