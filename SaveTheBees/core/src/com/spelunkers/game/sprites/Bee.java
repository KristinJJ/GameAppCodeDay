package com.spelunkers.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Represents a bee
 */
public class Bee {
    private Vector3 position;
    private Vector3 velocity;
    private Texture bee;

    private static final int SPEED = 50;

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

    public Bee(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bee = new Texture("worker-bee.png");
    }

    public void update(float delta) {
        position.mulAdd(velocity, delta);
    }

    public void move(int velocityX, int velocityY) {
        velocity.add(SPEED * velocityX, SPEED * velocityY, 0);
    }

    public Texture getTexture() {
        return bee;
    }

    public Vector3 getPosition() {
        return position;
    }

}
