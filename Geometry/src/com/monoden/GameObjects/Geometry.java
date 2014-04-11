package com.monoden.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Geometry {
	protected Vector2 position;
	protected Vector2 velocity;
	protected Vector2 acceleration;

	protected Circle boundingCircle;
	
	protected Color color;
	protected int size;

	public Geometry(Color color, Vector2 position, int size) {
		this.color = color;
		this.size = size; // diameter for circle
		this.position = position;
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 0);
		boundingCircle = new Circle();
	}

	public void update(float delta) {
		velocity.add(acceleration.cpy().scl(delta));

		if (velocity.x > 240) {
			velocity.x = 240;
		}
		if (velocity.x < -240) {
			velocity.x = -240;
		}
		if (velocity.y > 240) {
			velocity.y = 240;
		}
		if (velocity.y < -240) {
			velocity.y = -240;
		}
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

	public void setSize(int size) {
		this.size = size;
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}
}
