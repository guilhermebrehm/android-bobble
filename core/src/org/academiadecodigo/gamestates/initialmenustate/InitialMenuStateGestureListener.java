package org.academiadecodigo.gamestates.initialmenustate;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import org.academiadecodigo.GameStateManager;
import org.academiadecodigo.gamestates.playstate.PlayState;

/**
 * Created by guilherme on 02-12-2017.
 */

public class InitialMenuStateGestureListener implements GestureDetector.GestureListener{

    private GameStateManager gsm;

    public InitialMenuStateGestureListener(GameStateManager gsm) {

        this.gsm = gsm;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {

        gsm.push(new PlayState(gsm));
        return true;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
