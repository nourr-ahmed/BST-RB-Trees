package Benchmark;

import Trees.ITree;

import java.util.Random;

public class InsertionOperation implements BenchmarkOperation {
    @Override
    public OperationResult runOperation(int[] input, ITree tree, Random rand) {
        long startTime = System.nanoTime();
        for (int i = 0; i < input.length; i++) {
            tree.insert(input[i]);
        }
        long endTime = System.nanoTime();
        int height = tree.height();
        return new OperationResult(endTime - startTime, height);
    }

    @Override
    public String getName() {
        return "Insert-Operation";
    }
}
