package leetcode3;

/**
 * Created by huangxiquan on 2020/6/2.
 */
public class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        StringBuilder builder = new StringBuilder();
        for(int i = 0 ; i < s.length() ; i ++) {
            char c = s.charAt(i);
            int index = builder.indexOf(c + "");
            if(index == -1) {
                builder.append(c);
            }else {
                maxLength = Math.max(maxLength,builder.length());
                builder.delete(0,index + 1);
                builder.append(c);
            }
        }
        return Math.max(maxLength,builder.length());
    }

    public static void main(String[] args) {
        System.out.println(new Solution3().lengthOfLongestSubstring("pwwkew"));
    }
}
