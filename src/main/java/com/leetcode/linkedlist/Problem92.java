package com.leetcode.linkedlist;

import com.leetcode.common.ListNode;
import com.leetcode.common.Utils;
import org.junit.Before;
import org.junit.Test;

/**
 * 92. Reverse Linked List II
 *
 * @author kufei.dxm
 * @date 2022/6/5
 */
public class Problem92 {
    private Problem92 solution;

    @Before
    public void setUp() {
        solution = new Problem92();
    }

    /**
     * 复杂的各种临时指针
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null) {
            return head;
        }
        int idx = 1;
        ListNode tempHead = head, tempTail = head;
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode pre = newHead;
        while (head != null) {
            if (idx < left) {
                pre = head;
            }
            if (idx == left) {
                tempHead = new ListNode(head.val);
                tempTail = tempHead;
                pre.next = tempHead;
            }
            if (idx > left && idx <= right) {
                ListNode tmp = new ListNode(head.val);
                tmp.next = tempHead;
                tempHead = tmp;
                pre.next = tempHead;
                tempTail.next = head.next;
            }
            if (idx == right) {
                tempTail.next = head.next;
            }
            head = head.next;
            idx++;
        }
        return newHead.next;
    }

    @Test
    public void test() {
        ListNode head = Utils.buildListNode(new int[] {1, 2, 3, 4, 5});
        Utils.printListNode(head);
        ListNode reversed = solution.reverseBetween(head, 1, 1);
        Utils.printListNode(reversed);
    }
}
