package com.spelunkers.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.spelunkers.game.BeesGame;

public class Pollen extends Actor {

    private Sprite pollen1;
    private Sprite pollen2;
    private boolean isFalling;
    private float alpha = 1;

    private static final float SCALE = 0.07f;
    private static final float FALL_RATE = -100f;
    private static final float FADING_RATE = 0.3f;

    public Pollen() {
        pollen1 = new Sprite(new Texture("pollenBall.png"));
        pollen1.setSize(pollen1.getWidth() * SCALE, pollen1.getHeight() * SCALE);

        pollen2 = new Sprite(new Texture("pollenBall2.png"));
        pollen2.setSize(pollen1.getWidth(), pollen1.getHeight());
        setPollen2Position();

        setBounds(pollen1.getX(), pollen1.getY(), pollen1.getWidth(), pollen1.getHeight());
    }

    private void setPollen2Position() {
        pollen2.setPosition(pollen1.getX() + 15, pollen1.getY() + 5);
    }

    public void drop(float delta) {
        isFalling = true;
        act(delta);
    }

    @Override
    protected void positionChanged() {
        pollen1.setPosition(getX(), getY());
        setPollen2Position();
        super.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isVisible()) {
            pollen2.draw(batch, alpha);
            pollen1.draw(batch, alpha);
        }
    }

    @Override
    public void act(float delta) {
        if (isFalling) {
            moveBy(0, FALL_RATE * delta);
            alpha -= FADING_RATE * delta;
        }

        super.act(delta);
    }
}
