package org.academiadecodigo;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by codecadet on 30/11/2017.
 */


public class PlayStateGestureListener implements GestureDetector.GestureListener {

    PlayState playState;

    public PlayStateGestureListener(PlayState playState) {

        this.playState = playState;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {

        playState.shoot();
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

        int initialRotation = (int) playState.getCannon().getRotation();

        if(deltaX > 0) {

            if(initialRotation + deltaX > playState.getCannon().getRotation()) {

                playState.rotateCannonLeft();

            }

        } else if (deltaX < 0) {

            if(initialRotation + deltaX < playState.getCannon().getRotation()) {

                playState.rotateCannonRight();

            }

        }

        return true;
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
