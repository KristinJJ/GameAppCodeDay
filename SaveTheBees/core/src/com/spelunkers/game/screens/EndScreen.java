package com.spelunkers.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.spelunkers.game.BeesGame;
import com.spelunkers.game.sprites.Background;
import com.spelunkers.game.sprites.BlackSquare;
import com.spelunkers.game.sprites.Level;
import com.spelunkers.game.sprites.Scoreboard;

import java.util.Locale;


public class EndScreen extends ScreenAdapter {
    private BeesGame game;
    private Level level;
    private Background background;
    private Stage stage;
    private Music music;
    private Boolean passedStatus;

    private int beeHivePollen;
    private int beePollen;
    private float beeHealth;

    public EndScreen(BeesGame game, Level level, Scoreboard scoreboard) {
        this.beeHivePollen = scoreboard.getBeeHivePollenCount();
        this.beePollen = scoreboard.getBeePollenCount();
        this.beeHealth = scoreboard.getBeeHealth();
        this.game = game;
        this.level = level;
        this.passedStatus = Boolean.FALSE;
        background = new Background();
        music = Gdx.audio.newMusic(Gdx.files.internal("Kevin MacLeod ~ Move Forward.mp3"));
        music.setVolume((float) 0.2);
        music.setLooping(true);
        music.play();
    }

    @Override
    public void show() {
        OrthographicCamera camera = new OrthographicCamera(BeesGame.WIDTH, BeesGame.HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        // So much work for a button...
        Button playBtn = new TextButton("Play Again", skin, "small");
        playBtn.setSize(120, 50);
        playBtn.setPosition(((BeesGame.WIDTH / 4) * 3 - (playBtn.getWidth() / 2)), (BeesGame.HEIGHT / 2) - 200);
        playBtn.setTransform(true);
        playBtn.scaleBy(0.5f);
        playBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game, level));
            }
        });

        // button to go back to the menu
        Button menuBtn = new TextButton("Menu", skin, "small");
        menuBtn.setSize(120, 50);
        menuBtn.setPosition((BeesGame.WIDTH / 4) * 3 - (menuBtn.getWidth() / 2), (BeesGame.HEIGHT / 2) - 300);
        menuBtn.setTransform(true);
        menuBtn.scaleBy(0.5f);
        menuBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MenuScreen(game));
            }
        });

        // button to go to the Next Level
        Button nextBtn = new TextButton("Next Level", skin, "small");
        nextBtn.setSize(120, 50);
        nextBtn.setPosition((BeesGame.WIDTH / 4) * 3 - (nextBtn.getWidth() / 2), (BeesGame.HEIGHT / 2) - 100);
        nextBtn.setTransform(true);
        nextBtn.scaleBy(0.5f);
        nextBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game, level));
            }
        });

        //Honey picture
        Texture honey = new Texture("honey.png");
        Image honeyImage = new Image();
        honeyImage.setSize(BeesGame.WIDTH, (BeesGame.HEIGHT / 10) * 3);
        honeyImage.setPosition(0f,BeesGame.HEIGHT - honeyImage.getHeight());
        honeyImage.setDrawable(new TextureRegionDrawable(new TextureRegion(honey)));

        //HoneyHive picture
        Texture honeyHive = new Texture("beeHiveTrim.png");
        Image hiveBeesImage = new Image();
        hiveBeesImage.setSize(300, 300);
        hiveBeesImage.setPosition(0f,BeesGame.HEIGHT - hiveBeesImage.getHeight());
        hiveBeesImage.setDrawable(new TextureRegionDrawable(new TextureRegion(honeyHive)));

        //scoreboard
        String scoreFormatter = "Scoreboard\n\n" +
                                "\nPlayer" +
                                "\n" +
                                "\nBee Health : %.2f" +
                                "\n" +
                                "\nBee Pollen Count / 2 : %d" +
                                "\n" +
                                "\nBee Hive Pollen Count : %d" + " / " + level.getPollenGoal();

        String totalScoreText = String.format(Locale.getDefault(),
                                        scoreFormatter,
                                        beeHealth,
                                        beePollen / 2,
                                        beeHivePollen);

        Label scoreBoard = new Label(totalScoreText, skin);
        scoreBoard.setPosition(BeesGame.WIDTH / 6, (BeesGame.HEIGHT / 3));

        // passed the level pollen goal or not
        if ((beeHivePollen>= level.getPollenGoal()) && beeHealth > 0 ) {
            this.passedStatus = Boolean.TRUE;
        }

        // happy bee image
        Texture happyBeeTexture = new Texture("happyCartoonBee.png");
        Image happyBeeBox = new Image();
        happyBeeBox.setSize(220f, 120f);
        happyBeeBox.setPosition((BeesGame.WIDTH / 10) * 4, BeesGame.HEIGHT / 2);
        happyBeeBox.setDrawable(new TextureRegionDrawable(new TextureRegion(happyBeeTexture)));

        // sad bee image
        Texture angryBeeTexture = new Texture("AngryBee.png");
        Image angryBeeBox = new Image();
        angryBeeBox.setSize(220f, 120f);
        angryBeeBox.setPosition((BeesGame.WIDTH / 10) * 5, BeesGame.HEIGHT / 2);
        angryBeeBox.setDrawable(new TextureRegionDrawable(new TextureRegion(angryBeeTexture)));

        stage.addActor(background);
        stage.addActor(new BlackSquare(90f, 90f, 810f, 525f));
        stage.addActor(scoreBoard);
        stage.addActor(honeyImage);
        stage.addActor(hiveBeesImage);
        if (passedStatus) {
            Label passedCongrats = new Label("Congrats, you passed the level!", skin);
            passedCongrats.setPosition(BeesGame.WIDTH / 6, (BeesGame.HEIGHT / 6));
            stage.addActor(happyBeeBox);
            stage.addActor(passedCongrats);
            stage.addActor(nextBtn);
        } else {
            Label tryAgain = new Label("Sorry, your bee did not collect enough pollen,\n" +
                                            "or got too sick from the Pesticide. Try again!", skin);
            tryAgain.setPosition(BeesGame.WIDTH / 6, (BeesGame.HEIGHT / 6));
            stage.addActor(angryBeeBox);
            stage.addActor(tryAgain);
            stage.addActor(playBtn);
        }
        stage.addActor(menuBtn);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.75f, 0.25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void hide() {
        music.stop();
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        //background.dispose();
        game.dispose();
        stage.dispose();
        music.dispose();
    }
}


