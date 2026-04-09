package Benchmark;

import Trees.ITree;

import java.util.Random;

public class SortOperation implements BenchmarkOperation {
    @Override
    public OperationResult runOperation(int[] input, ITree tree, Random rand) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < input.length; i++){
            tree.insert(input[i]);
        }
        tree.inOrder();
        long endTime = System.currentTimeMillis();
        return new OperationResult(endTime - startTime);

    }

    @Override
    public String getName() {
        return "Sort-Operation";
    }
}
