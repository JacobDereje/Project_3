public class LinkedList<T extends Comparable<T>> implements List<T> {
    private Node<T> start;
    private Node<T> end;
    private int size;//how long is the list
    private boolean isSorted;
    public LinkedList(){
        this.start = null;
        this.end = null;
        this.size = 0;
        this.isSorted = true;
    }
    @Override
    public boolean add(T element) {
        //return false if the element being added is null
        if (element == null){
            return false;
        }
        else{
            //case 1: list is empty
            if (start==null){
                start = new Node<T>(element);
                end = start;
            }
            //case2: list not empty
            else{
                //check if the new element breaks the sorted list
                if (end.getData().compareTo(element)>0){
                    isSorted = false;
                }
                end.setNext(new Node<T>(element));
                end = end.getNext();
            }
            size++;
            return true;
        }
    }

    @Override
    public boolean add(int index, T element) {
        //check if index is out of bound or element is null
        if (index >= size || index < 0 || element == null){
            return false;
        }
        else{
            //case:add to the start of the list
            if (index == 0) {
                start = new Node<T>(element, start);
                if (start.getNext().getData().compareTo(element)<0) isSorted = false;
                return true;
            }
            //case: add to any index not at the start
            else {
                int count = 0;
                Node<T> trail = start;
                Node<T> ahead = trail;
                while (count < index) {
                    trail = ahead;
                    ahead = ahead.getNext();
                    count++;
                }
                Node<T> addNode = new Node(element);
                trail.setNext(addNode);
                addNode.setNext(ahead);
                size++;
                if (trail.getData().compareTo(element) > 0 || ahead.getData().compareTo(element) < 0) {
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
            Node front = start;
            Node temp;
            Node compare;
            while (front.getNext()!=null){
                compare = front.getNext();
                temp = front;
                while (compare!=null) {
                    if (temp.getData().compareTo(compare.getData()) > 0) {
                        temp = compare;
                    }
                    compare = compare.getNext();
                }
                Node beforeTemp = front;
                while(beforeTemp.getNext()!=temp){
                    beforeTemp = beforeTemp.getNext();
                }
                Node afterTemp = temp.getNext();
                temp.setNext(front);
                beforeTemp.setNext(afterTemp);
            }
        }


    }

    @Override
    public T remove(int index) {
        if (index>=size || index <0){
            return null;
        }
        else {
            int count = 0;
            Node loop = start;
            while (count < index - 1) {
                loop = loop.getNext();
                count++;
            }
            if (loop.getNext().getNext()==null) {
                T ret = (T) loop.getNext().getData();
                loop.setNext(null);
                return ret;
            }
            else{
                T ret = (T) loop.getNext().getData();
                loop.setNext(loop.getNext().getNext());
                return ret;
            }
        }
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
