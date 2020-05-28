/**
 * Created by huangxiquan on 2020/5/23.
 */
public class Test {
    public static void main(String[] args) {
        int[] num = new int[]{5,4,3,2,1};
//        int[] num = new int[]{2,3};
//        bubbleSort(num);
//        selectSort(num);w
//        insertSort(num);
//        fastSort(num);
//        print(num);
        //        7 1 5 3 6 4

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
            int minIndex = 0;
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
            System.out.println(num[i]);
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
