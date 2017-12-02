package org.academiadecodigo.grid;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by codecadet on 30/11/2017.
 */

public class Grid {

    private GridPosition[][] gridPositions;

    public Grid() {

        gridPositions = new GridPosition[8][8];

        populatePosition();
    }

    private void populatePosition() {

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                int x = i % 2 == 0 ? j * 60 : j * 60 + 30;
                int y = i * 60 + 30;

                gridPositions[i][j] = new GridPosition(x, y);
            }
        }
    }

    public GridPosition[][] getGridPositions() {
        return gridPositions;
    }

    public GridPosition getEnclosingGridPosition(Sprite sprite) {

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                if(gridPositions[i][j].isSpriteInside(sprite)) {


                }
            }
        }

    }
}
