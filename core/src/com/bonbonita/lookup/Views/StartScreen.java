package com.bonbonita.lookup.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bonbonita.lookup.LookUp;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.scaleTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

/**
 * Created by BonBonita on 28.01.2019.
 */

public class StartScreen implements Screen {
    private LookUp app;
    private Stage stage;
    private boolean timerIsOn = false;// для Сплєш Скрин


    public StartScreen(final LookUp app) {
        this.app = app;

    }
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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

    @Override
    public void show() {
        stage = new Stage(new FitViewport(app.SCREEN_WIDTH, app.SCREEN_HEIGHT));
        Gdx.input.setInputProcessor(stage);

        Texture backgroundTexture = Assets.getTexture(Assets.NIGHTCITY_BG);
        Image background = new Image(backgroundTexture);
        background.setScale((float)(app.SCREEN_WIDTH  / background.getWidth()),(float)(app.SCREEN_HEIGHT / background.getHeight() ));
        stage.addActor(background);


// для Сплєш Скрин
        if(!timerIsOn) {
            timerIsOn = true;

            Timer.schedule(new Timer.Task() {

                @Override
                public void run() {
                    app.setScreen(app.menuScreen);
                    dispose();
                }

            }, 3);

        } else if(Gdx.input.isTouched()) {
            // Remove the task so we don't call changeScreen twice:
            Timer.instance().clear();
            app.setScreen(app.menuScreen);
            dispose();
        }
    }
    @Override
    public void render(float delta) {
        clearScreen();
        stage.act(delta);
        stage.draw();
    }

    private void clearScreen(){
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}

