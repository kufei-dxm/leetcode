package com.leetcode.linkedlist;

import com.leetcode.common.ListNode;
import com.leetcode.common.ListUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author dxm
 * @Date 2025/7/1
 * @title 82. Remove Duplicates from Sorted List II
 * @link https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/description/
 */
public class Problem82 {
    private Problem82 solution;

    @Before
    public void setUp() {
        solution = new Problem82();
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode res = head;
        ListNode pre = null;
        boolean duplicateNum = false;
        while (head != null && head.next != null) {
            if (head.val == head.next.val) {
                //skip the node
                head.next = head.next.next;
                duplicateNum = true;
            } else {
                if (duplicateNum) {
                    if (null == pre) {
                        res = head.next;
                        pre = head.next;
                    } else {
                        pre.next = head.next;
                        head = pre;
                    }
                    duplicateNum = false;
                } else {
                    //move on
                    pre = head;
                    head = head.next;
                }
            }
        }
        if (duplicateNum) {
            if (null == pre) {
                res = null;
            } else {
                pre.next = head.next;
                head = pre;
            }
        }
        return res;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        List<Integer> list = new ArrayList();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        boolean skip = false;
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < list.size() -1; i++) {
            if (list.get(i).intValue() == list.get(i + 1).intValue()) {
                skip = true;
            } else {
                if (!skip ) {
                    newList.add(list.get(i));
                }
                if (skip) {
                    skip = false;
                }
            }
        }
        if(!skip){
            newList.add(list.get(list.size()-1));
        }
        if(newList.isEmpty()){
            return null;
        }
        ListNode newHead = new ListNode(newList.get(0));
        ListNode temp = newHead;
        for (int i = 1; i < newList.size(); i++) {
            ListNode node = new ListNode(newList.get(i));
            newHead.next = node;
            newHead = node;
        }
        return temp;
    }

    @Test
    public void test() {
        ListNode head = ListUtils.buildListNode(new int[]{1,1,1,2,2,3,4,5,5});
        ListUtils.printListNode(head);
        ListNode res = solution.deleteDuplicates2(head);
        ListUtils.printListNode(res);
    }
}