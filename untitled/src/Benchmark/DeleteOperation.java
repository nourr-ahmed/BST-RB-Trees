package Benchmark;

import Trees.ITree;

import java.util.Random;

public class DeleteOperation implements BenchmarkOperation {
    @Override
    public OperationResult runOperation(int[] input, ITree tree, Random rand) {
        int[] deleteArr = new int[(int)(input.length * 0.2)];
        for (int i = 0; i < deleteArr.length; i++) {
            deleteArr[i] = input[rand.nextInt(input.length)];
        }
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < deleteArr.length; i++) {
            tree.delete(deleteArr[i]);
        }
        long endTime = System.currentTimeMillis();
        return new OperationResult(endTime - startTime);
    }

    @Override
    public String getName() {
        return "Delete-Operation";
    }
}
