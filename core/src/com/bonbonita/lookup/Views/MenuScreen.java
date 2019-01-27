package com.bonbonita.lookup.Views;

/**
 * Created by BonBonita on 22.01.2019.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.bonbonita.lookup.LookUp;

/**
 * Created by BonBonita on 25.11.2018.
 */

public class MenuScreen implements Screen {

    private Stage staticStage;
    private Stage dynamicStage;
    InputMultiplexer multiplexer;
   // private static final float WORLD_WIDTH = Gdx.graphics.getWidth();
   // private static final float WORLD_HEIGHT = Gdx.graphics.getHeight();
    private final LookUp app;
   // private Texture playTexture;

    public MenuScreen(final LookUp app) {
        this.app = app;
    }

    @Override
    public void show() {
        multiplexer = new InputMultiplexer();
        dynamicStage = new Stage(new FitViewport(app.SCREEN_WIDTH*2, app.SCREEN_HEIGHT*2));
        staticStage = new Stage(new ScreenViewport());

        multiplexer.addProcessor(staticStage);//важно, чтоб первый добавился, тогда его кнопки в приоритете над динамическим, но это моё первое впечатление
        multiplexer.addProcessor(dynamicStage);

        Gdx.input.setInputProcessor(multiplexer);

        dynamicStage.getViewport().getCamera().translate(-300, 1100, 0);
        ((OrthographicCamera)dynamicStage.getViewport().getCamera()).zoom -= 0.4f;

        Texture skyMap = Assets.getTexture(Assets.NorthSkyMap);
        final Image picSkyMap = new Image(skyMap);
        picSkyMap.setPosition((int)(app.SCREEN_WIDTH /2 - picSkyMap.getWidth() / 2), (int)(app.SCREEN_HEIGHT/2 + picSkyMap.getHeight()/8 ));
        dynamicStage.addActor(picSkyMap);

        dynamicStage.addListener(new ActorGestureListener(){
            @Override
            public void zoom(InputEvent event, float initialDistance, float distance){
                super.zoom(event, initialDistance, distance);
                System.out.println("zoom:" +initialDistance + " " + distance );
                if(initialDistance-distance >0)
                    ((OrthographicCamera)dynamicStage.getCamera()).zoom += 0.02f;
                else
                    ((OrthographicCamera)dynamicStage.getCamera()).zoom -= 0.02f;
                dynamicStage.getViewport().getCamera().update();
            }

            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                event.getListenerActor().moveBy(deltaX, deltaY);
                if (deltaX < 0)
                {
                    System.out.println("panning " + deltaX + ", " + deltaY + " " + event.getTarget());
                    ((OrthographicCamera)dynamicStage.getCamera()).translate(-10,0,0);
                }
                if (deltaX > 0)
                {
                    System.out.println("panning " + deltaX + ", " + deltaY + " " + event.getTarget());
                    ((OrthographicCamera)dynamicStage.getCamera()).translate(10,0,0);
                }
                if (deltaY > 0)
                {
                    System.out.println("panning " + deltaX + ", " + deltaY + " " + event.getTarget());
                    ((OrthographicCamera)dynamicStage.getCamera()).translate(0,10,0);
                }
                if (deltaY < 0)
                {
                    System.out.println("panning " + deltaX + ", " + deltaY + " " + event.getTarget());
                    ((OrthographicCamera)dynamicStage.getCamera()).translate(0,-10,0);
                }
                dynamicStage.getViewport().getCamera().update();

            }
        });

        Texture playTexture2 = Assets.getTexture(Assets.PLAY_BUTTON);
        ImageButton play2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(playTexture2)));
        play2.setPosition(app.SCREEN_WIDTH /2 - play2.getWidth() / 2, (int)(app.SCREEN_HEIGHT) + picSkyMap.getHeight()/2 - play2.getHeight() / 2 -400);
        dynamicStage.addActor(play2);
        play2.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button){
                super.tap(event, x, y, count, button);
                app.setScreen(new PlayScreen(app));
                dispose();
            }
        });

        Texture playTexture = Assets.getTexture(Assets.PLAY_BUTTON);
        ImageButton play = new ImageButton(new TextureRegionDrawable(new TextureRegion(playTexture)));
        play.setPosition(app.SCREEN_WIDTH /2 - play.getWidth() / 2 -100, (int)(app.SCREEN_HEIGHT) + picSkyMap.getHeight()/2 - play.getHeight() / 2 -100);
        dynamicStage.addActor(play);
        play.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button){
                super.tap(event, x, y, count, button);
                app.setScreen(new PlayScreen(app));
                dispose();
            }
        });


        Texture playTexture3 = Assets.getTexture(Assets.PLAY_BUTTON);
        ImageButton play3 = new ImageButton(new TextureRegionDrawable(new TextureRegion(playTexture3)));
        play3.setPosition(app.SCREEN_WIDTH /2 - play3.getWidth() / 2 -350, (int)(app.SCREEN_HEIGHT ) + picSkyMap.getHeight()/2 - play3.getHeight() / 2 -100);
        dynamicStage.addActor(play3);
        play3.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button){
                super.tap(event, x, y, count, button);
                app.setScreen(new PlayScreen(app));
                dispose();
            }
        });

        Texture infoTexture = Assets.getTexture(Assets.INFO_BUTTON);
        ImageButton info = new ImageButton(new TextureRegionDrawable(new TextureRegion(infoTexture)));
        info.setPosition(Gdx.graphics.getWidth()  - info.getWidth() -15,  Gdx.graphics.getHeight()- info.getHeight()-15 );
        staticStage.addActor(info);
        info.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button){
                super.tap(event, x, y, count, button);
                //-----------------------------------------------------------------
         //       app.setScreen(new InfoScreen(app));
                //--------------------------------------------------------------------
                dispose();
            }
        });

        Texture settingTexture = Assets.getTexture(Assets.SETTING_BUTTON);
        ImageButton setting = new ImageButton(new TextureRegionDrawable(new TextureRegion(settingTexture)));
        setting.setPosition(Gdx.graphics.getWidth()  - setting.getWidth() -15,  Gdx.graphics.getHeight()- info.getHeight() - setting.getHeight()- 2 *15 );
        staticStage.addActor(setting);
        setting.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button){
                super.tap(event, x, y, count, button);

            //    app.setScreen(new SettingScreen(app));
                dispose();
            }
        });

        Texture recordsTexture = Assets.getTexture(Assets.RECORDS_BUTTON);
        ImageButton records = new ImageButton(new TextureRegionDrawable(new TextureRegion(recordsTexture)));
        records.setPosition(Gdx.graphics.getWidth() - records.getWidth() -15,  Gdx.graphics.getHeight() - info.getHeight() - setting.getHeight()-  records.getHeight()-4*15 );
        staticStage.addActor(records);
        records.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button){
                super.tap(event, x, y, count, button);

          //      app.setScreen(new RecordsScreen(app));
                dispose();
            }
        });

    }

    private void clearScreen(){
        Gdx.gl.glClearColor(Color.BROWN.r, Color.BROWN.g, Color.BROWN.b, Color.BROWN.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        dynamicStage.getViewport().apply();
        dynamicStage.act(delta);
        dynamicStage.draw();
        staticStage.getViewport().apply();
        staticStage.act(delta);
        staticStage.draw();

    }

    @Override
    public void resize(int width, int height){
        dynamicStage.getViewport().update(width,height, false);
        staticStage.getViewport().update(width,height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
