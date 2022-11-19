import java.util.stream.IntStream;
//written by derej006,chen7381

public class ArrayList<T extends Comparable<T>> implements List<T>, Cloneable {
    private T[] list;
    private int size;
    private boolean isSorted;

    public ArrayList() {
        size = 0;
        isSorted = true;
        list = (T[]) new Comparable[2];
    }

    @Override
    /*
     * Adds an element to end of the list. If element is null,
     * it wont add it and return false.
     */
    public boolean add(T element) {
        if (element == null)
            return false;
        if (size == list.length) {
            checkBound();
        }
        list[size++] = element;
        if (size == 1)
            isSorted = true;
        else
            isSorted = checkSort();
        return true;
    }

    @Override
    /*
     * Adds an element at specific index. if the element is null it returns false while updating isSorted
     */
    public boolean add(int index, T element) {
        if (index < size && index >= 0 && element != null) {
            if (size == list.length)
                checkBound();
            int i = size - 1;
            while (i >= index) {
                list[i + 1] = list[i];
                i-=1;
            }
            list[index] = element;
            size++;
            isSorted = checkSort();

            return true;
        } else {
            return false;
        }
    }

    @Override
    /**
     * Remove all elements from the list and updates isSorted accordingly.
     */
    public void clear() {
        size = 0;
        list = (T[]) new Comparable[10];
    }

    @Override
    /**
     * This method returns the first index of element in the list. If element
     *      * is null or not found in the list, it returns -1.
     */
    public T get(int index) {
        if (index < size && index >= 0)
            return list[index];

        return null;
    }

    @Override
    /**
     * returns true if the list is empty
     */
    public int indexOf(T element) {
        int i = 0;
        i = 0;
        while (i < size) {
            if (list[i].equals(element)) {
                return i;
            }
            i+=1;
        }
        return -1;
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

    /**
     * removes whatever is at "x" index in a list and returns it. if the index is out-of-bounds it will return null
     */
    public void sort() {
        for (int i = 0; i < size() - 1; i++) {
            T minimum = get(i);
            int index = i;
            int j = i + 1;
            while (j < size()) {
                if (minimum.compareTo(get(j)) > 0) {
                    minimum = get(j);
                    index = j;
                }
                j += 1;
            }
            if (index != i) {
                list[index] = get(i);
                list[i] = minimum;
            }
        }
        isSorted = true;
    }


    private boolean checkSort() {
        boolean check = true;
        int i = 0;
        while (i < size - 1) {
            if (get(i).compareTo(get(i + 1)) > 0) check = false;
            i++;
        }
        return check;

    }

    @Override

    public T remove(int index) {
        if (index >= 0 && index <= size) {
            if (index < size) {
                T item = list[index];
                for (int i = index + 1; i < size; i++) {
                    list[i - 1] = list[i];
                }
                size -= 1;
                isSorted = checkSort();


                return item;
            }
            return null;
        } else {
            return null;
        }

    }

    @Override
    /**
     * removes all of the elements within a list that are not equal to element
     */
    public void equalTo(T element) {
        if (element == null) {
        } else for (int i = 0; i < size(); i++) {
            if (!(get(i).equals(element))) {
                remove(i);
                i -= 1;
            }
        }
        isSorted = true;
    }

    @Override
    /**
     * reverses the list in a way that it ends up in place
     */
    public void reverse() {
        ArrayList<T> reverse = new ArrayList<>();
        IntStream.iterate(size - 1, i -> i >= 0, i -> i - 1).mapToObj(this::get).forEachOrdered(reverse::add);
        list = reverse.list;
        isSorted = checkSort();
    }

    @Override
    /**
     * merges two lists into a single sequential list
     */
    public void merge(List<T> otherList) {
        if(otherList != null){
            ArrayList<T> other = (ArrayList<T>) otherList;
            this.sort();
            other.sort();
            T[] newArray = (T[])new Comparable[this.list.length+other.list.length];
            int i = 0;
            int j = 0;
            for (int k=0; k < newArray.length; k++){
                if (this.list[i] !=null && other.list [j] != null){
                    if(this.list[i].compareTo(other.list[j]) <= 0){
                        newArray[k] = this.list[i];
                        i++;
                    }
                    else if (this.list[i].compareTo(other.list[j])>0){
                        newArray[k] = other.list[j];
                        j++;
                    }

                }else{
                    if (this.list[i] == null && other.list [j] != null){
                        newArray[k] = other.list[j];
                        j++;

                    }
                    if (other.list[j] ==  null && other.list[i] != null){
                        newArray[k] = other.list[i];
                        i++;
                    }
                }
            }
            this.list = newArray;
            this.isSorted = true;
        }
    }

    @Override
    /**
     * swaps elements within two lists
     */
    public void pairSwap() {
        for (int i=0;i<size;i=i+2){
            if (i + 1 != size) {
                T temp_val;
                temp_val = list[i];
                list[i] = list[i + 1];
                list[i + 1] = temp_val;
            } else return;

        }
    }

    public String toString() {

        StringBuilder s = new StringBuilder();

        for (int i = 0; i < size(); i++) s.append(get(i)).append("\n");

        return s.toString();

    }

    @Override
    public boolean isSorted() {
        return isSorted;
    }

    private void checkBound() {
        int val = list.length;
        val = val * 2;
        T[] newList = (T[]) new Comparable[val];
        if (size >= 0) System.arraycopy(list, 0, newList, 0, list.length);

        list = newList;


    }

}