package de.nicolas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class Chapter33Animation extends ApplicationAdapter {

    private SpriteBatch batch;
    private AssetManager assetManager;
    private TextureAtlas atlas;

    // Animations for our character´s stats
    private Animation<TextureRegion> walkingAnimation;
    private Animation<TextureRegion> idleAnimation;

    // State variable using an enum
    private enum State {IDLE, WALKING}

    private State currentState;

    // A variable to track the total elapsed time for the animation.
    private float stateTime;

    @Override
    public void create() {
        batch = new SpriteBatch();
        assetManager = new AssetManager();
        assetManager.load("p1_walk/test.atlas", TextureAtlas.class);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.2f,0.2f, 0.2f, 1f);

        if (assetManager.update()){
            if (atlas == null) {
                atlas = assetManager.get("p1_walk/test.atlas", TextureAtlas.class);

                // get all regions for the walking animation
                Array<TextureRegion> walkingFrames = new Array<>();
                walkingFrames.add(atlas.findRegion("p1_walk", 1));
                walkingFrames.add(atlas.findRegion("p1_walk", 2));
                walkingFrames.add(atlas.findRegion("p1_walk", 3));
                walkingFrames.add(atlas.findRegion("p1_walk", 4));
                walkingFrames.add(atlas.findRegion("p1_walk", 5));
                walkingFrames.add(atlas.findRegion("p1_walk", 6));
                walkingFrames.add(atlas.findRegion("p1_walk", 7));
                walkingFrames.add(atlas.findRegion("p1_walk", 8));
                walkingFrames.add(atlas.findRegion("p1_walk", 9));
                walkingFrames.add(atlas.findRegion("p1_walk", 10));
                walkingFrames.add(atlas.findRegion("p1_walk", 11));
                walkingAnimation = new Animation<>(0.08f, walkingFrames);

                // get all regions for the idle animation
                Array<TextureRegion> idleFrames = new Array<>();
                idleFrames.add(atlas.findRegion("p1_walk", 2));
                idleAnimation = new Animation<>(0.12f, idleFrames);

                currentState = State.IDLE;
            }

            // Get the time since last frame
            float deltaTime = Gdx.graphics.getDeltaTime();
            stateTime += deltaTime;

            TextureRegion currentFrame;

            // Check for input for transition states
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                currentState = State.WALKING;
            } else {
                currentState = State.IDLE;
            }

            // get the correct frame from the correct animation based on the current state
            if (currentState == State.WALKING){
                currentFrame = walkingAnimation.getKeyFrame(stateTime,true);
            } else {
                currentFrame = idleAnimation.getKeyFrame(stateTime, true);
            }

            batch.begin();
            batch.draw(currentFrame, (float)(Gdx.graphics.getWidth() /2 - currentFrame.getRegionWidth() /2),
                (float)(Gdx.graphics.getHeight() /2 - currentFrame.getRegionHeight() /2),
                currentFrame.getRegionWidth() * 2, currentFrame.getRegionHeight() * 2);
            batch.end();
        }

        else {
            // Display loading progress
            Gdx.app.log("Loading", "Progress: " + assetManager.getProgress() * 100 + "%");
        }
    }

    @Override
    public void dispose() {
        if (batch != null){
            batch.dispose();
        }
        if (assetManager != null){
            assetManager.dispose();
        }
    }
}
