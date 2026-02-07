package de.nicolas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Chapter23Test extends ApplicationAdapter {

    private AssetManager assetManager;
    private SpriteBatch batch;
    private Texture splashTexture;

    private boolean assetsLoaded = false;

    @Override
    public void create() {
        assetManager = new AssetManager();
        batch = new SpriteBatch();

        splashTexture = new Texture("libgdx.png");

        assetManager.load("Backgrounds/Stars.png", Texture.class);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);

        if (!assetsLoaded){
            assetsLoaded = assetManager.update();

            Gdx.app.log("loader", "Progress: " +assetManager.getProgress() * 100 + "%");

            batch.begin();
            batch.draw(splashTexture,
                Gdx.graphics.getWidth() / 2f - splashTexture.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f - splashTexture.getHeight() / 2f);
            batch.end();
        } else {
            Texture backgroundTexture = assetManager.get("Backgrounds/Stars.png", Texture.class);
            batch.begin();
            batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.end();
        }
    }

    @Override
    public void dispose() {
        if (assetManager != null){
            assetManager.dispose();
        }
        if (splashTexture != null){
            splashTexture.dispose();
        }
        if (batch != null){
            batch.dispose();
        }
    }
}
