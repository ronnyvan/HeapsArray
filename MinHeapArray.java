public class MinHeapArray {
    private Integer[] tree;
    private int lastElementIndex;

    public MinHeapArray() {
        tree = new Integer[20];
        lastElementIndex = -1;
    }

    public void add(Integer val) {
        grow();
        tree[++lastElementIndex] = val;
        addHelp(lastElementIndex, val);
    }

    private void addHelp(int i, Integer val) {
        if (i <= 0) {
            return;
        }
        int parentIndex = (i - 1) / 2;
        if (tree[parentIndex] > val) { //change sign
            Integer temp = tree[parentIndex];
            tree[parentIndex] = val;
            tree[i] = temp;
            addHelp(parentIndex, val);
        }
    }

    public void remove() {
        if (lastElementIndex == -1) {
            return;
        }
        tree[0] = tree[lastElementIndex];
        tree[lastElementIndex--] = null;
        removeHelp(0);
    }

    private void removeHelp(int i) {
        int leftChildIndex = i*2 + 1;
        int rightChildIndex = leftChildIndex + 1;
        if (leftChildIndex > lastElementIndex) {
            return;
        }

        int minChildIndex = tree[rightChildIndex] != null
                && tree[rightChildIndex] < tree[leftChildIndex] //change sign
                    ? rightChildIndex
                    : leftChildIndex;

        if(tree[i] > tree[minChildIndex]){ //change sign
            Integer temp = tree[i];
            tree[i] = tree[minChildIndex];
            tree[minChildIndex] = temp;
            removeHelp(minChildIndex);
        }
    }

    private void grow() {
        if (lastElementIndex == tree.length - 1) {
            Integer[] newTree = new Integer[tree.length * 2];
            for (int i = 0; i < tree.length; i++) {
                newTree[i] = tree[i];
            }
            tree = newTree;
        }
    }

    public String toString() {
        if (tree[0] == null) {
            return "Empty";
        }

        int power = 2;
        String result = "Level 0 -->| ";
        for (int i = 0, level = 0; i < tree.length; i++) {
            if (i == power - 1) {
                if (isRowEmpty(i, power)) break;
                result += "\nLevel " + ++level + " -->| ";
                power *= 2;
            }
            result += ((tree[i] != null) ? tree[i] : "(null)") + " ";
        }

        return result;
    }

    private boolean isRowEmpty(int currIndex, int power) {
        for (int i = currIndex; i < currIndex + power - 1 && i < tree.length; i++) {
            if (tree[i] != null) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        MinHeapArray h = new MinHeapArray();

        // Test Case 1: Adding elements in ascending order
        System.out.println("---------------------Test 1----------------------");
        h.add(2);
        h.add(5);
        h.add(9);
        h.add(12);
        h.add(15);
        h.add(11);
        h.add(1400);
        h.add(20);
        h.add(6666);
        h.add(151);
        h.add(8);
        System.out.println(h);
        System.out.println();
        h.remove();
        System.out.println(h);

        System.out.println("---------------------Test 2----------------------");

        // Test Case 2: Adding elements in descending order
        MinHeapArray h2 = new MinHeapArray();
        h2.add(5);
        h2.add(4);
        h2.add(3);
        h2.add(2);
        h2.add(1);
        h2.add(87);
        h2.add(-1);
        System.out.println(h2);
        System.out.println();
        h2.remove();
        System.out.println(h2);
        System.out.println("---------------------Test 3----------------------");

        // Test Case 3: Adding random elements
        MinHeapArray h3 = new MinHeapArray();
        h3.add(3);
        h3.add(8);
        h3.add(1);
        h3.add(6);
        h3.add(10);
        h3.add(14);
        h3.add(-4);
        h3.add(6);
        h3.add(2);
        h3.add(3);
        h3.add(-1000);
        System.out.println(h3);
        System.out.println();
        h3.remove();
        System.out.println(h3);
        System.out.println("---------------------Test 4----------------------");


        // Test Case 4: Adding negative values
        MinHeapArray h4 = new MinHeapArray();
        h4.add(-2);
        h4.add(-5);
        h4.add(-1);
        h4.add(-8);
        h4.add(-3);
        System.out.println(h4);
        System.out.println();
        h4.remove();
        System.out.println(h4);
        System.out.println("---------------------Test 5----------------------");


        // Additional Test Case: Adding duplicate values
        MinHeapArray h5 = new MinHeapArray();
        h5.add(10);
        h5.add(5);
        h5.add(10);
        h5.add(8);
        h5.add(5);
        System.out.println(h5);
        System.out.println();
        h5.remove();
        System.out.println(h5);
        System.out.println("---------------------Test 6----------------------");

        MinHeapArray h6 = new MinHeapArray();
        for(int i = 0; i < 10; i++){
            h6.add((int) (Math.random()*1000));
        }
        System.out.println(h6);
        System.out.println();
        h6.remove();
        System.out.println(h6);
        System.out.println("\n--End--");

    }

}

