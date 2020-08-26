package com.spelunkers.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Beehive extends Actor {

    private Sprite sprite;
    private static final float SCALE = 0.50f;

    public Beehive() {
        sprite = new Sprite(new Texture("beehive.png"));
        sprite.setPosition(-200.0f, 400f);
        sprite.setScale(SCALE);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
