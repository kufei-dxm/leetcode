package com.leetcode.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * 序列化可以通过toLevelOrderArray，再进一步转化为字符串；反序列化则把字符串转化为层序的数组，再调用buildTreeByLevelOrderArray。
 * @author kufei.dxm
 * @date 2022/5/20
 */
public class TreeUtils {
    /**
     * 层序遍历。子树为空填0.
     *
     * @param root
     * @return
     */
    public static int[] toLevelOrderArray(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> nodes = new ArrayList<>();
        int depth = getDepth(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            nodes.add(node.val);
            if (node.val != 0) {
                if (null != node.left) {
                    queue.offer(node.left);
                } else {
                    queue.offer(new TreeNode(0));
                }
                if (null != node.right) {
                    queue.offer(node.right);
                } else {
                    queue.offer(new TreeNode(0));
                }
            }
        }
        int[] result = new int[2 * depth + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = nodes.get(i);
        }
        return result;
    }

    public static int getDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        return 1 + Math.max(getDepth(root.left), getDepth(root.right));
    }

    /**
     * 从层序遍历结构的数组中构建出二叉树
     *
     * @param nums
     * @return
     */
    public static TreeNode buildTreeByLevelOrderArray(int[] nums) {
        if (nums[0] == 0) {
            return null;
        }
        TreeNode root = new TreeNode(nums[0]);
        buildTreeByLevelOrderArray(root, 0, nums);
        return root;
    }

    public static void buildTreeByLevelOrderArray(TreeNode root, int i, int[] nums) {
        TreeNode left = buildLeft(i, nums);
        if (null != left) {
            root.left = left;
            buildTreeByLevelOrderArray(left, i * 2 + 1, nums);
        }
        TreeNode right = buildRight(i, nums);
        if (null != right) {
            root.right = right;
            buildTreeByLevelOrderArray(right, i * 2 + 2, nums);
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

    @Test
    public void testBuildTree() {
        int[] nums = new int[] {5, 1, 4, 0, 0, 3, 6};
        TreeNode tree = buildTreeByLevelOrderArray(nums);
        Assert.assertNotNull(tree);

        int[] treeNums = toLevelOrderArray(tree);
        for (int num : treeNums) {
            System.out.print(num + " ");
        }
    }

}
