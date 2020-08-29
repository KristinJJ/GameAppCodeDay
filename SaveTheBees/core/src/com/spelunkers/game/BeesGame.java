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
		skin = new Skin(Gdx.files.internal("darkSkin/cloud-form-ui.json"));

		// Create the list of levels
		Level level1 = new Level("Piece Of Cake", skin, 15, 5f);
		Level level2 = new Level("Westward Wind", skin, 20, 6f, 10f, PlayScreen.WindDirection.WEST);
		Level level3 = new Level("Eastward wind", skin, 30, 6f, 10, PlayScreen.WindDirection.EAST);
		Level level4 = new Level("Tornado", skin, 15, 7f, 20f, PlayScreen.WindDirection.RANDOM);
		Level level5 = new Level("Strong wind", skin, 15, 7f, 25f, PlayScreen.WindDirection.NORTH);
		Level level6 = new Level("Strong pesticide", skin, 20, 8f, 25f, PlayScreen.WindDirection.SOUTH);
		// Create the stack of levels push hardest levels in first
		levels = new Stack<>();
		levels.push(level6);
		levels.push(level5);
		levels.push(level4);
		levels.push(level3);
		levels.push(level2);
		levels.push(level1);

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