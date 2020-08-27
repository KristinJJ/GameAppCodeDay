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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.spelunkers.game.BeesGame;
import com.spelunkers.game.sprites.Background;
import com.spelunkers.game.sprites.Bee;



public class MenuScreen extends ScreenAdapter {
    private BeesGame game;
    private Background background;
    private Stage stage;
    private Music music;
    private int playerPollenCount;
    private int playerPesticideCount;
    private int playerHP;
    private int computerPollenCount;
    private int computerPesticideCount;
    private int computerHP;


    public MenuScreen(BeesGame game) {
        this.game = game;
        background = new Background();
        music = Gdx.audio.newMusic(Gdx.files.internal("Kevin MacLeod - The Cannery (Background Gaming Music).mp3"));
        music.setVolume((float) 0.2);
        music.setLooping(true);
        music.play();
        this.playerPollenCount = 0;
        this.playerPesticideCount = 0;
        this.playerHP = 0;
        this.computerPollenCount = 0;
        this.computerPesticideCount = 0;
        this.computerHP = 0;
    }

    @Override
    public void show() {
        OrthographicCamera camera = new OrthographicCamera(BeesGame.WIDTH, BeesGame.HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        stage = new Stage(new ScreenViewport(camera));
        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        // So much work for a button...
        Button playBtn = new TextButton("Play", skin, "small");
        playBtn.setSize(150, 50);
        playBtn.setPosition(((BeesGame.WIDTH / 2f) - 110), (BeesGame.HEIGHT / 2f) - 160);
        playBtn.setTransform(true);
        playBtn.scaleBy(0.5f);
        playBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game));
                return true;
            }
        });

        Button creditsBtn = new TextButton("Credits", skin, "small");
        creditsBtn.setSize(100, 40);
        creditsBtn.setPosition((BeesGame.WIDTH / 2f - 70), (BeesGame.HEIGHT / 2f) - 250);
        creditsBtn.setTransform(true);
        creditsBtn.scaleBy(0.5f);
        creditsBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new CreditScreen(game));
                return true;
            }
        });

        // ---------   Temporary button to get to the end Screen -----------------------------------
        Button endBtn = new TextButton("The End", skin, "small");
        endBtn.setSize(100, 50);
        endBtn.setPosition((BeesGame.WIDTH / 1.5f) + 100, (BeesGame.HEIGHT / 2f) - 200);
        endBtn.setTransform(true);
        endBtn.scaleBy(0.5f);
        endBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new EndScreen(game, playerPollenCount, playerPesticideCount, playerHP,
                        computerPollenCount, computerPesticideCount, computerHP));
                return true;
            }
        });
        // -----------------------------------------------------------------------------------------

        // Here goes our logo
        Texture logo = new Texture("SaveTheBees-Logo.png");
        Image logoImage = new Image();
        logoImage.setSize(400, 400);
        logoImage.setPosition((BeesGame.WIDTH / 2f) - (logoImage.getWidth() / 2f),
                              (BeesGame.HEIGHT / 2f) - (logoImage.getHeight() / 3f));
        logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(logo)));


        //Honey picture
        Texture honey = new Texture("honey.png");
        Image honeyImage = new Image();
        honeyImage.setSize(800, 300);
        honeyImage.setPosition((0),(BeesGame.HEIGHT / 2f) + (logoImage.getHeight() / 3f));
        honeyImage.setDrawable(new TextureRegionDrawable(new TextureRegion(honey)));

        //HoneyHive picture
        Texture honeyHive = new Texture("beeHiveTrim.png");
        Image hiveBeesImage = new Image();
        hiveBeesImage.setSize(300, 300);
        hiveBeesImage.setPosition((0),(BeesGame.HEIGHT / 2f) + (hiveBeesImage.getHeight() / 3f));
        hiveBeesImage.setDrawable(new TextureRegionDrawable(new TextureRegion(honeyHive)));

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
        indigoCircleFlowerImage.setPosition((480),(0));
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

        //Bees looking at the left
        Texture leftYellowBee = new Texture("leftYellowBee.png");
        Image leftYellowBeeImage = new Image();
        leftYellowBeeImage.setSize(30, 30);
        leftYellowBeeImage.setPosition((50),(200));
        leftYellowBeeImage.setDrawable(new TextureRegionDrawable(new TextureRegion(leftYellowBee)));

        //Bees looking at the right
        Texture yellowBee = new Texture("yellowBee.png");
        Image yellowBeeImage = new Image();
        yellowBeeImage.setSize(30, 30);
        yellowBeeImage.setPosition((0),(20));
        yellowBeeImage.setDrawable(new TextureRegionDrawable(new TextureRegion(yellowBee)));

        Texture yellowBee2 = new Texture("yellowBee.png");
        Image yellowBeeImage2 = new Image();
        yellowBeeImage2.setSize(40, 40);
        yellowBeeImage2.setPosition((350),(125));
        yellowBeeImage2.setDrawable(new TextureRegionDrawable(new TextureRegion(yellowBee2)));

        //BlackBees
        Texture yellowBlackBee = new Texture("blackBeeYellow.png");
        Image blackBeeYellowImage = new Image();
        blackBeeYellowImage.setSize(30, 30);
        blackBeeYellowImage.setPosition((60),(40));
        blackBeeYellowImage.setDrawable(new TextureRegionDrawable(new TextureRegion(yellowBlackBee)));

        Texture yellowBlackBee2 = new Texture("blackBeeYellow.png");
        Image blackBeeYellowImage2 = new Image();
        blackBeeYellowImage2.setSize(40, 40);
        blackBeeYellowImage2.setPosition((150),(125));
        blackBeeYellowImage2.setDrawable(new TextureRegionDrawable(new TextureRegion(yellowBlackBee2)));

        //grass
        Texture tallGrass = new Texture("pngegg.png");
        Image tallGrassImage = new Image();
        tallGrassImage.setSize(800, 180);
        tallGrassImage.setPosition((0),(0));
        tallGrassImage.setDrawable(new TextureRegionDrawable(new TextureRegion(tallGrass)));

        //Cute Bee
        Texture cuteBee1 = new Texture("cuteBee.png");
        Image cuteBeeImage1 = new Image();
        cuteBeeImage1.setSize(70, 70);
        cuteBeeImage1.setPosition((420),(680));
        cuteBeeImage1.setDrawable(new TextureRegionDrawable(new TextureRegion(cuteBee1)));

        Texture cuteBee2 = new Texture("cuteBeeTrimmed.png");
        Image cuteBeeImage2 = new Image();
        cuteBeeImage2.setSize(100, 100);
        cuteBeeImage2.setPosition((680),(630));
        cuteBeeImage2.setDrawable(new TextureRegionDrawable(new TextureRegion(cuteBee2)));

        Texture cuteBee3 = new Texture("cuteBeeHorizontalTrimed.png");
        Image cuteBeeImage3 = new Image();
        cuteBeeImage3.setSize(145, 145);
        cuteBeeImage3.setPosition((550),(520));
        cuteBeeImage3.setDrawable(new TextureRegionDrawable(new TextureRegion(cuteBee3)));

        //credits
        String text = "\n" +
                "Spelunkers Studios";
        Label credits = new Label(text, skin);
        credits.setPosition(((BeesGame.WIDTH / 2f) - (credits.getWidth() / 2f)), 7);

        // Add our stellar actors
        stage.addActor(background);
        stage.addActor(logoImage);
        stage.addActor(honeyImage);
        stage.addActor(hiveBeesImage);
        stage.addActor(tallGrassImage);
        stage.addActor(blueCircleFlowerImage);
        stage.addActor(lightBlueFlowerImage);
        stage.addActor(greenCircleFlowerImage);
        stage.addActor(darkPinkCircleFlowerImage);
        stage.addActor(leftYellowBeeImage);
        stage.addActor(yellowBeeImage);
        stage.addActor(blackBeeYellowImage);
        stage.addActor(blackBeeYellowImage2);
        stage.addActor(cuteBeeImage1);
        stage.addActor(yellowBeeImage2);
        stage.addActor(credits);
        stage.addActor(orangeCircleFlowerImage);
        stage.addActor(cuteBeeImage2);
        stage.addActor(indigoCircleFlowerImage);
        stage.addActor(yellowCircleFlowerImage);
        stage.addActor(darkPinkCircleFlowerImage2);
        stage.addActor(lightBlueCircleFlowerImage2);
        stage.addActor(cuteBeeImage3);
        stage.addActor(playBtn);
        stage.addActor(creditsBtn);
        stage.addActor(endBtn); // This is temporary to get to end screen
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
        //playBtn.dispose();
        game.dispose();
        stage.dispose();
        music.dispose();
    }
}
