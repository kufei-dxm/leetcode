package com.leetcode.tree;

import com.leetcode.common.TreeNode;
import org.junit.Before;

/**
 * @Author dxm
 * @Date 2025/7/3
 */
public class Problem101A {
    private Problem101A solution;

    @Before
    public void setUp() {
        solution = new Problem101A();
    }

    public boolean isSymmetric(TreeNode root) {
        if (null == root) {
            return true;
        }
        return isEqualNode(root.left, root.right);
    }

    public boolean isEqualNode(TreeNode left, TreeNode right) {
        if (null == left && null == right) {
            return true;
        }
        if (null == left || null == right) {
            return false;
        }
        return left.val == right.val && isEqualNode(right.left, left.right) && isEqualNode(left.left, right.right);
    }
}
