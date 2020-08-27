package com.spelunkers.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class Bee extends Actor {
    private int pollenCount;
    private boolean poisonStatus;
    private int health;

    protected Sprite sprite;
    private Circle body;
    private ShapeRenderer shapeRenderer;

    public static final int SPEED = 200;
    private static final float SCALE = 0.25f;

    public Bee() {
        pollenCount = 0;
        poisonStatus = false;
        health = 100;

        setImage("singleBee2.png", SCALE);

        setTouchable(Touchable.enabled);
        shapeRenderer = new ShapeRenderer();

    }

    public void setImage(String internalPicPath, float scale) {
        sprite = new Sprite(new Texture(internalPicPath));
        sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
        sprite.setOrigin(0, 0);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        body = new Circle(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2, sprite.getHeight() * 0.50f);
    }

    public void drawPollen(Flower flower) {
        pollenCount += flower.harvestPollen();
    }

    public void wash() {
        poisonStatus = false;
        pollenCount = 0;
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(), getY());
        body.setPosition(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);
        super.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);

        batch.end();
        /// temporary just to make sure the circle is the right size and position
        /*shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(body.x, body.y, body.radius);
        shapeRenderer.end();*/

        batch.begin();

    }

    @Override
    public void act(float delta) {
        // Update bee new position after motion keys
        // Bee bounces off the walls
        int boost = 1;
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            boost = 3;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (getX() > 0) {
                moveBy(-SPEED * boost * delta, 0);
            } else {
                moveBy(SPEED * boost * delta, 0);

            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (getX() < getStage().getWidth() - sprite.getWidth()) {
                moveBy(SPEED * boost * delta, 0);
            } else {
                moveBy(-SPEED * boost * delta, 0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (getY() < getStage().getHeight() - sprite.getHeight()) {
                moveBy(0, SPEED * boost * delta);
            } else {
                moveBy(0, -SPEED * boost * delta);
            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (getY() > 0) {
                moveBy(0, -SPEED * boost * delta);
            } else {
                moveBy(0, SPEED * boost * delta);
            }
        }
    }

    public Circle getBody() {
        return body;
    }

    public int getPollenCount() {
        return pollenCount;
    }

    public int getHealth() {
        return health;
    }

}
