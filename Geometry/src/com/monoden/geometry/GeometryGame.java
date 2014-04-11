package com.monoden.geometry;

import com.badlogic.gdx.Game;
import com.monoden.GameHelpers.AssetLoader;
import com.monoden.Screens.GameScreen;

public class GeometryGame extends Game {
	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
