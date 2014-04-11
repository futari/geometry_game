package com.monoden.GameHelpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture texture, logoTexture;
	public static TextureRegion shiftButton, shootButton, emptyButton;
	public static Animation birdAnimation;
	public static Sound shot, explode;
	public static BitmapFont whiteFont;
	private static Preferences prefs;
	public static ArrayList<String> lvl1;
	
	public static HashMap<Color, TextureRegion> circle, rect, tri;
	
	public static void load() {

		// logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
		// logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		// logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		shiftButton = new TextureRegion(texture, 0, 0, 50, 50);
		shootButton = new TextureRegion(texture, 0, 50, 50, 50);
		emptyButton = new TextureRegion(texture, 0, 100, 50, 50);
		
		shiftButton.flip(false,  true);
		shootButton.flip(false,  true);
		emptyButton.flip(false,  true);

		whiteFont = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
		whiteFont.setScale(.1f, -.1f);
		
		readLevel();
		
		shot = Gdx.audio.newSound(Gdx.files.internal("data/163456__lemudcrab__pistol-shot.wav"));
		explode = Gdx.audio.newSound(Gdx.files.internal("data/110113__ryansnook__medium-explosion.wav"));
		
		/*
		TextureRegion[] birds = { birdDown, bird, birdUp };
		birdAnimation = new Animation(0.06f, birds);
		birdAnimation.setPlayMode(Animation.LOOP_PINGPONG);

		// Create (or retrieve existing) preferences file
		prefs = Gdx.app.getPreferences("ZombieBird");

		if (!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}
		*/
	}
/*
	public static void setHighScore(int val) {
		prefs.putInteger("highScore", val);
		prefs.flush();
	}

	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}
*/
	
	public static void loadLevel(ArrayList<String> arr) {
		arr.clear();
		arr.addAll(Arrays.asList(Gdx.files.internal("data/level1.txt").readString().split(";")));
	}
	
	public static void readLevel() {
		ArrayList<String> lvl1 = new ArrayList<String>();
		lvl1.addAll(Arrays.asList(Gdx.files.internal("data/level1.txt").readString().split(";")));
	}
	
	public static void dispose() {
		texture.dispose();
		shot.dispose();
		explode.dispose();
		whiteFont.dispose();
	}

}