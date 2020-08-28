package com.spelunkers.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.spelunkers.game.BeesGame;

import java.util.Locale;

public class Level {

    private int beePollenGoal;
    private double pesticideRate;
    private double wind;

    public Level(int beePollenGoal, double pesticideRate, double wind) {
        this.beePollenGoal = beePollenGoal;
        this.pesticideRate = pesticideRate;
        this.wind = wind;

    }
}
