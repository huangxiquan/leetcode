package leetcode14;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by huangxiquan on 2020/5/30.
 * 14. 最长公共前缀 https://leetcode-cn.com/problems/longest-common-prefix/
 */
public class Solution14 {

    Node root = new Node();

    class Node {
        public HashMap<Character,Node> next = new HashMap<>();
    }

    public String longestCommonPrefix(String[] strs) {
        int minLength = 0;
        for(int i = 0 ; i < strs.length ; i++) {
            if(strs[i].isEmpty()) {
                return "";
            }
            if(minLength == 0) {
                minLength = strs[i].length();
            }else {
                minLength = Math.min(minLength,strs[i].length());
            }
            addWord(strs[i],root);
        }
        StringBuilder prefix = new StringBuilder();
        Node current = root;
        System.out.println("minLength:" + minLength);
        while (current.next != null && current.next.size() == 1 && prefix.length() < minLength) {
            for(Character character : current.next.keySet()) {
                prefix.append(character);
                current = current.next.get(character);
            }
        }
        return prefix.toString();
    }

    private void addWord(String word,Node node) {
        for(int i = 0 ; i < word.length() ; i++) {
            char c = word.charAt(i);
            if(!node.next.containsKey(c)) {
                node.next.put(c,new Node());
            }
            node = node.next.get(c);
        }
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"aa","a"};
        System.out.println(new Solution14().longestCommonPrefix(strs));
    }

}
