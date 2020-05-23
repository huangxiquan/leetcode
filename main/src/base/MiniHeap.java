package base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangxiquan on 2020/5/16.
 */
public class MiniHeap<E extends Comparable> {
    private List<E> data = new ArrayList<>();


    public int getSize() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void add(E e) {
        data.add(e);
        siftUp(getSize() - 1);
    }

    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    private int getLeftChildIndex(int i) {
        return i * 2 + 1;
    }

    private int getRightChildIndex(int i) {
        return i * 2 + 2;
    }

    private void siftUp(int i) {
        while (i > 0) {
            int parentIndex = getParentIndex(i);
            if(data.get(parentIndex).compareTo(data.get(i)) > 0) {
                //父亲节点要大
                swap(parentIndex,i);
            }
            i = parentIndex;
        }
    }

    private void swap(int parentIndex, int i) {
        E temp = data.get(i);
        data.set(i,data.get(parentIndex));
        data.set(parentIndex,temp);
    }

    public E extractMin() {
        E result = data.get(0);
        E target = data.remove(getSize() - 1);
        if(!data.isEmpty()) {
            data.set(0,target);
            siftDown(0);
        }
        return result;
    }

    private void siftDown(int i) {
        while (getLeftChildIndex(i) < getSize()) {
            int rightIndex = getRightChildIndex(i);
            int targetIndex = getLeftChildIndex(i);
            if(rightIndex < getSize()) {
                //有右孩子
                targetIndex = (data.get(targetIndex).compareTo(data.get(rightIndex)) > 0) ? rightIndex : targetIndex;

            }

            if(data.get(targetIndex).compareTo(data.get(i)) < 0) {
                swap(targetIndex,i);
            }
            i = targetIndex;
        }

    }

    @Override
    public String toString() {
        return data.toString();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,9,8,3,7,2};
        MiniHeap<Integer> miniHeap = new MiniHeap();
        for(int num : nums) {
            miniHeap.add(num);
        }
        System.out.println(miniHeap);
        while (!miniHeap.isEmpty()) {
            System.out.println(miniHeap.extractMin());
        }
    }

}
