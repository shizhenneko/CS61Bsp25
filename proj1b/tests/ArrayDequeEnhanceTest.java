import deque.ArrayDeque61B;
import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;

import java.sql.Array;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDequeEnhanceTest {
    @Test
    public void iteratortest(){
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        for(String s : lld1){
            System.out.println(s);
        }
    }
    @Test
    public void equalstest(){
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        Deque61B<String> lld2 = new ArrayDeque61B<>();
        Deque61B<String> deque1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        deque1.addLast("front");
        deque1.addLast("middle");
        deque1.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
        assertThat(lld1).isEqualTo(deque1);
    }
    @Test
    public void toStringtest(){
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        System.out.println(lld1);
    }
}
