package com.spelunkers.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.spelunkers.game.BeesGame;
import com.spelunkers.game.sprites.Background;
import com.spelunkers.game.sprites.Bee;
import com.spelunkers.game.sprites.BeeAI;
import com.spelunkers.game.sprites.Flower;
import com.spelunkers.game.sprites.PurpleFlower;
import com.spelunkers.game.sprites.RedFlower;
import com.spelunkers.game.sprites.Scoreboard;
import com.spelunkers.game.sprites.Water;
import com.spelunkers.game.sprites.YellowFlower;
import com.spelunkers.game.sprites.Beehive;

public class PlayScreen extends ScreenAdapter{
    private BeesGame game;
    private Stage stage;
    private Music music_level;
    private Bee bee;
    private Flower[] flowerList;
    private Water stream;
    private int playerPollenCount;
    private int playerPesticideCount;
    private int playerHP;
    private int computerPollenCount;
    private int computerPesticideCount;
    private int computerHP;
    Skin skin;

    public PlayScreen(BeesGame game) {
        this.game = game;
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

        Background background = new Background();
        stage.addActor(background); //set it and forget it

        bee = new Bee();
//        BeeAI beeAI = new BeeAI();
//        BeeAI beeAI2 = new BeeAI();
        Group aiBees = new Group();
        aiBees.setName("aiBees");
        aiBees.addActor(new BeeAI());
        aiBees.addActor(new BeeAI());

        Beehive beehive = new Beehive();
        stream = new Water();
        Scoreboard scoreboard = new Scoreboard();

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

        Flower flower = new YellowFlower();
        stage.addActor(flower);

        stage.addActor(beehive);
        stage.addActor(stream);
        //stage.addActor(scoreboard);

        stage.addActor(aiBees);
        //stage.addActor(beeAI2);

        //make sure this is last so player is top layer
        stage.addActor(bee);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());

        //boing when playerbee hits aibee
        Group aiBees = (Group)stage.getRoot().findActor("aiBees");
        for (Actor actor : aiBees.getChildren()) {
            Bee ai = (Bee)actor;
            if (bee.getBody().overlaps(ai.getBody())) {
                bee.moveBy(-100, 0);
                ai.moveBy(100, 0);
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

        stage.draw();
    }

    // Call this whenever you want to save the playerHP
    void savePlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    // Call this whenever you want to save the computerPollenCount
    void saveComputerPollenCount(int computerPollenCount) {
        this.computerPollenCount = computerPollenCount;
    }

    // Call this whenever you want to save the computerPesticideCount
    void saveComputerPesticideCount(int computerPesticideCount) {
        this.computerPesticideCount = computerPesticideCount;
    }

    // Call this whenever you want to save the computerHP
    void saveComputerHP(int computerHP) {
        this.computerHP = computerHP;
    }

    // Call this whenever you want to switch to the EndScreen
    void goToEndScreen() {
        game.setScreen(new EndScreen(game, playerPollenCount, playerPesticideCount, playerHP,
                computerPollenCount, computerPesticideCount, computerHP));
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
