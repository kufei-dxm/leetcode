package com.leetcode.design;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

/**
 * 146. LRU Cache
 * 双向链表+Map
 * @author kufei.dxm
 * @date 2022/5/31
 */
public class Problem146 {
    private Problem146 solution;

    @Before
    public void setUp() {
        solution = new Problem146();
    }

    class LRUCache {
        class Node {
            int key;
            int value;
            Node pre;
            Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private HashMap<Integer, Node> map;
        private int capacity;
        Node head, tail;

        public LRUCache(int capacity) {
            map = new HashMap<>();
            this.capacity = capacity;
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node != null) {
                deleteNode(node);
                addToHead(node);
                return node.value;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            Node node = new Node(key, value);
            if (map.containsKey(key)) {
                deleteNode(map.get(key));
                addToHead(node);
            } else {
                addToHead(node);
                if (map.size() > capacity) {
                    deleteNode(tail.pre);
                }
            }
        }

        private void addToHead(Node node) {
            node.next = head.next;
            node.pre = head;
            head.next = node;
            node.next.pre = node;
            map.put(node.key, node);
        }

        private void deleteNode(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            map.remove(node.key);
        }

    }

    @Test
    public void test() {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(2);    // return 1
        lRUCache.put(1, 1); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.put(4, 1); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(2);    // return 4
    }
}
