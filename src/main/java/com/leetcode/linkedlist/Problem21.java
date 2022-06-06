package com.leetcode.linkedlist;

import java.util.List;

import com.leetcode.common.ListNode;
import com.leetcode.common.Utils;
import org.junit.Before;
import org.junit.Test;

/**
 * 21. Merge Two Sorted Lists
 *
 * @author kufei.dxm
 * @date 2022/6/6
 */
public class Problem21 {
    private Problem21 solution;

    @Before
    public void setUp() {
        solution = new Problem21();
    }

    /**
     * 一遍过。不过代码看着比较啰嗦。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode temp = new ListNode(-1);
        ListNode newHead = temp;
        while (l1 != null && l2 != null) {
            ListNode node;
            if (l1.val >= l2.val) {
                node = new ListNode(l2.val);
                l2 = l2.next;
            } else {
                node = new ListNode(l1.val);
                l1 = l1.next;
            }
            temp.next = node;
            temp = node;
        }
        while (l1 != null) {
            ListNode node = new ListNode(l1.val);
            temp.next = node;
            temp = node;
            l1 = l1.next;
        }
        while (l2 != null) {
            ListNode node = new ListNode(l2.val);
            temp.next = node;
            temp = node;
            l2 = l2.next;
        }
        temp.next = null;
        return newHead.next;
    }

    /**
     * 递归解法简直牛X！
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoListsV2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoListsV2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsV2(l1, l2.next);
            return l2;
        }
    }

    @Test
    public void test() {
        ListNode l1 = Utils.buildListNode(new int[] {1, 2, 4});
        ListNode l2 = Utils.buildListNode(new int[] {1, 3, 4});
        ListNode newList = solution.mergeTwoLists(l1, l2);
        Utils.printListNode(newList);
        newList = solution.mergeTwoListsV2(l1, l2);
        Utils.printListNode(newList);
    }
}
