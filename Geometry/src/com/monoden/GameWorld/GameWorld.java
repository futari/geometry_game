package com.monoden.GameWorld;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.monoden.GameHelpers.AssetLoader;
import com.monoden.GameHelpers.EnemyType;
import com.monoden.GameHelpers.Globals;
import com.monoden.GameObjects.Enemy;
import com.monoden.GameObjects.Player;
import com.monoden.GameObjects.Projectile;

public class GameWorld {

	private Player player;
	private ArrayList<Enemy> enemies;
	private static Color DEFAULT_COLOR = Color.RED;
	private static int DEFAULT_SIZE = 5;
	private static float runtime;
	private static Vector2 START_POS = new Vector2(
			(Globals.gameWidth + DEFAULT_SIZE) / 2, Globals.gameHeight - 80);
	public GameState state;
	public ArrayList<String> lvl1;

	public enum GameState {
		MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
	}

	public GameWorld() {
		runtime = 0;
		state = GameState.MENU;
		enemies = new ArrayList<Enemy>();
		Globals.projectiles = new ArrayList<Projectile>();
		lvl1 = new ArrayList<String>();

		player = new Player(DEFAULT_COLOR, START_POS, DEFAULT_SIZE);
		AssetLoader.loadLevel(lvl1);
	}

	public void reset() {
		runtime = 0;
		enemies.clear();
		Globals.projectiles.clear();
		AssetLoader.loadLevel(lvl1);
		player.setColor(DEFAULT_COLOR);
		player.setPosition(START_POS);
		player.setSize(DEFAULT_SIZE);
	}

	public void loadEnemies(float runtime) {
		if (lvl1.size() > 0 && lvl1.get(0).trim().startsWith("t=")) {
			if (Float
					.parseFloat(lvl1.get(0).trim().substring(2, lvl1.get(0).trim().length())) <= runtime) {
				lvl1.remove(0);
				while (lvl1.size() > 0 && !lvl1.get(0).trim().startsWith("t=")) {
					String[] a = lvl1.get(0).split(",");
					Color c = (a[0].trim().equals("CYAN")) ? Color.CYAN
							: Color.RED;
					Vector2 p = new Vector2(Float.parseFloat(a[1]),
							Float.parseFloat(a[2]));
					int s = (a[3].equals("DEFAULT_SIZE")) ? DEFAULT_SIZE
							: Integer.parseInt(a[3]);
					int d = Integer.parseInt(a[4]);
					Vector2 v = new Vector2(Float.parseFloat(a[5]),
							Float.parseFloat(a[6]));
					Vector2 acc = new Vector2(Float.parseFloat(a[7]),
							Float.parseFloat(a[8]));
					EnemyType t;

					if (a[9].trim().equals("PREACHER")) {
						t = EnemyType.PREACHER;
					} else if (a[9].trim().equals("BLOOM")) {
						t = EnemyType.BLOOM;
					} else if (a[9].trim().equals("FIREWORK")) {
						t = EnemyType.FIREWORK;
					} else if (a[9].trim().equals("BURST")) {
						t = EnemyType.BURST;
					} else if (a[9].trim().equals("HOOK")) {
						t = EnemyType.HOOK;
					} else {
						t = EnemyType.STRAIGHT;
					}
					enemies.add(new Enemy(c, p, s, d, v, acc, t));
					lvl1.remove(0);
				}
			}
		}
		
	}

	public void update(float delta) {
		if (state == GameState.RUNNING) {
			runtime += delta;
			loadEnemies(runtime);

			player.update(delta);

			for (Enemy enemy : enemies) {
				enemy.update(delta);
				if (enemy.collides(player)) {
					state = GameState.GAMEOVER;
					AssetLoader.explode.play();
					enemy.setRemove(true);
				}
			}

			for (int i = 0; i < enemies.size(); i++) {
				if (enemies.get(i).toRemove())
					enemies.remove(enemies.get(i));
			}

			for (Projectile projectile : Globals.projectiles) {
				projectile.update(delta);
				if (projectile.collides(player)) {
					if (player.getColor() != projectile.getColor()) {
						AssetLoader.explode.play();
						state = GameState.GAMEOVER;
					}
					projectile.setRemove(true);
				}
				for (int i = 0; i < enemies.size(); i++) {
					if (projectile.collides(enemies.get(i))) {
						AssetLoader.explode.play();
						enemies.remove(enemies.get(i));
						projectile.setRemove(true);
					}
				}
			}

			for (int i = 0; i < Globals.projectiles.size(); i++) {
				if (Globals.projectiles.get(i).toRemove())
					Globals.projectiles.remove(Globals.projectiles.get(i));
			}
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}
}
