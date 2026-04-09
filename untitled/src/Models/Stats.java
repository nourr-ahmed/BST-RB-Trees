package Models;

public class Stats {
    double meanTime;
    double medianTime;
    double stdevTime;
    public Stats(double meanTime, double medianTime, double stdevTime) {
        this.meanTime = meanTime;
        this.medianTime = medianTime;
        this.stdevTime = stdevTime;

    }
    public double getMeanTime() {
        return meanTime;
    }
}
