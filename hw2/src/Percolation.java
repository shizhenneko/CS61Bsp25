import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private enum Cell{
        CLOSED,OPEN
    }
    private Cell[][] plate;
    private int platesize;
    private int opensize;
    private int virtualtop;
    private int virtualdown;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF toponly;
    public Percolation(int N) {
        if(N<=0){
        throw new IllegalArgumentException();
        }
        else
        {plate = new Cell[N][N];
            platesize=N;
            opensize=0;
            uf = new WeightedQuickUnionUF(N*N+2);
            toponly = new WeightedQuickUnionUF(N*N+2);
            virtualtop = N*N;
            virtualdown = N*N+1;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                plate[i][j] = Cell.CLOSED;
            }
        }}
    }

    public void open(int row, int col) {
        // 边界检查
        if(row < 0 || row >= platesize || col < 0 || col >= platesize){
            throw new IndexOutOfBoundsException();
        }
        
        // 如果已经是开放状态，直接返回
        if(plate[row][col].equals(Cell.OPEN)){
            return;
        }
        
        // 打开单元格
        plate[row][col] = Cell.OPEN;
        opensize += 1;
        
        int currentIndex = row * platesize + col;
        
        // 如果是第一行，连接到虚拟顶部
        if(row == 0){
            uf.union(virtualtop, currentIndex);
            toponly.union(virtualtop,currentIndex);
        }
        
        // 如果是最后一行，连接到虚拟底部
        if(row == platesize - 1){
            uf.union(virtualdown, currentIndex);
        }
        
        // 连接上方相邻单元格
        if(row > 0 && isOpen(row - 1, col)){
            uf.union(currentIndex, (row - 1) * platesize + col);
            toponly.union(currentIndex,(row-1)*platesize+col);
        }
        
        // 连接下方相邻单元格
        if(row < platesize - 1 && isOpen(row + 1, col)){
            uf.union(currentIndex, (row + 1) * platesize + col);
            toponly.union(currentIndex, (row+1)*platesize+col);
        }
        
        // 连接左侧相邻单元格
        if(col > 0 && isOpen(row, col - 1)){
            uf.union(currentIndex, row * platesize + col - 1);
            toponly.union(currentIndex,row*platesize+col-1);
        }
        
        // 连接右侧相邻单元格
        if(col < platesize - 1 && isOpen(row, col + 1)){
            uf.union(currentIndex, row * platesize + col + 1);
            toponly.union(currentIndex,row*platesize+col+1);
        }
    }

    public boolean isOpen(int row, int col) {
        if(row < 0 || row >= platesize || col < 0 || col >= platesize){
            throw new IndexOutOfBoundsException();
        }
        return plate[row][col].equals(Cell.OPEN);
    }

    public boolean isFull(int row, int col) {
        if(row < 0 || row >= platesize || col < 0 || col >= platesize){
            throw new IndexOutOfBoundsException();
        }
        else{
            return toponly.connected(virtualtop,row*platesize+col);
        }
    }

    public int numberOfOpenSites() {
        return opensize;
    }

    public boolean percolates() {
        return uf.connected(virtualtop, virtualdown);
    }
}
