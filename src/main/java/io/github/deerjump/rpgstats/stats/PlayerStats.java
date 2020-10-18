package io.github.deerjump.rpgstats.stats;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerStats {
    public static Map<UUID,PlayerStats> playerStats = new HashMap<>();
    private Defense defense;
    private Mana mana;
    private HP hp;

    public PlayerStats(UUID uuid){
        defense = new Defense();
        mana = new Mana();
        hp = new HP();
        playerStats.put(uuid, this);
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

}
