package Benchmark;

import Trees.ITree;

import java.util.Random;

public interface BenchmarkOperation {
    OperationResult runOperation(int[] input, ITree tree, Random rand);

    String getName();
}
