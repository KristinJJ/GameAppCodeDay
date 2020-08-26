package com.spelunkers.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Flower extends Actor {
    private Sprite sprite;
    private Circle center;
    private int pollenCount;
    private boolean pollinated;

    private static final float SCALE = 0.125f;

    public Flower(String imageName, int pollenCount) {
        sprite = new Sprite(new Texture(imageName));
        this.pollenCount = pollenCount;
        pollinated = true;

        //creating a random point for the flower to be drawn at
        float randomX = (float)(Math.random() * 800.0f);
        float randomY = (float)(Math.random() * 800.0f);
        if(randomX < 200 && randomY > 600) { //avoid beehive
            //randomX = (float)(Math.random() * 800.0f);
            randomY = (float)(Math.random() * 600.0f);
        } else if(randomX > 600 && randomY < 200) { //avoid stream
            randomX = (float)(Math.random() * 600.0f);
            //randomY = (float)(Math.random() * 800.0f);
        }
        sprite.setOrigin(randomX, randomY);

        sprite.setScale(SCALE);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());

        center = new Circle(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2, sprite.getHeight() / 3.0f);
    }

    public int harvestPollen() {
        pollinated = false;
        return pollenCount;
    }

    public int getPollenCount() {
        return pollenCount;
    }

    public boolean isPollinated() {
        return pollinated;
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


