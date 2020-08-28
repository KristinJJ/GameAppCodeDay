package com.spelunkers.game.sprites;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.spelunkers.game.BeesGame;

public class Water extends Actor {

    private Sprite sprite;
    private static final float SCALE = 0.50f;

    public Water() {
        sprite = new Sprite(new Texture("stream.png"));
        sprite.setSize(sprite.getWidth() * SCALE, sprite.getHeight() * SCALE);
        sprite.setPosition(BeesGame.WIDTH - sprite.getWidth(), 0f);
        //sprite.setScale(SCALE);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public Circle getBody() {
        return new Circle(sprite.getX() + sprite.getWidth() * 0.35f,
                          sprite.getY() + sprite.getHeight() * 0.35f,
                      sprite.getHeight() * 0.4f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }
}
