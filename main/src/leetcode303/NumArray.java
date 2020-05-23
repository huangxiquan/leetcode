package leetcode303;

/**
 * Created by huangxiquan on 2020/5/18.
 * 303:区域和检索 - 数组不可变 https://leetcode-cn.com/problems/range-sum-query-immutable/
 */
public class NumArray {
    int[] sum;

    public NumArray(int[] nums) {
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for(int i = 0 ; i < nums.length ; i++) {
            sum[i + 1] = nums[i] + sum[i];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray);
        System.out.println(numArray.sumRange(0,2));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for(int i = 0 ; i < sum.length ; i ++) {
            builder.append(sum[i] + ",");
        }
        builder.append("]");
        return builder.toString();
    }
}
