package com.wagologies.HubPlugin;

import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    public boolean gameRunning = true;
    public List<List<Player>> teamList = new ArrayList<List<Player>>();
    public World world;
    public Game(List<List<Player>> teams, World world) {
        teamList = teams;
        this.world = world;
    }

    /**
     * Stop the game in the middle
     * @param deleteWorld should delete world
     */
    public abstract void StopGame(boolean deleteWorld);
    public static int GetMaxTeams()
    {
        return 8;
    }
}
