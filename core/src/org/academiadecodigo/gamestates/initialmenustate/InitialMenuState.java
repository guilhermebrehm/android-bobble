package org.academiadecodigo.gamestates.initialmenustate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import org.academiadecodigo.GameStateManager;
import org.academiadecodigo.State;

/**
 * Created by guilherme on 01-12-2017.
 */

public class InitialMenuState extends State {

    private OrthographicCamera cam;
    private Sprite splashScreen;

    public InitialMenuState(GameStateManager gsm) {

        super(gsm);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        cam = new OrthographicCamera(w, h);

        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();

        GestureDetector gestureDetector = new GestureDetector(new InitialMenuStateGestureListener(gsm));
        Gdx.input.setInputProcessor(gestureDetector);

        splashScreen = new Sprite(new Texture("splashscreen.jpg"));
        splashScreen.setSize(cam.viewportWidth, cam.viewportHeight);

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        splashScreen.draw(sb);
        sb.end();

    }

    @Override
    public void dispose() {

    }
}
