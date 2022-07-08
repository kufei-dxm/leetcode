package com.leetcode.linkedlist;

import com.leetcode.common.ListNode;
import com.leetcode.common.ListUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * 206. Reverse Linked List
 * @author kufei.dxm
 * @date 2022/6/5
 */
public class Problem206 {
    private Problem206 solution;

    @Before
    public void setUp() {
        solution = new Problem206();
    }

    /**
     * 原地反转，bingo！留意引用赋值!
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = new ListNode(head.val);
        temp.next = null;
        while (head.next != null) {
            head = head.next;
            ListNode newTemp = new ListNode(head.val);
            newTemp.next = temp;
            temp = newTemp;
        }
        return temp;
    }

    @Test
    public void test() {
        ListNode head = ListUtils.buildListNode(new int[] {1, 2, 3, 4, 5});
        ListUtils.printListNode(head);
        ListNode reversed = solution.reverseList(head);
        ListUtils.printListNode(reversed);
    }
}
