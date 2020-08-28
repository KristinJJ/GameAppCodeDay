package com.spelunkers.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.spelunkers.game.BeesGame;

public class Beehive extends Actor {

    private Sprite sprite;
    private static final float SCALE = 0.50f;
    private int totalPollen;

    public Beehive() {
        sprite = new Sprite(new Texture("beehive.png"));
        sprite.setSize(sprite.getWidth() * SCALE, sprite.getHeight() * SCALE);
        sprite.setPosition(0f, BeesGame.HEIGHT - sprite.getHeight());
        //sprite.setScale(SCALE);
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public void addPollen(int newPollen) {
        totalPollen += newPollen;
    }

    public Circle getBody() {
        return new Circle(sprite.getX() + sprite.getWidth() * 0.43f,
                          sprite.getY() + sprite.getHeight() * 0.35f,
                      sprite.getHeight() * 0.3f);
    }

    public int getPollenCount() {
        return totalPollen;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch, parentAlpha);
        batch.end();
        /// temporary just to make sure the circle is the right size and position
        /*
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0.3f, 0.75f, 0.9f, 0.01f);
        shapeRenderer.circle(sprite.getX() + sprite.getWidth() * 0.43f, sprite.getY() + sprite.getHeight() * 0.35f, sprite.getHeight() * 0.3f);
        shapeRenderer.end();
         */
        batch.begin();
    }
}
