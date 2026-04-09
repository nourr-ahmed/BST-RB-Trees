package Benchmark;

import Trees.ITree;

import java.util.Random;

public class ContainsOperation implements BenchmarkOperation {
    public OperationResult runOperation(int[] input, ITree tree, Random rand) {
        int length = input.length;
        int[] inArray = new int[length / 2];
        int[]  outArray = new int[length / 2];
        for (int i = 0; i < inArray.length; i++) {
            inArray[i] = input[rand.nextInt(length)];
            outArray[i] = 10*length + rand.nextInt(10 *  length);
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < length / 2; i++) {
            tree.contains(inArray[i]);
            tree.contains(outArray[i]);
        }
        long endTime = System.currentTimeMillis();
        return new OperationResult(endTime - startTime);
    }

    public String getName() {
        return "Contains-Operation";
    }
}
