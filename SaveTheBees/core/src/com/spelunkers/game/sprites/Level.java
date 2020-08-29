package com.spelunkers.game.sprites;

import com.spelunkers.game.screens.PlayScreen.WindDirection;

public class Level {

    private String name;
    private int pollenGoal;
    private float pesticideRate;
    private float windSpeed;
    private WindDirection windDir;


    public Level(String name, int pollenGoal, float pesticideRate, float windSpeed, WindDirection windDir) {
        this.name = name;
        this.pollenGoal = pollenGoal;
        this.pesticideRate = pesticideRate;
        this.windSpeed = windSpeed;
        this.windDir = windDir;
    }

    public Level(String name, int pollenGoal, float pesticideRate) {
        this(name, pollenGoal, pesticideRate, 0.0f, WindDirection.WEST);
    }

    public String getName() {
        return name;
    }

    public int getPollenGoal() {
        return this.pollenGoal;
    }

    public float getPesticideRate() {
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
