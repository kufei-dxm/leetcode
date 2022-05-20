package com.leetcode.tree;

import com.leetcode.common.TreeNode;
import org.junit.Before;

/**
 * @author kufei.dxm
 * @date 2022/5/20
 */
public class Problem104 {
    private Problem104 solution;

    @Before
    public void setUp() {
        solution = new Problem104();
    }

    /**
     * 非常简单的一个递归的思路
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
