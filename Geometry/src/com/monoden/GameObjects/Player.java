package com.monoden.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.monoden.GameHelpers.AssetLoader;
import com.monoden.GameHelpers.Globals;

public class Player extends Geometry {

	public Player(Color color, Vector2 position, int size) {
		super(color, position, size);
	}

	public void update(float delta) {
		super.update(delta);

		// BORDER CHECKING
		if (position.x > Globals.gameWidth - size) {
			position.x = Globals.gameWidth - size;
			velocity.x = 0;
		}
		if (position.x < 0) {
			position.x = 0;
			velocity.x = 0;
		}
		if (position.y > Globals.gameHeight - 70 - size) {
			position.y = Globals.gameHeight - 70 - size;
			velocity.y = 0;
		}
		if (position.y < 0) {
			position.y = 0;
			velocity.y = 0;
		}

		position.add(velocity.cpy().scl(delta));
		boundingCircle.set(position.x, position.y, size);
	}

	public void shoot() {
		Globals.projectiles.add(new Projectile(Color.PINK, position.cpy(),
				new Vector2(0, -200), true));
		AssetLoader.shot.play();
	}

	public void shift() {
		if (getColor() == Color.CYAN) {
			setColor(Color.RED);
		} else {
			setColor(Color.CYAN);
		}
	}
}
