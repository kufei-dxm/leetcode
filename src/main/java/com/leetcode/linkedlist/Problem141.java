package com.leetcode.linkedlist;

import com.leetcode.common.ListNode;
import org.junit.Before;
import org.junit.Test;

/**
 * 141. Linked List Cycle
 *
 * @author kufei.dxm
 * @date 2022/7/20
 */
public class Problem141 {
    private Problem141 solution;

    @Before
    public void setUp() {
        solution = new Problem141();
    }

    /**
     * 快慢指针解法。看起来简单，实际写边界条件及遍历逻辑得小心，容易搞错。
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
