package org.academiadecodigo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import org.academiadecodigo.grid.Grid;
import org.academiadecodigo.grid.Position;
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
    private Grid grid;

    private Ball activeBall;
    private boolean ballInMotion;

    private OrthographicCamera cam;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        grid = new Grid();

        cannon = new Cannon();

        balls = new LinkedList<Ball>();

        activeBall = new Ball(cannon);
        ballInMotion = false;

        balls.add(activeBall);

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        cam = new OrthographicCamera(w, h);

        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();

        GestureDetector gestureDetector = new GestureDetector(new PlayStateGestureListener(this));
        Gdx.input.setInputProcessor(gestureDetector);
    }

    @Override
    public void handleInput() {

    }

    protected void shoot() {

        activeBall.setVelocity(ballVelocity());

        ballInMotion = true;
    }

    private Vector2 getTouchPosition() {

        Vector3 touchPosition3 = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

        return new Vector2(touchPosition3.x, touchPosition3.y);

    }

    private void handleTouchInput() {

        if(!Gdx.input.isTouched()) {
            return;
        }

        Vector2 touchPosition = getTouchPosition();

        if(touchPosition.x < Gdx.graphics.getWidth() / 2) {

            rotateCannonLeft();

        } else if (touchPosition.x > Gdx.graphics.getWidth() / 2) {

            rotateCannonRight();
        }

    }

    protected void rotateCannonRight() {

        cannon.getSprite().setRotation(cannon.getRotation() + 2);
    }

    protected void rotateCannonLeft() {

        cannon.getSprite().setRotation(cannon.getRotation() - 2);
    }

    private void pointCannonToPointer() {

        Vector2 touchPosition = getTouchPosition();

        Vector2 velocityVector = new Vector2(cannon.getCenterPositionVector().x - touchPosition.x,
                touchPosition.y - cannon.getCenterPositionVector().y).nor().scl(10);

        cannon.getSprite().setRotation(-(int) velocityVector.angle() + 180);

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

        for(Position[] positions : grid.getPositions()) {
            for(Position position : positions) {

                position.getSprite().draw(sb);
            }
        }

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

    public Cannon getCannon() {
        return cannon;
    }
}
