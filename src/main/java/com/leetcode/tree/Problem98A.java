package com.leetcode.tree;

import com.leetcode.common.TreeNode;

/**
 * @Author dxm
 * @Date 2025/7/3
 */
public class Problem98A {

    public boolean isValidBST(TreeNode root) {
        return validateBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean validateBST(TreeNode root, int rightMin, int leftMax) {
        if (null == root) {
            return true;
        }
        if (root.val <= rightMin || root.val >= leftMax) {
            return false;
        }
        return validateBST(root.left, rightMin, root.val) && validateBST(root.right, root.val, leftMax);
    }
}
