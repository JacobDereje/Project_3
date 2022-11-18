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
        if(index >= size || index < 0 || element == null)
            return false;
        if (size == list.length)
            checkBound();
        for (int i = size - 1; i >= index; i--) {
            list[i + 1] = list[i];
        }
        list[index] = element;
        size++;
        isSorted = checkSorted();

        return true;
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
            for (int j = i + 1; j < size(); j++) {
                if (min.compareTo(get(j)) > 0) {
                    min = get(j);
                    minInd = j;
                }
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
        }if (check)
            return true;
        else
            return false;

    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size)
            return  null;
        if (index < size) {
            T item = list[index];
            for (int i = index + 1; i < size; i++) {
                list[i - 1] = list[i];
            }
            size--;
            isSorted = checkSorted();
            return item;
        }
        return null;

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
        ArrayList<T> reverselist = new ArrayList<>();
        for (int i = size - 1; i >= 0; i--) {
            reverselist.add(get(i));
        }
        list = reverselist.list;
        isSorted = checkSorted();
    }

    @Override
    public void merge(List<T> otherList) {
        ArrayList<T> other = (ArrayList<T>) otherList;
        sort();
        other.sort();
        T[] RealSize = (T[]) new Comparable[size() + other.size()];
        for (int i = 0; i < otherList.size(); i++)
            add(otherList.get(i));
        isSorted = checkSorted();
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