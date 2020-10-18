package io.github.deerjump.rpgstats.stats;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.util.Timer;
import java.util.TimerTask;

public class Mana implements Stat {
    private static final double MAXMANA = 100;
    private double value;
    private Timer regenTimer;

    public Mana(){
        regenTimer = new Timer();
        setValue(MAXMANA);
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public void setValue(double value) {
        if(value > 0)
            this.value = value > MAXMANA ? MAXMANA: value;
        else
            this.value = 0;
    }

    public void startRegen(){
        regenTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                setValue(getValue() + MAXMANA * 0.05); //probably make an addValue()
            }
        },0,1);
    }

    public void stopRegen(){
        regenTimer.cancel();
    }
}
