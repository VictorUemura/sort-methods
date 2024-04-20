import java.util.Random;

public class Utils {
    private static Random random = new Random();

    public static int randomInt(int max) {
        return random.nextInt(max);
    }

    public static int randomInt() {
        return random.nextInt();
    }

    public static int randomInt(int min, int max) {
        return random.nextInt(min, max);
    }
}
