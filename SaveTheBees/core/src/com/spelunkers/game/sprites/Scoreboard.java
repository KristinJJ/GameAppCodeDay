package com.spelunkers.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Scoreboard extends Actor {
    private Sprite sprite;
    private Bee bee;
    private Beehive beehive;

    private Label beehivePollenCount;
    private Label beePollenCount;
    private Label beeHealth;

    private final float SCALE = 0.25f;

    public Scoreboard(Bee bee, Beehive beehive) {
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));


        sprite = new Sprite(new Texture("scoreboard.png"));
        sprite.setPosition(400, 500);
        sprite.setScale(SCALE);

        this.bee = bee;
        this.beehive = beehive;

        beehivePollenCount = new Label(String.valueOf(beehive.getPollenCount()), skin);
        beehivePollenCount.setPosition(sprite.getX() + 255, sprite.getY() + 265);

        beePollenCount = new Label(String.valueOf(bee.getPollenCount()), skin);
        beePollenCount.setPosition(sprite.getX() + 255, sprite.getY() + 230);

        beeHealth = new Label(String.valueOf(bee.getHealth()), skin);
        beeHealth.setPosition(sprite.getX() + 255, sprite.getY() + 190);
    }

    public void update() {
        //gets data from bee & beehive, changes what is displayed
        beehivePollenCount.setText(String.valueOf(beehive.getPollenCount()));

        beePollenCount.setText(String.valueOf(bee.getPollenCount()));

        beeHealth.setText(String.valueOf(bee.getHealth()));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
        beehivePollenCount.draw(batch, parentAlpha);
        beePollenCount.draw(batch, parentAlpha);
        beeHealth.draw(batch, parentAlpha);
    }
}
