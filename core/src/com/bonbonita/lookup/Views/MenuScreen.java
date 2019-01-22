package com.bonbonita.lookup.Views;

/**
 * Created by BonBonita on 22.01.2019.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bonbonita.lookup.LookUp;

/**
 * Created by BonBonita on 25.11.2018.
 */

public class MenuScreen implements Screen {
    float r = 255f;
    float g = 255f;
    float b = 255f;
    private Stage stage;
    private static final float WORLD_WIDTH = Gdx.graphics.getWidth();
    private static final float WORLD_HEIGHT = Gdx.graphics.getHeight();
    private final LookUp lookUp;
    private Texture playTexture;

    public MenuScreen(final LookUp lookUp) {
        this.lookUp = lookUp;
    }
    @Override
    public void show() {
        stage = new Stage(new FitViewport(WORLD_WIDTH, WORLD_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        playTexture = new Texture(Gdx.files.internal("images\\play.png"));
        ImageButton play = new ImageButton(new TextureRegionDrawable(new TextureRegion(playTexture)));
        play.setPosition(WORLD_WIDTH /2 - play.getWidth() / 2, WORLD_HEIGHT / 8 - play.getHeight() / 2 );
        stage.addActor(play);
        play.addListener(new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button){
                super.tap(event, x, y, count, button);

                r -=10;
                g -= 10;
                b -= 10;
                // dispose();
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(r/255.0f, g/255.0f, b/255f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
