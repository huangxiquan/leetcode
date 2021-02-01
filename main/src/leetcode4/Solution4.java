package leetcode4;

/**
 * 4. 寻找两个正序数组的中位数 https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 */
public class Solution4 {

    public static void main(String[] args) {
        System.out.println("main... ...");
//        int[] arr1 = new int[]{1,4,7,9};
//        int[] arr2 = new int[]{2,3,5};
//        int[] arr1 = new int[]{1,4,7,9};
//        int[] arr2 = new int[]{2,3,5,6};
//        int[] arr1 = new int[]{1,3};
//        int[] arr2 = new int[]{2};
//        int[] arr1 = new int[]{1,2};
//        int[] arr2 = new int[]{3,4};
//        int[] arr1 = new int[]{0,0};
//        int[] arr2 = new int[]{0,0};
//        int[] arr1 = new int[]{};
//        int[] arr2 = new int[]{1};
//        int[] arr1 = new int[]{2};
//        int[] arr2 = new int[]{};
//        int[] arr1 = new int[]{3};
//        int[] arr2 = new int[]{-2,-1};

//        [1,1,1,1,1,1,1,1,1,1,4,4]
//        [1,3,4,4,4,4,4,4,4,4,4]
        int[] arr1 = new int[]{1,1,1,1,1,1,1,1,1,1,4,4};
        int[] arr2 = new int[]{1,3,4,4,4,4,4,4,4,4,4};
        double result = findMedianSortedArrays(arr1,arr2);
        System.out.println(result);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2,nums1);
        }
        if(nums1.length == 0) {
            //第一个数组为空
            int mid = (nums2.length -1) / 2;
            if(nums2.length % 2 == 0) {
                //偶数
                return (nums2[mid] + nums2[mid + 1]) / 2.0;
            }else {
                //奇数
                return nums2[mid];
            }
        }
        int cc = (nums1.length + nums2.length + 1) / 2;
        int c1 = (nums1.length + 1) / 2;
        int l1 = nums1.length - 1;
        int l2 = nums2.length - 1;
        int L1 = 0,L2 = 0,R1 = 0,R2 = 0,c2 = 0;
        while (true) {
            c2 = cc - c1;
            if(c1 > 0 && c1 <= l1) {
                //切割点不在两头
                L1 = nums1[c1 - 1];
                R1 = nums1[c1];
            }else if(c1 > l1) {
                //切点在尾部
                L1 = nums1[l1];
                R1 = Integer.MAX_VALUE;
            }else if(c1 == 0) {
                //切点在头部
                L1 = Integer.MIN_VALUE;
                R1 = nums1[0];
            }
            if(c2 > 0 && c2 <= l2) {
                //切割点不在两头
                L2 = nums2[c2 - 1];
                R2 = nums2[c2];
            }else if(c2 > l2) {
                //切点在尾部
                L2 = nums2[l2];
                R2 = Integer.MAX_VALUE;
            }else if(c2 == 0) {
                //切点在头部
                L2 = Integer.MIN_VALUE;
                R2 = nums2[0];
            }
            if(L1 > R2) {
                c1 --;
            }else if(L2 > R1) {
                c1 ++;
            }else {
                break;
            }
        }
        if((nums1.length + nums2.length) % 2 == 0) {
            //偶数
            return (Math.max(L1,L2) + Math.min(R1,R2)) / 2.0;
        }else {
            //奇数
            return Math.max(L1,L2);
        }
    }
}
