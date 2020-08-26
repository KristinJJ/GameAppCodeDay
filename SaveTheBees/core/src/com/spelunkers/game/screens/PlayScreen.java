package com.spelunkers.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.spelunkers.game.BeesGame;
import com.spelunkers.game.sprites.Bee;

public class PlayScreen extends ScreenAdapter {
    private BeesGame game;
    private Stage stage;

    public PlayScreen(BeesGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0, 0.75f, 0.25f, 1);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Bee bee = new Bee();
        stage.addActor(bee);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }



    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        super.hide();
    }

    @Override
    public void dispose() {
        game.dispose();
    }
}
