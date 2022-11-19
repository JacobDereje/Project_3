//written by derej006,chen7381
public class LinkedList<T extends Comparable<T>> implements List<T> {
    private Node<T> start;//head of the list
    private Node<T> end;//end of the list
    private int size;//how long is the list
    private boolean isSorted;

    public LinkedList() {
        this.start = null;
        this.end = null;
        this.size = 0;
        this.isSorted = true;
    }

    @Override
    public boolean add(T element) {
        //check if the element being added is null
        if (element == null) {
            return false;
        }
        //case1:nothing in list
        if (start == null) {
            Node<T> addNode = new Node<T>(element);
            end = addNode;
            start = end;
        }
        //case2:adding to a list that already has things in it
        else{
            Node<T> addNode = new Node<T>(element);
            end.setNext(addNode);
            //check if the new added element breaks the isSorted feature
            if (end.getData().compareTo(element) > 0) {
                isSorted = false;
            }
            end = end.getNext();
        }
        size++;
        return true;
    }

    public boolean add(int index, T element) {
        //check for index out of bounds or if element being added is null
        if (index < 0 || index >= size || element == null) {
            return false;
        }
        else{
            //case 1:adding to start of list
            if (index == 0) {
                start = new Node<>(element, start);
                if (element.compareTo(start.getNext().getData()) > 0) {
                    this.isSorted = false;
                }
                return true;
            }
            //case 2: add to any other index
            else {
                int count = 0;
                Node<T> ahead = start;
                Node<T> trail = ahead;
                //using the while loop to get to the index we want
                while (count < index) {
                    trail = ahead;
                    ahead = ahead.getNext();
                    count++;
                }
                Node<T> addNode = new Node<T>(element);
                //"insert" the addNode between trail and ahead
                trail.setNext(addNode);
                addNode.setNext(ahead);
                size++;
                //check if the "insertion" mess up the sorted list
                if (trail.getData().compareTo(addNode.getData()) > 0 || addNode.getData().compareTo(addNode.getNext().getData()) > 0) {
                    this.isSorted = false;
                }
            }
            return true;
        }
    }

    public void clear() {
        start = null;
        end = null;
        size = 0;
        isSorted = true;
    }

    public T get(int index) {
        //check if index out of bounds
        if (index >= size || index < 0) {
            return null;
        }
        else {
            Node<T> loop = start;
            int count = 0;
            //loop to the index we want
            while (loop != null) {
                if (count == index) {
                    return loop.getData();
                }
                count++;
                loop = loop.getNext();
            }
        }
        return null;
    }

    public int indexOf(T element) {
        Node<T> loop = start;
        if (element == null) {
            return -1;
        }
        if (isSorted()) {
            int count = 0;
            //use the sorted feature to stop at the point where we know we can
            while (loop != null && loop.getData().compareTo(element) <= 0) {
                if (element.equals(loop.getData())) {
                    return count;
                }
                loop = loop.getNext();
                count++;
            }
            return -1;
        }
        else{
            int count = 0;
            //loop till we find the element we want
            while (loop != null) {
                if (!element.equals(loop.getData())) {
                    loop = loop.getNext();
                    count++;
                }
                else {
                    return count;
                }
            }
            return -1;
        }
    }

    public boolean isEmpty() {
        return (size==0);
    }

    public int size() {
        Node<T> counter = start;
        size = 0;
        while (counter != null) {
            counter = counter.getNext();
            size++;
        }
        return size;
    }

    public void sort() {
        //if the list is sorted don't do anything
        if (isSorted()) {
            return;
        } 
        else {
            Node<T> front;
            Node<T> compare;
            //use selection sort to sort the list
            for (front = start; front.getNext() != null; front = front.getNext()) {
                Node<T> lowest = front;
                for (compare = front.getNext(); compare != null; compare = compare.getNext()) {
                    if (compare.getData().compareTo(lowest.getData()) < 0) {
                        lowest = compare;
                    }
                }
                T temp = lowest.getData();
                lowest.setData(front.getData());
                front.setData(temp);
            }
        }
        isSorted = true;
    }

    public T remove(int index) {
        isSorted = true;
        Node<T> loop = start;
        Node<T> trail = null;
        T target;
        int count = 0;
        //check for invalid index
        if (index < 0 || index >= size) {
            return null;
        }
        else {
            //remove the first element
            if (index == 0) {
                target = start.getData();
                start = start.getNext();
            }
            //general case
            else {
                //loop until reach the desired index
                while (count < index) {
                    trail = loop;
                    loop = loop.getNext();
                    count++;
                }
                //remove the target and reconnect the elements before and after the target
                target = loop.getData();
                trail.setNext(loop.getNext());
            }
            size--;
        }
        //check if the list is still sorted and update accordingly
        loop = start;
        if (size <= 1) {
            isSorted = true;
        }
        else {
            checkSorted();
        }
        return target;
    }

