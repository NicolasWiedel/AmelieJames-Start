package de.nicolas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class Chapter32TexturePacker extends ApplicationAdapter {

    private SpriteBatch batch;
    private AssetManager assetManager;
    private TextureAtlas atlas;

    private TextureRegion backgroundRegion;
    private TextureRegion playerRegion;

    @Override
    public void create() {
        batch = new SpriteBatch();
        assetManager = new AssetManager();

        assetManager.load("Spaceshooter.atlas", TextureAtlas.class);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.2f,0.2f, 0.2f,1f);

        if (assetManager.update()){
            if (atlas == null){
                atlas = assetManager.get("Spaceshooter.atlas", TextureAtlas.class);
                backgroundRegion = atlas.findRegion("black");
                playerRegion = atlas.findRegion("playerShip1_green");
            }

            batch.begin();
            batch.draw(backgroundRegion, 0, 0);
            batch.draw(playerRegion, 100, 100);
            batch.end();
        }
    }

    @Override
    public void dispose() {
        if(batch != null){
            batch.dispose();
        }
        if (assetManager != null){
            assetManager.dispose();
        }
    }
}
