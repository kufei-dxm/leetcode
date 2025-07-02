package com.leetcode.linkedlist;

import com.leetcode.common.ListNode;
import com.leetcode.common.ListUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author dxm
 * @Date 2025/7/1
 * @title 83. Remove Duplicates from Sorted List
 * @link https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
 */
public class Problem83 {
    private Problem83 solution;

    @Before
    public void setUp() {
        solution = new Problem83();
    }

    /**
     *  比较ugly的实现。有特判逻辑，还有新node的赋值逻辑。
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newList = new ListNode(head.val);
        ListNode curIndex = newList;
        while (head.next != null) {
            if (head.next.val != curIndex.val) {
                ListNode newNode = new ListNode(head.next.val);
                curIndex.next = newNode;
                curIndex = newNode;
            }
            head = head.next;
        }
        return newList;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        ListNode result = head;
        while (head !=null && head.next !=null){
            if(head.val == head.next.val){
               head.next = head.next.next;
            }else{
                head = head.next;
            }
        }
        return result;
    }
    @Test
    public void test() {
        ListNode list = ListUtils.buildListNode(new int[]{1, 1, 2});
        ListUtils.printListNode(list);
        ListUtils.printListNode(list);
        ListNode newList = solution.deleteDuplicates(list);
        ListUtils.printListNode(newList);
    }
}
