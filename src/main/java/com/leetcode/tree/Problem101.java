package com.leetcode.tree;

import com.leetcode.common.TreeNode;
import com.leetcode.common.TreeUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author kufei.dxm
 * @date 2022/5/26
 */
public class Problem101 {
    private Problem101 solution;

    @Before
    public void setUp() {
        solution = new Problem101();
    }

    public boolean isSymmetric(TreeNode root) {
        if (null == root) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode left, TreeNode right) {
        if ((null == left && null != right) || (null == right && null != left)) {
            return false;
        }
        if (null == left && null == right) {
            return true;
        }
        return (left.val == right.val) && isSymmetric(left.right, right.left) && isSymmetric(left.left, right.right);
    }

    @Test
    public void test() {
        int[] tree = new int[] {1, 2, 2, 3, 4, 4, 3};
        boolean result = solution.isSymmetric(TreeUtils.buildTreeByLevelOrderArray(tree));
        Assert.assertTrue(result);
        int[] tree2 = new int[] {1, 2, 2, 0, 3, 0, 3};
        boolean result2 = solution.isSymmetric(TreeUtils.buildTreeByLevelOrderArray(tree2));
        Assert.assertFalse(result2);
    }
}
