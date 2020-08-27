package com.spelunkers.game.sprites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Scoreboard extends Actor {
    private Sprite sprite;
    private Bee bee;

    public Scoreboard(Bee bee) {
        sprite = new Sprite();
        sprite.setPosition(800,800);

        this.bee = bee;
    }

    @Override
    public void act(float delta) {
        //get bee updates
        
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
