package de.nicolas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class Chapter43AABBCollision extends ApplicationAdapter {

    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private Sprite playerSprite;
    private Sprite coinSprite;

    // bounding boxes
    private Rectangle playerBounds;
    private Rectangle coinBounds;

    // A flag to ensure, collision is only logged once
    private boolean coinCollected = false;

    @Override
    public void create() {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        Texture playerTexture = new Texture("p1_walk/p1_walk02.png");
        Texture coinTexture = new Texture("Coin.png");

        playerSprite = new Sprite(playerTexture);
        coinSprite = new Sprite(coinTexture);
        coinSprite.setSize(50, 50);

        // set initial positions
        playerSprite.setPosition(100, 100);
        coinSprite.setPosition(300, 300);

        // initialize the bounding boxes
        playerBounds = new Rectangle(
            playerSprite.getX(),
            playerSprite.getY(),
            playerSprite.getWidth(),
            playerSprite.getHeight()
        );
        coinBounds = new Rectangle(
            coinSprite.getX(),
            coinSprite.getY(),
            coinSprite.getWidth(),
            coinSprite.getHeight()
        );
    }

    @Override
    public void render() {
        // update player position with keyboard input
        float playerSpeed = 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            playerSprite.translateX(playerSpeed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            playerSprite.translateX(-playerSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            playerSprite.translateY(playerSpeed);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            playerSprite.translateY(-playerSpeed);
        }

        // update the bounding box
        playerBounds.setPosition(playerSprite.getX(), playerSprite.getY());

        // check for collision
        if (!coinCollected && playerBounds.overlaps(coinBounds)){
            Gdx.app.log("Collision", "!Player collected the coin.");
            coinCollected = true;
        }

        ScreenUtils.clear(0.2f,0.2f, 0.2f, 1f);
        batch.begin();
        playerSprite.draw(batch);

        if (!coinCollected){
            coinSprite.draw(batch);
        }
        batch.end();

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.rect(playerSprite.getX(), playerSprite.getY(), playerSprite.getWidth(), playerSprite.getHeight());
        renderer.rect(coinSprite.getX(), coinSprite.getY(), coinSprite.getWidth(), coinSprite.getHeight());
        renderer.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        playerSprite.getTexture().dispose();
        coinSprite.getTexture().dispose();
        renderer.dispose();
    }
}
