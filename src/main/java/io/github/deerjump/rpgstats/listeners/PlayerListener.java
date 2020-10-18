package io.github.deerjump.rpgstats.listeners;

import io.github.deerjump.rpgstats.stats.HP;
import io.github.deerjump.rpgstats.stats.PlayerStats;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Map;
import java.util.UUID;

public class PlayerListener implements Listener {
    Map<UUID, PlayerStats> playerStatMap;
    public PlayerListener(Map<UUID, PlayerStats> map){
        playerStatMap = map;
    }

    @EventHandler public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        PlayerStats playerStats = playerStatMap.get(uuid);
        if(playerStats != null)
            playerStats.startActionBar(uuid);
    }

    @EventHandler public void onQuit(PlayerQuitEvent event){
        Player player= event.getPlayer();
        PlayerStats playerStats = playerStatMap.get(player.getUniqueId());
        playerStats.stopActionBar();

    }

    @EventHandler public void onDamage(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        PlayerStats playerStats = playerStatMap.get(player.getUniqueId());
        HP hp = playerStats.getHp();
        hp.subtract(event.getFinalDamage());

        event.setDamage(0);
        if(hp.getValue() == 0) {
            player.setHealth(0);
            event.setCancelled(true);
        }

    }

    @EventHandler public void onDeath(PlayerRespawnEvent event){
        Player player = event.getPlayer();
        PlayerStats playerStats = playerStatMap.get(player.getUniqueId());
        if(playerStats != null){
            playerStats.getHp().respawn();
        }
    }
}
