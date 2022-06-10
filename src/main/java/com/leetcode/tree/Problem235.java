package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

import com.leetcode.common.TreeNode;
import com.leetcode.common.TreeUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 *
 * @author kufei.dxm
 * @date 2022/6/10
 */
public class Problem235 {
    private Problem235 solution;

    @Before
    public void setUp() {
        solution = new Problem235();
    }

    /**
     * 被236题带歪了
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pPath = new ArrayList<>();
        List<TreeNode> qPath = new ArrayList<>();
        dfs(root, p, pPath);
        dfs(root, q, qPath);
        //
        TreeNode node = null;
        for (int i = 0; i < Math.min(pPath.size(), qPath.size()); i++) {
            if (pPath.get(i).val == qPath.get(i).val) {
                node = pPath.get(i);
            }
        }
        return node;
    }

    private boolean dfs(TreeNode curNode, TreeNode targetNode, List<TreeNode> path) {
        if (curNode == null) {
            return false;
        }
        if (curNode.val == targetNode.val) {
            //find
            path.add(curNode);
            return true;
        }
        path.add(curNode);
        if (targetNode.val > curNode.val) {
            if (dfs(curNode.right, targetNode, path)) {
                return true;
            } else {
                path.remove(curNode);
            }
        } else {
            if (dfs(curNode.left, targetNode, path)) {
                return true;
            } else {
                path.remove(curNode);
            }
        }
        return false;
    }

    /**
     * 充分利用BST特性的解法
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorV2(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }

    @Test
    public void test() {
        int[] nums = new int[] {6, 1, 8, 0, 4, 7, 9, 0, 0, 3, 5};
        TreeNode root = TreeUtils.buildTreeByLevelOrderArray(nums);
        TreeNode ret = solution.lowestCommonAncestor(root, new TreeNode(5), new TreeNode(9));
        Assert.assertNotNull(ret);

        nums = new int[] {2, 1};
        root = TreeUtils.buildTreeByLevelOrderArray(nums);
        ret = solution.lowestCommonAncestor(root, new TreeNode(2), new TreeNode(1));
        Assert.assertNotNull(ret);
    }
}
