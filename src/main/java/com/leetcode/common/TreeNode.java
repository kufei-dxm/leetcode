package com.leetcode.common;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;

/**
 * @author kufei.dxm
 * @date 2022/5/20
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int val) { this.val = val; }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
