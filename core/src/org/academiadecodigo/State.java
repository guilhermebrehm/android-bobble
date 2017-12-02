package org.academiadecodigo;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by codecadet on 21/11/17.
 */
public abstract class State {

    protected OrthographicCamera cam;
    protected GameStateManager gsm;

    protected State(GameStateManager gsm) {
        this.gsm = gsm;
        cam = new OrthographicCamera();
    }

    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
