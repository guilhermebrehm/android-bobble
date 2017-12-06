package org.academiadecodigo.grid;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import org.academiadecodigo.sprites.Ball;

/**
 * Created by codecadet on 30/11/2017.
 */

public class GridPosition {

    private Ball ball;
    private Sprite sprite;

    public GridPosition(int x, int y) {

        sprite = new Sprite(new Texture("share.png"));
        sprite.setSize(64, 64);
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

    public boolean isSpriteInside(Sprite sprite) {

        float x = this.sprite.getX();
        float y = this.sprite.getY();

        float maxX = x + this.sprite.getWidth();
        float maxY = y + this.sprite.getHeight();

        float spriteCenterX = sprite.getX() + sprite.getWidth() / 2;
        float spriteCenterY = sprite.getY() + sprite.getHeight() / 2;

        if(spriteCenterX > x && spriteCenterX < maxX && spriteCenterY > y && spriteCenterY < maxY) {
            return true;
        }

        return false;
    }

    public void occupy(Ball ball) {

        this.ball = ball;
        float spriteX = sprite.getX();
        float spriteY = sprite.getY();

        sprite = ball.getSprite();
        sprite.setX(spriteX);
        sprite.setY(spriteY);

        ball.stop();
    }

    public Vector2 centerPosition() {

        return new Vector2(sprite.getX()+ sprite.getWidth() / 2,
                sprite.getY() + sprite.getHeight() / 2);
    }
}
