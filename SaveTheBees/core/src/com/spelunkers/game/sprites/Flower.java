package com.spelunkers.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Random;

public abstract class Flower extends Actor {
    private Sprite flowerSprite;
    private Sprite centerSprite;
    private int pollenCount;
    private boolean pollinated;
    private boolean poisoned;
    private float timeToPoison;
    private float timeSinceEmptied;
    private float timeSinceLastPoisoned;

    private static final float SCALE = 0.125f;
    private static final float POLLEN_SCALE = .12f; //.20f;
    private static final float POLLINATION_TIME = 2f;
    private static final Random RANDOM = new Random();

    public Flower(String flowerImageName, int pollenCount, String pollenImageName) {
        timeToPoison = 5f;

        pollinated = true;
        poisoned = false;
        flowerSprite = new Sprite(new Texture(flowerImageName));
        this.pollenCount = pollenCount;

        //creating a random point for the flower to be drawn at
        float randomX = (float)(Math.random() * 800.0f);
        float randomY = (float)(Math.random() * 800.0f);
        if(randomX < 200 && randomY > 600) { //avoid beehive
            randomY = (float)(Math.random() * 600.0f);
        } else if(randomX > 600 && randomY < 200) { //avoid stream
            randomX = (float)(Math.random() * 600.0f);
        }
        flowerSprite.setOrigin(randomX, randomY);

        setBounds(flowerSprite.getX(), flowerSprite.getY(), flowerSprite.getWidth(), flowerSprite.getHeight());
        flowerSprite.setSize(flowerSprite.getWidth() * SCALE, flowerSprite.getHeight() * SCALE);
        flowerSprite.setPosition(randomX, randomY);

        centerSprite = new Sprite(new Texture(pollenImageName));
        centerSprite.setPosition(randomX + flowerSprite.getWidth() / 2.5f, randomY + flowerSprite.getHeight() / 2.5f);
        centerSprite.setSize(centerSprite.getWidth() * POLLEN_SCALE, centerSprite.getHeight() * POLLEN_SCALE);

    }

    public int harvestPollen() {
        if (!pollinated) {
            return 0;
        } else {
            pollinated = false;
            timeSinceEmptied = 0;
            return pollenCount;
        }
    }

    public int getPollenCount() {
        return pollenCount;
    }

    public boolean isPollinated() {
        return pollinated;
    }

    public Circle getBody() {
        return new Circle(centerSprite.getX(), centerSprite.getY(), 5);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        flowerSprite.draw(batch, poisoned ? 0.5f : 1f);
        if(pollinated) {
            centerSprite.draw(batch);
        }
    }

    @Override
    public void act(float delta) {
        if (!pollinated) {
            if (timeSinceEmptied > POLLINATION_TIME * pollenCount) {
                pollinated = true;
            } else {
                timeSinceEmptied += delta;
            }
        }

        if (timeSinceLastPoisoned > timeToPoison) {
            int randomInt = RANDOM.nextInt(10);
            poisoned = randomInt < 2;
            timeSinceLastPoisoned = 0;
        } else {
            timeSinceLastPoisoned += delta;
        }
    }

}



