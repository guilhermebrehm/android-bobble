package org.academiadecodigo.grid;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import org.academiadecodigo.sprites.Ball;

/**
 * Created by codecadet on 30/11/2017.
 */

public class Grid {

    public static final int colNum = 8;
    public static final int rowNum = 8;

    private GridPosition[][] gridPositions;

    public Grid() {

        gridPositions = new GridPosition[rowNum][colNum];

        populatePosition();
    }

    private void populatePosition() {

        for (int i = 0; i < rowNum; i++) {

            for (int j = 0; j < colNum; j++) {

                int x = i % 2 == 0 ? j * 70 : j * 70 + 30;
                int y = i * 70 + 30;

                gridPositions[i][j] = new GridPosition(x, y);
            }
        }
    }

    public GridPosition[][] getGridPositions() {
        return gridPositions;
    }

    public void fitBall(Ball ball) {

        float x = ball.getCenterPosition().x;
        float y = ball.getCenterPosition().y;

        GridPosition gridPosition = getClosestAvailableGridPosition(x, y);

        gridPosition.occupy(ball);
        ball.setFit(true);

    }

    private GridPosition getClosestAvailableGridPosition(float x, float y) {

        GridPosition gridPosition = getRandomGridPosition();
        float rX = gridPosition.getSprite().getX() + gridPosition.getSprite().getWidth() / 2;
        float rY = gridPosition.getSprite().getY() + gridPosition.getSprite().getHeight() / 2;

        float distance = distance(x, y, rX, rY);

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {

                GridPosition thisGridPosition = gridPositions[i][j];

                float thisX = thisGridPosition.centerPosition().x;
                float thisY = thisGridPosition.centerPosition().y;

                float thisDistance = distance(x, y, thisX, thisY);

                if (thisDistance < distance && !thisGridPosition.isOccupied()) {
                    distance = thisDistance;
                    gridPosition = thisGridPosition;
                }
            }
        }

        return gridPosition;
    }

    private GridPosition getRandomGridPosition() {

        int i = (int) Math.random() * rowNum;
        int j = (int) Math.random() * colNum;

        return gridPositions[i][j];
    }

    private float distance(float x, float y, float x1, float y1) {

        return (float) Math.hypot(x - x1, y - y1);
    }
}
