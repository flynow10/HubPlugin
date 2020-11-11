package com.wagologies.HubPlugin;

import com.github.juliarn.npc.event.PlayerNPCInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCListener implements Listener {
    @EventHandler
    public void ClickedOnNpc(PlayerNPCInteractEvent event)
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
