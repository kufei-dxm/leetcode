package com.leetcode.linkedlist;

import com.leetcode.common.ListNode;

/**
 * @Author dxm
 * @Date 2025/7/3
 */
public class Problem19A {
    /**
     * NB！代码居然一遍AC！！！
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode slowPre = null;
        int delay = 0;
        while (fast != null) {
            fast = fast.next;
            if (delay++ >= n) {
                slowPre = slow;
                slow = slow.next;
            }
        }
        if (null == slowPre) {
            head = head.next;
        } else {
            slowPre.next = slow.next;
        }
        return head;
    }
}
