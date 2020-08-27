package com.spelunkers.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

import java.util.Random;

public class BeeAI extends Bee {
    private float addDelta;

    private static final float INTERVAL = 0.5f;
    private static final Random RANDOM = new Random();

    public BeeAI() {
        //super("yellowBee.png");
        setImage("yellowBee.png", 0.15f);
        setPosition(200, 200);
    }

    @Override
    public void act(float delta) {
        int speedX = 0;
        int speedY = 0;
        if (addDelta > INTERVAL) {
            int xMotion = RANDOM.nextInt(3);
            switch(xMotion) {
                case (0):
                    speedX = 0;
                    break;
                case (1):
                    speedX = 700;
                    break;
                case (2):
                    speedX = -700;
                    break;
            }

            int yMotion = RANDOM.nextInt(3);
            switch(yMotion) {
                case (0):
                    speedY = 0;
                    break;
                case (1):
                    speedY = 700;
                    break;
                case (2):
                    speedY = -700;
                    break;
            }
            addDelta = 0;
        } else {
            addDelta += delta;
        }

        if (speedX < 0 && getX() <= 0) {
            moveBy(-speedX * delta, 0);
        }

        if (speedY < 0 && getY() <= 0) {
            moveBy(0, -speedY * delta);
        }

        if (speedX > 0 && getX() > getStage().getWidth() - sprite.getWidth()) {
            moveBy(-speedX * delta, 0);
        }

        if (speedY > 0 && getY() > getStage().getHeight() - sprite.getHeight()) {
            moveBy(0, -speedY * delta);
        }

        moveBy(speedX * delta, speedY * delta);
    }


}
