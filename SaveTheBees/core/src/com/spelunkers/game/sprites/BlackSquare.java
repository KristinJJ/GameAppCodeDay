package com.spelunkers.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class BlackSquare extends Actor {
    Sprite sprite;

    public BlackSquare(float xOrigin, float yOrigin, float width, float height) {
        sprite = new Sprite(new Texture("blacksquare.png"));
        sprite.setBounds(xOrigin, yOrigin, width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
