package de.nicolas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Chapter2 extends ApplicationAdapter {

    private static final String BACKGROUND_PATH = "Backgrounds/black.png";
    private static final String PLAYER_PATH = "PNG/playerShip1_blue.png";

    private SpriteBatch batch;
    private AssetManager assetManager;

    @Override
    public void create() {
        assetManager = new AssetManager();
        batch = new SpriteBatch();

        assetManager.load(BACKGROUND_PATH, Texture.class);
        assetManager.load(PLAYER_PATH, Texture.class);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1f);

        if (assetManager.update()){
            // loading is complete!
            Texture backgroundTexture = assetManager.get(BACKGROUND_PATH, Texture.class);
            Texture playerTexture = assetManager.get(PLAYER_PATH, Texture.class);

            batch.begin();
            batch.draw(backgroundTexture,
                0, 0,
                backgroundTexture.getWidth() * 4, backgroundTexture.getHeight()* 4);
            batch.draw(playerTexture, 100, 100);
            batch.end();
        } else {
            // Loading bar
            float progress = assetManager.getProgress();
            Gdx.app.log("Loading", "loadingprogress: " + (int) (progress * 100) + "%");
        }
    }
}
