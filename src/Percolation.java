import java.util.Random;

public class Percolation {
    private final int[][] system;
    private final int numberOfCells;
    private final QuickUnionUF quickUnion;
    private int counter;
    private final WeightedQuickUnionUF quickUnionUF;
    private final boolean[][] booleans;
    public Percolation(int N){
        this.numberOfCells = N;
        quickUnion = new QuickUnionUF(N*N+2);
        quickUnionUF = new WeightedQuickUnionUF(N*N+2);
        system = new int[N][N];
        booleans = new boolean[N][N];
        int counter = 0;
        for (int i = 0; i < N;i++){
            for (int j = 0; j < N; j++){

                system[i][j] = counter;
                booleans[i][j] = false;
                if (i == 0) {
//                    quickUnion.union(N*N, system[i][j]);
                    quickUnionUF.union(N*N, system[i][j]);
                }
                if (i == N-1){
//                    quickUnion.union(N*N+1, system[i][j]);
                    quickUnionUF.union(N*N+1, system[i][j]);
                }
                counter++;
            }
        }
        while (!percolates())
        {
            Random random = new Random();
            Random random1 = new Random();
            int x = random.nextInt(N);
            int y = random1.nextInt(N);
            open(x,y);
        }
    }
    // створюємо матрицю N-на-N, з усіма заблокованими об’єктами
    public int getOpenedCount(){
            return counter;
    }

    public void open(int i, int j){
        if (isOpened(i,j))return;
        booleans[i][j] = true;
        counter++;
        if (i != 0){
        if (isOpened(i-1,j)){
//            quickUnion.union(system[i-1][j],system[i][j]);
            quickUnionUF.union(system[i-1][j],system[i][j]);
        }}
        if (j!=0){
        if (isOpened(i,j-1)){
//            quickUnion.union(system[i][j-1],system[i][j]);
            quickUnionUF.union(system[i][j-1],system[i][j]);
        }
        }
        if (i != numberOfCells-1){
        if (isOpened(i+1,j)){
//            quickUnion.union(system[i+1][j],system[i][j]);
            quickUnionUF.union(system[i+1][j],system[i][j]);
        }}
        if (j != numberOfCells-1){
        if (isOpened(i,j+1)){
//            quickUnion.union(system[i][j+1],system[i][j]);
            quickUnionUF.union(system[i][j+1],system[i][j]);
        }}
    }
    // відкрити об’єкт (row i, column j) якщо він ще не відкритий
    public boolean isOpened(int i, int j){
        return booleans[i][j];
    }
    // чи відкитий об’єкт (row i, column j)?
    public boolean percolates(){
//    return quickUnion.connected(numberOfCells*numberOfCells,numberOfCells*numberOfCells+1);
        return quickUnionUF.connected(numberOfCells*numberOfCells,numberOfCells*numberOfCells+1);
    }
// чи протікає система?
}
