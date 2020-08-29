package com.spelunkers.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.spelunkers.game.screens.MenuScreen;
import com.spelunkers.game.screens.PlayScreen;
import com.spelunkers.game.sprites.Level;

import java.util.Stack;

public class BeesGame extends Game {
	private SpriteBatch batch; // we only need one.
	private Stack<Level> levels;

	public static final String TITLE = "Save The Bees!";
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1000;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		// Create the stack of levels push hardest levels in first
		levels = new Stack<Level>();
		levels.push(new Level(20, 35, 25f, PlayScreen.WindDirection.SOUTH));
		levels.push(new Level(15, 35, 25f, PlayScreen.WindDirection.NORTH));
		levels.push(new Level(15, 30, 20f, PlayScreen.WindDirection.RANDOM));
		levels.push(new Level(30, 30, 10, PlayScreen.WindDirection.EAST));
		levels.push(new Level(20, 25, 10f, PlayScreen.WindDirection.WEST));
		levels.push(new Level(15, 20));

		setScreen(new MenuScreen(this));
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public Stack<Level> getLevels() {
		return levels;
	}
}