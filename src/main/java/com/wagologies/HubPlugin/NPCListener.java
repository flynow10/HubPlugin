package com.wagologies.HubPlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class NPCListener implements Listener {
    public void ClickedOnNpc()
    {
        /*switch (event.getNPC().getGameProfile().getName())
        {
            case "Bedwars":
                JoinBedwarsLobby(event.getPlayer());
        }*/
    }

    private void JoinBedwarsLobby(Player player) {
        for (Lobby lobby : HubPlugin.instance.lobbys) {
            if(lobby.name.equals("Bedwars"))
            {
                lobby.AddPlayer(player);
                return;
            }
        }
        CreateBedwarsLobby();
        JoinBedwarsLobby(player);
    }
    private void CreateBedwarsLobby()
    {
        /*try {
            HubPlugin.instance.lobbys.add(new Lobby("Bedwars", HubPlugin.instance.configReader.getBedwarsSpawnLocation(), ))
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
