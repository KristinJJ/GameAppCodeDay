package com.spelunkers.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Flower extends Actor {
    private Sprite sprite;
    //private Circle body;

    private static final float SCALE = 0.125f;

    public Flower(String imageName) {

        sprite = new Sprite(new Texture(imageName));

        double randomNumberA = Math.random();
        double randomNumberB = Math.random();

        sprite.setOrigin((float)(randomNumberA * 1000.0), (float)(randomNumberB * 1000.0));
        sprite.setScale(SCALE);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());

        //body = new Circle(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2, sprite.getHeight() / 1.5f);
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(), getY());
        super.positionChanged();
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


