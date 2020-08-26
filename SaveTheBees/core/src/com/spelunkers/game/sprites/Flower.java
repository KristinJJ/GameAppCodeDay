package com.spelunkers.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Flower extends Actor {
    private Sprite sprite;
    private Circle center;
    int pollenCount;

    private static final float SCALE = 0.125f;

    public Flower(String imageName, int pollenCount) {
        sprite = new Sprite(new Texture(imageName));
        this.pollenCount = pollenCount;

        double randomNumberA = Math.random();
        double randomNumberB = Math.random();

        sprite.setOrigin((float)(randomNumberA * 1000.0), (float)(randomNumberB * 1000.0));
        sprite.setScale(SCALE);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());

        center = new Circle(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2, sprite.getHeight() / 3.0f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        //do nothing
    }

}


