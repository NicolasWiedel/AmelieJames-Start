package de.nicolas;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Chapter42InputProcessor extends ApplicationAdapter {

    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // create our input processors
        InputProcessor gameProcessor = new GameInputProcessor();
        InputProcessor uiProcessor = new UIInputProcessor();

        // create an InputMultiplexer and add processors in order of priority
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(uiProcessor);
        multiplexer.addProcessor(gameProcessor);

        // set the Multiplexer as the main input processor
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1);

        batch.begin();

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    // InputProcessor for game world input
    private class GameInputProcessor implements InputProcessor{

        @Override
        public boolean keyDown(int keycode) {
            Gdx.app.log("GameInput", "KeyDown: " + keycode);
            //Return false, to allow another Processor to handle it.
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            Gdx.app.log("GameInput", "KeyUp: " + keycode);
            //Return false, to allow another Processor to handle it.
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            Gdx.app.log("GameInput", "Game world touchDown at: " + screenX + "/" + screenY);
            //Return false, to allow another Processor to handle it.
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(float amountX, float amountY) {
            return false;
        }
    }

    // InputProcessor for handling UI input
    private class UIInputProcessor implements InputProcessor{

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            // Assume we have a UI button in the top left 100x100 pixels
            if (screenX < 100 && screenY < 100){
                Gdx.app.log("UIInput", "UIButton clicked");
                return true;
            }
            // Otherwise return false to pass it to the next processor
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(float amountX, float amountY) {
            return false;
        }
    }
}
