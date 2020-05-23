package niuke;

import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Created by huangxiquan on 2020/5/18.
 */
public class Done {

    private static void reverseInt() {
        Scanner sc = new Scanner(System.in);
        int inNum = sc.nextInt();
        StringBuilder builder = new StringBuilder();
        builder.append(inNum);
        builder.reverse();
        System.out.println(builder.toString());
    }

    private static void reverseString() {
        Scanner sc = new Scanner(System.in);
        String inString = sc.nextLine();
        String outString = "";
        for(int i = inString.length() - 1 ; i >= 0 ; i--) {
            outString += inString.charAt(i);
        }
        System.out.println(outString);
    }


    private static void dulplicatAndSort() {
        Scanner sc = new Scanner(System.in);
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        while (sc.hasNext()) {
            int count = sc.nextInt();
            for(int i = 0 ; i < count ; i++) {
                treeMap.put(sc.nextInt(),0);
            }
            for(Integer key : treeMap.keySet()) {
                System.out.println(key);
            }
            treeMap.clear();
        }
    }

    private static void caculateCharCount() {
        Scanner sc = new Scanner(System.in);
        String inString = sc.nextLine().trim().toLowerCase();
        String target = sc.nextLine().trim().toLowerCase();
        char c = target.charAt(0);
        int count = 0;
        for(int i = 0 ; i < inString.length() ; i++) {
            if(c == inString.charAt(i)) {
                count ++;
            }
        }
        System.out.println(count);
    }

    private static void lastWordLength() {
        Scanner sc = new Scanner(System.in);
        String inString = sc.nextLine().trim();
        Stack<Character> stack = new Stack();
        int currentIndex = inString.length() - 1;
        char current;
        while (currentIndex >= 0 && (current = inString.charAt(currentIndex)) != ' ') {
            stack.push(current);
            currentIndex --;
        }
        System.out.println(stack.size());
    }
}
