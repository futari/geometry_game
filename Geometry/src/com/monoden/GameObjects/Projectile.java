package com.monoden.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.monoden.GameHelpers.Globals;

public class Projectile extends Geometry {

	private boolean friendly;
	private boolean remove;

	public Projectile(Color color, Vector2 position, Vector2 velocity,
			boolean friendly) {
		super(color, position, 2);
		this.velocity = velocity;
		this.friendly = friendly;
		remove = false;
	}

	public Projectile(Color color, Vector2 position, Vector2 velocity) {
		super(color, position, 2);
		this.velocity = velocity;
		this.friendly = false;
		remove = false;
	}

	public void update(float delta) {
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
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public Circle getBoundingCircle() {
		return boundingCircle;
	}

	public void setBoundingCircle(Circle boundingCircle) {
		this.boundingCircle = boundingCircle;
	}

	public int getSize() {
		return size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean toRemove() {
		return remove;
	}

	public void setRemove(boolean b) {
		remove = b;
	}
	
	public boolean collides(Player p) {
		if (!friendly
				&& Intersector.overlaps(p.getBoundingCircle(), boundingCircle)) {
			return true;
		}
		return false;
	}
	
	public boolean collides(Enemy e) {
		if (friendly
				&& Intersector.overlaps(e.getBoundingCircle(), boundingCircle)) {
			return true;
		}
		return false;
	}
}
