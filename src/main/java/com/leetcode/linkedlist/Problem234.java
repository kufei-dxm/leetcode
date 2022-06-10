package com.leetcode.linkedlist;

import com.leetcode.common.ListNode;
import com.leetcode.common.ListUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 234. Palindrome Linked List
 *
 * @author kufei.dxm
 * @date 2022/6/5
 */
public class Problem234 {
    private Problem234 solution;

    @Before
    public void setUp() {
        solution = new Problem234();
    }

    public boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast.next != null) {
                fast = fast.next;
            }
        }
        ListNode temp = new ListNode(slow.val);
        temp.next = null;
        while (slow.next != null) {
            slow = slow.next;
            ListNode newSlow = new ListNode(slow.val);
            newSlow.next = temp;
            temp = newSlow;
        }
        while (temp !=null) {
            if (temp.val != head.val) {
                return false;
            }
            head = head.next;
            temp = temp.next;
        }
        return true;
    }

    @Test
    public void test() {
        ListNode head = ListUtils.buildListNode(new int[] {   1});
        boolean ret = solution.isPalindrome(head);
        Assert.assertTrue(ret);
    }
}