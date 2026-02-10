package de.nicolas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Chapter41Input extends ApplicationAdapter {

    private SpriteBatch batch;
    private Sprite playerSprite;
    private float playerSpeed = 200.0f;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Texture playerTexture = new Texture("p1_walk/p1_walk01.png");
        playerSprite = new Sprite(playerTexture);
        playerSprite.setPosition(Gdx.graphics.getWidth() /2f - playerSprite.getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f - playerSprite.getHeight() / 2f);
    }

    @Override
    public void render() {
        // get the time since the last frame for frame rate independent movement
        float deltaTime = Gdx.graphics.getDeltaTime();

        // check keyboard input
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            playerSprite.translateX(playerSpeed * deltaTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            playerSprite.translateX(-playerSpeed * deltaTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            playerSprite.translateY(playerSpeed * deltaTime);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            playerSprite.translateY(-playerSpeed * deltaTime);
        }

        // Check for touch input (mobile/desktop)
        if (Gdx.input.isTouched()){
            int touchX = Gdx.input.getX();
            int touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

            // move the sprite towards the touchpoint
            if (touchX > playerSprite.getX() + playerSprite.getWidth() / 2f){
                playerSprite.translateX(playerSpeed * deltaTime);
            }
            if (touchX < playerSprite.getX() + playerSprite.getWidth() / 2f) {
                playerSprite.translateX(-playerSpeed * deltaTime);
            }
            if (touchY > playerSprite.getY() + playerSprite.getHeight() / 2f){
                playerSprite.translateY(playerSpeed * deltaTime);
            }
            if (touchY < playerSprite.getY() + playerSprite.getHeight() / 2f){
               playerSprite.translateY(-playerSpeed * deltaTime);
            }
        }

        ScreenUtils.clear(0.2f, 0.2f, 0.2f,1f);

        batch.begin();
        playerSprite.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        playerSprite.getTexture().dispose();
    }
}
