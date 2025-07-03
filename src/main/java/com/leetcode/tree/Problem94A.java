package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author dxm
 * @Date 2025/7/3
 */
public class Problem94A {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        travelTree(root,result);
        return result;
    }

    private void travelTree(TreeNode root, List<Integer> res) {
        if(null == root){
            return;
        }
        travelTree(root.left, res);
        res.add(root.val);
        travelTree(root.right, res);
    }

}
