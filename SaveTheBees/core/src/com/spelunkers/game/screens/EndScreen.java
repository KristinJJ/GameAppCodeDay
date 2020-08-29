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
        playBtn.setPosition(((BeesGame.WIDTH / 4f) * 3 - (playBtn.getWidth() / 2)), (BeesGame.HEIGHT / 2f) - 200);
        playBtn.setTransform(true);
        playBtn.scaleBy(0.5f);
        playBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                    game.setScreen(new PlayScreen(game, game.nextLevel()));
            }
        });

        // button to go back to the menu
        Button menuBtn = new TextButton("Menu", skin, "small");
        menuBtn.setSize(120, 50);
        menuBtn.setPosition((BeesGame.WIDTH / 4f) * 3 - (menuBtn.getWidth() / 2), (BeesGame.HEIGHT / 2f) - 300);
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
        nextBtn.setPosition((BeesGame.WIDTH / 4f) * 3 - (nextBtn.getWidth() / 2), (BeesGame.HEIGHT / 2f) - 100);
        nextBtn.setTransform(true);
        nextBtn.scaleBy(0.5f);
        nextBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
               // if (!game.getLevels().empty()) {
                    game.setScreen(new PlayScreen(game, game.nextLevel()));
               // }
            }
        });

        //Honey picture
        Texture honey = new Texture("honey.png");
        Image honeyImage = new Image();
        honeyImage.setSize(BeesGame.WIDTH, (BeesGame.HEIGHT / 10f) * 3);
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
                                "\nBee Hive Pollen Count : %d" + " / " + level.getPollenGoal();

        String totalScoreText = String.format(Locale.getDefault(),
                                        scoreFormatter,
                                        beeHealth,
                                        beeHivePollen);

        Label scoreBoard = new Label(totalScoreText, skin);
        scoreBoard.setPosition(BeesGame.WIDTH / 6f, (BeesGame.HEIGHT / 3f));

        // passed the level pollen goal or not
        if ((beeHivePollen>= level.getPollenGoal()) && beeHealth > 0 ) {
            this.passedStatus = Boolean.TRUE;
            game.passCurrentLevel();
        }

        // happy bee image
        Texture happyBeeTexture = new Texture("happyCartoonBee.png");
        Image happyBeeBox = new Image();
        happyBeeBox.setSize(220f, 120f);
        happyBeeBox.setPosition((BeesGame.WIDTH / 10f) * 4, (BeesGame.HEIGHT / 10f) * 4);
        happyBeeBox.setDrawable(new TextureRegionDrawable(new TextureRegion(happyBeeTexture)));

        // crown image
        Texture crownTexture = new Texture("CartoonCrown.png");
        Image crownBox = new Image();
        crownBox.setSize(220f, 120f);
        crownBox.setPosition((BeesGame.WIDTH / 20f) * 9, (BeesGame.HEIGHT / 20f) * 9);
        crownBox.setDrawable(new TextureRegionDrawable(new TextureRegion(crownTexture)));

        // sad bee image
        Texture angryBeeTexture = new Texture("AngryBee.png");
        Image angryBeeBox = new Image();
        angryBeeBox.setSize(220f, 120f);
        angryBeeBox.setPosition((BeesGame.WIDTH / 10f) * 5, (BeesGame.HEIGHT / 10f) * 4);
        angryBeeBox.setDrawable(new TextureRegionDrawable(new TextureRegion(angryBeeTexture)));

        //Light Blue flower picture
        Texture lightBlueFlower = new Texture("lightBlueCircleFlower.png");
        Image lightBlueFlowerImage = new Image();
        lightBlueFlowerImage.setSize(90, 90);
        lightBlueFlowerImage.setPosition((0),(0));
        lightBlueFlowerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(lightBlueFlower)));

        //Blue flower picture
        Texture blueShortFlower = new Texture("blueCircleFlower.png");
        Image blueCircleFlowerImage = new Image();
        blueCircleFlowerImage.setSize(80, 80);
        blueCircleFlowerImage.setPosition((0),(85));
        blueCircleFlowerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(blueShortFlower)));

        Texture blueShortFlower2 = new Texture("blueCircleFlower.png");
        Image blueCircleFlowerImage2 = new Image();
        blueCircleFlowerImage2.setSize(170, 170);
        blueCircleFlowerImage2.setPosition((800),(0));
        blueCircleFlowerImage2.setDrawable(new TextureRegionDrawable(new TextureRegion(blueShortFlower2)));

        //green flower picture
        Texture greenCircleFlower = new Texture("greenCircleFlower.png");
        Image greenCircleFlowerImage = new Image();
        greenCircleFlowerImage.setSize(90, 90);
        greenCircleFlowerImage.setPosition((370),(20));
        greenCircleFlowerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(greenCircleFlower)));

        //Dark Pink flower picture
        Texture darkPinkCircleFlower = new Texture("darkPinkFlowers.png");
        Image darkPinkCircleFlowerImage = new Image();
        darkPinkCircleFlowerImage.setSize(90, 90);
        darkPinkCircleFlowerImage.setPosition((90),(0));
        darkPinkCircleFlowerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(darkPinkCircleFlower)));

        //Orange flower picture
        Texture orangeCircleFlower = new Texture("orangeCircleFlower.png");
        Image orangeCircleFlowerImage = new Image();
        orangeCircleFlowerImage.setSize(135, 135);
        orangeCircleFlowerImage.setPosition((190),(0));
        orangeCircleFlowerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(orangeCircleFlower)));

        //Indigo flower picture
        Texture indigoCircleFlower = new Texture("indigoCircleFlower.png");
        Image indigoCircleFlowerImage = new Image();
        indigoCircleFlowerImage.setSize(120, 120);
        indigoCircleFlowerImage.setPosition((480),20);
        indigoCircleFlowerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(indigoCircleFlower)));

        //Yellow flower picture
        Texture yellowCircleFlower = new Texture("yellowCircleFlower.png");
        Image yellowCircleFlowerImage = new Image();
        yellowCircleFlowerImage.setSize(90, 90);
        yellowCircleFlowerImage.setPosition((600),(0));
        yellowCircleFlowerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(yellowCircleFlower)));

        //Dark Pink on the right flower picture
        Texture darkPinkCircleFlower2 = new Texture("darkPinkFlowers.png");
        Image darkPinkCircleFlowerImage2 = new Image();
        darkPinkCircleFlowerImage2.setSize(110, 110);
        darkPinkCircleFlowerImage2.setPosition((690),(0));
        darkPinkCircleFlowerImage2.setDrawable(new TextureRegionDrawable(new TextureRegion(darkPinkCircleFlower2)));

        //light blue on the right flower picture
        Texture lightBlueCircleFlower2 = new Texture("lightBlueCircleFlower.png");
        Image lightBlueCircleFlowerImage2 = new Image();
        lightBlueCircleFlowerImage2.setSize(75, 75);
        lightBlueCircleFlowerImage2.setPosition((720),(95));
        lightBlueCircleFlowerImage2.setDrawable(new TextureRegionDrawable(new TextureRegion(lightBlueCircleFlower2)));

        //grass
        Texture tallGrass = new Texture("pngegg.png");
        Image tallGrassImage = new Image();
        tallGrassImage.setSize(BeesGame.WIDTH, 200);
        tallGrassImage.setPosition((0),(0));
        tallGrassImage.setDrawable(new TextureRegionDrawable(new TextureRegion(tallGrass)));

        // Level label
        Label levelLbl = new Label(level.getText(), skin);
        levelLbl.setPosition(BeesGame.WIDTH / 6f, BeesGame.HEIGHT / 4.7f);

        stage.addActor(background);
        stage.addActor(new BlackSquare(90f, 90f, 810f, 525f));
        stage.addActor(scoreBoard);
        stage.addActor(honeyImage);
        stage.addActor(hiveBeesImage);
        if (passedStatus) {
            Label passedCongrats = new Label("Congrats, you passed the level!", skin);
            passedCongrats.setPosition(BeesGame.WIDTH / 6f, (BeesGame.HEIGHT / 4f));
            stage.addActor(happyBeeBox);
            stage.addActor(crownBox);
            if (game.hasNextLevel()) {
                stage.addActor(passedCongrats);
                stage.addActor(nextBtn);
            } else {

                Label wonCongrats = new Label("Congrats, you won the Game and Saved the Bees!", skin);
                wonCongrats.setPosition(BeesGame.WIDTH / 6f, (BeesGame.HEIGHT / 4f));
                stage.addActor(wonCongrats);
                stage.addActor(tallGrassImage);
                stage.addActor(blueCircleFlowerImage);
                stage.addActor(blueCircleFlowerImage2);
                stage.addActor(lightBlueFlowerImage);
                stage.addActor(greenCircleFlowerImage);
                stage.addActor(darkPinkCircleFlowerImage);
                stage.addActor(orangeCircleFlowerImage);
                stage.addActor(indigoCircleFlowerImage);
                stage.addActor(yellowCircleFlowerImage);
                stage.addActor(darkPinkCircleFlowerImage2);
                stage.addActor(lightBlueCircleFlowerImage2);
            }
        } else {
            Label tryAgain = new Label("Sorry, your bee did not collect enough pollen,\n" +
                                            "or got too sick from the Pesticide. Try again!", skin);
            tryAgain.setPosition(BeesGame.WIDTH / 6f, (BeesGame.HEIGHT / 6f));
            stage.addActor(angryBeeBox);
            stage.addActor(tryAgain);
            stage.addActor(playBtn);
        }
        stage.addActor(menuBtn);
        stage.addActor(levelLbl);
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


