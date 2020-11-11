package com.wagologies.HubPlugin.Commands;

import com.wagologies.HubPlugin.HubPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubCommand implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player && strings.length == 0)
        {
            Player player = (Player) commandSender;
            HubPlugin.TeleportToHub(player);
            return true;
        }
        if(strings.length > 1 && commandSender.isOp())
        {
            for(String string : strings) {
                Player namedPlayer = Bukkit.getPlayer(string);
                if (namedPlayer != null) {
                    HubPlugin.TeleportToHub(namedPlayer);
                }
                else
                {
                    commandSender.sendMessage(ChatColor.RED + "Could not find this player");
                }
            }
            return true;
        }
        return false;
    }
}
