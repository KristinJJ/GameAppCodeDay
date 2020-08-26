package com.spelunkers.game.screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.spelunkers.game.BeesGame;
import com.spelunkers.game.sprites.Bee;
import com.spelunkers.game.sprites.BeeAI;

public class PlayScreen extends ScreenAdapter{
    private BeesGame game;
    private Stage stage;
    private Music music_level;
    Skin skin;

    public PlayScreen(BeesGame game) {
        this.game = game;
    }


    @Override
    public void show() {
        Gdx.gl.glClearColor(0, 0.75f, 0.25f, 1);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Bee bee = new Bee();
        BeeAI beeAI = new BeeAI();
        BeeAI beeAI2 = new BeeAI();
        Flower[] flowerList = new Flower[25];

        for(int flowerNum = 0; flowerNum < flowerList.length; flowerNum++) {
            Flower aFlower;
            double randomNumberA = Math.random();

            String imageName;
            if (randomNumberA < (1.0 / 3.0)) aFlower = new RedFlower();
            else if (randomNumberA < (2.0 / 3.0)) {
                aFlower = new PurpleFlower();
            } else {
                aFlower = new YellowFlower();
            }

            flowerList[flowerNum] = aFlower;
            stage.addActor(flowerList[flowerNum]);
        }

        stage.addActor(bee);
        stage.addActor(beeAI);
        stage.addActor(beeAI2);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        music_level = Gdx.audio.newMusic(Gdx.files.internal("Kevin MacLeod - Happy Bee (Background Gaming Music).mp3"));
        music_level.setVolume((float) 0.2);
        music_level.setLooping(true);
        music_level.play();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        super.hide();
    }

    @Override
    public void dispose() {
        game.dispose();
        music_level.dispose();
        skin.dispose();
    }
}
