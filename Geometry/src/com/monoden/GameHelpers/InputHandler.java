package com.monoden.GameHelpers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.monoden.GameObjects.Player;
import com.monoden.GameWorld.GameWorld;
import com.monoden.GameWorld.GameWorld.GameState;

public class InputHandler implements InputProcessor {

	private GameWorld world;
	private Player player;
	private float scaleFactorX, scaleFactorY;

	public InputHandler(GameWorld world, float scaleFactorX, float scaleFactorY) {
		this.world = world;
		this.player = world.getPlayer();
		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (world.state != GameState.RUNNING) {
			world.reset();
			world.state = GameState.RUNNING;
		}
		if (world.state == GameState.RUNNING) {
			int x = scaleX(screenX);
			int y = scaleY(screenY);
			if (shiftButtonPressed(x, y)) {
				player.shift();
			}
			if (shootButtonPressed(x, y)) {
				player.shoot();
			}
		}
		return true;
	}

	public boolean shiftButtonPressed(int x, int y) {
		if (x >= Globals.shiftX && x <= Globals.shiftX + Globals.shiftW
				&& y >= Globals.shiftY && y <= Globals.shiftY + Globals.shiftH) {
			return true;
		}
		return false;
	}

	public boolean shootButtonPressed(int x, int y) {
		if (x >= Globals.shootX && x <= Globals.shootX + Globals.shootW
				&& y >= Globals.shootY && y <= Globals.shootY + Globals.shootH) {
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		player.setVelocity(new Vector2(0, 0));
		player.setAcceleration(new Vector2(0, 0));
		Globals.joyStickX = Globals.joyPadX;
		Globals.joyStickY = Globals.joyPadY;
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.A:
			player.shift();
			break;
		case Keys.S:
			player.shoot();
			break;
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		float r, dx, dy, dr;

		r = Globals.joyPadR - Globals.joyStickR / 2;
		dx = scaleX(screenX) - Globals.joyPadX;
		dy = scaleY(screenY) - Globals.joyPadY;
		dr = (float) Math.sqrt(dx * dx + dy * dy);
		
		if (dr < r) {
			Globals.joyStickX = scaleX(screenX);
			Globals.joyStickY = scaleY(screenY);
			player.setVelocity(new Vector2(dx * 5, dy * 5));
		} else {
			Globals.joyStickX = dx * r / dr + Globals.joyPadX;
			Globals.joyStickY = dy * r / dr + Globals.joyPadY;
			player.setVelocity(new Vector2(dx * r / dr * 5, dy *r / dr * 5));
		}
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	private int scaleX(int screenX) {
		return (int) (screenX / scaleFactorX);
	}

	private int scaleY(int screenY) {
		return (int) (screenY / scaleFactorY);
	}
}
