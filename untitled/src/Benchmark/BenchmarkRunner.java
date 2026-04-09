package Benchmark;

import InputGenerators.InputGeneratorI;
import InputGenerators.NealySortedGenerator;
import InputGenerators.RandomGenerator;
import Models.Stats;
import Trees.BSTree;
import Trees.ITree;
import Trees.RBTree;

import java.util.*;
import java.util.function.Supplier;

public class BenchmarkRunner {
    private final List<InputGeneratorI> inputGenerators;
//    private final List<ITree> trees;
    private final List<Supplier<ITree>> treeFactories;
    private final List<BenchmarkOperation> benchmarkOperations;
    private final int N;
    private final int Runs;

    public BenchmarkRunner() {
        N = 100000;
        Runs = 8;
        inputGenerators = Arrays.asList(new RandomGenerator(),
                new NealySortedGenerator(0.01), new NealySortedGenerator(0.05), new NealySortedGenerator(0.1));
        treeFactories = Arrays.asList(
                BSTree::new,
                RBTree::new
        );
        benchmarkOperations = Arrays.asList(new InsertionOperation(), new ContainsOperation(),
                new DeleteOperation(), new SortOperation());
    }
    public void runBenchmarks() {
        int seed = 37;
        Random random = new Random(seed);
        List<Double> speedups = new ArrayList<>();
        for (InputGeneratorI input: inputGenerators) {
            int[] arr = input.generateInput(N, random);

            Map<String, Double> treeMeanTimes = new HashMap<>();
            for (Supplier<ITree> factory : treeFactories) {
                ITree tree = factory.get();
                for (BenchmarkOperation operation: benchmarkOperations){
                    long[] runTimes = new long[Runs];
                    int height = -1;

                    for (int i  = 0; i < Runs; i++){
                        OperationResult result = operation.runOperation(arr, tree, random);
                        runTimes[i] = result.time;
                        if (operation instanceof InsertionOperation) height = result.height;
                    }
                    long[] runTimes2 = Arrays.copyOfRange(runTimes, 2, Runs);
                    Stats stats = computeStatistics(runTimes2);
                    treeMeanTimes.put(tree.getName() + "-" + operation.getName(), stats.getMeanTime() );
                }


            }

            double meanBST = treeMeanTimes.get("BST-Insert");
            double meanRB = treeMeanTimes.get("RBTree-Insert");
            double speedup = meanBST / meanRB;
            speedups.add(speedup);
        }
    }

    private Stats computeStatistics(long[] runTimes) {
        double meanTime,  medianTime, stdevTime;
        double sum = 0;
        int newRuns = Runs - 2;
        for  (int i = 0; i < newRuns; i++){
            sum += runTimes[i];
        }
        meanTime = sum / newRuns;

        long[] copy = runTimes.clone();
        Arrays.sort(copy);
        if (newRuns % 2 == 0) medianTime = (copy[newRuns / 2 - 1] + copy[newRuns / 2]) / 2.0;
        else medianTime = (double) copy[newRuns / 2];

        sum = 0;
        for (int i = 0; i < newRuns; i++){
            sum += (runTimes[i] - meanTime) *  (runTimes[i] - meanTime);
        }
        stdevTime = Math.sqrt(sum / newRuns);
        return new Stats(meanTime, medianTime, stdevTime);
    }
}
