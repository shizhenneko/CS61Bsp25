import deque.ArrayDeque61B;

import deque.Deque61B;
import jh61b.utils.Reflection;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Array;
import java.util.Deque;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

    @Test
    public void generatetest(){
        Deque61B<String> deque1 = new ArrayDeque61B<>();
        assertThat(deque1.size()).isEqualTo(0);
        assertThat(deque1.isEmpty()).isTrue();
    }
    @Test
    public void addfirsttest(){
       Deque61B<String> deque1 = new ArrayDeque61B<>();
       deque1.addFirst("one");
       deque1.addFirst("two");
       deque1.addFirst("three");
       assertThat(deque1.toList()).containsExactly("three","two","one").inOrder();

    }
    @Test
    public void addlasttest(){
        Deque61B<Integer> deque1 = new ArrayDeque61B<>();
        deque1.addLast(1);
        deque1.addLast(2);
        deque1.addLast(3);
        assertThat(deque1.toList()).containsExactly(1,2,3).inOrder();
    }
    @Test
    public void addFirstAndAddLastTest(){
        Deque61B<Integer> deque1 = new ArrayDeque61B<>();
        deque1.addLast(0);
        deque1.addLast(1);
        deque1.addFirst(-1);
        deque1.addLast(2);
        deque1.addFirst(-2);
        assertThat(deque1.toList()).containsExactly(-2,-1,0,1,2).inOrder();
    }
    @Test
    public void isEmpty(){
        Deque61B<Integer> deque1 = new ArrayDeque61B<>();
        assertThat(deque1.isEmpty()).isTrue();
        deque1.addFirst(1);
        assertThat(deque1.isEmpty()).isFalse();
    }
    @Test
    public void sizeTest(){
        Deque61B<Integer> deque1 = new ArrayDeque61B<>();
        assertThat(deque1.size()).isEqualTo(0);
        deque1.addFirst(1);
        deque1.addLast(2);
        assertThat(deque1.size()).isEqualTo(2);
    }
    @Test
    public void reshapeTest(){
        Deque61B<Integer> deque1 = new ArrayDeque61B<>();
        for(int i=0;i<14;i++)
        {
            deque1.addLast(i);
        }
        assertThat(deque1.get(13)).isEqualTo(13);
        assertThat(deque1.size()).isEqualTo(14);

    }
    @Test
    public void getTest(){
        Deque61B<String> deque1 = new ArrayDeque61B<>();
        deque1.addLast("front");
        deque1.addLast("middle");
        deque1.addLast("back");
        assertThat(deque1.get(0)).isEqualTo("front");
        assertThat(deque1.get(1)).isEqualTo("middle");
        assertThat(deque1.get(2)).isEqualTo("back");
        assertThat(deque1.get(-1)).isNull();
        assertThat(deque1.get(255)).isNull();
    }
    @Test
    public void removeFirstTest(){
        Deque61B<Integer> lld1=  new ArrayDeque61B<>();
        assertThat(lld1.removeFirst()).isNull();
        lld1.addFirst(1);
        lld1.addLast(2);
        assertThat(lld1.removeFirst()).isEqualTo(1);
        assertThat(lld1.removeFirst()).isEqualTo(2);
        assertThat(lld1.removeFirst()).isNull();

    }
    @Test
    public void removeLastTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addFirst(1);
        lld1.addLast(2);
        assertThat(lld1.removeLast()).isEqualTo(2);
        assertThat(lld1.removeLast()).isEqualTo(1);
        assertThat(lld1.removeLast()).isNull();

    }

}
