package com.monoden.GameHelpers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.monoden.GameObjects.Projectile;

public class Globals {

	public static final float SCROLL_SPEED = 10;
	public static float gameWidth;
	public static float gameHeight;
	public static ArrayList<Projectile> projectiles;

	public static Color SKY = new Color(0.12f, 0.14f, 0.14f, 1);
	public static Color joyPadColor = Color.DARK_GRAY;
	public static Color joyStickColor = Color.GRAY;

	public static float shiftX, shiftY, shiftW, shiftH;
	public static float shootX, shootY, shootW, shootH;
	public static float joyPadX, joyPadY, joyPadR;
	public static float joyStickX, joyStickY, joyStickR;
}
