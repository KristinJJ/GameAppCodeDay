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
//		levels.push(new Level("Strong pesticide", 20, 8f, 25f, PlayScreen.WindDirection.SOUTH));
//		levels.push(new Level("Strong wind", 15, 7f, 25f, PlayScreen.WindDirection.NORTH));
//		levels.push(new Level("Tornado", 15, 7f, 20f, PlayScreen.WindDirection.RANDOM));
//		levels.push(new Level("Eastward wind",30, 6f, 10, PlayScreen.WindDirection.EAST));
//		levels.push(new Level("Westward Wind", 20, 6f, 10f, PlayScreen.WindDirection.WEST));
		levels.push(new Level("Piece Of Cake", 15, 5f));

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