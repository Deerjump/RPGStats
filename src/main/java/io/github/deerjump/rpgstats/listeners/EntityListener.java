package io.github.deerjump.rpgstats.listeners;

import io.github.deerjump.rpgstats.RPGStats;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class EntityListener implements Listener {
    RPGStats plugin;

    public EntityListener(RPGStats plugin){
        this.plugin = plugin;
    }

    @EventHandler public void onSpawn(EntitySpawnEvent event){
        if(!(event.getEntity() instanceof LivingEntity)) return;
        LivingEntity entity = (LivingEntity) event.getEntity();
        entity.setCustomNameVisible(true);
        Bukkit.getScheduler().runTaskLater(plugin, bukkitTask -> {
            double health = Math.round(entity.getHealth()*100D)/100D;
            entity.setCustomName(ChatColor.RED + "Health: " + ChatColor.WHITE + health);
        }, 1);
    }

    @EventHandler public void onDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Player) return;
        LivingEntity entity = (LivingEntity) event.getEntity();
        entity.setCustomNameVisible(true);
        Bukkit.getScheduler().runTaskLater(plugin, bukkitTask -> {
            double health = Math.round(entity.getHealth()*100D)/100D;
            entity.setCustomName(ChatColor.RED + "Health: " + ChatColor.WHITE + health);
        }, 1);
    }
}
