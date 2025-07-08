package com.leetcode.linkedlist;

import com.leetcode.common.ListNode;
import com.leetcode.common.ListUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
    public static ListNode temp = null;
    public boolean isPalindrome2(ListNode head){
        temp = head;
        return palindrome(head);
    }
    public boolean palindrome(ListNode head){
        if(head==null){
            return true;
        }
        if(!palindrome(head.next)){
            return false;
        }
        if(head.val != temp.val){
            return false;
        }
        temp = temp.next;
        return true;
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
    public ListNode revereList(ListNode list){
        if(null == list){
            return null;
        }
        ListNode newTail = new ListNode(list.val);
        newTail.next = null ;
        while (list.next !=null){
            list = list.next;
            ListNode tempNode = new ListNode(list.val);
            tempNode.next = newTail;
            newTail = tempNode;
        }
        return newTail;
    }
    public ListNode reverseList(ListNode head){
        ListNode prev=null;
        ListNode curr=head;
        while(curr!=null){
            ListNode nextTemp= curr.next;
            curr.next=prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
    @Test
    public void test() {
        ListNode head = ListUtils.buildListNode(new int[] {1,2,2,1});
        boolean ret = solution.isPalindrome2(head);
        Assert.assertTrue(ret);
        head =  ListUtils.buildListNode(new int[]{1,2,3,4});
        ListUtils.printListNode(head);
        ListNode newList = solution.reverseList(head);
        ListUtils.printListNode(newList);
    }
}