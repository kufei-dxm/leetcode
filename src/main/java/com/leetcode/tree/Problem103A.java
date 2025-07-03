package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @Author dxm
 * @Date 2025/7/3
 */
public class Problem103A {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        queue.add(root);
        zTravel(queue, res, 1);
        return res;
    }

    private void zTravel(Queue<TreeNode> queue, List<List<Integer>> result, int level) {
        if (queue.isEmpty()) {
            return;
        }
        List<Integer> levelResult = new ArrayList<>();
        Queue<TreeNode> newQueue = new ArrayDeque<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            levelResult.add(node.val);
            if (null != node.left) {
                newQueue.add(node.left);
            }
            if (null != node.right) {
                newQueue.add(node.right);
            }
        }
        if (level % 2 == 0) {
            List<Integer> levelResultNew = new ArrayList<>();
            for (int i = levelResult.size() - 1; i >= 0; i--) {
                levelResultNew.add(levelResult.get(i));
            }
            levelResult = levelResultNew;
        }
        result.add(levelResult);
        zTravel(newQueue, result, level + 1);
    }
}
