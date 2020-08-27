package com.spelunkers.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Scoreboard extends Actor {
    private Sprite sprite;
    private Bee bee;
    private Beehive beehive;

    private final float SCALE = 0.25f;

    public Scoreboard(Bee bee) {
        sprite = new Sprite(new Texture("scoreboard.png"));
        sprite.setPosition(400,500);
        sprite.setScale(SCALE);

        this.bee = bee;
    }

    public void update() {
        //gets data from bee & beehive, changes what is displayed
        //bee.getPollenCount();
        //beehive.getPollenCount();
        //bee.getHealth();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
