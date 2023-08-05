package me.fakepumpkin7.npcplugin.listeners;

import me.fakepumpkin7.npcplugin.NPC;
import me.fakepumpkin7.npcplugin.NpcPlugin;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class NpcDamagedListener implements Listener {
    public NpcPlugin plugin;

    public NpcDamagedListener(NpcPlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onNpcDamaged(EntityDamageEvent e) {
        Entity entity = e.getEntity();
        if(entity instanceof Player){
            Player playerClicked = (Player) entity;
            for(NPC npc: plugin.NpcList){
                if(playerClicked.getUniqueId() == npc.getUniqueID()){
                    //the entity clicked is an npc
                    e.setCancelled(true);
                }
            }
        }
    }
}
