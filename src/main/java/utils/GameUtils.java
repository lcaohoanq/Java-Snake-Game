package utils;

public class GameUtils {

    public static int getRandomPosition(int rand_pos, int wallThickness) {
        return (int) (Math.random() * (rand_pos - 2 * wallThickness));
    }

    public static int getApplePosition(int rand_pos, int wallThickness, int dotSize) {
        int r = getRandomPosition(rand_pos, wallThickness);
        return ((r + wallThickness) * dotSize);
    }

}
