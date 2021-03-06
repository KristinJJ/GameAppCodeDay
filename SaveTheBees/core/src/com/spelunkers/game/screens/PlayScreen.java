package com.spelunkers.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.spelunkers.game.BeesGame;
import com.spelunkers.game.sprites.Background;
import com.spelunkers.game.sprites.Bee;
import com.spelunkers.game.sprites.BeeAI;
import com.spelunkers.game.sprites.Flower;
import com.spelunkers.game.sprites.Level;
import com.spelunkers.game.sprites.PurpleFlower;
import com.spelunkers.game.sprites.RedFlower;
import com.spelunkers.game.sprites.Scoreboard;
import com.spelunkers.game.sprites.Timer;
import com.spelunkers.game.sprites.Water;
import com.spelunkers.game.sprites.YellowFlower;
import com.spelunkers.game.sprites.Beehive;

import java.util.Random;

public class PlayScreen extends ScreenAdapter{
    private BeesGame game;
    private Stage stage;
    private Music music_level;
    private Bee bee;
    private Flower[] flowerList;
    private Water stream;
    private Beehive beehive;
    private Scoreboard scoreboard;
    private Timer timer;
    private Skin skin;
    private Skin lightSkin;
    private Level level;


    private static final Random RANDOMIZER = new Random();
    private static final float DEFAULT_PLAY_TIME = 30f;
    public enum WindDirection {EAST, WEST, NORTH, SOUTH, RANDOM}

    public PlayScreen(BeesGame game, Level level) {
        this.game = game;
        this.level = level;

        skin = new Skin(Gdx.files.internal("darkSkin/cloud-form-ui.json"));
        lightSkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        music_level = Gdx.audio.newMusic(Gdx.files.internal("Kevin MacLeod - Happy Bee (Background Gaming Music).mp3"));
        music_level.setVolume((float) 0.2);
        music_level.setLooping(true);
        music_level.play();
    }

    @Override
    public void show() {
        Gdx.gl.glClearColor(0, 0.75f, 0.25f, 1);

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        //this needs to be the first thing added
        Background background = new Background();
        stage.addActor(background); //set it and forget it

        bee = new Bee();
        bee.setPesticideRate(level.getPesticideRate());

        Group aiBees = new Group();
        aiBees.setName("aiBees");
        aiBees.addActor(new BeeAI());
        aiBees.addActor(new BeeAI());

        beehive = new Beehive();
        stream = new Water();
        scoreboard = new Scoreboard(bee, beehive, level, skin);

        flowerList = new Flower[15];

        for(int flowerNum = 0; flowerNum < flowerList.length; flowerNum++) {
            Flower aFlower;
            double randomNumberA = Math.random();

            if (randomNumberA < (1.0 / 3.0)) {
                aFlower = new RedFlower();
            } else if (randomNumberA < (2.0 / 3.0)) {
                aFlower = new PurpleFlower();
            } else {
                aFlower = new YellowFlower();
            }

            flowerList[flowerNum] = aFlower;
            stage.addActor(flowerList[flowerNum]);
        }

        //Water sign label
        Texture signBackground = new Texture("watersign.png");
        Image signBox = new Image();
        signBox.setSize(230f, 50f);
        signBox.setPosition(700f, BeesGame.HEIGHT / 4f);
        signBox.setDrawable(new TextureRegionDrawable(new TextureRegion(signBackground)));
        Label signLbl = new Label("Wash your Bee Here !", lightSkin);
        signLbl.setPosition(720, BeesGame.HEIGHT / 4f + 15);

        // Starting the timer
        timer = new Timer(skin);
        timer.setMaxTime(DEFAULT_PLAY_TIME);

        stage.addActor(beehive);
        stage.addActor(stream);
        stage.addActor(scoreboard);
        stage.addActor(timer);

        stage.addActor(level);
        //stage.addActor(pollenProgress);
        stage.addActor(signBox);
        stage.addActor(signLbl);

        stage.addActor(aiBees);

        //make sure this is last so player is top layer
        stage.addActor(bee);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());

        if (level.hasWind()) {
            switch (level.getWindDir()) {
                case EAST:
                    bee.moveBy(-level.getWindSpeed() * delta, 0);
                    break;
                case WEST:
                    bee.moveBy(level.getWindSpeed() * delta, 0);
                    break;
                case SOUTH:
                    bee.moveBy(0, -level.getWindSpeed() * delta);
                    break;
                case NORTH:
                    bee.moveBy(0, level.getWindSpeed() * delta);
                    break;
                case RANDOM:
                    int xDir = RANDOMIZER.nextInt(3) - 1;
                    int yDir = RANDOMIZER.nextInt(3) - 1;
                    bee.moveBy(xDir * level.getWindSpeed() * delta, yDir * level.getWindSpeed() * delta);
            }
        }

        // Checking if time is over or bee is dead
        if (timer.timeOver() || bee.getHealth() <= 0) {
            goToEndScreen();
        }

        // boing when playerbee hits aiBee
        Group aiBees = (Group)stage.getRoot().findActor("aiBees");
        for (Actor actor : aiBees.getChildren()) {
            Bee ai = (Bee)actor;
            if (bee.getBody().overlaps(ai.getBody())) {
                bee.moveBy(Bee.SPEED * -bee.getXdir() * delta * 10f, Bee.SPEED * -bee.getYdir() * delta * 10f);
                bee.emptyPockets(); // Bee looses its pollen :-(
                ai.moveBy(Bee.SPEED * bee.getXdir() * delta * 10f, Bee.SPEED * bee.getYdir() * delta * 10f);
            }
        }

        // wash in the water
        if (bee.getBody().overlaps(stream.getBody())){
            bee.wash();
        }

        //pollinate from flowers
        for (Flower aFlower : flowerList) {
            if(bee.getBody().overlaps(aFlower.getBody())) {
                bee.drawPollen(aFlower);
                if(aFlower.isPoisoned()) {
                    bee.becomePoisoned();
                }
            }
        }

        // deposit pollen at beehive
        if (bee.getBody().overlaps(beehive.getBody())) {
            bee.depositPollen(beehive);
        }

        stage.draw();
    }

    // Call this whenever you want to switch to the EndScreen
    void goToEndScreen() {
        game.setScreen(new EndScreen(game, level, scoreboard));
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        music_level.stop();
        super.hide();
    }

    @Override
    public void dispose() {
        game.dispose();
        music_level.dispose();
        skin.dispose();
    }
}
