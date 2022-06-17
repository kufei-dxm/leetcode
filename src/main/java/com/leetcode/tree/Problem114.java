package com.leetcode.tree;

import com.leetcode.common.TreeNode;
import com.leetcode.common.TreeUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * 114. Flatten Binary Tree to Linked List
 *
 * @author kufei.dxm
 * @date 2022/6/17
 */
public class Problem114 {
    private Problem114 solution;

    @Before
    public void setUp() {
        solution = new Problem114();
    }

    /**
     * 递归式调试了大半天。。。实在不应该啊。。左子树展平后的叶子节点衔接 右子树的逻辑比较绕。
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        //TreeNode tmp = root;
        if (root == null) {
            return;
        }
        doFlatten(root);
    }

    private TreeNode doFlatten(TreeNode root) {
        if (null == root || (root.left == null && root.right == null)) {
            return root;
        }
        TreeNode left = root.left;
        root.left = null;
        TreeNode right = root.right;
        if (left == null) {
            return doFlatten(root.right);
        }
        if (right == null) {
            root.right = left;
            return doFlatten(root.right);
        }
        root.right = left;
        TreeNode newRight = doFlatten(root.right);
        newRight.right = right;
        return doFlatten(newRight.right);
    }

    /**
     * 比较巧妙的衔接子树.只能说递归的思路太巧妙，不能以穷举的思路去考虑。
     *
     * @param root
     */
    public void flattenV2(TreeNode root) {
        if (null == root) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        flattenV2(left);
        flattenV2(right);
        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        cur.right = right;
    }

    @Test
    public void test() {
        TreeNode root = TreeUtils.buildTreeByLevelOrderArray(new int[] {1, 2, 5, 3, 4, 0, 6});
        solution.flatten(root);
        int[] arr = TreeUtils.toLevelOrderArray(root);
        for (int a : arr) {
            System.out.print(a + " ");
        }
    }
}
