package me.fakepumpkin7.npcplugin;


import me.fakepumpkin7.npcplugin.commands.SpawnNpcCommand;
import me.fakepumpkin7.npcplugin.listeners.NpcDamagedListener;
import me.fakepumpkin7.npcplugin.listeners.NpcInteractListener;
import me.fakepumpkin7.npcplugin.listeners.PlayerJoinListener;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.UUID;

public final class NpcPlugin extends JavaPlugin {

    // below, the commented out function createNpcTest
    // shows how to spawn an NPC



    public ArrayList<NPC> NpcList = new ArrayList<>();
    //update command usage in yml
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("by FakePumpkin7");

        //spawn an npc at 0,100,0

        this.getCommand("spawnnpc").setExecutor(new SpawnNpcCommand(this));
        getServer().getPluginManager().registerEvents(new NpcInteractListener(this),this);
        getServer().getPluginManager().registerEvents(new NpcDamagedListener(this),this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this),this);


        //createNpcTest();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


/*
    public void createNpcTest(){
        Location location = new Location(Bukkit.getWorlds().get(0),0,100,0);
        String name = "TEST";
        String command = "give fakepumpkin7 diamond 10";
        String signature = "aCHxucILQpJINhAM5IYJ+ZVKjLhUs5qx0a0tNzFBBy5abVDHni82+GGQvx7RxuIpMnecSQpjoagextwVra4lLVbAEOmBS/BpbLu4mqwaXzWB5gdEIGPWF6CZBqW3q6gtXKLrz7oPjrq1PjcE3tIScO/AuiE5loWFZ/S08glIvk8zeceVUW5h7rbFGjTCN9mIP+KgIKDjXrpfULWek/9jx0rEC6/X0AGj4291ZeDXl32VjJVhb6aXIlqPwX4dTYoUHr4iA+SqxjG8o2ouUXNxKFdIOm7dcsE607AZK18OcSB7rIZ7sj3qpE9LNTNqlUnEB6NAyNK81GlJd560IEw0QY8w7PXqAd9UMsmXl85scCLmeXh1GwWqi10OXMGv656sk66r82rWYguB/lQHyOnLt8tNjZ8BGM8+w+FYmE7rAHG36UpAa69YT+WgUqjypOcyAfAbnWSGpEEc5Y2A7IpY13HMPaiYg9gb2bOZ6FYUBX6AfLm2FFySHypp2LAWMr5XqdC5gmmB4Lh0wQtN+TEKIH7PfgRvzPC6IZjMX5sgTgIxcuKB0PYp59Ax8/ftWNglXWmaahhoFI3cjkNdkKWYp+WEpby8kcHFeg6xcGzV34aUr8JHr/+AwoMTIElKJzdfgpIEsbnU0VK/lkxKBpugzGO5T59GHRI7qpo52ommqn8=";
        String texture = "ewogICJ0aW1lc3RhbXAiIDogMTY5MTI3MjA3NDcyNiwKICAicHJvZmlsZUlkIiA6ICJkZTU3MWExMDJjYjg0ODgwOGZlN2M5ZjQ0OTZlY2RhZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJNSEZfTWluZXNraW4iLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzliZjRiM2ZhOGRlM2FmZWY0ZjRlMGNmYThlNWEwYWU4MDEyMWRlOGM4ZDMwMjY3YTY3N2Q1NThmZjg2MDY4ZCIKICAgIH0KICB9Cn0=";
        String[] skin = new String[]{texture, signature};
        NPC test = new NPC(name, location, command, skin);
        this.NpcList.add(test);
        Location location2 = new Location(Bukkit.getWorlds().get(0),0,105,0);
        String name2 = "TEST2";
        String command2 = "give fakepumpkin7 diamond 15";
        NPC test2 = new NPC(name2, location2, command2);
        this.NpcList.add(test2);
    }
    */

    public void showAllNpc(EntityPlayer entityPlayer, Location location) {
        PacketPlayOutPlayerInfo playerInfoAdd = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer);
        PacketPlayOutSpawnEntityLiving namedEntitySpawn = new PacketPlayOutSpawnEntityLiving(entityPlayer);
        PacketPlayOutEntityHeadRotation headRotation = new PacketPlayOutEntityHeadRotation(entityPlayer, (byte) ((location.getYaw() * 256f) / 360f));
        PacketPlayOutPlayerInfo playerInfoRemove = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entityPlayer);

        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
            connection.sendPacket(playerInfoAdd);
            connection.sendPacket(namedEntitySpawn);
            connection.sendPacket(headRotation);
            new BukkitRunnable(){
                @Override
                public void run(){
                    connection.sendPacket(playerInfoRemove);
                }
            }.runTaskLaterAsynchronously(this,20L);

        }
    }
}
