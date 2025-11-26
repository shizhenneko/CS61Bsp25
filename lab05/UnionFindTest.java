import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UnionFindTest {
    @Test
    public void unionfindtest(){
        UnionFind uf = new UnionFind(100);
        for(int i=0;i<100;i++){
           Assertions.assertEquals(i, uf.find(i));
           Assertions.assertEquals(-1, uf.parent(i));
           Assertions.assertEquals(1, uf.sizeOf(i));
        }
    }
    @Test
    public void parenttest(){
        UnionFind uf = new UnionFind(10);
        uf.union(0,1);
        Assertions.assertEquals(0,uf.parent(1));
        Assertions.assertEquals(0,uf.find(1));
        Assertions.assertEquals(-2,uf.parent(0));
        Assertions.assertEquals(2,uf.sizeOf(1));
        Assertions.assertEquals(2,uf.sizeOf(0));
        Assertions.assertEquals(0,uf.find(1));
        Assertions.assertEquals(1,uf.sizeOf(2));
    }
    @Test
    public void connectedtest(){
        UnionFind uf = new UnionFind(10);
        uf.union(0,1);
        uf.union(1,3);
        Assertions.assertTrue(uf.connected(0,3));
        Assertions.assertTrue(uf.connected(1,3));
        Assertions.assertTrue(uf.connected(0,1));
        Assertions.assertFalse(uf.connected(0,2));
    }

}
