package com.monoden.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.monoden.GameHelpers.Globals;
import com.monoden.GameHelpers.InputHandler;
import com.monoden.GameWorld.GameRenderer;
import com.monoden.GameWorld.GameWorld;

public class GameScreen implements Screen {

	private GameWorld world;
	private GameRenderer renderer;
	private float runTime; // inits to 0

	public GameScreen() {
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();

		Globals.gameWidth = 136;
		Globals.gameHeight = screenHeight / (screenWidth / Globals.gameWidth);

		Globals.shiftX = Globals.gameWidth - 30;
		Globals.shiftY = Globals.gameHeight - 30;
		Globals.shiftW = 20;
		Globals.shiftH = 20;

		Globals.shootX = Globals.gameWidth - 30;
		Globals.shootY = Globals.gameHeight - 60;
		Globals.shootW = 20;
		Globals.shootH = 20;

		Globals.joyPadX = 30;
		Globals.joyPadY = Globals.gameHeight - 30;
		Globals.joyPadR = 20;

		Globals.joyStickX = Globals.joyPadX;
		Globals.joyStickY = Globals.joyPadY;
		Globals.joyStickR = Globals.joyPadR / 2;

		world = new GameWorld();
		Gdx.input.setInputProcessor(new InputHandler(world, screenWidth
				/ Globals.gameWidth, screenHeight / Globals.gameHeight));
		renderer = new GameRenderer(world, (int) Globals.gameWidth,
				(int) Globals.gameHeight);
	}

	@Override
	public void render(float delta) {
		runTime += delta;
		world.update(delta);
		renderer.render(delta, runTime);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
