import java.util.stream.IntStream;

public class ArrayList<T extends Comparable<T>> implements List<T>, Cloneable {
    private T[] list;
    private int size;
    private boolean isSorted;

    public ArrayList() {
        list = (T[]) new Comparable[2];
        size = 0;
        isSorted = true;
    }

    @Override
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
            isSorted = checkSorted();
        return true;
    }

    @Override
    public boolean add(int index, T element) {
        if (index < size && index >= 0 && element != null) {
            if (size == list.length)
                checkBound();
            int i = size - 1;
            while (i >= index) {
                list[i + 1] = list[i];
                i--;
            }
            list[index] = element;
            size++;
            isSorted = checkSorted();

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        list = (T[]) new Comparable[10];
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0)
            return list[index];

        return null;
    }

    @Override
    public int indexOf(T element) {
        int i = 0;
        for (i = 0; i < size; i++) {
            if (list[i].equals(element)) {
                return i;
            }
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
    public void sort() {
        for (int i = 0; i < size() - 1; i++) {
            T min = get(i);
            int minInd = i;
            int j = i + 1;
            while (j < size()) {
                if (min.compareTo(get(j)) > 0) {
                    min = get(j);
                    minInd = j;
                }
                j += 1;
            }
            if (minInd != i) {
                list[minInd] = get(i);
                list[i] = min;
            }
        }
        isSorted = true;
    }


    private boolean checkSorted() {
        boolean check = true;
        for (int i = 0; i < size - 1; i++) {
            if (get(i).compareTo(get(i + 1)) > 0) {
                check = false;
            }
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
                isSorted = checkSorted();


                return item;
            }
            return null;
        } else {
            return null;
        }

    }

    @Override
    public void equalTo(T element) {
        if (element != null) {
            for (int i = 0; i < size(); i++) {
                if (!(get(i).equals(element))) {
                    remove(i);
                    i--;
                }
            }
        }
        isSorted = true;
    }

    @Override
    public void reverse() {
        ArrayList<T> reverse = new ArrayList<>();
        IntStream.iterate(size - 1, i -> i >= 0, i -> i - 1).mapToObj(this::get).forEachOrdered(reverse::add);
        list = reverse.list;
        isSorted = checkSorted();
    }

    @Override
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
    public void pairSwap() {
        for (int i=0;i<size;i=i+2){
            if (i+1 == size)return;
            else{
                T temp;
                temp = list[i];
                list[i] = list[i + 1];
                list[i + 1] = temp;
            }

        }
    }

    public String toString() {

        String s = "";

        for (int i = 0; i < size(); i++) {
            s = s + get(i) + "\n";
        }

        return s;

    }

    @Override
    public boolean isSorted() {
        return isSorted;
    }

    private void checkBound() {
        int temp = list.length;
        temp = temp * 2;
        T[] newList = (T[]) new Comparable[temp];
        if (size >= 0) System.arraycopy(list, 0, newList, 0, list.length);

        list = newList;


    }

}