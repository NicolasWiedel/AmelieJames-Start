package de.nicolas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Chapter43ExerciseInput extends ApplicationAdapter {

    private SpriteBatch batch;
    private Sprite playerSprite;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Texture playerTexture = new Texture("p1_walk/p1_walk01.png");
        playerSprite = new Sprite(playerTexture);
        playerSprite.setOrigin(playerSprite.getWidth() / 2, playerSprite.getHeight() /2);
        playerSprite.setPosition(Gdx.graphics.getWidth() / 2f - playerSprite.getHeight() / 2f,
            Gdx.graphics.getHeight() / 2f - playerSprite.getHeight() / 2);
    }

    @Override
    public void render() {
        // check for mouse/touch input
        if (Gdx.input.isTouched()){
            // get raw screen coordinates
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();
            // convert screen coordinates (top left) to world coordinates (bottom left)
            float worldTouchY = Gdx.graphics.getHeight() - touchY;

            // update the sprite position to the new coordinates
            playerSprite.setPosition(touchX - playerSprite.getWidth() / 2,
                worldTouchY - playerSprite.getHeight() / 2);

            // calculate the angle between sprites new position and touch point
            // we use new position and a point slightly offset to find the direction vector
            Vector2 playerPosition = new Vector2(playerSprite.getX(), playerSprite.getY());
            Vector2 touchPosition = new Vector2(touchX, worldTouchY);
            // calculate the angle in radians from the direction vector
            float angle = MathUtils.atan2(touchPosition.y - playerPosition.y,
                touchPosition.x - playerPosition.x);
            // convert radians to degrees and adjust by 90° to align with sprites default orientation
            float rotation = angle * MathUtils.radDeg;
            playerSprite.setRotation(rotation);
        }

        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1f);
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
