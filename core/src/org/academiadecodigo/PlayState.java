package org.academiadecodigo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import org.academiadecodigo.sprites.Ball;
import org.academiadecodigo.sprites.Cannon;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by codecadet on 21/11/17.
 */
public class PlayState extends State {

    private List<Ball> balls;
    private Cannon cannon;

    private Ball activeBall;
    private boolean ballInMotion;

    private OrthographicCamera cam;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        cannon = new Cannon();

        balls = new LinkedList<Ball>();

        activeBall = new Ball(cannon);
        ballInMotion = false;

        balls.add(activeBall);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        cam = new OrthographicCamera(w,h);

        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
    }

    @Override
    public void handleInput() {

        Vector3 touchPosition3 = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

        Vector2 touchPosition = new Vector2(touchPosition3.x, touchPosition3.y);

        Vector2 velocityVector = new Vector2(cannon.getCenterPositionVector().x - touchPosition.x,
                touchPosition.y - cannon.getCenterPositionVector().y).nor().scl(10);

        cannon.getSprite().setRotation(-(int) velocityVector.angle() + 180);

        if (Gdx.input.isTouched() && !ballInMotion) {

            activeBall.setVelocity(ballVelocity());

            ballInMotion = true;
        }

    }

    @Override
    public void update(float dt) {

        handleInput();

        for (Ball ball : balls) {

            ball.update(dt);
        }

        detectCollisions();
    }

    @Override
    public void render(SpriteBatch sb) {

        cam.update();
        sb.setProjectionMatrix(cam.combined);

        sb.begin();

        cannon.getSprite().draw(sb);

        for (Ball ball : balls) {
            ball.getSprite().draw(sb);
        }

        sb.end();
    }

    @Override
    public void dispose() {

    }

    private Vector2 ballVelocity() {

        float rotationDeg = (float) Math.toRadians(cannon.getRotation());

        float horizontalV = (float) Math.cos(rotationDeg);
        float verticalV = (float) Math.sin(rotationDeg);

        return new Vector2(horizontalV, verticalV).scl(10);

    }

    private void detectCollisions() {

        if (!ballInMotion) {
            return;
        }

        if (!activeBall.isMoving()) {

            activeBall = new Ball(cannon);
            ballInMotion = false;

            balls.add(activeBall);
            return;
        }

        for (Ball ball : balls) {

            if (ball == activeBall) {
                continue;
            }

            if (Math.pow(ball.getX() - activeBall.getX(), 2) + Math.pow(ball.getY() - activeBall.getY(), 2) <= Math.pow(45, 2)) {
                activeBall.stop();

                if (activeBall.getBallType() == ball.getBallType()) {
                    balls.remove(activeBall);
                    balls.remove(ball);
                }
                return;
            }

        }
    }
}
