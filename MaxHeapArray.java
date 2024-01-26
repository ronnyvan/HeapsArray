public class MaxHeapArray {
    private Integer[] tree;
    private int lastElementIndex;

    public MaxHeapArray() {
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
        if (tree[parentIndex] < val) { //change sign
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

        int maxChildIndex = tree[rightChildIndex] != null
                && tree[rightChildIndex] > tree[leftChildIndex] //change sign
                ? rightChildIndex
                : leftChildIndex;

        if(tree[i] < tree[maxChildIndex]){ //change sign
            Integer temp = tree[i];
            tree[i] = tree[maxChildIndex];
            tree[maxChildIndex] = temp;
            removeHelp(maxChildIndex);
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
}

