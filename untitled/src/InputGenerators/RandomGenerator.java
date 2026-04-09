package InputGenerators;

import java.util.Random;

public class RandomGenerator implements InputGeneratorI {
    @Override
    public int[] generateInput(int n,  Random rand) {
        int[] arr  = new int[n];
        for (int i  = 0; i < n; i++) {
            arr[i] = rand.nextInt(10*n);
        }
        return arr;
    }
}
