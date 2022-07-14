package com.leetcode.linkedlist;

import com.leetcode.common.ListNode;
import com.leetcode.common.ListUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * 24. Swap Nodes in Pairs
 *
 * @author kufei.dxm
 * @date 2022/7/14
 */
public class Problem24 {
    private Problem24 solution;

    @Before
    public void setUp() {
        solution = new Problem24();
    }

    /**
     * 有2处特判代码，不够简洁
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode prev = new ListNode(0);
        ListNode dummy = new ListNode(0);
        ListNode p1 = head;
        ListNode p2 = p1.next;
        while (p1 != null && p2 != null) {
            p1.next = p2.next;
            p2.next = p1;
            prev.next = p2;
            if (p1 == head) {
                dummy = prev;
            }
            prev = p1;
            p1 = p1.next;
            p2 = p1 == null ? null : p1.next;
        }
        return dummy.next;
    }

    /**
     * 比上面的精简很多，循环体内部用到了2个临时变量，不过少用了2个指针，少了特判逻辑
     *
     * @param head
     * @return
     */
    public ListNode swapPairsV2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;
            first.next = second.next;
            current.next = second;
            current.next.next = first;
            current = current.next.next;
        }
        return dummy.next;
    }

    /**
     * 递归解法，更精简。。。
     */
    public ListNode swapPairsV3(ListNode head) {
        if ((head == null) || (head.next == null)) {
            return head;
        }
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        return n;
    }

    @Test
    public void test() {
        int[] nums = new int[] {1, 2, 3, 4};
        ListNode listNode = ListUtils.buildListNode(nums);
        ListUtils.printListNode(listNode);
        ListNode res = solution.swapPairs(listNode);
        ListUtils.printListNode(res);
    }
}
