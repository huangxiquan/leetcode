package leetcode20;

import java.util.Stack;

/**
 * Created by huangxiquan on 2020/5/10.
 * 20.有效的括号https://leetcode-cn.com/problems/valid-parentheses/
 */
public class Solution20 {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for(int i = 0 ; i < s.length() ; i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }else {
                if(stack.isEmpty()) {
                    return false;
                }
                char top = stack.peek();
                if(c == ')' && top != '(') {
                    return false;
                }
                if(c == ']' && top != '[') {
                    return false;
                }
                if(c == '}' && top != '{') {
                    return false;
                }
                stack.pop();
            }

        }
        return stack.isEmpty();
    }

    public static void main(String[] args){
        System.out.println(new Solution20().isValid("]"));
        System.out.println(new Solution20().isValid("()[]{}"));
        System.out.println(new Solution20().isValid("(]"));
        System.out.println(new Solution20().isValid("([)]"));
        System.out.println(new Solution20().isValid("{[]}"));
    }
}
