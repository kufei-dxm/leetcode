package com.leetcode.common;

import java.util.List;

import org.junit.Test;

/**
 * @author kufei.dxm
 * @date 2022/5/20
 */
public class Utils {
    public static TreeNode buildTree(int[] nums) {
        if (nums[0] == 0) {
            return null;
        }
        TreeNode root = new TreeNode(nums[0]);
        buildTree(root, 0, nums);
        return root;
    }

    public static void buildTree(TreeNode root, int i, int[] nums) {
        TreeNode left = buildLeft(i, nums);
        if (null != left) {
            root.left = left;
            buildTree(left, i * 2 + 1, nums);
        }
        TreeNode right = buildRight(i, nums);
        if (null != right) {
            root.right = right;
            buildTree(right, i * 2 + 2, nums);
        }
    }

    public static TreeNode buildLeft(int i, int[] nums) {
        int leftIndex = i * 2 + 1;
        if (leftIndex < nums.length && nums[leftIndex] != 0) {
            return new TreeNode(nums[leftIndex]);
        }
        return null;
    }

    public static TreeNode buildRight(int i, int[] nums) {
        int leftIndex = i * 2 + 2;
        if (leftIndex < nums.length && nums[leftIndex] != 0) {
            return new TreeNode(nums[leftIndex]);
        }
        return null;
    }

    public static ListNode buildListNode(int[] nums) {
        ListNode head = new ListNode(nums[0]);
        ListNode temp = head;
        for (int i = 1; i < nums.length; i++) {
            ListNode node = new ListNode(nums[i]);
            head.next = node;
            head = node;
        }
        return temp;
    }

    public static void printListNode(ListNode head) {
        while (head != null) {
            if (head.next == null) {
                System.out.print(head.val);
            } else {
                System.out.print(head.val + " -> ");
            }
            head = head.next;
        }
        System.out.println();
    }

    @Test
    public void testBuildTree() {
        int[] nums = new int[] {5, 1, 4, 0, 0, 3, 6};
        TreeNode tree = buildTree(nums);
    }

    @Test
    public void testBuildList() {
        int[] list = new int[] {4, 5, 1, 9};
        ListNode listNode = buildListNode(list);

    }
}
