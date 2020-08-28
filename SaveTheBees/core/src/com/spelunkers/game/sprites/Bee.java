package com.spelunkers.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

    protected Sprite sprite;
    private Circle body;
    private ShapeRenderer shapeRenderer;

    public static final int SPEED = 200;
    private static final float SCALE = 0.25f;
    private static final float POISON_HIT = 5f;

    public Bee() {
        pollenCount = 0;
        poisoned = false;
        health = 100;

        setImage("cuteBeeTrimmed.png", SCALE);

        setTouchable(Touchable.enabled);
        shapeRenderer = new ShapeRenderer();

    }

    public void setImage(String internalPicPath, float scale) {
        sprite = new Sprite(new Texture(internalPicPath));
        sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
        sprite.setOrigin(0, 0);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        body = new Circle(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2, sprite.getHeight() * 0.50f);
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

    public boolean hitWall() {
        float x = getX();
        float y = getY();
        return x < 0 || x > getStage().getWidth() - sprite.getWidth() ||
               y < 0 || y > getStage().getHeight() - sprite.getHeight();
    }

    @Override
    protected void positionChanged() {
        sprite.setPosition(getX(), getY());
        body.setPosition(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);
        super.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        sprite.draw(batch, health / 100f);

        batch.end();
        /// temporary just to make sure the circle is the right size and position
        /*
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0.3f, 0.75f, 0.9f, 0.01f);
        shapeRenderer.circle(body.x, body.y, body.radius);
        shapeRenderer.end();
         */

        batch.begin();

    }

    @Override
    public void act(float delta) {
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
