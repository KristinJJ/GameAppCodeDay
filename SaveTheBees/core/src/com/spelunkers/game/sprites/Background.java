package com.spelunkers.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.spelunkers.game.BeesGame;

public class Background extends Actor {
    private Sprite sprite;
    private static final float SCALE = 0.8f;
    //private static final float SCALE = 1.0f;

    public Background() {

        sprite = new Sprite(new Texture("backgroundB.png"));
        sprite.setPosition(0,0);
        sprite.setSize(BeesGame.WIDTH, BeesGame.HEIGHT);

        //sprite = new Sprite(new Texture("backgroundA.png"));
        //sprite.setPosition(0,0);
        //sprite.setScale(SCALE);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

}
