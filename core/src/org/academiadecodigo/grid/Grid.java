package org.academiadecodigo.grid;

/**
 * Created by codecadet on 30/11/2017.
 */

public class Grid {

    private Position[][] positions;

    public Grid() {

        positions = new Position[8][8];

        populatePosition();
    }

    private void populatePosition() {

        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                positions[i][j] = new Position(j * 60, i * 60);
            }
        }
    }

    public Position[][] getPositions() {
        return positions;
    }
}
