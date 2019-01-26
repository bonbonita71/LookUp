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
    public static String PLAY_BUTTON = "images/buttons/playbtn.png";
    public static String NorthSkyMap = "images/stars_sever.png";
    public static String INFO_BUTTON = "images/buttons/infobtn.png";
    public static String RECORDS_BUTTON = "images/buttons/recordsbtn.png";
    public static String SETTING_BUTTON = "images/buttons/settingbtn.png";
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
        assetManager.load(NorthSkyMap, Texture.class);
        assetManager.load(INFO_BUTTON, Texture.class);
        assetManager.load(RECORDS_BUTTON, Texture.class);
        assetManager.load(SETTING_BUTTON, Texture.class);
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
