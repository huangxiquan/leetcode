package leetcode211;

import java.util.HashMap;

/**
 * Created by huangxiquan on 2020/5/19.
 * 211:添加与搜索单词 - 数据结构设计 https://leetcode-cn.com/problems/add-and-search-word-data-structure-design/
 */
public class WordDictionary {

    class Node {
        boolean isWord;
        HashMap<Character,Node> map = new HashMap<>();
        public Node(boolean isWord) {
            this.isWord = isWord;
        }
    }
    Node root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node(false);
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node currentNode = root;
        for(int i = 0 ; i < word.length() ; i++) {
            char c = word.charAt(i);
            if(!currentNode.map.containsKey(c)) {
                currentNode.map.put(c,new Node(false));
            }
            currentNode = currentNode.map.get(c);
        }
        currentNode.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return search(root,word);
    }

    private boolean search(Node node, String word) {
        for(int i = 0 ; i < word.length() ; i++) {
            char c = word.charAt(i);
            if(c == '.') {
                for(Character key : node.map.keySet()) {
                    if(search(node.map.get(key),word.substring(i + 1))) {
                        return true;
                    }
                }
                return false;
            }else {
                if(!node.map.containsKey(c)) {
                    return false;
                }
                node = node.map.get(c);
            }
        }
        return node.isWord;
    }

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
//        wordDictionary.addWord("a");
//        wordDictionary.addWord("a");
//        System.out.println(wordDictionary.search("."));
//        System.out.println(wordDictionary.search("a"));
//        System.out.println(wordDictionary.search("aa"));
//        System.out.println(wordDictionary.search("a"));
//        System.out.println(wordDictionary.search(".a"));
//        System.out.println(wordDictionary.search("a."));
//        wordDictionary.addWord("bad");
//        wordDictionary.addWord("dad");
//        wordDictionary.addWord("mad");
//        System.out.println(wordDictionary.search("pad"));
//        System.out.println(wordDictionary.search("bad"));
//        System.out.println(wordDictionary.search(".ad"));
//        System.out.println(wordDictionary.search("b.."));
//[[],["at"],["and"],["an"],["add"],["a"],[".at"],["bat"],[".at"],["an."],["a.d."],["b."],["a.d"],["."]]
        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");
        System.out.println(wordDictionary.search("a"));
        System.out.println(wordDictionary.search(".at"));
        wordDictionary.addWord("bat");
        System.out.println(wordDictionary.search(".at"));
    }
}
