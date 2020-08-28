package com.spelunkers.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.spelunkers.game.BeesGame;

import java.util.Locale;

public class Scoreboard extends Actor {
    private Sprite sprite;
    private Bee bee;
    private Beehive beehive;

    private Label beehivePollenCount;
    private Label beePollenCount;
    private Label beeHealth;

    private final float SCALE = 0.25f;

    private static final String BEEHIVE_PC_LABEL = "Beehive Pollen: %d";
    private static final String BEE_PC_LABEL = "Bee Pollen: %d";
    private static final String BEE_HEALTH_LABEL = "Bee Health: %d";

    public Scoreboard(Bee bee, Beehive beehive, Skin skin) {

        sprite = new Sprite(new Texture("scoreboard.png"));
        sprite.setSize(sprite.getWidth() * SCALE + 50, sprite.getHeight() * SCALE);
        sprite.setPosition(BeesGame.WIDTH - sprite.getWidth(), BeesGame.HEIGHT - sprite.getHeight());
        //sprite.setScale(SCALE);

        this.bee = bee;
        this.beehive = beehive;

        beehivePollenCount = new Label(String.format(Locale.getDefault(), BEEHIVE_PC_LABEL, beehive.getPollenCount()), skin);
        beehivePollenCount.setPosition(sprite.getX() + 15, sprite.getY() + 85);

        beePollenCount = new Label(String.format(Locale.getDefault(), BEE_PC_LABEL, bee.getPollenCount()), skin);
        beePollenCount.setPosition(sprite.getX() + 15, sprite.getY() + 50);

        beeHealth = new Label(String.format(Locale.getDefault(), BEE_HEALTH_LABEL, bee.getHealth()), skin);
        beeHealth.setPosition(sprite.getX() + 15, sprite.getY() + 15);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
        beehivePollenCount.draw(batch, parentAlpha);
        beePollenCount.draw(batch, parentAlpha);
        beeHealth.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        beehivePollenCount.setText(String.format(Locale.getDefault(), BEEHIVE_PC_LABEL, beehive.getPollenCount()));
        beePollenCount.setText(String.format(Locale.getDefault(), BEE_PC_LABEL, bee.getPollenCount()));
        beeHealth.setText(String.format(Locale.getDefault(), BEE_HEALTH_LABEL, bee.getHealth()));
        super.act(delta);
    }
}
