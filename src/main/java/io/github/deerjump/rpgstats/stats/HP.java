package io.github.deerjump.rpgstats.stats;

import java.util.Timer;
import java.util.TimerTask;

public class HP implements Stat {
    private static final double MAXHP = 100;
    private double value;
    private Timer regenTimer;

    public HP(){
        setValue(MAXHP);
        regenTimer = new Timer();
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setValue(double value) {
        if(value > 0)
            this.value = value > MAXHP ? MAXHP : value;
        else
            this.value = 0;
    }

    public void startRegen(){
        regenTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                setValue(getValue() + MAXHP * 0.05); //probably make an addValue()
            }
        },0,1);
    }

    public void stopRegen(){
        regenTimer.cancel();
    }

}
