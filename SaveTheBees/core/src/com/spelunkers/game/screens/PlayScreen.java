package com.spelunkers.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spelunkers.game.BeesGame;
import com.spelunkers.game.sprites.Bee;

public class PlayScreen extends ScreenAdapter {
    private BeesGame game;
    private Bee bee;
    private Music music_level;

    public PlayScreen(BeesGame game) {
        this.game = game;
        bee = new Bee(100, 100);

    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0, 0.75f, 0.25f, 1);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                // Implement key presses
                return true;
            }
        });
    }

    public void update() {
        // Update bee new position after motion keys
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            bee.move(Bee.Direction.LEFT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            bee.move(Bee.Direction.RIGHT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            bee.move(Bee.Direction.UP);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            bee.move(Bee.Direction.DOWN);
        }


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //Clear screen before drawing
        SpriteBatch batch = game.getSpriteBatch(); //get sprite batch from game
        music_level = Gdx.audio.newMusic(Gdx.files.internal("Kevin MacLeod - Happy Bee (Background Gaming Music).mp3"));
        music_level.setLooping(true);
        music_level.play();

        update(); // Update sprite's location
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
        game.dispose();
        bee.dispose();
    }
}
