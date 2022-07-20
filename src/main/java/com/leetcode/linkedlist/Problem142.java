package com.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

import com.leetcode.common.ListNode;

/**
 * 142. Linked List Cycle II
 *
 * @author kufei.dxm
 * @date 2022/7/20
 */
public class Problem142 {
    /**
     * map的解法，空间复杂度应该是O(n)。O(1)的解法暂时想不到...
     *
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        Map<ListNode, Integer> map = new HashMap<>();
        while (head != null) {
            if (map.containsKey(head)) {
                return head;
            }
            map.put(head, 1);
            head = head.next;
        }
        return null;
    }

    /**
     * 巧妙的解法，精简的代码，一遍过！
     *
     * @param head
     * @return
     */
    public ListNode detectCycleV2(ListNode head) {
        ListNode fast = head, slow = head, slow1 = head;
        while (slow != null & fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                while (slow1 != slow) {
                    slow1 = slow1.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}
