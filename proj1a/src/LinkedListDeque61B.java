import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    private class Node{
        Node prev;
        T point;
        Node next;
        public Node(Node prev,T x,Node next){
           this.prev=prev;
           this.point = x;
           this.next = next;
        }
    }
    int size;
    Node sentinel;
    public LinkedListDeque61B(){
    this.size = 0;
    this.sentinel = new Node(null, null, null);
    this.sentinel.prev = this.sentinel;
    this.sentinel.next = this.sentinel;
    }
    @Override
    public void addFirst(T x) {
    size +=1;
    Node x_node = new Node(null,x,null);
    x_node.next = sentinel.next;
    x_node.prev = sentinel;
    sentinel.next.prev = x_node;
    sentinel.next = x_node;
    }

    @Override
    public void addLast(T x) {
    size+=1;
    Node x_node = new Node(null,x,null);
    x_node.next = sentinel;
    x_node.prev = sentinel.prev;
    sentinel.prev.next = x_node;
    sentinel.prev = x_node;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node current = sentinel.next;
        while(current != sentinel){
            returnList.add(current.point);
            current = current.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T x=sentinel.next.point;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        return x;
    }

    @Override
    public T removeLast() {
        T x = sentinel.prev.point;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return x;
    }

    @Override
    public T get(int index) {
       Node current = sentinel.next;
       T x = current.point;
       if (index >= size || index<0)
       {
           return null;
       }
       else
       {
           for(int i=0;i<index;i++)
           {
              current = current.next;
              x = current.point;
           }
       }
       return x;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node current, int index) {
        if (index == 0) {
            return current.point;
        }
        return getRecursiveHelper(current.next, index - 1);
    }
}
