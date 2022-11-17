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
        if (index > size || index < 0 || element == null){
            return false;
        }
        else{
            if (size == 0){
                start = new Node<>(element,null);
                return true;
            }
            else if(size == 1){
                if (index == 0){
                    start = new Node<>(element, start);
                    end = start.getNext();
                    return true;
                }
                else{
                    end = new Node<>(element,null);
                    return true;
                }

            }
            else{
                int count = 0;
                Node loop = start;
                while (count < index-2){
                    loop = loop.getNext();
                    count++;
                }
                if (loop.getData().compareTo(element) > 0 || loop.getNext().getData().compareTo(element) <0){
                    isSorted = false;
                }
                Node<T> temp = loop.getNext();
                loop.setNext(new Node(element, temp));
                size++;
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
        if (this.size == 0)return true;
        else return false;
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
