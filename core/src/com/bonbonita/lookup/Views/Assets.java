package com.bonbonita.lookup.Views;

/**
 * Created by BonBonita on 22.01.2019.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class Assets {
    // static names for assets, for easier usage
    public static String LOGO = "images/logo.png";
    public static String PLAY_BUTTON = "images/play.png";
    //  public static String SPACESHIP = "images/spaceship.png";
    // public static String ALIEN_1 = "images/alien1.png";
    // public static String BULLET = "images/bullet.png";
    // public static String SKIN = "skin/a/uiskin.atlas";

    private static AssetManager assetManager;
    public static BitmapFont mainFont;

    public static void init(){
        assetManager = new AssetManager();
        // load font before other assets, cause we need it first
        float fontSize = .50f;
        mainFont = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
        mainFont.getData().setScale(fontSize, fontSize);
    }
    public static void dispose(){
        assetManager.dispose();
        mainFont.dispose();
    }

    public static void load() {
        assetManager.load(LOGO, Texture.class);
        assetManager.load(PLAY_BUTTON, Texture.class);
        // assetManager.load(SPACESHIP, Texture.class);
        //  assetManager.load(ALIEN_1, Texture.class);
        //  assetManager.load(BULLET, Texture.class);
        //  assetManager.load(SKIN, TextureAtlas.class);
    }

    // get loading progress
    public static float getProgress(){
        return assetManager.getProgress();
    }
    // is loading finished?
    public static boolean update(){
        return assetManager.update();
    }

    public static Texture getTexture(String tex){
        return assetManager.get(tex, Texture.class);
    }

    public static TextureAtlas getTextureAtlas(String tex){
        return assetManager.get(tex, TextureAtlas.class);
    }
}
