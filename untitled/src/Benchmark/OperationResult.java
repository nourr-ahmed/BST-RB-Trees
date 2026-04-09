package Benchmark;

import Trees.ITree;

public class OperationResult {
    long time;
    int height;
//    TreeType treeType;
    public OperationResult(long time, int height) {
        this.time = time;
        this.height = height;
    }
    public OperationResult(long time) {
        this.time = time;
    }
}
