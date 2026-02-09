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
        assetManager.load("test.atlas", TextureAtlas.class);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.2f,0.2f, 0.2f, 1f);

        if (assetManager.update()){
            if (atlas == null) {
                atlas = assetManager.get("test.atlas", TextureAtlas.class);

                // get all regions for the walking animation
                Array<TextureRegion> walkingFrames = new Array<>();
                walkingFrames.add(atlas.findRegion("p1_walk01"));
                walkingFrames.add(atlas.findRegion("p1_walk02"));
                walkingFrames.add(atlas.findRegion("p1_walk03"));
                walkingFrames.add(atlas.findRegion("p1_walk04"));
                walkingFrames.add(atlas.findRegion("p1_walk05"));
                walkingFrames.add(atlas.findRegion("p1_walk06"));
                walkingFrames.add(atlas.findRegion("p1_walk07"));
                walkingFrames.add(atlas.findRegion("p1_walk08"));
                walkingFrames.add(atlas.findRegion("p1_walk09"));
                walkingFrames.add(atlas.findRegion("p1_walk101"));
                walkingFrames.add(atlas.findRegion("p1_walk11"));
                walkingAnimation = new Animation<>(0.08f, walkingFrames);

                // get all regions for the idle animation
                Array<TextureRegion> idleframes = new Array<>();
                idleframes.add(atlas.findRegion("p1_walk02"));
                idleAnimation = new Animation<>(0.12f, idleframes);

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

            
        }
    }
}
