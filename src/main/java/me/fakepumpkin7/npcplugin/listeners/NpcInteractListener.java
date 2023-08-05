package me.fakepumpkin7.npcplugin.listeners;

import me.fakepumpkin7.npcplugin.NPC;
import me.fakepumpkin7.npcplugin.NpcPlugin;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.entity.Entity;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class NpcInteractListener implements Listener {
    public NpcPlugin plugin;

    public NpcInteractListener(NpcPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteractNPC(PlayerInteractEntityEvent e) {
        Entity entity = e.getRightClicked();
        if(entity instanceof Player){
            Player playerClicked = (Player) entity;
            for(NPC npc: plugin.NpcList){
                if(playerClicked.getUniqueId() == npc.getUniqueID()){
                    //the entity clicked is an npc
                    if(npc.getCommand() != null) {
                        Player player = e.getPlayer();
                        player.performCommand(npc.getCommand());
                    }
                }
            }

        }

    }
}
