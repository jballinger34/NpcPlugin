package me.fakepumpkin7.npcplugin.commands;

import me.fakepumpkin7.npcplugin.NPC;
import me.fakepumpkin7.npcplugin.NpcPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpawnNpcCommand implements CommandExecutor {

    NpcPlugin plugin;
    public SpawnNpcCommand(NpcPlugin npcPlugin) {
        this.plugin = npcPlugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        //adds a line to the datafile with the npcs information, then refreshes which npcs are being shown to players
        return true;
    }
}

