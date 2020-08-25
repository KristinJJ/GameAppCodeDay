package com.spelunkers.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spelunkers.game.BeesGame;

public class MenuScreen extends ScreenAdapter {
    private BeesGame game;
    private Texture background;
    private Texture playBtn;

    public MenuScreen(BeesGame game) {
        this.game = game;
        background = new Texture("background.png");
        playBtn = new Texture("worker-bee.png");
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.ENTER) {
                    game.setScreen(new PlayScreen(game));
                }
                return true;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                game.setScreen(new PlayScreen(game));
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.75f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SpriteBatch batch = game.getSpriteBatch();
        batch.begin();
        batch.draw(background, 0, 0, BeesGame.WIDTH, BeesGame.HEIGHT);
        batch.draw(playBtn, (BeesGame.WIDTH / 2) - 25, BeesGame.HEIGHT / 2, 50, 50);
        batch.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        game.dispose();
    }
}
