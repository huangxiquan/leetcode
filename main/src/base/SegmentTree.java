package base;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

/**
 * Created by huangxiquan on 2020/5/17.
 */
public class SegmentTree<E> {

    private E[] data;
    private E[] treeData;
    private Merge<E> merge;

    public SegmentTree(E[] arr,Merge<E> merge) {
        this.merge = merge;
        data = (E[]) new Object[arr.length];
        for(int i = 0 ; i < arr.length ; i ++) {
            data[i] = arr[i];
        }

        treeData = (E[]) new Object[data.length *  4];
        createTreeData(0,0,data.length - 1);
    }

    private int leftChildIndex(int i) {
        return i * 2 + 1;
    }

    private int rightChildIndex(int i) {
        return i * 2 + 2;
    }

    private void createTreeData(int treeIndex, int left, int right) {
        if(left == right) {
            treeData[treeIndex] = data[left];
            return;
        }

        int mid = left + (right - left) / 2;
        int leftChildIndex = leftChildIndex(treeIndex);
        int rightChildIndex = rightChildIndex(treeIndex);
        createTreeData(leftChildIndex,left,mid);
        createTreeData(rightChildIndex,mid + 1,right);
        treeData[treeIndex] = (E) this.merge.merge(treeData[leftChildIndex],treeData[rightChildIndex]);
    }

    public E query(int qleft,int qright) {
        return query(0,0,data.length - 1,qleft,qright);
    }


    private E query(int treeIndex, int left, int right, int qleft, int qright) {
        if(left == qleft && right == qright) {
            //找到某个节点
            return treeData[treeIndex];
        }
        int mid = (left + right) / 2;
        int leftChildIndex = leftChildIndex(treeIndex);
        int rightChildIndex = rightChildIndex(treeIndex);
        if(qright <= mid) {
            //向左找
            return query(leftChildIndex,left,mid,qleft,qright);
        }
        if(qleft > mid) {
            //向右找
            return query(rightChildIndex,mid + 1,right,qleft,qright);
        }

        E leftTree = query(leftChildIndex,left,mid,qleft,mid);
        E rightTree = query(rightChildIndex,mid + 1,right,mid + 1,qright);
        return this.merge.merge(leftTree,rightTree);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0 ; i < treeData.length ; i++) {
            stringBuilder.append(treeData[i] + ",");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2,0,3,-5,2,-1};
        Integer[] params = new Integer[nums.length];
        for(int i = 0 ; i < nums.length ; i ++) {
            params[i] = nums[i];
        }
        SegmentTree<Integer> segmentTree = new SegmentTree<>(params,(a, b) -> a + b);
        System.out.println(segmentTree);
        System.out.println(segmentTree.query(2,5));

    }
}
