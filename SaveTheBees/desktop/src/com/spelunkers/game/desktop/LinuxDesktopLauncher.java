package com.spelunkers.game.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.spelunkers.game.BeesGame;

public class LinuxDesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle(BeesGame.TITLE);
		config.setWindowedMode(BeesGame.WIDTH, BeesGame.HEIGHT);
//		config.title = BeesGame.TITLE;
//		config.width = BeesGame.WIDTH;
//		config.height = BeesGame.HEIGHT;

		new Lwjgl3Application(new BeesGame(), config);
	}
}