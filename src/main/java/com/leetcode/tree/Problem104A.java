package com.leetcode.tree;

import com.leetcode.common.TreeNode;
import com.leetcode.common.TreeUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author dxm
 * @Date 2025/7/3
 */
public class Problem104A {
    private Problem104A solution;

    @Before
    public void setUp() {
        solution = new Problem104A();
    }

    public int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    @Test
    public void test() {
        TreeNode root = TreeUtils.buildTreeByLevelOrderArray(new int[]{3, 9, 20, 0, 0, 15, 7});
        int maxDepth = solution.maxDepth(root);
        System.out.println(maxDepth);
    }
}
