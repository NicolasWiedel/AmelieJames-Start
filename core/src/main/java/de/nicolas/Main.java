package de.nicolas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Sprite sprite;
    private Texture texture;

    @Override
    public void create() {
        Gdx.app.log("Game Lifecycle", "create() method called");

        batch = new SpriteBatch();
        texture = new Texture("starGold.png");
        sprite = new Sprite(texture);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.388f, 0.584f, 0.933f, 0.0f);

        batch.begin();
        batch.draw(texture, Gdx.graphics.getWidth() / 2f - texture.getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f - texture.getHeight() / 2f);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("Game Lifecycle", "resize() method called with new " +
            "new dimension: " + width + " and " + height + ".");
    }

    @Override
    public void pause() {
        Gdx.app.log("Game Lifecycle", "pause() method called");
    }

    @Override
    public void resume() {
        Gdx.app.log("Game Lifecycle", "resume() method called");
    }

    @Override
    public void dispose() {
        Gdx.app.log("Game Lifecycle", "dispose() method called");
        batch.dispose();
        sprite.getTexture().dispose();
    }
}
