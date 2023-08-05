package me.fakepumpkin7.npcplugin.commands;

import com.mojang.authlib.GameProfile;
import me.fakepumpkin7.npcplugin.NPC;
import me.fakepumpkin7.npcplugin.NpcPlugin;
import me.fakepumpkin7.npcplugin.netutil.TutNetHandler;
import me.fakepumpkin7.npcplugin.netutil.TutNetworkManager;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

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

