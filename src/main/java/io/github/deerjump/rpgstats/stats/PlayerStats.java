package io.github.deerjump.rpgstats.stats;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;


public class PlayerStats {
    private final Defense defense;
    private final Mana mana;
    private final HP hp;
    private Timer timer;

    public PlayerStats(UUID uuid){
        defense = new Defense();
        mana = new Mana();
        hp = new HP();
        startActionBar(uuid);
    }

    public Defense getDefense() {
        return defense;
    }

    public HP getHp() {
        return hp;
    }

    public Mana getMana() {
        return mana;
    }

    public void startActionBar(UUID uuid){
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Player player = Bukkit.getPlayer(uuid);
                if(player == null) return;
                TextComponent message = new TextComponent(
                        ChatColor.RED + "Health: " + hp.getValue() +
                        ChatColor.GREEN + "  Defense: " + defense.getValue() +
                        ChatColor.BLUE + "  Mana: " + mana.getValue());
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, message);
            }
        }, 0,50);
    }

    public void stopActionBar(){
        timer.cancel();
        timer = null;
        hp.stopRegen();
        mana.stopRegen();
    }
}
