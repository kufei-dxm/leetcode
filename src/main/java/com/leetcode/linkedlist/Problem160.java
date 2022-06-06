package com.leetcode.linkedlist;

import com.leetcode.common.ListNode;
import org.junit.Before;

/**
 * 160. Intersection of Two Linked Lists
 *
 * @author kufei.dxm
 * @date 2022/6/6
 */
public class Problem160 {
    private Problem160 solution;

    @Before
    public void setUp() {
        solution = new Problem160();
    }

    /**
     * 半天才理解题目意思。。。常规思路：先按长度对齐，然后比较
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = lenOfList(headA);
        int lenB = lenOfList(headB);
        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }
        while (lenB > lenA) {
            headB = headB.next;
            lenB--;
        }
        while (headA != null && headB != null) {
            if (headA.val == headB.val) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    private int lenOfList(ListNode node) {
        ListNode temp = node;
        int len = 0;
        while (temp != null) {
            temp = temp.next;
            len++;
        }
        return len;
    }

    /**
     * 奇淫技巧。。。两次交换。
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNodeV2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode a = headA;
        ListNode b = headB;
        while (a != b) {
            a = a == null ? headB : headA.next;
            b = b == null ? headA : headB.next;
        }
        return a;
    }
}
