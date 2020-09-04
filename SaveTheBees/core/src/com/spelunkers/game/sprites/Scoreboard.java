package com.spelunkers.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.spelunkers.game.BeesGame;

import java.util.Locale;

public class Scoreboard extends Actor {
    private Sprite sprite;
    private Bee bee;
    private Beehive beehive;
    private Level level;

    private Label beehivePollenCount;
    private Label beePollenCount;
    private Label beeHealth;
    private ProgressBar pollenProgress;

    private final float SCALE = 0.25f;

    private static final String BEEHIVE_PC_LABEL = "Beehive Pollen: %d / %d";
    private static final String BEE_PC_LABEL = "Bee Pollen: %d";
    private static final String BEE_HEALTH_LABEL = "Bee Health: %.2f";

    public Scoreboard(Bee bee, Beehive beehive, Level level, Skin skin) {

        sprite = new Sprite(new Texture("scoreboard.png"));
        sprite.setSize(sprite.getWidth() * SCALE + 50, sprite.getHeight() * SCALE);
        sprite.setPosition(BeesGame.WIDTH - sprite.getWidth(), BeesGame.HEIGHT - sprite.getHeight());
        //sprite.setScale(SCALE);

        this.bee = bee;
        this.beehive = beehive;
        this.level = level;

        beehivePollenCount = new Label(String.format(Locale.getDefault(), BEEHIVE_PC_LABEL, beehive.getPollenCount(), level.getPollenGoal()), skin);
        beehivePollenCount.setPosition(sprite.getX() + 15, sprite.getY() + 85);

        beePollenCount = new Label(String.format(Locale.getDefault(), BEE_PC_LABEL, bee.getPollenCount()), skin);
        beePollenCount.setPosition(sprite.getX() + 15, sprite.getY() + 50);

        beeHealth = new Label(String.format(Locale.getDefault(), BEE_HEALTH_LABEL, bee.getHealth()), skin);
        beeHealth.setPosition(sprite.getX() + 15, sprite.getY() + 15);

        // Pollen progress bar
        TextureRegionDrawable textureBar = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("pollenBar.png"))));
        textureBar.setMinSize(5, 50f);
        ProgressBar.ProgressBarStyle barStyle = new ProgressBar.ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
        barStyle.knobBefore = barStyle.knob;
        pollenProgress = new ProgressBar(0f, level.getPollenGoal(), 0.5f, false, barStyle);
        pollenProgress.setWidth(250);
        pollenProgress.setPosition(BeesGame.WIDTH - (pollenProgress.getWidth() + sprite.getWidth()),
                BeesGame.HEIGHT - pollenProgress.getHeight());
        Color barColor = pollenProgress.getColor();
        pollenProgress.setColor(barColor.r, barColor.g, barColor.b, 0.75f);
    }

    public float getScoreboardWidth() {
        return sprite.getWidth();
    }

    public int getBeeHivePollenCount() {
        return this.beehive.getPollenCount();
    }

    public int getBeePollenCount() {
        return this.bee.getPollenCount();
    }

    public float getBeeHealth() {
        return this.bee.getHealth();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
        beehivePollenCount.draw(batch, parentAlpha);
        beePollenCount.draw(batch, parentAlpha);
        beeHealth.draw(batch, parentAlpha);
        pollenProgress.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        beehivePollenCount.setText(String.format(Locale.getDefault(), BEEHIVE_PC_LABEL, beehive.getPollenCount(), level.getPollenGoal()));
        beePollenCount.setText(String.format(Locale.getDefault(), BEE_PC_LABEL, bee.getPollenCount()));
        beeHealth.setText(String.format(Locale.getDefault(), BEE_HEALTH_LABEL, bee.getHealth()));
        pollenProgress.setValue((float)beehive.getPollenCount());
        super.act(delta);
    }
}
