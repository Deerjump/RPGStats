package io.github.deerjump.rpgstats.stats;

import org.bukkit.Bukkit;

import java.util.Timer;
import java.util.TimerTask;

public class HP implements Stat {
    private static final double MAXHP = 100;
    private double value;
    private final Timer regenTimer;

    public HP(){
        regenTimer = new Timer();
        setValue(MAXHP);
        startRegen();
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public void setValue(double value) {
        if(value > 0)
            this.value = Math.round(Math.min(value, MAXHP) * 100D) / 100D;
        else
            this.value = 0;
    }

    public void add(double hp){
        this.setValue(this.getValue() + hp);
    }

    public void subtract(double hp){
        this.setValue(this.getValue() - hp);
    }

    public void respawn(){
        this.value = MAXHP;
    }

    public void startRegen(){
        regenTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(value >= MAXHP || value == 0) return;
                setValue(getValue() + MAXHP * 0.001); //probably make an addValue()
            }
        },0,50);
    }

    public void stopRegen(){
        Bukkit.getLogger().info("Stopping regen");
        regenTimer.cancel();
    }

}
