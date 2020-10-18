package io.github.deerjump.rpgstats.stats;

public class Defense implements Stat{
    private double value = 0;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double calculateReduction(double damage){
        return damage * 100 /(100 + value);
    }
}
