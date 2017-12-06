package org.academiadecodigo.gamestates.playstate;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import org.academiadecodigo.GameStateManager;
import org.academiadecodigo.State;
import org.academiadecodigo.grid.Grid;
import org.academiadecodigo.grid.GridPosition;
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

    protected void shoot() {

        if (ballInMotion) {
            return;
        }

        activeBall.setVelocity(ballVelocity());

        ballInMotion = true;
    }

    /*private Vector2 getTouchPosition() {

        Vector3 touchPosition3 = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

        return new Vector2(touchPosition3.x, touchPosition3.y);

    }*/

    protected void rotateCannonRight() {

        cannon.getSprite().setRotation(cannon.getRotation() + 2);
    }

    protected void rotateCannonLeft() {

        cannon.getSprite().setRotation(cannon.getRotation() - 2);
    }

    @Override
    public void update(float dt) {

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

        for (GridPosition[] gridPositions : grid.getGridPositions()) {
            for (GridPosition gridPosition : gridPositions) {

                gridPosition.getSprite().draw(sb);
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

            if (ball.isColliding()) {
                if (!ball.isFit()) {
                    grid.fitBall(ball);
                    ball.setFit(true);
                }
            }


            if (Math.pow(ball.getX() - activeBall.getX(), 2) + Math.pow(ball.getY() - activeBall.getY(), 2) <= Math.pow(45, 2) && ball != activeBall) {
                activeBall.stop();

                if (!activeBall.isFit()) {
                    grid.fitBall(activeBall);
                    activeBall.setFit(true);
                }

            }

        }
    }

    public Cannon getCannon() {
        return cannon;
    }

}
