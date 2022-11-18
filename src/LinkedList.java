public class LinkedList<T extends Comparable<T>> implements List<T> {
    private Node<T> start;
    private Node<T> end;

    private int size;//how long is the list
    private boolean isSorted;
    public LinkedList(){
        this.size = 0;
        this.isSorted = true;
    }
    @Override
    public boolean add(T element) {
        if (element == null){
            return false;
        }

        else{
            if (start==null){
                start = new Node<T>(element, null);
                end = start;
            }
            else{
                if (end.getData().compareTo(element)>0){
                    isSorted = false;
                }
                end.setNext(new Node<T>(element,null));
                end = end.getNext();
            }
            size++;
            return true;
        }
    }

    @Override
    public boolean add(int index, T element) {
        System.out.println(size);
        System.out.println(index);
        if (index >= size || index < 0 || element == null){
            return false;
        }
        else{
            if (size == 0){
                start = new Node<>(element,null);
                return true;
            }
            else {
                if (index == 0) {
                    Node temp = start;
                    start = new Node(element, temp);
                    return true;
                }
                int count = 1;
                Node t1 = start;
                Node t2 = start.getNext();
                while (count < index) {
                    t1 = t1.getNext();
                    t2 = t2.getNext();
                    count++;
                }
                Node temp = new Node(element,t2);
                t1.setNext(temp);
                size++;
                if (t1.getData().compareTo(element)>0 || t2.getData().compareTo(element) < 0){
                    isSorted = false;
                }
                return true;
            }
        }
    }

    @Override
    public void clear() {
        start = null;
        end = null;
        this.size = 0;
        this.isSorted = true;
    }

    @Override
    public T get(int index) {
        if (index>=this.size || index < 0){
            return null;
        }
        else{
            Node loop = start;
            for (int i=0;i<index;i++){
                loop = loop.getNext();
            }
            return (T) loop.getData();
        }
    }

    @Override
    public int indexOf(T element) {
        if (element == null)return -1;
        int index = 0;
        Node<T> loop = start;
        while (index < size){
            if (loop.getData().equals(element)){
                return index;
            }
            else {
                loop = loop.getNext();
                index++;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return (this.size==0);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void sort() {
        if (isSorted==true) return;
        else{

        }
    }

    @Override
    public T remove(int index) {
        int count = 0;
        Node loop = start;
        while (count < index-1){
            loop = loop.getNext();
            count++;
        }
        T ret = (T) loop.getNext().getData();
        loop.setNext(loop.getNext().getNext());
        return ret;
    }

    @Override
    public void equalTo(T element) {

    }

    @Override
    public void reverse() {
        Node newList = null;
        Node ptr = start;
        while (ptr!=null){
            Node temp = ptr;
            ptr = ptr.getNext();
            temp.setNext(newList);
            newList = temp;
        }
        start.setNext(newList);
    }

    @Override
    public void merge(List<T> otherList) {
        if (otherList==null) return;
        else{
            LinkedList<T> other = (LinkedList<T>) otherList;
            sort();
            other.sort();

        }
    }

    @Override
    public void pairSwap() {

    }

    @Override
    public boolean isSorted() {
        return isSorted;
    }
}
