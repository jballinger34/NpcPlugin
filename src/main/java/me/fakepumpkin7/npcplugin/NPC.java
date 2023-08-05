package me.fakepumpkin7.npcplugin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.fakepumpkin7.npcplugin.netutil.TutNetHandler;
import me.fakepumpkin7.npcplugin.netutil.TutNetworkManager;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NPC extends EntityPlayer {
    private String command;
    private Location location;
    public NPC(String name, Location loc) {
        super(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld) loc.getWorld()).getHandle(), new GameProfile(UUID.randomUUID(), name), new PlayerInteractManager(((CraftWorld) loc.getWorld()).getHandle()));
        this.location = loc;
        handleConnection();
        addToWorldInPos(loc);
    }

    private void handleConnection() {
        this.playerConnection = new TutNetHandler(((CraftServer) Bukkit.getServer()).getServer(), new TutNetworkManager(EnumProtocolDirection.CLIENTBOUND), this);
    }
    private void addToWorldInPos(Location loc){
        this.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        ((CraftWorld) loc.getWorld()).getHandle().addEntity(this);
    }

    public NPC(String name, Location loc, String command) {
        super(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld) loc.getWorld()).getHandle(), new GameProfile(UUID.randomUUID(), name), new PlayerInteractManager(((CraftWorld) loc.getWorld()).getHandle()));
        this.command = command;
        this.location = loc;
        handleConnection();
        addToWorldInPos(loc);
    }

    public NPC(String name, Location loc, String command, String[] skin) {
        super(((CraftServer) Bukkit.getServer()).getServer(), ((CraftWorld) loc.getWorld()).getHandle(), new GameProfile(UUID.randomUUID(), name), new PlayerInteractManager(((CraftWorld) loc.getWorld()).getHandle()));
        this.command = command;
        this.location = loc;
        setSkin(skin);
        handleConnection();
        addToWorldInPos(loc);
    }

    private void setSkin(String[] skin) {
        this.getProfile().getProperties().put("textures", new Property("textures", skin[0], skin[1]));
    }


    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public Location getLocation() {
        return location;
    }
}
