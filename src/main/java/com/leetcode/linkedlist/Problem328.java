package com.leetcode.linkedlist;

import com.leetcode.common.ListNode;
import com.leetcode.common.Utils;
import org.junit.Before;
import org.junit.Test;

/**
 * 328. Odd Even Linked List
 *
 * @author kufei.dxm
 * @date 2022/6/6
 */
public class Problem328 {
    private Problem328 solution;

    @Before
    public void setUp() {
        solution = new Problem328();
    }

    /**
     * OK。不过多了node这个临时变量。（可以干掉，不过链表尾部需要小心处理，避免成环状）
     *
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int idx = 1;
        ListNode evenHead = new ListNode(0);
        ListNode oddHead = new ListNode(0);
        ListNode dummyEvenHead = evenHead;
        ListNode newHead = oddHead;
        while (head != null) {
            if (idx % 2 == 0) {
                evenHead.next = head;
                evenHead = evenHead.next;
            } else {
                oddHead.next = head;
                oddHead = oddHead.next;
            }
            head = head.next;
            idx++;
        }
        //下面2步很重要
        oddHead.next = dummyEvenHead.next;
        evenHead.next = null;
        return newHead.next;
    }

    @Test
    public void test() {
        ListNode head = Utils.buildListNode(new int[] {1, 2, 3, 4, 5});
        Utils.printListNode(head);
        ListNode newHead = solution.oddEvenList(head);
        Utils.printListNode(newHead);

        head = Utils.buildListNode(new int[] {2, 1, 3, 5, 6, 4, 7});
        Utils.printListNode(head);
        newHead = solution.oddEvenList(head);
        Utils.printListNode(newHead);
    }
}
