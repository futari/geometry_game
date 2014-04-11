package com.monoden.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.monoden.GameHelpers.EnemyType;
import com.monoden.GameHelpers.Globals;

public class Enemy extends Geometry {

	private boolean remove;
	private float origX, origY;
	private EnemyType type;
	private int wait, delay, count;

	public Enemy(Color color, Vector2 position, int size, int delay, Vector2 velocity, Vector2 acceleration, EnemyType type) {
		super(color, position, size);
		this.velocity = velocity;
		this.acceleration = acceleration;
		this.type = type;
		this.delay = delay;
		count = 0;
		origX = position.x;
		origY = position.y;
		remove = false;
	}

	public boolean toRemove() {
		return remove;
	}

	public void setRemove(boolean b) {
		remove = b;
	}

	public void update(float delta) {
		super.update(delta);
		
		// BORDER CHECKING
		if (position.x > Globals.gameWidth + size * 2) {
			remove = true;
		}
		if (position.x < -50) {
			remove = true;
		}
		if (position.y > Globals.gameHeight + size * 2) {
			remove = true;
		}
		if (position.y < - 50) {
			remove = true;
		}

		position.add(velocity.cpy().scl(delta));
		boundingCircle.set(position.x, position.y, size);

		count++;
		if (type == EnemyType.PREACHER) {
			updatePreacher();
		} else if (type == EnemyType.BURST) {
			updateBurst();
		} else if (type == EnemyType.HOOK) {
			updateHook();
		}
	}
	
	public void updateHook() {
		if (count > delay) {
			acceleration = new Vector2(acceleration.x, -acceleration.y);
		}
		
		wait++;
		if (wait > 10) {
			Globals.projectiles.add(new Projectile(color, position.cpy(),
					new Vector2(0, 200)));
			wait = 0;
		}
	}
	
	public void updateBurst() {
		if (count > delay) {
			velocity = new Vector2(velocity.x, Globals.SCROLL_SPEED);
			acceleration = new Vector2(acceleration.x, 0);
			wait++;
		}

		if (wait > 10) {
			Globals.projectiles.add(new Projectile(color, position.cpy(),
					new Vector2(150, 150)));
			Globals.projectiles.add(new Projectile(color, position.cpy(),
					new Vector2(-150, 150)));
			Globals.projectiles.add(new Projectile(color, position.cpy(),
					new Vector2(150, -150)));
			Globals.projectiles.add(new Projectile(color, position.cpy(),
					new Vector2(-150, -150)));
		}
		if (wait > 15) {
			Globals.projectiles.add(new Projectile(color, position.cpy(),
					new Vector2(0, 200)));
			Globals.projectiles.add(new Projectile(color, position.cpy(),
					new Vector2(200, 0)));
			Globals.projectiles.add(new Projectile(color, position.cpy(),
					new Vector2(0, -200)));
			Globals.projectiles.add(new Projectile(color, position.cpy(),
					new Vector2(-200, 0)));
			wait = 0;
		}
	}
	
	public void updatePreacher() {
		if (count > delay) {
			velocity = new Vector2(velocity.x, Globals.SCROLL_SPEED);
			acceleration = new Vector2(acceleration.x, 0);
			wait++;
		}

		if (wait > 5) {
			Globals.projectiles.add(new Projectile(color, position.cpy(),
					new Vector2(0, 200)));
			Globals.projectiles.add(new Projectile(color, position.cpy(),
					new Vector2(200, 0)));
			Globals.projectiles.add(new Projectile(color, position.cpy(),
					new Vector2(0, -200)));
			Globals.projectiles.add(new Projectile(color, position.cpy(),
					new Vector2(-200, 0)));
			wait = 0;
		}
	}

	public boolean collides(Player p) {
		if (Intersector.overlaps(p.getBoundingCircle(), boundingCircle)) {
			return true;
		}
		return false;
	}
}
