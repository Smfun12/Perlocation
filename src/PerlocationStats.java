import java.util.ArrayList;

class PercolationStats {
    double sum;
    double product;
    int T;
    ArrayList<Double> arrayList = new ArrayList<>();
    public PercolationStats(int N, int T) {
        Percolation percolation;

        this.T = T;
        for (int i = 0; i < T; i++) {
            percolation = new Percolation(N);
            double x = percolation.getOpenedCount() / (double)(N*N);
            sum += (x / T);
            arrayList.add(x);
        }
        for (double d : arrayList){
            product += Math.pow(d - mean(),2) / (T-1);
        }
    }

    // проведемо T окремих експериментів в N на N матриці
    public double mean() {
     return sum;
    }

    // рахує середнє
    public double stddev() {
        return product;
    }

    // рахує відхилення
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int T = 100;
        PercolationStats percolationStats = new PercolationStats(200,T);
        System.out.println(percolationStats.mean());
        System.out.println(percolationStats.stddev());
        System.out.println("95% interval - > [" + (percolationStats.mean() -
                (1.96*Math.sqrt(percolationStats.stddev()) / Math.sqrt(T)))
                + ", " + (percolationStats.mean() + (1.96*Math.sqrt(percolationStats.stddev()) / Math.sqrt(T))) + "]");
        System.out.println("Seconds: " + (System.currentTimeMillis()-start)/1000 );
    }
// запускаємо експерименти і рахуємо відповідні значення середнє, відхилення, інтервал довіри, та виводимо на екран
}
