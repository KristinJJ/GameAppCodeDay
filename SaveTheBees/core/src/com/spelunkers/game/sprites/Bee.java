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

    private Sprite sprite;
    private Circle body;
    private ShapeRenderer shapeRenderer;

    public static final int SPEED = 500;
    private static final float SCALE = 0.075f;

    public Bee() {
        pollenCount = 0;
        poisonStatus = false;
        health = 100;

        sprite = new Sprite(new Texture("worker-bee.png"));
        sprite.setSize(sprite.getWidth() * SCALE, sprite.getHeight() * SCALE);
        sprite.setOrigin(0, 0);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);

        shapeRenderer = new ShapeRenderer();
        body = new Circle(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2, sprite.getHeight() * 0.25f);
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
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(body.x, body.y, body.radius);
        shapeRenderer.end();

        batch.begin();

    }

    @Override
    public void act(float delta) {
        // Update bee new position after motion keys
        // Bee bounces off the walls
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (getX() > 0) {
                moveBy(-SPEED * delta, 0);
            } else {
                moveBy(SPEED * delta, 0);

            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (getX() < getStage().getWidth() - sprite.getWidth()) {
                moveBy(SPEED * delta, 0);
            } else {
                moveBy(-SPEED * delta, 0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (getY() < getStage().getHeight() - sprite.getHeight()) {
                moveBy(0, SPEED * delta);
            } else {
                moveBy(0, -SPEED * delta);
            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (getY() > 0) {
                moveBy(0, -SPEED * delta);
            } else {
                moveBy(0, SPEED * delta);
            }
        }

        super.act(delta);
    }

    public Circle getBody() {
        return body;
    }
}
