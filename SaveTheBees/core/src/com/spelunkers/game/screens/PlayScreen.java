package com.spelunkers.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spelunkers.game.BeesGame;
import com.spelunkers.game.sprites.Bee;

public class PlayScreen extends ScreenAdapter {
    private BeesGame game;
    private Bee bee;

    public PlayScreen(BeesGame game) {
        this.game = game;
        bee = new Bee(100, 100);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.LEFT:
                        bee.move(-1, 0);
                        break;
                    case Input.Keys.RIGHT:
                        bee.move(1, 0);
                        break;
                    case Input.Keys.UP:
                        bee.move(0, 1);
                        break;
                    case Input.Keys.DOWN:
                        bee.move(0, -1);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.75f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SpriteBatch batch = game.getSpriteBatch();
        bee.update(delta);
        batch.begin();
        batch.draw(bee.getTexture(), bee.getPosition().x, bee.getPosition().y, Bee.WIDTH, Bee.HEIGHT);
        batch.end();

    }



    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
