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


    public MenuScreen(BeesGame game) {
        this.game = game;
        background = new Background();
        music = Gdx.audio.newMusic(Gdx.files.internal("Kevin MacLeod - The Cannery (Background Gaming Music).mp3"));
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
        Button playBtn = new TextButton("Play", skin, "small");
        playBtn.setSize(100, 50);
        playBtn.setPosition((BeesGame.WIDTH / 2) - 70, (BeesGame.HEIGHT / 2) - 200);
        playBtn.setTransform(true);
        playBtn.scaleBy(0.5f);
        playBtn.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game));
                music.pause();
                return true;
            }

            //@Override
            //public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
              //  game.setScreen(new PlayScreen(game));
            //}
        });

        // Here goes our logo
        Texture logo = new Texture("SaveTheBees-Logo.png");
        Image logoImage = new Image();
        logoImage.setSize(400, 400);
        logoImage.setPosition((BeesGame.WIDTH / 2) - (logoImage.getWidth() / 2),(BeesGame.HEIGHT / 2) - (logoImage.getHeight() / 3));
        logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(logo)));


        //Honey picture
        Texture honey = new Texture("honey.png");
        Image honeyImage = new Image();
        honeyImage.setSize(800, 300);
        honeyImage.setPosition((0),(BeesGame.HEIGHT / 2) + (logoImage.getHeight() / 3));
        honeyImage.setDrawable(new TextureRegionDrawable(new TextureRegion(honey)));

        //HoneyHive picture
        Texture honeyHive = new Texture("hiveBees.png");
        Image hiveBeesImage = new Image();
        hiveBeesImage.setSize(300, 300);
        hiveBeesImage.setPosition((0),(BeesGame.HEIGHT / 2) + (hiveBeesImage.getHeight() / 3));
        hiveBeesImage.setDrawable(new TextureRegionDrawable(new TextureRegion(honeyHive)));

        //Light Blue flower picture
        Texture lightBlueFlower = new Texture("lightBlueCircleFlower.png");
        Image lightBlueFlowerImage = new Image();
        lightBlueFlowerImage.setSize(90, 90);
        lightBlueFlowerImage.setPosition((0),(0));
        lightBlueFlowerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(lightBlueFlower)));

        //Purple flower picture
        Texture blueShortFlower = new Texture("blueCircleFlower.png");
        Image blueCircleFlowerImage = new Image();
        blueCircleFlowerImage.setSize(90, 90);
        blueCircleFlowerImage.setPosition((0),(85));
        blueCircleFlowerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(blueShortFlower)));

        //Purple flower picture
        Texture greenCircleFlower = new Texture("greenCircleFlower.png");
        Image greenCircleFlowerImage = new Image();
        greenCircleFlowerImage.setSize(90, 90);
        greenCircleFlowerImage.setPosition((0),(160));
        greenCircleFlowerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(greenCircleFlower)));

        //Dark Pink flower picture
        Texture darkPinkCircleFlower = new Texture("darkPinkFlowers.png");
        Image darkPinkCircleFlowerImage = new Image();
        darkPinkCircleFlowerImage.setSize(90, 90);
        darkPinkCircleFlowerImage.setPosition((90),(0));
        darkPinkCircleFlowerImage.setDrawable(new TextureRegionDrawable(new TextureRegion(darkPinkCircleFlower)));

        //credits
        /*
        String text = "\n" +
                "    Kristin Jue\n" +
                "    Erica Chong\n" +
                "    Obaid Sidiqi\n" +
                "    Lily Aguirre\n" +
                "    Monica King\n" +
                "    Jardi Martinez\n";
        Label credits = new Label(text, skin);
        credits.setPosition(((BeesGame.WIDTH / 2) - (credits.getWidth() / 2)), 10);
        */
        stage.addActor(background);
        stage.addActor(playBtn);
        stage.addActor(logoImage);
        stage.addActor(credits);
        stage.addActor(honeyImage);
        stage.addActor(hiveBeesImage);
        stage.addActor(blueCircleFlowerImage);
        stage.addActor(lightBlueFlowerImage);
        stage.addActor(greenCircleFlowerImage);
        stage.addActor(darkPinkCircleFlowerImage);

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
