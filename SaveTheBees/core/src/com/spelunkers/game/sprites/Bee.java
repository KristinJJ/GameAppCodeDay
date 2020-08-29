package com.spelunkers.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.graphics.GL20;

public class Bee extends Actor {
    private int pollenCount;
    private boolean poisoned;
    private float health;

    private int xDir;
    private int yDir;

    private Sprite sprite;
    private Sprite sickBee;
    private Circle body;
    private Pollen pollen;
    private ShapeRenderer shapeRenderer;

    public static final int SPEED = 200;
    private static final float SCALE = 0.25f;
    private static final float POISON_HIT = 5f;

    public Bee() {
        pollenCount = 0;
        poisoned = false;
        health = 100;

        setImage("cuteBeeTrimmed.png", SCALE);
        pollen = new Pollen();
        setPollenPosition();

        setTouchable(Touchable.enabled);
        shapeRenderer = new ShapeRenderer();

    }

    public void setImage(String internalPicPath, float scale) {
        // Healthy bee image
        sprite = new Sprite(new Texture(internalPicPath));
        sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
        sprite.setOrigin(0, 0);

        // Sick bee image
        sickBee = new Sprite(new Texture("sickCuteBee.png"));
        sickBee.setSize(sickBee.getWidth() * scale, sickBee.getHeight() * scale);

        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        body = new Circle(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2, sprite.getHeight() * 0.50f);
    }

    private void setPollenPosition() {
        pollen.setPosition(getX(), getY() - 15);
    }

    public void drawPollen(Flower flower) {
        pollenCount += flower.harvestPollen();
    }

    public void depositPollen(Beehive beehive) {
        beehive.addPollen(pollenCount);
        emptyPockets();
    }

    public void wash() {
        poisoned = false;
        emptyPockets();
    }

    public void emptyPockets() {
        pollenCount = 0;
        //add code to remove graphics of bee holding pollen
        pollen.drop(Gdx.graphics.getDeltaTime());
    }

    public void stop() {
        xDir = 0;
        yDir = 0;
    }

    public Circle getBody() {
        return body;
    }

    public int getPollenCount() {
        return pollenCount;
    }

    public float getHealth() {
        return health;
    }

    public void becomePoisoned() {
        poisoned = true;
    }

    public boolean isPoisoned() {
        return poisoned;
    }

    public int getXdir() {
        return xDir;
    }

    public int getYdir() {
        return yDir;
    }

    public Pollen getPollen() {
        return pollen;
    }

    public Sprite getSprite() { return sprite; };

    public boolean hitWall() {
        float x = getX();
        float y = getY();
        return x < 0 || x > getStage().getWidth() - sprite.getWidth() ||
               y < 0 || y > getStage().getHeight() - sprite.getHeight();
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(), getY());
        sickBee.setPosition(getX(), getY());
        setPollenPosition();
        body.setPosition(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);
        super.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        pollen.draw(batch, parentAlpha);
        sickBee.draw(batch);
        sprite.draw(batch, health / 100f);

    }

    @Override
    public void act(float delta) {

        if (pollenCount == 0) {
            pollen.setFalling(false); // Nothing to drop
        }

        // Set pollen alpha color according to the pollen quantity
        if (!pollen.isFalling()) {
            pollen.setAlpha(Math.min(1f, pollenCount / 10f));
        }

        if (isPoisoned()) {
            health -= POISON_HIT * delta;
        }

        // Update bee new position after motion keys
        // Bee bounces off the walls
        int boost = 1;
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            boost = 3;
        }

        stop();

        // Fixes bug where bee could get out of the cage
        if (hitWall()) {
            if (getX() < 0) {
                moveBy(25 * boost, 0);
            }
            if (getX() > getStage().getWidth() - sprite.getWidth()) {
                moveBy(-25 * boost, 0);
            }
            if (getY() < 0) {
                moveBy(0, 25 * boost);
            }
            if (getY() > getStage().getHeight() - sprite.getHeight()) {
                moveBy(0, -25 * boost);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            xDir = -1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            xDir = 1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            yDir = -1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            yDir = 1;
        }

        float futureX = getX() + (SPEED * xDir * boost * delta);
        float futureY = getY() + (SPEED * yDir * boost * delta);

        setPosition(futureX, futureY);

    }

}
