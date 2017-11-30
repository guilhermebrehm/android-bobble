package org.academiadecodigo.grid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import org.academiadecodigo.sprites.Ball;

/**
 * Created by codecadet on 30/11/2017.
 */

public class Position {

    private Ball ball;
    private Sprite sprite;

    public Position(int x, int y) {

        sprite = new Sprite(new Texture("old-cannon.png"));
        sprite.setSize(50, 50);
        sprite.setX(x);
        sprite.setY(y);
    }

    public Ball getBall() {
        return ball;
    }

    public boolean isOccupied(){
        return ball != null;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
