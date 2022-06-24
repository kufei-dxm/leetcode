package com.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.leetcode.common.TreeNode;
import com.leetcode.common.TreeUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 *
 * @author kufei.dxm
 * @date 2022/6/23
 */
public class Problem103 {
    private Problem103 solution;

    @Before
    public void setUp() {
        solution = new Problem103();
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        levelOrderTraversal(result, queue, 1);
        return result;
    }

    /**
     * 一开始的左右反转写错了（写成了左右子树轮换顺序加的方式，实际是错误的），层序遍历，偶数层的顺序反转下就可以了。
     * @param result
     * @param queue
     * @param direction
     */
    private void levelOrderTraversal(List<List<Integer>> result, Queue<TreeNode> queue, int direction) {
        if (queue.isEmpty()) {
            return;
        }
        Queue<TreeNode> nextLevelQueue = new LinkedList<>();
        List<Integer> nodeValues = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            nodeValues.add(node.val);
            if (null != node.left) {
                nextLevelQueue.offer(node.left);
            }
            if (null != node.right) {
                nextLevelQueue.offer(node.right);
            }
        }
        queue = nextLevelQueue;
        if (direction % 2 == 0) {
            List<Integer> nodeValuesNew = new ArrayList<>();
            for (int i = nodeValues.size() - 1; i >= 0; i--) {
                nodeValuesNew.add(nodeValues.get(i));
            }
            nodeValues = nodeValuesNew;
            //reverse
        }
        result.add(nodeValues);

        levelOrderTraversal(result, queue, direction + 1);
    }

    @Test
    public void test() {
        int[] nums = new int[] {1, 2, 3, 4, 0, 0, 5};
        TreeNode node = TreeUtils.buildTreeByLevelOrderArray(nums);
        List<List<Integer>> result = solution.zigzagLevelOrder(node);
        result.forEach(e -> {
            e.forEach(f -> System.out.print(f + " "));
            System.out.println();
        });
    }
}
