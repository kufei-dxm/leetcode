package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.*;

/**
 * @Author dxm
 * @Date 2025/7/3
 * @title 102. Binary Tree Level Order Traversal
 * @link https://leetcode.com/problems/binary-tree-level-order-traversal/
 */
public class Problem102 {
    private Problem102 solution;

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (null == root) {
            return null;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> result = new ArrayList<>();
        queue.add(root);
        levelOrderTravel(result, queue);
        return result;
    }

    private void levelOrderTravel(List<List<Integer>> res, Queue<TreeNode> queue) {
        if (queue.isEmpty()) {
            return;
        }
        List<Integer> levelResult = new ArrayList<>();
        Queue<TreeNode> newQueue = new ArrayDeque<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelResult.add(node.val);
            if(null !=node.left) {
                newQueue.add(node.left);
            }
            if(null != node.right) {
                newQueue.add(node.right);
            }
        }
        res.add(levelResult);
        levelOrderTravel(res, newQueue);
    }
}
