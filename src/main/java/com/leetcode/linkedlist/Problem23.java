package com.leetcode.linkedlist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.leetcode.common.ListNode;
import com.leetcode.common.Utils;
import org.junit.Before;
import org.junit.Test;

/**
 * 23. Merge k Sorted Lists
 *
 * @author kufei.dxm
 * @date 2022/6/6
 */
public class Problem23 {
    private Problem23 solution;

    @Before
    public void setUp() {
        solution = new Problem23();
    }

    /**
     * 两两合并，直接干到最优的solution！
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        int mergeSize = lists.length;
        if (null == lists || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }
        while (mergeSize > 1) {
            for (int i = 0; i < mergeSize / 2; i++) {
                lists[i] = mergeTwoList(lists[i], lists[mergeSize - 1 - i]);
            }
            if (mergeSize % 2 != 0 && mergeSize > 1) {
                mergeSize = (mergeSize + 1) / 2;
            } else {
                mergeSize /= 2;
            }
        }
        return lists[0];
    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
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

    @Test
    public void test() {
        ListNode l1 = Utils.buildListNode(new int[] {1, 4, 5});
        ListNode l2 = Utils.buildListNode(new int[] {1, 3, 4});
        ListNode l3 = Utils.buildListNode(new int[] {2, 6});
        ListNode[] list = new ListNode[] {l1, l2, l3};
        ListNode result = solution.mergeKLists(list);
        Utils.printListNode(result);
    }
}