    public void equalTo(T element) {
        Node<T> ptr = start;
        Node<T> trail = null;
        size = 0;
        if (isSorted) {
            if (start.getData() == null) {
                return;
            }
            //use the while loop to find the element and keep it
            //use the isSorted feature to find it faster
            while (ptr != null) {
                if (element.compareTo(ptr.getData()) > 0) {
                    start = ptr.getNext();
                }
                else if (element.compareTo(ptr.getData()) < 0) {
                    end = trail;
                    return;
                }
                else{
                    size++;
                }
                trail = ptr;
                ptr = ptr.getNext();
            }
        }
        else {
            // eliminate elements that don't equal to the one we want
            while (start != null && start.getData().compareTo(element) != 0) {
                trail = ptr;
                start = start.getNext();
                ptr = start;
            }
            if (start == null) {
                isSorted = true;
                return;
            }
            //loop through the list and recount the size
            while (ptr != null) {
                if (element.compareTo(ptr.getData()) == 0) size++; 
                else {
                    trail.setNext(ptr.getNext());
                    ptr = trail;
                }
                trail = ptr;
                ptr = ptr.getNext();
            }
        }
        isSorted = true;
    }

    public void reverse() {
        Node<T> n1 = start;
        Node<T> n2 = start;
        int i = 0;
        while (i < size / 2) {
            int index = size - 1 - (2 * i);
            int j = 0;
            while (j < index) {
                n2 = n2.getNext();
                j++;
            }
            T temp = n1.getData();
            n1.setData(n2.getData());
            n2.setData(temp);
            n1 = n1.getNext();
            n2 = n1;
            i++;
        }
        //if list was sorted, reversing would make it not sorted
        if (isSorted) {
            isSorted = false;
        }
        //check whether the reversed list was sorted if the list was not sorted previously
        else {
            checkSorted();
        }
    }

    public void merge(List<T> otherList) {
        LinkedList<T> other= (LinkedList<T>) otherList;
        sort();
        other.sort();
        size = 0;
        Node<T> ptr1 = start;
        Node<T> ptr2 = other.start;
        Node<T> temp = null;
        Node<T> ptr = temp;
        if (ptr2 == null) {
            return;
        }
        if (ptr1 == null) {
            this.start = other.start;
            return;
        }
        while (ptr1 != null || ptr2 != null) {
            if (ptr1 == null) {
                ptr.setNext(ptr2);
                break;
            } else if (ptr2 == null) {
                ptr.setNext(ptr1);
                break;
            } else {
                if (ptr1.getData().compareTo(ptr2.getData()) < 0) {
                    if (temp == null) {
                        temp = ptr1;
                        ptr = temp;
                    } else {
                        ptr.setNext(ptr1);
                        ptr = ptr.getNext();
                    }
                    ptr1 = ptr1.getNext();
                } else {
                    if (temp == null) {
                        temp = ptr2;
                        ptr = temp;
                    } else {
                        ptr.setNext(ptr2);
                        ptr = ptr.getNext();
                    }
                    ptr2 = ptr2.getNext();
                }
            }
        }
        size += other.size;
        this.start = temp;
    }

    public void pairSwap() {
        Node<T> ptr = start;
        while (ptr != null && ptr.getNext() != null) {
            //reverse the two elements in every set of two elements
            Node<T> next = ptr.getNext();
            T temp = ptr.getData();
            ptr.setData(next.getData());
            next.setData(temp);
            ptr = next.getNext();
        }
        //check if sorted
        checkSorted();
    }


    public String toString() {
        Node<T> loop = start;
        String output = "";
        while (loop != null) {
            output += loop.getData() + "\n";
            loop = loop.getNext();
        }
        return output;
    }

    public boolean isSorted() {
        return isSorted;
    }

    public void checkSorted(){
        Node<T> ptr;
        ptr = start;
        while (ptr != null && ptr.getNext() != null) {
            if (ptr.getData().compareTo(ptr.getNext().getData()) > 0) {
                isSorted = false;
                return;
            }
            ptr = ptr.getNext();
        }
        isSorted = true;
    }
}
