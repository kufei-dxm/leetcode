package com.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

import com.leetcode.common.ListNode;
import com.leetcode.common.ListUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * Remove Nth Node From End of List
 *
 * @author kufei.dxm
 * @date 2022/5/22
 */
public class Problem19 {
    private Problem19 solution;

    @Before
    public void setUp() {
        solution = new Problem19();
    }

    /**
     * 常规解法，依赖额外的map来统计链表当前位置。
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        Map<Integer, ListNode> nodeMap = new HashMap<>();
        int i = 0;
        ListNode temp = head;
        while (head != null) {
            nodeMap.put(i++, head);
            head = head.next;
        }
        if (i == n) {
            temp = nodeMap.get(1);
        } else {
            ListNode node = nodeMap.get(i - n - 1);
            node.next = nodeMap.get(i - n + 1);
        }
        return temp;
    }

    /**
     * 比较巧妙的思路。借住slow、fast两个指针。
     * 添加虚拟头结点以解决删除头节点的特判问题。
     * 不借助额外存储。
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEndV2(ListNode head, int n) {
        ListNode start = new ListNode(0);
        ListNode slow = start, fast = start;
        start.next = head;
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return start.next;
    }

    @Test
    public void test() {
        ListNode link = ListUtils.buildListNode(new int[] {1, 2, 3, 4, 5, 6, 7});
        ListNode node = solution.removeNthFromEndV2(link, 3);

        node = solution.removeNthFromEndV2(ListUtils.buildListNode(new int[] {1}), 1);
        node = solution.removeNthFromEnd(ListUtils.buildListNode(new int[] {1, 2}), 1);
        node = solution.removeNthFromEnd(ListUtils.buildListNode(new int[] {1, 2}), 2);
    }
}
