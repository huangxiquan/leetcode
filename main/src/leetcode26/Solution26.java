package leetcode26;

/**
 * Created by huangxiquan on 2020/5/30.
 */
public class Solution26 {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int count = 1;
        for(int i = nums.length - 1 ; i > 0 ; i--) {
            if(nums[i] == nums[i - 1]) {
                nums[i] = nums[nums.length - 1];
            }else {
                count ++;
            }
        }
        printArr(nums);
        System.out.println();
        int a = 0;
        int end = nums[nums.length - 1];
        for(int i = 1 ; i < nums.length ; i++) {
            if(nums[i] == end && a == 0) {
                a = i;
            }
            if(nums[i] < end) {
                if(a != 0) {
                    swap(nums,a,i);
                    i = a;
                    a = 0;
                }
            }
        }
        printArr(nums);
        System.out.println();
        return count;
    }

    private void swap(int[] nums,int a,int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b]  = temp;
    }

    private void printArr(int[] nums) {
        for(int i = 0 ; i < nums.length ; i++) {
            System.out.print(nums[i] + ",");
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};
        System.out.println(new Solution26().removeDuplicates(nums));
    }
}
