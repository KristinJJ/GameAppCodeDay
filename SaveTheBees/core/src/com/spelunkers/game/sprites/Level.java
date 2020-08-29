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

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class Level extends Label {

    private String name;
    private int pollenGoal;
    private float pesticideRate;
    private float windSpeed;
    private int number;
    private WindDirection windDir;
    private Sprite sprite;

    private static final AtomicInteger count = new AtomicInteger(0);

    public Level(String name, Skin skin, int pollenGoal, float pesticideRate, float windSpeed, WindDirection windDir) {
        super("", skin);
        number = count.incrementAndGet();
        setText(String.format(Locale.getDefault(), "Level %d: %s", number, name));

        setPosition(30, BeesGame.HEIGHT - 25f);
        sprite = new Sprite(new Texture("scoreboard-singlecell.png"));
        sprite.setSize(300f, 50f);
        sprite.setPosition(0, BeesGame.HEIGHT - sprite.getHeight());

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

    public int getNumber() { return number; }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
        super.draw(batch, parentAlpha);
    }
}
