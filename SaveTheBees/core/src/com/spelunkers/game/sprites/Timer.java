package com.spelunkers.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.spelunkers.game.BeesGame;

public class Timer extends Label {
    private float timePassed;
    private float maxTime;
    private Sprite sprite;

    private static float DEFAULT_MAX_TIME = 10;

    public Timer(Skin skin) {
        super("Remaining Time:", skin);
        sprite = new Sprite(new Texture("scoreboard-singlecell.png"));
        maxTime = DEFAULT_MAX_TIME;
        setPosition(20,BeesGame.HEIGHT - 35f);
        sprite.setSize(230f, 50f);
        sprite.setPosition(0, BeesGame.HEIGHT - sprite.getHeight());
    }

    public void setMaxTime(float maxTime) {
        this.maxTime = maxTime;
    }

    public boolean timeOver() {
        return (maxTime - timePassed) < (0.0001f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setText(String.format("Remaining Time: %.2f", maxTime - timePassed));
        timePassed += delta;
    }
}
