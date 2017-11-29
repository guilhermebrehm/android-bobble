package org.academiadecodigo.sprites;

/**
 * Created by codecadet on 21/11/17.
 */
public enum BallType {
    BLACK("black-pokeball.png"),
    BLUE("blue-pokeball.png"),
    GREEN("green-pokeball.png"),
    PURPLE("purple-pokeball.png"),
    RED("red-pokeball.png"),
    YELLOW("yellow-pokeball.png");

    private String filePath;

    BallType(String filePath) {
        this.filePath = filePath;
    }

    public static BallType getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }

    public String getFilePath() {
        return filePath;
    }
}
