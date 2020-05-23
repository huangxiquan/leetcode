package niuke;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangxiquan on 2020/5/18.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();
//        Pattern p = Pattern.compile("(?<!\\d)-?\\d+(\\.\\d+)?|[+\\-*/()]");
//        Matcher matcher = p.matcher(expression);
//        while (matcher.find()) {
//            System.out.println(matcher.group());
//        }
        calculate(expression);
        //3*5+8-0*3-6+0+0
        //"(1+(4+5+2)-3)+(6+8)"
        //5-3+9*6*(6-10-2)
    }

    public static int calculate(String strExpression) {
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

    private static boolean isOperator(String c) {
        return "+-*/#".contains(c);
    }


    private static void sumAwardBottle() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int i = sc.nextInt();
            int total = sumAwardBottle(i);
            System.out.println(total);
        }
    }

    private static int sumAwardBottle(int i) {
        if(i < 3) {
            if(i == 2) {
                return 1;
            }
            return 0;
        }


        return i / 3 + sumAwardBottle(i / 3 + i % 3);
    }

    private static void maze() {
        Scanner sc = new Scanner(System.in);
        int raw = sc.nextInt();
        int column = sc.nextInt();
        int[][] arr = new int[raw][column];
        int readLine = 0;
        while (readLine < raw) {
            String numString = sc.nextLine().trim();
            if(numString.isEmpty()) {
                continue;
            }
            String[] split = numString.split(" ");
            for(int i = 0 ; i < split.length ; i++) {
                arr[readLine][i] = Integer.parseInt(split[i]);
            }
            readLine ++;
        }
        Queue<Node> queue = new LinkedList();
        Node first = new Node(0,0,null);
        queue.add(first);
        //上下左右四种走法
        int[][] step = new int[][]{{0,-1},{0,1},{-1,0},{1,0}};
        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();
            if(currentNode.raw == raw - 1 && currentNode.column == column - 1) {
                //走出迷宫，打印链路
//                System.out.println("走出迷宫:" + currentNode);
                Stack<Node> stack = new Stack<>();
                stack.push(currentNode);
                while (currentNode.pre != null) {
                    stack.push(currentNode.pre);
                    currentNode = currentNode.pre;
                }
                System.out.println();
                while (!stack.isEmpty()) {
                    System.out.println(stack.pop());
                }
                //跳出循环
                break;
            }
            for(int i = 0 ; i < 4 ; i++) {
                //尝试上下左右四种走法
                Node newNode = new Node(currentNode.raw + step[i][0],currentNode.column + step[i][1],currentNode);
                if(newNode.raw < 0 || newNode.column < 0 || newNode.raw >= raw || newNode.column >= column) {
                    //越界
                    continue;
                }
                if(newNode.equals(currentNode.pre)) {
                    //前一个节点
                    continue;
                }
                if(arr[newNode.raw][newNode.column] == 1) {
                    //撞墙
                    continue;
                }
                //将新的节点加入队列
//                System.out.println("add queue:" + newNode);
                queue.add(newNode);

            }

        }
    }
}

class Node {
    public int raw;
    public int column;
    public Node pre;

    public Node(int raw,int column,Node pre) {
        this.raw = raw;
        this.column = column;
        this.pre = pre;
    }

    @Override
    public String toString() {
        return "(" + raw + "," + column + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Node) {
            return this.raw == ((Node) obj).raw && this.column == ((Node) obj).column;
        }
        return false;
    }
}

