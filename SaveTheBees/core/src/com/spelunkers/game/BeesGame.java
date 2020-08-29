package com.spelunkers.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.spelunkers.game.screens.MenuScreen;
import com.spelunkers.game.screens.PlayScreen;
import com.spelunkers.game.sprites.Level;

import java.util.ArrayList;
import java.util.Stack;

public class BeesGame extends Game {
	private SpriteBatch batch; // we only need one.
	private Stack<Level> levels;
	private ArrayList<Level> completedLevels;
	private int nextLevelIdx;
	private Skin skin;

	public static final String TITLE = "Save The Bees!";
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1000;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		completedLevels = new ArrayList<>();
		skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

		// Create the stack of levels push hardest levels in first
		levels = new Stack<>();
		levels.push(new Level("Strong pesticide", skin, 20, 8f, 25f, PlayScreen.WindDirection.SOUTH));
		levels.push(new Level("Strong wind", skin, 15, 7f, 25f, PlayScreen.WindDirection.NORTH));
		levels.push(new Level("Tornado", skin, 15, 7f, 20f, PlayScreen.WindDirection.RANDOM));
		levels.push(new Level("Eastward wind", skin, 30, 6f, 10, PlayScreen.WindDirection.EAST));
		levels.push(new Level("Westward Wind", skin, 20, 6f, 10f, PlayScreen.WindDirection.WEST));
		levels.push(new Level("Piece Of Cake", skin, 15, 5f));

		setScreen(new MenuScreen(this));
	}
	
	@Override
	public void dispose () {
		skin.dispose();
		batch.dispose();
	}

	public Level nextLevel() {
		if (hasNextLevel()) {
			return levels.peek();
		} else {
			return completedLevels.get(nextLevelIdx);
		}
	}

	public boolean hasNextLevel() {
		return !levels.isEmpty();
	}

	public void passCurrentLevel() {
		if (hasNextLevel()) {
			completedLevels.add(levels.pop());
		} else if (nextLevelIdx < completedLevels.size() - 1){
			nextLevelIdx += 1;
		} else {
			nextLevelIdx = 0;
		}
	}

	public Skin getSkin() {
		return skin;
	}
}