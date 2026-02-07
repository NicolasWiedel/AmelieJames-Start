package de.nicolas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Chapter23 extends ApplicationAdapter {

    private SpriteBatch batch;
    private Sprite playerSprite;
    private OrthographicCamera camera;

    private static final float WORLD_WIDTH = 800f;
    private static final float WORLD_HEIGHT = 600f;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // 1. Initialize Camera
        camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
        // position the camera at the center of our world
        camera.position.set(WORLD_WIDTH / 2f, WORLD_HEIGHT / 2f, 0);

        // Load the player sprite
        Texture playerTexture = new Texture(Gdx.files.internal("PNG/playerShip1_blue.png"));
        playerSprite = new Sprite(playerTexture);
        playerSprite.setPosition(100, 100);
    }

    @Override
    public void render() {
        // 2. update the camera´s position o follow the player
        camera.position.x = playerSprite.getX() + playerSprite.getWidth() / 2;
        camera.position.y = playerSprite.getY() + playerSprite.getHeight() / 2;
        camera.update();

        // 3. Clear the screen
        ScreenUtils.clear(0.388f, 0.584f, 0.933f, 0.0f);

        // 4. Tell the SpriteBatch to use our camera´s matrices
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // 5. Draw the Sprite at its world coordinates
        playerSprite.draw(batch);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // adjust the camera´s viewport when the window size changes
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT * height / width);
        camera.update();
    }

    @Override
    public void dispose() {
        batch.dispose();
        playerSprite.getTexture().dispose();
    }
}
