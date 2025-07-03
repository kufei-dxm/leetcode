package com.leetcode.tree;

import com.leetcode.common.TreeNode;
import org.junit.Before;

/**
 * @Author dxm
 * @Date 2025/7/3
 */
public class Problem111 {
    private Problem111 solution;

    @Before
    public void setUp() {
        solution = new Problem111();
    }

    public int minDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        if (root.right != null && root.left != null) {
            return 1 + Math.min(minDepth(root.right), minDepth(root.left));
        } else {
            if (root.right == null) {
                return 1 + minDepth(root.left);
            }
            return 1 + minDepth(root.right);
        }
    }
}
