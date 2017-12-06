package org.academiadecodigo.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by codecadet on 21/11/17.
 */
public class Ball {

    private Vector2 velocity;

    private BallType ballType;

    private Sprite sprite;

    private boolean moving;
    private boolean fit;

    public Ball(Cannon cannon) {

        ballType = BallType.getRandom();
        sprite = new Sprite(new Texture(ballType.getFilePath()));

        sprite.setCenterX(cannon.getCenterPositionVector().x);
        sprite.setCenterY(cannon.getCenterPositionVector().y);

        velocity = new Vector2(0, 0);

    }

    public void update(float dt) {

        checkCollision();
        sprite.setX(sprite.getX() + velocity.x);
        sprite.setY(sprite.getY() + velocity.y);
    }

    private void checkCollision() {

        if (sprite.getY() + sprite.getHeight() >= Gdx.graphics.getHeight()) {

            velocity.x = 0;
            velocity.y = 0;
            moving = false;

            return;
        }

        if (sprite.getY() <= 0) {

            velocity.x = 0;
            velocity.y = 0;
            moving = false;

            return;
        }

        if (sprite.getX() < 0) {

            velocity.x = -velocity.x;
            return;
        }

        if (sprite.getX() + sprite.getWidth() >= Gdx.graphics.getWidth()) {

            velocity.x = -velocity.x;
        }

    }

    public void stop() {
        velocity.x = 0;
        velocity.y = 0;
        moving = false;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
        moving = true;
    }


    public int getX() {
        return (int) sprite.getX();
    }

    public int getY() {
        return (int) sprite.getY();
    }

    public Vector2 getCenterPosition() {
        return new Vector2(sprite.getX() + sprite.getWidth() / 2,
                sprite.getY() + sprite.getHeight() / 2);
    }

    public boolean isMoving() {
        return moving;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isFit() {
        return fit;
    }

    public void setFit(boolean fit) {
        this.fit = fit;
    }

    public BallType getBallType() {
        return ballType;
    }
}
