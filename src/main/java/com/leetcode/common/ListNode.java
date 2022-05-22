package com.leetcode.common;

/**
 * @author kufei.dxm
 * @date 2022/5/21
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) { val = x; }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

}
