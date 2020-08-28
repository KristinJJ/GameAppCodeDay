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



public class MenuScreen extends ScreenAdapter {
    private BeesGame game;
    private Background background;
    private Stage stage;
    private Music music;
    private int playerPollenCount;
    private int playerPesticideCount;
    private int playerHP;


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
        creditsBtn.setPosition((BeesGame.WIDTH / 2f + 20), (BeesGame.HEIGHT / 2f) - 250);
        creditsBtn.setTransform(true);
        creditsBtn.scaleBy(0.5f);
        creditsBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new CreditScreen(game));
                return true;
            }
        });

        Button instructBtn = new TextButton("Instructions", skin, "small");
        instructBtn.setSize(120, 40);
        instructBtn.setPosition((BeesGame.WIDTH / 2f - 180), (BeesGame.HEIGHT / 2f) - 250);
        instructBtn.setTransform(true);
        instructBtn.scaleBy(0.5f);
        instructBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new InstructionScreen(game));
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
                game.setScreen(new EndScreen(game, playerPollenCount, playerPesticideCount, playerHP));
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
        honeyImage.setSize(BeesGame.WIDTH, (BeesGame.HEIGHT / 10) * 3);
        honeyImage.setPosition(0f,BeesGame.HEIGHT - honeyImage.getHeight());
        honeyImage.setDrawable(new TextureRegionDrawable(new TextureRegion(honey)));

        //HoneyHive picture
        Texture honeyHive = new Texture("beeHiveTrim.png");
        Image hiveBeesImage = new Image();
        hiveBeesImage.setSize(350, 350);
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
        yellowBeeImage2.setPosition((400),(100));
        yellowBeeImage2.setDrawable(new TextureRegionDrawable(new TextureRegion(yellowBee2)));

        Texture yellowBee3 = new Texture("yellowBee.png");
        Image yellowBeeImage3 = new Image();
        yellowBeeImage3.setSize(30, 30);
        yellowBeeImage3.setPosition((520),(180));
        yellowBeeImage3.setDrawable(new TextureRegionDrawable(new TextureRegion(yellowBee3)));

        Texture yellowBee4 = new Texture("yellowBee.png");
        Image yellowBeeImage4 = new Image();
        yellowBeeImage4.setSize(50, 50);
        yellowBeeImage4.setPosition((800),(230));
        yellowBeeImage4.setDrawable(new TextureRegionDrawable(new TextureRegion(yellowBee4)));

        Texture yellowBee5 = new Texture("yellowBee.png");
        Image yellowBeeImage5 = new Image();
        yellowBeeImage5.setSize(30, 30);
        yellowBeeImage5.setPosition((905),(80));
        yellowBeeImage5.setDrawable(new TextureRegionDrawable(new TextureRegion(yellowBee5)));



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

        Texture yellowBlackBee3 = new Texture("blackBeeYellow.png");
        Image blackBeeYellowImage3 = new Image();
        blackBeeYellowImage3.setSize(60, 60);
        blackBeeYellowImage3.setPosition((750),(890));
        blackBeeYellowImage3.setDrawable(new TextureRegionDrawable(new TextureRegion(yellowBlackBee3)));

        Texture yellowBlackBee4 = new Texture("blackBeeYellow.png");
        Image blackBeeYellowImage4 = new Image();
        blackBeeYellowImage4.setSize(40, 40);
        blackBeeYellowImage4.setPosition((920),(250));
        blackBeeYellowImage4.setDrawable(new TextureRegionDrawable(new TextureRegion(yellowBlackBee4)));

        Texture yellowBlackBee5 = new Texture("blackBeeYellow.png");
        Image blackBeeYellowImage5 = new Image();
        blackBeeYellowImage5.setSize(20, 20);
        blackBeeYellowImage5.setPosition((820),(115));
        blackBeeYellowImage5.setDrawable(new TextureRegionDrawable(new TextureRegion(yellowBlackBee5)));

        Texture yellowBlackBee6 = new Texture("blackBeeYellow.png");
        Image blackBeeYellowImage6 = new Image();
        blackBeeYellowImage6.setSize(20, 20);
        blackBeeYellowImage6.setPosition((620),(115));
        blackBeeYellowImage6.setDrawable(new TextureRegionDrawable(new TextureRegion(yellowBlackBee6)));

        Texture yellowBlackBee7 = new Texture("blackBeeYellow.png");
        Image blackBeeYellowImage7 = new Image();
        blackBeeYellowImage7.setSize(20, 20);
        blackBeeYellowImage7.setPosition((500),(100));
        blackBeeYellowImage7.setDrawable(new TextureRegionDrawable(new TextureRegion(yellowBlackBee7)));

        Texture yellowBlackBee8 = new Texture("blackBeeYellow.png");
        Image blackBeeYellowImage8 = new Image();
        blackBeeYellowImage8.setSize(20, 20);
        blackBeeYellowImage8.setPosition((450),(60));
        blackBeeYellowImage8.setDrawable(new TextureRegionDrawable(new TextureRegion(yellowBlackBee8)));

        Texture yellowBlackBee9 = new Texture("blackBeeYellow.png");
        Image blackBeeYellowImage9 = new Image();
        blackBeeYellowImage9.setSize(100, 100);
        blackBeeYellowImage9.setPosition((0),(890));
        blackBeeYellowImage9.setDrawable(new TextureRegionDrawable(new TextureRegion(yellowBlackBee9)));

        //grass
        Texture tallGrass = new Texture("pngegg.png");
        Image tallGrassImage = new Image();
        tallGrassImage.setSize(BeesGame.WIDTH, 200);
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
        cuteBeeImage2.setPosition((780),(730));
        cuteBeeImage2.setDrawable(new TextureRegionDrawable(new TextureRegion(cuteBee2)));

        Texture cuteBee3 = new Texture("cuteBeeHorizontalTrimed.png");
        Image cuteBeeImage3 = new Image();
        cuteBeeImage3.setSize(145, 145);
        cuteBeeImage3.setPosition(600f,(520));
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
        stage.addActor(blueCircleFlowerImage2);
        stage.addActor(lightBlueFlowerImage);
        stage.addActor(greenCircleFlowerImage);
        stage.addActor(darkPinkCircleFlowerImage);
        stage.addActor(leftYellowBeeImage);
        stage.addActor(yellowBeeImage);
        stage.addActor(yellowBeeImage2);
        stage.addActor(yellowBeeImage3);
        stage.addActor(yellowBeeImage4);
        stage.addActor(yellowBeeImage5);
        stage.addActor(credits);
        stage.addActor(orangeCircleFlowerImage);
        stage.addActor(cuteBeeImage2);
        stage.addActor(indigoCircleFlowerImage);
        stage.addActor(yellowCircleFlowerImage);
        stage.addActor(darkPinkCircleFlowerImage2);
        stage.addActor(lightBlueCircleFlowerImage2);
        stage.addActor(cuteBeeImage3);
        stage.addActor(playBtn);
        stage.addActor(instructBtn);
        stage.addActor(creditsBtn);
        stage.addActor(blackBeeYellowImage);
        stage.addActor(blackBeeYellowImage2);
        stage.addActor(blackBeeYellowImage3);
        stage.addActor(blackBeeYellowImage4);
        stage.addActor(blackBeeYellowImage5);
        stage.addActor(blackBeeYellowImage6);
        stage.addActor(blackBeeYellowImage7);
        stage.addActor(blackBeeYellowImage8);
        stage.addActor(blackBeeYellowImage9);
        stage.addActor(cuteBeeImage1);
        //stage.addActor(endBtn); // This is temporary to get to end screen
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
        game.dispose();
        stage.dispose();
        music.dispose();
    }
}
