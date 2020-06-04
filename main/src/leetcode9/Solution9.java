package leetcode9;

/**
 * Created by huangxiquan on 2020/5/29.
 *  9. 回文数 https://leetcode-cn.com/problems/palindrome-number/
 */
public class Solution9 {

    public boolean isPalindrome(int x) {
        if(x < 0) {
            return false;
        }
        int result = getPalindrome(x,0);
        System.out.println(result);
        return x == result;
    }

    private int getPalindrome(int x, int result) {
        if(x == 0) {
            return result;
        }
        int y = x % 10;
       return getPalindrome(x / 10,result * 10 + y);
    }

    public static void main(String[] args) {
        System.out.println(new Solution9().isPalindrome(1221));
    }
}
