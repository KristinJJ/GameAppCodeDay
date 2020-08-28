package com.spelunkers.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.spelunkers.game.BeesGame;
import com.spelunkers.game.sprites.Background;
import com.spelunkers.game.sprites.Bee;
import com.spelunkers.game.sprites.BeeAI;
import com.spelunkers.game.sprites.Flower;
import com.spelunkers.game.sprites.PurpleFlower;
import com.spelunkers.game.sprites.RedFlower;
import com.spelunkers.game.sprites.Scoreboard;
import com.spelunkers.game.sprites.Timer;
import com.spelunkers.game.sprites.Water;
import com.spelunkers.game.sprites.YellowFlower;
import com.spelunkers.game.sprites.Beehive;

import java.util.Locale;

public class PlayScreen extends ScreenAdapter{
    private BeesGame game;
    private Stage stage;
    private Music music_level;
    private Bee bee;
    private Flower[] flowerList;
    private Water stream;
    private Beehive beehive;
    private Scoreboard scoreboard;
    private int playerPollenCount;
    private int playerPesticideCount;
    private int playerHP;
    private Timer timer;
    private Skin skin;
    private int pollenGoal = 10;

    public PlayScreen(BeesGame game) {
        this.game = game;
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        music_level = Gdx.audio.newMusic(Gdx.files.internal("Kevin MacLeod - Happy Bee (Background Gaming Music).mp3"));
        music_level.setVolume((float) 0.2);
        music_level.setLooping(true);
        music_level.play();
    }

    // Call this whenever you want to save the playerPollenCount
    void savePlayerPollenCount(int playerPollenCount) {
        this.playerPollenCount = playerPollenCount;
    }

    // Call this whenever you want to save the playerPesticideCount
    void savePlayerPesticideCount(int playerPesticideCount) {
        this.playerPesticideCount = playerPesticideCount;
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

        Group aiBees = new Group();
        aiBees.setName("aiBees");
        aiBees.addActor(new BeeAI());
        aiBees.addActor(new BeeAI());

        beehive = new Beehive();
        stream = new Water();
        scoreboard = new Scoreboard(bee, beehive, skin);

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

        //Pollen goal label
        Texture lblBackground = new Texture("scoreboard-singlecell.png");
        Image lblBox = new Image();
        lblBox.setSize(200f, 50f);
        lblBox.setPosition(300f, BeesGame.HEIGHT - lblBox.getHeight());
        lblBox.setDrawable(new TextureRegionDrawable(new TextureRegion(lblBackground)));
        Label pollenGoalLbl = new Label(String.format(Locale.getDefault(), "Pollen Goal: %d", pollenGoal), skin);
        pollenGoalLbl.setPosition(320, BeesGame.HEIGHT - lblBox.getHeight() + 15);

        // Starting the timer
        timer = new Timer(skin);
        timer.setMaxTime(20f);

         stage.addActor(beehive);
        stage.addActor(stream);
        stage.addActor(scoreboard);
        stage.addActor(timer);

        stage.addActor(lblBox);
        stage.addActor(pollenGoalLbl);

        stage.addActor(aiBees);

        //make sure this is last so player is top layer
        stage.addActor(bee);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());

        // Checking if time is over
        if (timer.timeOver()) {
            game.setScreen(new EndScreen(game, bee.getPollenCount(), 0, bee.getHealth()));
        }

        //boing when playerbee hits aiBee
        Group aiBees = (Group)stage.getRoot().findActor("aiBees");
        for (Actor actor : aiBees.getChildren()) {
            Bee ai = (Bee)actor;
            if (bee.getBody().overlaps(ai.getBody())) {
                bee.moveBy(Bee.SPEED * -bee.getXdir() * delta * 10f, Bee.SPEED * -bee.getYdir() * delta * 10f);
                bee.emptyPockets(); // Bee looses its pollen :-(
                ai.moveBy(Bee.SPEED * bee.getXdir() * delta * 10f, Bee.SPEED * bee.getYdir() * delta * 10f);
            }
        }

        //wash in the water
        if(bee.getBody().overlaps(stream.getBody())){
            bee.wash();
        }

        //pollinate from flowers
        for(Flower aFlower : flowerList) {
            if(bee.getBody().overlaps(aFlower.getBody())) {
                bee.drawPollen(aFlower);
            }
        }

        //deposit pollen at beehive
        if(bee.getBody().overlaps(beehive.getBody())) {
            bee.depositPollen(beehive);
        }

        stage.draw();
    }

    // Call this whenever you want to save the playerHP
    void savePlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    // Call this whenever you want to switch to the EndScreen
    void goToEndScreen() {
        game.setScreen(new EndScreen(game, playerPollenCount, playerPesticideCount, playerHP));
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
