package com.spelunkers.game.sprites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Scoreboard extends Actor {
    private Sprite sprite;

    public Scoreboard() {
        sprite = new Sprite();
        sprite.setPosition(800,800);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
