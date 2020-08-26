package com.spelunkers.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class Bee extends Actor {
    private Sprite sprite;
    private Circle body;

    public static final int SPEED = 100;
    private static final float SCALE = 0.075f;

    public Bee() {
        sprite = new Sprite(new Texture("worker-bee.png"));
        sprite.setScale(SCALE);
        sprite.setOrigin(0, 0);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        setTouchable(Touchable.enabled);

        body = new Circle(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2, sprite.getHeight() / 1.5f);
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
        // Update bee new position after motion keys
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveBy(-SPEED * delta, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveBy(SPEED * delta, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            moveBy(0, SPEED * delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            moveBy(0, -SPEED * delta);
        }
        super.act(delta);
    }

}
