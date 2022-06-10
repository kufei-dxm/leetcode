package com.leetcode.linkedlist;

import com.leetcode.common.ListNode;
import com.leetcode.common.ListUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * 2. Add Two Numbers
 *
 * @author kufei.dxm
 * @date 2022/6/6
 */
public class Problem2 {
    private Problem2 solution;

    @Before
    public void setUp() {
        solution = new Problem2();
    }

    /**
     * 解法类似 Problem21的合并链表。不过代码比较啰嗦。
     *
     * 转换为数字BigInteger的解法是有点复杂化的。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newL = new ListNode(-1);
        ListNode head = newL;
        int jinWei = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + jinWei;
            ListNode node = new ListNode(sum % 10);
            jinWei = sum / 10;
            head.next = node;
            head = node;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int sum = l1.val + jinWei;
            jinWei = sum / 10;
            ListNode node = new ListNode(sum % 10);
            head.next = node;
            head = node;
            l1 = l1.next;
        }

        while (l2 != null) {
            int sum = l2.val + jinWei;
            jinWei = sum / 10;
            ListNode node = new ListNode(sum % 10);
            head.next = node;
            head = node;
            l2 = l2.next;
        }
        if (jinWei == 1) {
            head.next = new ListNode(1);
        }
        return newL.next;
    }

    /**
     * 把上面的解法精简下
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersV3(ListNode l1, ListNode l2) {

        ListNode newL = new ListNode(-1);
        ListNode head = newL;
        int jinWei = 0;
        while (l1 != null || l2 != null) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = x + y + jinWei;
            jinWei = sum / 10;
            head.next = new ListNode(sum % 10);
            head = head.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (jinWei > 0) {
            head.next = new ListNode(jinWei);
        }
        return newL.next;
    }

    /**
     * 递归解法。也不是太优雅。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersV2(ListNode l1, ListNode l2) {
        return doAdd(l1, l2, 0);
    }

    private ListNode doAdd(ListNode l1, ListNode l2, int i) {
        if (l1 == null) {
            return mergeList(l2, i);
        }
        if (l2 == null) {
            return mergeList(l1, i);
        }
        int sum = l1.val + l2.val + i;
        l1.val = sum % 10;
        l1.next = doAdd(l1.next, l2.next, sum > 9 ? 1 : 0);
        return l1;
    }

    private ListNode mergeList(ListNode l1, int i) {
        if (l1 == null) {
            if (i != 0) {
                return new ListNode(i);
            } else {
                return null;
            }
        }
        ListNode node = l1;
        while (l1.next != null) {
            int sum = l1.val + i;
            if (sum > 9) {
                i = 1;
            } else {
                i = 0;
            }
            l1.val = sum % 10;
            l1 = l1.next;
        }
        if (l1.val + i > 9) {
            l1.val = (l1.val + i) % 10;
            l1.next = new ListNode(1);
        }
        return node;
    }

    @Test
    public void test() {
        ListNode l1 = ListUtils.buildListNode(new int[] {2, 4, 3});
        ListNode l2 = ListUtils.buildListNode(new int[] {5, 6, 4});
        ListNode l3 = solution.addTwoNumbersV3(l1, l2);
        ListUtils.printListNode(l3);

        l3 = solution.addTwoNumbersV2(l1, l2);
        ListUtils.printListNode(l3);

        l1 = ListUtils.buildListNode(new int[] {9, 9, 9, 9, 9, 9, 9});
        l2 = ListUtils.buildListNode(new int[] {9, 9, 9, 9});
        l3 = solution.addTwoNumbersV3(l1, l2);
        ListUtils.printListNode(l3);

        l3 = solution.addTwoNumbersV2(l1, l2);
        ListUtils.printListNode(l3);
    }
}
