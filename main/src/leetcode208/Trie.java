package leetcode208;

import java.util.HashMap;

/**
 * Created by huangxiquan on 2020/5/18.
 * 208:实现前缀树 https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 */
public class Trie {

    class Node {
        boolean isWord = false;
        HashMap<Character,Node> map = new HashMap<>();
    }

    Node root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
//        Node current = root;
//        for(int i = 0 ; i < word.length() ; i++) {
//            char c = word.charAt(i);
//            if(!current.map.containsKey(c)) {
//                current.map.put(c,new Node());
//            }
//            current = current.map.get(c);
//        }
//        current.isWord = true;
        insert(root,word);

    }

    private void insert(Node node,String word) {
        char c = word.charAt(0);
        if(word.length() == 1) {
            if(! node.map.containsKey(c)) {
                Node leaf = new Node();
                leaf.isWord = true;
                node.map.put(c,leaf);
            }else {
                node.map.get(c).isWord = true;
            }
            return;
        }
        if(! node.map.containsKey(c)) {
            node.map.put(c,new Node());
        }
        insert(node.map.get(c),word.substring(1,word.length()));
    }


    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node current = root;
        for(int i = 0 ; i < word.length() ; i++) {
            char c = word.charAt(i);
            if(!current.map.containsKey(c)) {
                return false;
            }
            current = current.map.get(c);
        }
        return current.isWord;
    }


    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node current = root;
        for(int i = 0 ; i < prefix.length() ; i++) {
            char c = prefix.charAt(i);
            if(!current.map.containsKey(c)) {
                return false;
            }
            current = current.map.get(c);
        }
        return true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.startsWith("app"));
    }
}
