package com.spelunkers.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spelunkers.game.screens.MenuScreen;

public class BeesGame extends Game {
	private SpriteBatch batch; // we only need one.

	public static final String TITLE = "Save The Bees!";
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MenuScreen(this));
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public SpriteBatch getSpriteBatch() {
		return batch;
	}
}
