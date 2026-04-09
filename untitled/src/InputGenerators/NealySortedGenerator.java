package InputGenerators;

import java.util.Random;

public class NealySortedGenerator implements InputGeneratorI {
    double percentage;
    public NealySortedGenerator(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public int[] generateInput(int n, Random rand) {
        int[] arr  = new int[n];
        for (int i  = 0; i < n; i++) {
            arr[i] = rand.nextInt(10*n);
        }
        int swaps = (int) percentage * n;
        for (int i  = 0; i < swaps; i++) {
            int idx1 = rand.nextInt(n);
            int idx2 = rand.nextInt(n);
            int tmp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = tmp;
        }
        return arr;
    }
}
