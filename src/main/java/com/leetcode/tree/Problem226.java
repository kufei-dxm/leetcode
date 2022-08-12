package com.leetcode.tree;

import com.leetcode.common.TreeNode;
import org.junit.Before;

/**
 * 226. Invert Binary Tree
 */
public class Problem226 {
    private Problem226 solution;

    @Before
    public void setUp() {
        solution = new Problem226();
    }

    /**
     * 简单的递归式
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if(null == root){
            return null;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.right = invertTree(left);
        root.left = invertTree(right);
        return root ;
    }
}
