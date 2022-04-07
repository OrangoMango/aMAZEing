package com.orangomango.amazeing.ui.scene;

import javafx.scene.canvas.*;

public enum Screens {
	HOME, PLAY_TYPE, PLAY_MODE, SELECT_SEED, MAZE_DISPLAY, HELP, SETTINGS, UPGRADES;

	public static final Home getHomeScreen(GraphicsContext gc){
		return new Home(gc);
	}

	public static final PlayMode getPlayModeScreen(GraphicsContext gc){
		return new PlayMode(gc, () ->  getHomeScreen(gc).display());
	}

	public static final PlayType getPlayTypeScreen(GraphicsContext gc){
		return new PlayType(gc, () -> getPlayModeScreen(gc).display());
	}

	public static final HelpScreen getHelpScreen(GraphicsContext gc){
		return new HelpScreen(gc, () -> getHomeScreen(gc).display());
	}

	public static final UpgradesScreen getUpgradesScreen(GraphicsContext gc){
		return new UpgradesScreen(gc, () -> getHomeScreen(gc).display());
	}

	public static final SettingsScreen getSettingsScreen(GraphicsContext gc){
		return new SettingsScreen(gc, () -> getHomeScreen(gc).display());
	}

	public static final CreateSeed getCreateSeedScreen(GraphicsContext gc){
		return new CreateSeed(gc, () -> getPlayTypeScreen(gc).display());
	}
}