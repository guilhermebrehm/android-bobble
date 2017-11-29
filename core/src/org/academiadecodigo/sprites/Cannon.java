package org.academiadecodigo.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import org.academiadecodigo.AndroidApp;

/**
 * Created by codecadet on 21/11/17.
 */
public class Cannon {

    private Sprite sprite;

    public Cannon() {

        Texture texture = new Texture("cannon.png");

        int positionX = Gdx.graphics.getWidth() / 2 - texture.getWidth() / 2;
        int positionY = Gdx.graphics.getHeight() / 5 - texture.getHeight() / 2;

        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        sprite = new Sprite(texture, 0, 0, texture.getWidth(), texture.getHeight());

        sprite.setX(positionX);
        sprite.setY(positionY);

        sprite.setRotation(90);
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Vector2 getCenterPositionVector() {

        return new Vector2(sprite.getX() + sprite.getWidth() / 2, sprite.getY() + sprite.getHeight() / 2);
    }

    public float getRotation() {

        return sprite.getRotation();
    }

}
