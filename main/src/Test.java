import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by huangxiquan on 2020/5/23.
 */
public class Test {
    public static void main(String[] args) {
        int[] num = new int[]{14,12,15,13,11,16};
//        int[] num = new int[]{2,3};
//        bubbleSort(num);
//        selectSort(num);
//        insertSort(num);
//        fastSort(num);
        mergeSort(num);
//        int result = getK(num,2);
//        System.out.print(result);
        //        7 1 5 3 6 4
    }

    private static void mergeSort(int[] num) {
        int[] result = new int[num.length];
        mergeSort(num,result,0,num.length - 1);
//        print(num);
    }

    private static void mergeSort(int[] num, int[] result, int start, int end) {
        if(start >= end) {
            return;
        }
        int middle = (start + end) / 2;
        int start1 = start;
        int end1 = middle;
        int start2 = middle + 1;
        int end2 = end;
        mergeSort(num,result,start1,end1);
        mergeSort(num,result,start2,end2);
        System.out.println("start:" + start);
        System.out.println("end:" + end);
        int k = start;
        while (start1 <= end1 && start2 <= end2) {
            result[k] = num[start1] < num[start2] ? num[start1++] : num[start2++];
            k++;
        }
        while (start1 <= end1) {
            result[k] = num[start1];
            start1 ++;
            k++;
        }
        while (start2 <= end2) {
            result[k] = num[start2];
            start2 ++;
            k++;
        }
        for(int i = start ; i <= end ; i++) {
            num[i] = result[i];
        }
        print(num);
    }

    private static int getK(int[] num, int k) {
        return getK(num,0,num.length - 1,k);
    }

    private static int getK(int[] num, int left, int right, int k) {
        int base = num[left];
        int start = left;
        int end = right;
        //头下标小于尾下标
        while (start < end) {
            //从尾往头，找到第一个小于base的值
            while (end > start) {
                if(num[end] < base) {
                    break;
                }
                end --;
            }
            //从头到尾，找到第一个大于base的值
            while (start < end) {
                if(num[start] > base) {
                    break;
                }
                start ++;
            }
            swap(num,start,end);
        }
        swap(num,left,start);
        if(k - 1 == start) {
            //直接返回
            return num[start];
        }else if(k - 1 < start) {
            //从左边找
            return getK(num,left,start - 1,k);
        }else {
            //从右边找
            return getK(num,start + 1,right,k);
        }
    }

    private static void fastSort(int[] num) {
        fastSort(num,0,num.length - 1);
    }

    private static void fastSort(int[] nums,int left, int right) {
        if(left > right) {
            return;
        }
        int base = nums[left];
        int i = left;
        int j = right;
        while (i != j) {
            //从右往左找一个小于基准值的元素
            while (j > i) {
                if(nums[j] < base) {
                    break;
                }
                j--;
            }

            //从左往右找一个大于基准值的元素
            while (i < j) {
                if(nums[i] > base) {
                    break;
                }
                i++;
            }
            if(i != j) {
                swap(nums,i,j);
            }
        }
        swap(nums,i,left);
        fastSort(nums,left,i - 1);
        fastSort(nums,i + 1,right);
    }

    private static void swap(int[] arr,int a,int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static void insertSort(int[] num) {
        for(int i = 1 ; i < num.length ; i++) {
            int insertIndex = i;
            for(int j = insertIndex - 1 ; j >= 0 ; j--) {
                if(num[insertIndex] < num[j]) {
                    int temp = num[insertIndex];
                    num[insertIndex] = num[j];
                    num[j] = temp;
                    insertIndex = j;
                }
            }
        }
    }

    private static void selectSort(int[] num) {
        for(int i = 0 ; i < num.length ; i++) {
            int minIndex = i;
            for(int j = i + 1; j < num.length ; j++) {
                if(num[j] < num[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = num[i];
            num[i] = num[minIndex];
            num[minIndex] = temp;
        }
    }

    private static void print(int[] num) {
        for(int i = 0 ; i < num.length ; i ++) {
            System.out.print(num[i] + " ");
        }
    }

    private static void bubbleSort(int[] num) {
        for(int i = 0 ; i < num.length ; i++) {
            for(int j = i + 1; j < num.length ; j++) {
                if(num[i] > num[j]) {
                    int temp = num[i];
                    num[i] = num[j];
                    num[j] = temp;
                }
            }
        }
    }
}
