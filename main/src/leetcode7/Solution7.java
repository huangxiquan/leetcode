package leetcode7;

/**
 * Created by huangxiquan on 2020/5/29.
 * 7. 整数反转 https://leetcode-cn.com/problems/reverse-integer/
 */
public class Solution7 {
    public int reverse(int x) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(x);
        String resultStr;
        if (stringBuilder.charAt(0) == '-') {
            resultStr = "-" + stringBuilder.reverse().substring(0,stringBuilder.length() - 1);
        }else {
            resultStr = stringBuilder.reverse().toString();
        }
        int result = 0;
        try {
            result = Integer.parseInt(resultStr);
        }catch (Exception exception){

        }
        return result;
    }


    public static void main(String[] args) {

        System.out.println(2 << 30);
        System.out.println(new Solution7().reverse(-214748364));
        System.out.println(Integer.parseInt("2147483648"));

    }
}
