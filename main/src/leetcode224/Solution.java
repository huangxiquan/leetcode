package leetcode224;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by huangxiquan on 2020/5/23.
 * 224:基本计算器 https://leetcode-cn.com/problems/basic-calculator/
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println("1+1");
    }

    public int calculate(String strExpression) {
        strExpression = strExpression.replaceAll("\\{","\\(");
        strExpression = strExpression.replaceAll("\\}","\\)");
        strExpression = strExpression.replaceAll("\\[","\\(");
        strExpression = strExpression.replaceAll("\\]","\\)");
        //3+2*(1+2*(-4/(8-6)+7))
        //中缀表达式转后缀表达式
//        System.out.println(strExpression);
        Queue<String> newExpression = new LinkedList<>();
        Stack<Character> operator = new Stack<>();
        String numStr = "";//处理多位数
        for(int i = 0 ; i < strExpression.length() ; i++) {
            Character c = strExpression.charAt(i);
            if(c == ' ') {
                continue;
            }
            if(Character.isDigit(c)) {
                //如果是数字
                numStr += c;
            }else  {
                if(!numStr.isEmpty()) {
                    newExpression.add(numStr);
                    numStr = "";
                }
                //处理负号的情况
                if(c == '-' && i > 0 && (Character.isDigit(strExpression.charAt(i - 1)) || strExpression.charAt(i - 1) == ')')) {
                    //是减号
                }else if(c == '-'){
                    //是负号
                    c = '#';
                }
                if(operator.isEmpty() || c == '(') {
                    operator.push(c);
                }else if(c == ')'){
                    Character top = operator.pop();
                    while (top != '(') {
                        newExpression.add(top + "");
                        top = operator.pop();
                    }
                }else {
                    Character top = operator.peek();
                    if(isOperator(top+ "")) {
                        //如果栈顶也是操作符
                        while ((isOperator(top + "") && (c == '-' || c == '+')) || (top == '#')) {
                            newExpression.add(operator.pop() + "");
                            if(operator.isEmpty()) {
                                break;
                            }
                            top = operator.peek();

                        }
                        operator.push(c);
                    }else {
                        operator.push(c);
                    }
                }
            }

        }
        if(!numStr.isEmpty()) {
            newExpression.add(numStr);
            numStr = "";
        }
        while (!operator.isEmpty()) {
            Character pop = operator.pop();
            if(!(pop + "").isEmpty()) {
                newExpression.add( pop+ "");
            }
        }
//        System.out.println(newExpression);
        Stack<Integer> calculate = new Stack<>();
        while (!newExpression.isEmpty()) {
            String str = newExpression.poll();
            if(isOperator(str)) {
                //操作符
                if("#".equals(str)) {
                    Integer num = calculate.pop();
                    calculate.push(num * -1);
                }else if("-".equals(str)) {
                    Integer rightNum = calculate.pop();
                    Integer leftNum = calculate.pop();
                    calculate.push(leftNum - rightNum);
                }else if("+".equals(str)) {
                    Integer rightNum = calculate.pop();
                    Integer leftNum = calculate.pop();
                    calculate.push(leftNum + rightNum);
                }else if("*".equals(str)) {
                    Integer rightNum = calculate.pop();
                    Integer leftNum = calculate.pop();
                    calculate.push(leftNum * rightNum);
                }else {
                    Integer rightNum = calculate.pop();
                    Integer leftNum = calculate.pop();
                    calculate.push(leftNum / rightNum);
                }
            }else {
                calculate.push(Integer.parseInt(str));
            }
        }
        System.out.println(calculate.peek());
        return calculate.peek();

    }
    private boolean isOperator(String c) {
        return "+-*/#".contains(c);
    }
}
