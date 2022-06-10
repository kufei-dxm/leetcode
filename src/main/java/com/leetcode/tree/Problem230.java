package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

import com.leetcode.common.TreeNode;
import com.leetcode.common.TreeUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 230. Kth Smallest Element in a BST
 *
 * @author kufei.dxm
 * @date 2022/5/26
 */
public class Problem230 {
    private Problem230 solution;

    @Before
    public void setUp() {
        solution = new Problem230();
    }

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> result = new ArrayList<>();
        inOrderTraversal(result, root);
        return result.get(k - 1);
    }

    private void inOrderTraversal(List<Integer> result, TreeNode node) {
        if (null == node) {
            return;
        }
        inOrderTraversal(result, node.left);
        result.add(node.val);
        inOrderTraversal(result, node.right);
    }

    @Test
    public void test() {
        int[] treeNodes = new int[] {5, 3, 6, 2, 4, 0, 0, 1};
        TreeNode tree = TreeUtils.buildTreeByLevelOrderArray(treeNodes);
        int result = kthSmallest(tree, 3);
        Assert.assertEquals(result, 3);
    }
}
