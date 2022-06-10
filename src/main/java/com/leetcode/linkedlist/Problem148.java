package com.leetcode.linkedlist;

import com.leetcode.common.ListNode;
import com.leetcode.common.ListUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * 148. Sort List
 *
 * @author kufei.dxm
 * @date 2022/6/6
 */
public class Problem148 {
    private Problem148 solution;

    @Before
    public void setUp() {
        solution = new Problem148();
    }

    /**
     * 变态的题目要求，只能上merge sort了。。
     *
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = head;
        ListNode newNode = mergeSort(head);
        return newNode;
    }

    public ListNode mergeSort(ListNode head) {
        if (lenOf(head) >= 2) {
            ListNode[] split = split(head);
            return mergeTwoList(mergeSort(split[0]), mergeSort(split[1]));
        } else {
            return head;
        }
    }

    private int lenOf(ListNode head) {
        int l = 0;
        while (head != null) {
            l++;
            head = head.next;
        }
        return l;
    }

    private ListNode[] split(ListNode head) {
        ListNode fast = head, slow = head, pre = head, newHead = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode list2 = slow;
        pre.next = null;
        ListNode[] result = new ListNode[2];
        result[0] = newHead;
        result[1] = list2;
        return result;
    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        if (null == l1) {
            return l2;
        }
        if (null == l2) {
            return l1;
        }
        if (l1.val > l2.val) {
            l2.next = mergeTwoList(l1, l2.next);
            return l2;
        } else {
            l1.next = mergeTwoList(l1.next, l2);
            return l1;
        }
    }

    /**
     * 链表长度判断的逻辑可以去除
     *
     * @param head
     * @return
     */
    public ListNode sortListV2(ListNode head) {
        if (head == null || head.next == null) { return head; }

        // step 1. cut the list to two halves
        ListNode prev = null, slow = head, fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;

        // step 2. sort each half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        // step 3. merge l1 and l2
        return mergeTwoList(l1, l2);
    }

    @Test
    public void test() {
        ListNode list = ListUtils.buildListNode(new int[] {4, 2, 1, 3});
        ListNode result = solution.sortList(list);
        ListUtils.printListNode(result);

        list = ListUtils.buildListNode(new int[] {-1, 5, 3, 4, 0});
        result = solution.sortList(list);
        ListUtils.printListNode(result);
    }
}
