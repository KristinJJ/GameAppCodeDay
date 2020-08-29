package com.spelunkers.game.sprites;

import com.spelunkers.game.screens.PlayScreen.WindDirection;

public class Level {

    private int pollenGoal;
    private double pesticideRate;
    private float windSpeed;
    private WindDirection windDir;


    public Level(int pollenGoal, double pesticideRate, float windSpeed, WindDirection windDir) {
        this.pollenGoal = pollenGoal;
        this.pesticideRate = pesticideRate;
        this.windSpeed = windSpeed;
        this.windDir = windDir;
    }

    public Level(int pollenGoal, double pesticideRate) {
        this(pollenGoal, pesticideRate, 0.0f, WindDirection.WEST);
    }

    public int getPollenGoal() {
        return this.pollenGoal;
    }

    public double getPesticideRate() {
        return this.pesticideRate;
    }

    public float getWindSpeed() {
        return this.windSpeed;
    }

    public WindDirection getWindDir() {
        return windDir;
    }

    public boolean hasWind() { return windSpeed > 0; }
}
