package com.spelunkers.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.spelunkers.game.BeesGame;

public class Timer extends Label {
    private float timePassed;
    private float maxTime;

    private static float DEFAULT_MAX_TIME = 10;

    public Timer() {
        super("Remaining Time:", new Skin(Gdx.files.internal("skin/glassy-ui.json")));

        maxTime = DEFAULT_MAX_TIME;
        setPosition(0,BeesGame.HEIGHT - 50);
    }

    public void setMaxTime(float maxTime) {
        this.maxTime = maxTime;
    }

    public boolean timeOver() {
        return (maxTime - timePassed) < (0.0001f);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setText(String.format("Remaining Time: %.2f", maxTime - timePassed));
        timePassed += delta;
    }
}
