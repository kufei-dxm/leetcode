package com.leetcode.tree;

import com.leetcode.common.TreeNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 *
 * @author kufei.dxm
 * @date 2022/6/7
 */
public class Problem105 {
    private Problem105 solution;

    @Before
    public void setUp() {
        solution = new Problem105();
    }

    /**
     * 递归构建，思路相对清晰，代码也能AC。不过代码效率比较低。。。
     * 可以优化下去除中间数组的开销。
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return doBuild(preorder, inorder);
    }

    public TreeNode doBuild(int[] preorder, int[] inorder) {
        if (null == preorder || null == inorder ) {
            return null;
        }
        int rootVal = preorder[0];
        int rootIdx = getIndex(rootVal, inorder);
        TreeNode root = new TreeNode(rootVal);
        root.left = doBuild(subArray(preorder, 1, rootIdx), subArray(inorder, 0, rootIdx - 1));
        root.right = doBuild(subArray(preorder, rootIdx + 1, preorder.length - 1),
            subArray(inorder, rootIdx + 1, inorder.length - 1));
        return root;
    }

    private int[] subArray(int[] array, int left, int right) {
        if (left > right) {
            return null;
        }
        int[] ret = new int[right - left + 1];
        for (int i = left, j = 0; i <= right; i++, j++) {
            ret[j] = array[i];
        }
        return ret;
    }

    private int getIndex(int val, int[] treeNodes) {
        for (int i = 0; i < treeNodes.length; i++) {
            if (val == treeNodes[i]) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int[] pre = new int[] {3, 9, 20, 15, 7};
        int[] in = new int[] {9, 3, 15, 20, 7};
        TreeNode tree = solution.buildTree(pre, in);
        Assert.assertNotNull(tree);
    }
}
