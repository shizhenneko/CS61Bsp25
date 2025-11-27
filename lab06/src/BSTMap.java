import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{
    private class newnode{
        BSTNode node;
        boolean has_equal;
        private newnode(BSTNode node,boolean has_equal){
            this.node = node;
            this.has_equal = has_equal;
        }
    }
    private class BSTNode{
        K key;
        V value;
        BSTNode left;
        BSTNode right;
        private BSTNode(K key,V value,BSTNode left,BSTNode right){
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
        private newnode insert(K key,V value){
            boolean has_equal = false;
            if(this.key.compareTo(key)>0){
                if(this.left==null){
                    this.left = new BSTNode(key,value,null,null);
                }else{
                    newnode result = this.left.insert(key,value);
                    this.left = result.node;
                    has_equal = result.has_equal;
                }
            }
            else if(this.key.compareTo(key)<0){
                if(this.right==null){
                    this.right = new BSTNode(key,value,null,null);
                }else{
                    newnode result = this.right.insert(key,value);
                    this.right = result.node;
                    has_equal = result.has_equal;
                }
            }
            else{
                this.value = value;
                has_equal=true;
            }
            return new newnode(this,has_equal);
        }
        private V get(K key){
            if(this.key.compareTo(key)>0){
                if(this.left==null){
                    return null;
                }
                else{
                    return this.left.get(key);
                }
            }
            else if(this.key.compareTo(key)<0){
                if(this.right==null){
                    return null;
                }
                else{
                    return this.right.get(key);
                }
            }
            else{return this.value;}
        }
        private boolean containsKey(K key){
            if(this.key.compareTo(key)>0){
                if(this.left==null){
                    return false;
                }
                else{
                    return this.left.containsKey(key);
                }
            }
            else if(this.key.compareTo(key)<0){
                if(this.right==null){
                    return false;
                }
                else{
                    return this.right.containsKey(key);
                }
            }
            else{return true;}
        }
        private BSTNode findMin(){
            if(this.left == null){
                return this;
            }
            return this.left.findMin();
        }
        private BSTNode remove(K key){
            if(this.key.compareTo(key)>0){
                if(this.left != null){
                    this.left = this.left.remove(key);
                }
                return this;
            }
            else if(this.key.compareTo(key)<0){
                if(this.right != null){
                    this.right = this.right.remove(key);
                }
                return this;
            }
            else{
                if(this.left == null){
                    return this.right;
                }
                if(this.right == null){
                    return this.left;
                }
                BSTNode successor = this.right.findMin();
                this.key = successor.key;
                this.value = successor.value;
                this.right = this.right.remove(successor.key);
                return this;
            }
        }
    }
    private BSTNode root;
    private int size;
    private LinkedList<K> keyset;
    public BSTMap(){
        root = null;
        size = 0;
        keyset = new LinkedList<>();
    }
    @Override
    public void put(K key, V value) {
    if(root==null){
        root = new BSTNode(key,value,null,null);
        size+=1;
        keyset.add(key);
    }
    else{
        newnode node;
        node = root.insert(key,value);
        if(!node.has_equal){
            size+=1;
            keyset.add(key);
        }
    }
    }

    @Override
    public V get(K key) {
        if(root==null){
            return null;
        }
        else{
            return root.get(key);
        }
    }

    @Override
    public boolean containsKey(K key) {
        if(root==null){
            return false;
        }
        else{
            return root.containsKey(key);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root=null;
        size=0;
        keyset.clear();
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new TreeSet<>();
        for (K key : this) {
            keys.add(key);
        }
        return keys;
    }

    @Override
    public V remove(K key) {
        if(!containsKey(key)){
            return null;
        }
        V removedValue = get(key);
        root = root.remove(key);
        size--;
        keyset.remove(key);
        return removedValue;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }
    
    // 中序遍历迭代器（按键的升序遍历）
    private class BSTMapIterator implements Iterator<K> {
        private Stack<BSTNode> stack;

        public BSTMapIterator(){
            stack = new Stack<>();
            // 将最左路径的所有节点压入栈
            pushLeft(root);
        }
        
        // 将节点及其所有左子节点压入栈
        private void pushLeft(BSTNode node){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
        }
        
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }
        
        @Override
        public K next() {
            BSTNode node = stack.pop();
            // 如果有右子节点，将右子节点及其左路径压入栈
            pushLeft(node.right);
            return node.key;
        }
    }
}
