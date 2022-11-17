public class LinkedList<T extends Comparable<T>> implements List<T> {

    private Node<T> head;
    private boolean isSorted;

    public LinkedList() {
        head = null;
        isSorted = true;
    }

    @Override
    public boolean add(T element) {
        if (element == null) return false;
        Node<T> newNode = new Node<>(element);
        if (head == null) head = newNode;
        else {
            Node<T> temp = head;
            while (temp.getNext() != null) temp = temp.getNext();
            temp.setNext(newNode);
        }
        isSorted = false;
        return true;
    }

    @Override
    public boolean add(int index, T element) {
        if (element == null || index >= size()) return false;
        Node<T> newNode = new Node<>(element);
        Node<T> temp = head;
        int i = 0;
        while (i++ < index) temp = temp.getNext();
        temp.setNext(newNode);
        isSorted = false;
        return true;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public int indexOf(T element) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void sort() {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public void equalTo(T element) {

    }

    @Override
    public void reverse() {

    }

    @Override
    public void merge(List<T> otherList) {

    }

    @Override
    public void pairSwap() {

    }

    @Override
    public boolean isSorted() {
        return false;
    }
}





