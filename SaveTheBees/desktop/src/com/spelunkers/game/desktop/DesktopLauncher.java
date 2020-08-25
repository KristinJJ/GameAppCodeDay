package com.spelunkers.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.spelunkers.game.BeesGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		config.setTitle(BeesGame.TITLE);
//		config.setWindowedMode(BeesGame.WIDTH, BeesGame.HEIGHT);
		config.title = BeesGame.TITLE;
		config.width = BeesGame.WIDTH;
		config.height = BeesGame.HEIGHT;

		new LwjglApplication(new BeesGame(), config);
	}
}