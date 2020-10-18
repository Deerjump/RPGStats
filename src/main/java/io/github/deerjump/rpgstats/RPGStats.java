package io.github.deerjump.rpgstats;

import io.github.deerjump.rpgstats.listeners.EntityListener;
import io.github.deerjump.rpgstats.listeners.PlayerListener;
import io.github.deerjump.rpgstats.stats.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RPGStats extends JavaPlugin {
   public static Map<UUID, PlayerStats> playerStats = new HashMap<>();

   @Override
   public void onEnable() {
      Bukkit.getPluginManager().registerEvents(new PlayerListener(playerStats), this);
      Bukkit.getPluginManager().registerEvents(new EntityListener(this), this);
      Bukkit.getOnlinePlayers().forEach(player -> {
         UUID uuid = player.getUniqueId();
         playerStats.put(uuid, new PlayerStats(uuid));
      });
   }

   @Override
   public void onDisable() {
      playerStats.forEach((uuid, playerStats)->{
         playerStats.stopActionBar();

      });
   }
}