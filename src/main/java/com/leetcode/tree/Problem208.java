package com.leetcode.tree;

import org.junit.Before;

/**
 * 208. Implement Trie (Prefix Tree)
 *
 * @author kufei.dxm
 * @date 2022/6/15
 */
public class Problem208 {
    private Problem208 solution;

    @Before
    public void setUp() {
        solution = new Problem208();
    }

   class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char curChar = word.charAt(i);
                if (!node.containsKey(curChar)) {
                    node.put(curChar, new TrieNode());
                }
                node = node.get(curChar);
            }
            node.setEnd();
        }

        private TrieNode searchPrefix(String prefix) {
            TrieNode node = root;
            for (int i = 0; i < prefix.length(); i++) {
                char curChar = prefix.charAt(i);
                if (node.containsKey(curChar)) {
                    node = node.get(curChar);
                } else {
                    return null;
                }
            }
            return node;
        }

        public boolean search(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isEnd();
        }

        public boolean startsWith(String prefix) {
            TrieNode node = searchPrefix(prefix);
            return node != null;
        }

    }

    static class TrieNode {
        private TrieNode[] links;
        private final int R = 26;
        private boolean isEnd;

        public TrieNode() {
            links = new TrieNode[R];
        }

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }
}
