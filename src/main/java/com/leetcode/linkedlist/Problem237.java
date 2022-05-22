package com.leetcode.linkedlist;

import com.leetcode.common.ListNode;
import org.junit.Before;

/**
 * Delete Node in a Linked List
 *
 * @author kufei.dxm
 * @date 2022/5/21
 */
public class Problem237 {
    private Problem237 solution;

    @Before
    public void setUp() {
        solution = new Problem237();
    }

    /**
     * 这题目有点鸡贼，初看还以为接口定义的不对。。。。
     * 思路是把下个节点的值赋给当前节点，然后跳过下一节点。
     *
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
