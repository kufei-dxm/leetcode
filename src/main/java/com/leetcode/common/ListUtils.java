package com.leetcode.common;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author kufei.dxm
 * @date 2022/6/9
 */
public class ListUtils {
    public static ListNode buildListNode(int[] nums) {
        ListNode head = new ListNode(nums[0]);
        ListNode temp = head;
        for (int i = 1; i < nums.length; i++) {
            ListNode node = new ListNode(nums[i]);
            head.next = node;
            head = node;
        }
        return temp;
    }

    public static void printListNode(ListNode head) {
        while (head != null) {
            if (head.next == null) {
                System.out.print(head.val);
            } else {
                System.out.print(head.val + " -> ");
            }
            head = head.next;
        }
        System.out.println();
    }

    @Test
    public void testBuildList() {
        int[] list = new int[] {4, 5, 1, 9};
        ListNode listNode = ListUtils.buildListNode(list);
        Assert.assertNotNull(listNode);
    }
}
