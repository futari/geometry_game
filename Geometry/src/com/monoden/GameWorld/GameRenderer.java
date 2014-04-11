package com.monoden.GameWorld;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.monoden.GameHelpers.AssetLoader;
import com.monoden.GameHelpers.Globals;
import com.monoden.GameObjects.Enemy;
import com.monoden.GameObjects.Player;
import com.monoden.GameObjects.Projectile;
import com.monoden.GameWorld.GameWorld.GameState;

public class GameRenderer {

	private GameWorld world;
	private Player player;
	private ArrayList<Enemy> enemies;

	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batcher;

	public GameRenderer(GameWorld world, int width, int height) {
		this.world = world;
		player = world.getPlayer();
		enemies = world.getEnemies();

		cam = new OrthographicCamera();
		cam.setToOrtho(true, width, height);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
	}

	public void render(float delta, float runTime) {
		if (world.state == GameState.RUNNING) {
			renderPlay(delta, runTime);
		} else if (world.state == GameState.GAMEOVER) {
			renderGameOver(delta, runTime);
		} else if (world.state == GameState.MENU) {
			renderMenu(delta, runTime);
		}
	}

	public void renderGameOver(float delta, float runTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Globals.SKY);
		shapeRenderer.rect(0, 0, Globals.gameWidth, Globals.gameHeight);

		shapeRenderer.end();
		renderText("Game Over. Click to retry.");
	}

	public void renderText(String s) {
		batcher.begin();
		AssetLoader.whiteFont.draw(batcher, s,
				Globals.gameWidth / 2 - (2 * s.length()),
				Globals.gameHeight / 2 - 10);
		batcher.end();
	}

	public void renderMenu(float delta, float runTime) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(Globals.SKY);
		shapeRenderer.rect(0, 0, Globals.gameWidth, Globals.gameHeight);

		shapeRenderer.end();
		renderText("Click to start.");
	}

	public void renderPlay(float delta, float runTime) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		shapeRenderer.begin(ShapeType.Filled);

		// Draw Background color
		shapeRenderer.setColor(Globals.SKY);
		shapeRenderer.rect(0, 0, Globals.gameWidth, Globals.gameHeight);

		// Draw characters
		drawEnemies();
		drawProjectiles();

		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.rect(0, Globals.gameHeight - 70, Globals.gameWidth, 70);

		drawPlayer();
		drawJoypad();

		shapeRenderer.end();

		batcher.begin();
		drawButtons();
		batcher.end();
	}

	public void drawButtons() {
		batcher.draw(AssetLoader.shiftButton, Globals.shiftX, Globals.shiftY,
				Globals.shiftW, Globals.shiftH);
		batcher.draw(AssetLoader.shootButton, Globals.shootX, Globals.shootY,
				Globals.shootW, Globals.shootH);
	}

	public void drawEnemies() {
		for (Enemy enemy : enemies) {
			shapeRenderer.setColor(enemy.getColor());
			Circle r = enemy.getBoundingCircle();
			shapeRenderer.circle(r.x, r.y, r.radius);
		}
	}

	public void drawProjectiles() {
		for (Projectile projectile : Globals.projectiles) {
			shapeRenderer.setColor(projectile.getColor());
			Circle r = projectile.getBoundingCircle();
			shapeRenderer.circle(r.x, r.y, r.radius);
		}
	}

	public void drawPlayer() {
		Circle c = player.getBoundingCircle();
		shapeRenderer.setColor(player.getColor());
		shapeRenderer.circle(c.x, c.y, c.radius);
	}

	public void drawJoypad() {
		shapeRenderer.setColor(Globals.joyPadColor);
		shapeRenderer.circle(Globals.joyPadX, Globals.joyPadY, Globals.joyPadR);
		shapeRenderer.setColor(Globals.joyStickColor);
		shapeRenderer.circle(Globals.joyStickX, Globals.joyStickY,
				Globals.joyStickR);
	}

	public void drawPlayerB() {
		Circle r = player.getBoundingCircle();
		batcher.draw(AssetLoader.circle.get(player.getColor()), r.x - r.radius,
				r.y - r.radius, r.radius * 2, r.radius * 2);
	}

}
