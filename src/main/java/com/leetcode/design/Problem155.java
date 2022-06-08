package com.leetcode.design;

import java.util.HashMap;

/**
 * 155. Min Stack
 *
 * @author kufei.dxm
 * @date 2022/6/7
 */
public class Problem155 {
    /**
     * LIFO
     */
    class MinStack {
        private Node head;

        public MinStack() {

        }

        public void push(int val) {
            if (head == null) {
                head = new Node(val, val, null);
            } else {
                head = new Node(val, Math.min(val, head.min), head);
            }
        }

        public void pop() {
            head = head.next;
        }

        public int top() {
            return head.val;
        }

        public int getMin() {
            return head.min;
        }
    }

    class Node {
        int val;
        int min;
        Node next;

        public Node(int val, int min) {
            this.val = val;
            this.min = min;
        }

        public Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}
