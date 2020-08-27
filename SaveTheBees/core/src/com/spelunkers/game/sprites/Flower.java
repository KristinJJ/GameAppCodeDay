package com.spelunkers.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Flower extends Actor {
    private Sprite flowerSprite;
    private Sprite centerSprite;
    private int pollenCount;
    private boolean pollinated;

    private static final float SCALE = 0.125f;
    private static final float POLLENSCALE = .5f;

    public Flower(String flowerImageName, int pollenCount, String pollenImageName) {
        flowerSprite = new Sprite(new Texture(flowerImageName));
        this.pollenCount = pollenCount;
        pollinated = true;

        //creating a random point for the flower to be drawn at
        float randomX = (float)(Math.random() * 800.0f);
        float randomY = (float)(Math.random() * 800.0f);
        if(randomX < 200 && randomY > 600) { //avoid beehive
            randomY = (float)(Math.random() * 600.0f);
        } else if(randomX > 600 && randomY < 200) { //avoid stream
            randomX = (float)(Math.random() * 600.0f);
        }
        flowerSprite.setOrigin(randomX, randomY);

        flowerSprite.setScale(SCALE);
        setBounds(flowerSprite.getX(), flowerSprite.getY(), flowerSprite.getWidth(), flowerSprite.getHeight());

        centerSprite = new Sprite(new Texture("aPollen.png"));
        centerSprite.setOrigin(flowerSprite.getX() + flowerSprite.getWidth() / 2.0f, flowerSprite.getY() + flowerSprite.getHeight() / 2.0f);
        centerSprite.setScale(POLLENSCALE);
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
        flowerSprite.draw(batch);
        centerSprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        //do nothing
    }

}


