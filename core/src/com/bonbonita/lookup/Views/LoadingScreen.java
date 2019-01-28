package com.bonbonita.lookup.Views;

/**
 * Created by BonBonita on 22.01.2019.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.bonbonita.lookup.LookUp;

/**
 * Created by BonBonita on 25.11.2018.
 */

public class LoadingScreen implements Screen {
    private final LookUp app;
    private SpriteBatch batcher;
    private ShapeRenderer shapeRenderer;
    private float progress;
    private float titleWidth;
    private String TITLE = "Загрузка...";
    private String TITLE1 = "Loading...";

    public LoadingScreen(final LookUp lookUp) {
        this.app = lookUp;
        shapeRenderer = new ShapeRenderer();

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(app.camera.combined);

        GlyphLayout layout = new GlyphLayout();
        layout.setText(Assets.mainFont, TITLE);
        titleWidth = layout.width;
    }
    @Override
    public void show() {
        shapeRenderer.setProjectionMatrix(app.camera.combined);
        this.progress = 0f;
        Assets.load();
    }

    private void update(float delta) {
        progress = MathUtils.lerp(progress, Assets.getProgress(), .1f);
        if (Assets.update() && progress >= Assets.getProgress() - .001f) {
            app.setScreen(app.splashScreen);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.enableBlending();
        // draw title
        Assets.mainFont.draw(batcher, TITLE, (app.SCREEN_WIDTH - titleWidth) / 2 - 1, app.SCREEN_HEIGHT / 2 + 50);
        Assets.mainFont.draw(batcher, TITLE1, (app.SCREEN_WIDTH - titleWidth) / 2 - 1, app.SCREEN_HEIGHT / 2 - 15);
        batcher.end();

        update(delta);

        int xOffset = 30;
        int yOffset = 10;

        // draw progress bar - red on black
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(xOffset, app.camera.viewportHeight / 2 - yOffset, app.camera.viewportWidth - xOffset * 2, yOffset * 2);

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(xOffset + 2, app.camera.viewportHeight / 2 - (yOffset - 2),
                progress * (app.camera.viewportWidth - (xOffset + 2) * 2), (yOffset - 2) * 2);
        shapeRenderer.end();
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
        batcher.dispose();
        shapeRenderer.dispose();
    }
}
