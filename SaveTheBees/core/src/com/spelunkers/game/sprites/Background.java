package com.spelunkers.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor {
    private Sprite sprite;
    private static final float SCALE = 0.8f;
    //private static final float SCALE = 1.0f;

    public Background() {

        sprite = new Sprite(new Texture("backgroundB.png"));
        sprite.setPosition(-400,-130);
        sprite.setScale(SCALE);

        //sprite = new Sprite(new Texture("backgroundA.png"));
        //sprite.setPosition(0,0);
        //sprite.setScale(SCALE);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
