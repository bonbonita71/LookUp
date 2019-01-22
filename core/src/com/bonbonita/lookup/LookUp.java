package com.bonbonita.lookup;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bonbonita.lookup.Views.*;

public class LookUp extends Game {
	// screens
	public LoadingScreen loadingScreen;
	public SplashScreen splashScreen;
	public MenuScreen menuScreen;
	public PlayScreen playScreen;

	public int SCREEN_WIDTH = 480;
	public int SCREEN_HEIGHT = 800;

	public OrthographicCamera camera;

	@Override
	public void create () {
		Assets.init();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);

		loadingScreen = new LoadingScreen(this);
		splashScreen = new SplashScreen(this);
		menuScreen = new MenuScreen(this);
		playScreen = new PlayScreen(this);


		setScreen(loadingScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		loadingScreen.dispose();
		splashScreen.dispose();
		menuScreen.dispose();
		playScreen.dispose();

		Assets.dispose();
	}
}

