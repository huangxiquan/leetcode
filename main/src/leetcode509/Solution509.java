package leetcode509;

/**
 * 509. 斐波那契数 https://leetcode-cn.com/problems/fibonacci-number/
 */
public class Solution509 {

    public  int fib(int N) {
        if(N == 0) {
            return 0;
        }
        if(N == 1) {
            return 1;
        }
        return fib(N - 1) + fib(N - 2);
    }

    public static void main(String args[]) {
        System.out.println(new Solution509().fib(3));
    }
}
