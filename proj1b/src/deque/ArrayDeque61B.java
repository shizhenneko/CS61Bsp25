package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private int size;
    private int nextfirst;
    private int nextlast;
    T[] deque = (T [])(new Object[8]);
    public ArrayDeque61B(){
    size = 0;
    nextfirst = 0;
    nextlast = 1;
    }
    public void reshape(){
        if(size == deque.length){
            T[] new_deque = (T [])(new Object[size*2]);
            int first = Math.floorMod(nextfirst + 1, deque.length);
            int firstPartSize = deque.length - first;
            System.arraycopy(deque, first, new_deque, 0, firstPartSize);
            System.arraycopy(deque, 0, new_deque, firstPartSize, nextlast);
            deque = new_deque;
            nextfirst = deque.length - 1;
            nextlast = size;
        }
        else{
            return;
        }
    }
    public void small(){
        if((double) size /deque.length<=0.25 && deque.length>=16){
            T[] new_deque = (T [])(new Object[deque.length/2]);
            for(int i=0;i<size;i++)
            {
                new_deque[i] = get(i);
            }
            deque = new_deque;
            nextfirst = deque.length-1;
            nextlast = size;
        }
        else{
            return;
        }
    }
    @Override
    public void addFirst(T x) {
    reshape();
    deque[Math.floorMod(nextfirst,deque.length)] = x;
    nextfirst = Math.floorMod(nextfirst-1,deque.length);
    size+=1;
    return;
    }

    @Override
    public void addLast(T x) {
    reshape();
    deque[Math.floorMod(nextlast,deque.length)] = x;
    nextlast = Math.floorMod(nextlast+1,deque.length);
    size+=1;
    return;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for(int i=0;i<size;i++)
        {
            returnList.add(get(i));
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        small();
        if(size==0)
            return null;
        else
        {int first = Math.floorMod(nextfirst+1,deque.length);
        T x= deque[first];
        deque[first] = null;
        size-=1;
        nextfirst = first;
        return x;}

    }

    @Override
    public T removeLast() {
        small();
        if(size==0)
            return null;
        else{int last = Math.floorMod(nextlast-1,deque.length);
        T x = deque[last];
        deque[last] = null;
        size-=1;
        nextlast = last;
        return x;}
    }

    @Override
    public T get(int index) {
        if(index>=size)
        {
            return null;
        }
        else
        {
            return deque[Math.floorMod(nextfirst+1+index,deque.length)];
        }
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj1b");
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
    private class ArrayDequeIterator implements Iterator<T>{
        private int wizPos;
        public ArrayDequeIterator(){
           wizPos=0;
        }

        @Override
        public boolean hasNext() {
            return wizPos<size;
        }

        @Override
        public T next() {
            T returnitem = get(wizPos);
            wizPos +=1;
            return returnitem;
        }
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Deque61B)) {
            return false;
        }
        Deque61B<?> other = (Deque61B<?>) obj;
        if (size != other.size())
        {
            return false;
        }
        for(int i=0;i<size;i++)
        {
            T thisItem = get(i);
            Object otherItem = other.get(i);
            if (thisItem == null && otherItem != null) {
                return false;
            }
            if (thisItem != null && !thisItem.equals(otherItem)){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return toList().toString();
    }
}
