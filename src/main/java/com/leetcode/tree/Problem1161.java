package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

import com.leetcode.common.TreeNode;
import com.leetcode.common.TreeUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 1161. Maximum Level Sum of a Binary Tree
 *
 * @author kufei.dxm
 * @date 2022/6/23
 */
public class Problem1161 {
    private Problem1161 solution;

    @Before
    public void setUp() {
        solution = new Problem1161();
    }

    /**
     * 刚AC完Problem103，这题不要太简单。
     *
     * @param root
     * @return
     */
    public int maxLevelSum(TreeNode root) {
        int maxSum = Integer.MIN_VALUE;
        int maxLevel = 0;
        if (null == root) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        return levelOrderTraversal(maxLevel, maxSum, queue, 1);
    }

    private int levelOrderTraversal(int maxLevel, int maxSum, Queue<TreeNode> queue, int level) {
        if (queue.isEmpty()) {
            return maxLevel;
        }
        Queue<TreeNode> nextLevelQueue = new LinkedList<>();
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            sum += node.val;
            if (null != node.left) {
                nextLevelQueue.offer(node.left);
            }
            if (null != node.right) {
                nextLevelQueue.offer(node.right);
            }
        }
        queue = nextLevelQueue;
        if (sum > maxSum) {
            maxSum = sum;
            maxLevel = level;
        }
        return levelOrderTraversal(maxLevel, maxSum, queue, level + 1);
    }

    @Test
    public void test() {
        int[] nums = new int[] {1, 7, 2, 7, -8, 0, 0};
        TreeNode root = TreeUtils.buildTreeByLevelOrderArray(nums);
        int level = solution.maxLevelSum(root);
        Assert.assertEquals(level, 2);

        nums = new int[] {989, 0, 10250, 98693, -89388, 0, 0, 0, -32127};
        root = TreeUtils.buildTreeByLevelOrderArray(nums);
        level = solution.maxLevelSum(root);
        Assert.assertEquals(level, 2);
    }
}
