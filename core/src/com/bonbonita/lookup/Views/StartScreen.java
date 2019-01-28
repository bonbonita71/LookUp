package com.bonbonita.lookup.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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

    public StartScreen(final LookUp app) {
        this.app = app;
        this.stage = new Stage(new FitViewport(app.SCREEN_WIDTH, app.SCREEN_HEIGHT, app.camera));
    }
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
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
        Runnable transitionRunnable = new Runnable() {
            @Override
            public void run() {
                app.setScreen(app.menuScreen);
            }
        };

        Texture splashTex = Assets.getTexture(Assets.LOGO);
        Image logo = new Image(splashTex);
        logo.setOrigin(logo.getWidth() / 2, logo.getHeight() / 2);
        logo.setPosition((stage.getWidth() - logo.getWidth()) / 2, (stage.getHeight() - logo.getHeight()) / 2);
        logo.addAction(sequence(alpha(0), scaleTo(.1f, .1f),
                parallel(fadeIn(2f, Interpolation.pow2),
                        scaleTo(2f, 2f, 1.5f, Interpolation.pow5),
                        scaleTo(1f, 1f, 1.5f, Interpolation.pow5)),
                fadeOut(1.25f), run(transitionRunnable)));

        stage.addActor(logo);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }
}

