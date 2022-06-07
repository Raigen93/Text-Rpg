import java.util.Random;

public class RNG {

    public int randomNumber(int x) {
        Random random = new Random();
        int y = random.nextInt(x);
        return y;
    }

}
