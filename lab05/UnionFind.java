public class UnionFind {
    private int[] uniontree;
    public UnionFind(int N){
    uniontree = new int[N];
    for(int i=0;i<N;i++)
    {
        uniontree[i] = -1;
    }
    }
    /* return the direct parent node of the node*/
    public int parent(int v){
        return uniontree[v];
    }
    /* return the weight of the size*/
    public int sizeOf(int v){
        return -uniontree[find(v)];
    }
    /* find the root of set with path compression */
    public int find(int v){
        int root = v;
        while(uniontree[root] >= 0){
            root = parent(root);
        }
        int current = v;
        while(current != root){
            int parent = parent(current);
            uniontree[current] = root;
            current = parent;
        }
        
        return root;
    }
    public boolean connected(int v1,int v2){
        return find(v1) == find(v2);
    }
    public void union(int v1,int v2){
        int root1 = find(v1);
        int root2 = find(v2);
        if(root1 == root2){
            return;
        }
        if(sizeOf(v1) >= sizeOf(v2)){
            uniontree[root1] += uniontree[root2];
            uniontree[root2] = root1;
        }
        else{
            uniontree[root2] += uniontree[root1];
            uniontree[root1] = root2;
        }
    }
}
