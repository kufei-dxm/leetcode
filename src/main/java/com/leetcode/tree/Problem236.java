package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

import com.leetcode.common.TreeNode;

/**
 * Lowest Common Ancestor of a Binary Tree
 * 这道题在05-25蚂蚁转岗面试时考到，差点做不出来，递归式和路径存储一时间没办法在一个方法里实现出来。
 *
 * @author kufei.dxm
 * @date 2022/5/25
 */
public class Problem236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathP = new ArrayList<>();
        List<TreeNode> pathQ = new ArrayList<>();
        findPath(pathP, root, p);
        findPath(pathQ, root, q);
        TreeNode sameParent = root;
        for (int i = 0; i < Math.min(pathP.size(), pathQ.size()); i++) {
            if (pathP.get(i).val == pathQ.get(i).val) {
                sameParent = pathP.get(i);
            }
        }
        return sameParent;
    }

    public boolean findPath(List<TreeNode> path, TreeNode root, TreeNode node) {
        if (null == root) {
            return false;
        }
        if (root.val == node.val) {
            path.add(root);
            return true;
        }
        path.add(root);
        if (findPath(path, root.left, node) || findPath(path, root.right, node)) {
            return true;
        } else {
            path.remove(root);
            return false;
        }
    }
}
