package com.leetcode.tree;

import com.leetcode.common.TreeNode;
import com.leetcode.common.TreeUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author dxm
 * @Date 2025/7/3
 */
public class Problem236A {
    private Problem236A solution;

    @Before
    public void setUp() {
        solution = new Problem236A();
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pPath = new ArrayList<>();
        List<TreeNode> qPath = new ArrayList<>();
        findPath(root, p, pPath);
        findPath(root, q, qPath);
        TreeNode node = root;
        for (int i = 0; i < qPath.size() && i < pPath.size(); i++) {
            if (qPath.get(i).val == pPath.get(i).val) {
                node = pPath.get(i);
            }
        }
        System.out.println(node.val);
        return node;
    }

    private boolean findPath(TreeNode root, TreeNode node, List<TreeNode> path) {
        if (null == root) {
            return false;
        }
        //find it
        if (root.val == node.val) {
            path.add(root);
            return true;
        }
        path.add(root);
        if (findPath(root.left, node, path) || findPath(root.right, node, path)) {
            return true;
        } else {
            path.remove(root);
            return false;
        }
    }

    @Test
    public void test() {
        TreeNode root = TreeUtils.buildTreeByLevelOrderArray(new int[]{3, 5, 1, 6, 2, 9, 8, 0, 0, 7, 4});
        solution.lowestCommonAncestor(root, new TreeNode(5), new TreeNode(4));
        TreeNode root2 = TreeUtils.buildTreeByLevelOrderArray(new int[]{1, 2});
        solution.lowestCommonAncestor(root2, new TreeNode(1), new TreeNode(2));
    }

}