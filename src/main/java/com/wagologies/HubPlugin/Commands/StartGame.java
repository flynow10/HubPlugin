package com.wagologies.HubPlugin.Commands;

import com.wagologies.HubPlugin.HubPlugin;
import com.wagologies.HubPlugin.Lobby;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartGame implements CommandExecutor {
    /**
     * Executes the given command, returning its success
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player)
        {
            Player player = (Player) sender;
            for (Lobby lobby : HubPlugin.instance.lobbys) {
                if(lobby.PlayerInLobby(player))
                {
                    if(player.isOp() || player.hasPermission("hubPlugin.startGame"))
                    {
                        lobby.StartGame();
                    }
                }
            }
            player.sendMessage(ChatColor.RED + "You aren't in a game!");
            return true;
        }
        sender.sendMessage(ChatColor.RED + "This command must be sent by a player!");
        return true;
    }
}
