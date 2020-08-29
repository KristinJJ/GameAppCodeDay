package com.spelunkers.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.spelunkers.game.BeesGame;
import com.spelunkers.game.screens.PlayScreen.WindDirection;

public class Level extends Label {

    private String name;
    private int pollenGoal;
    private float pesticideRate;
    private float windSpeed;
    private WindDirection windDir;
    private Sprite sprite;

    public Level(String name, Skin skin, int pollenGoal, float pesticideRate, float windSpeed, WindDirection windDir) {
        super("Level: " + name, skin);

        setPosition(550, BeesGame.HEIGHT - 35f);
        sprite = new Sprite(new Texture("scoreboard-singlecell.png"));
        sprite.setSize(230f, 50f);
        sprite.setPosition(530, BeesGame.HEIGHT - sprite.getHeight());

        this.name = name;
        this.pollenGoal = pollenGoal;
        this.pesticideRate = pesticideRate;
        this.windSpeed = windSpeed;
        this.windDir = windDir;
    }

    public Level(String name, Skin skin, int pollenGoal, float pesticideRate) {
        this(name, skin, pollenGoal, pesticideRate, 0.0f, WindDirection.WEST);
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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
        super.draw(batch, parentAlpha);
    }
}
