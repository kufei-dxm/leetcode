package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

import com.leetcode.common.TreeNode;
import com.leetcode.common.TreeUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * 98. Validate Binary Search Tree
 * @author kufei.dxm
 * @date 2022/5/20
 */
public class Problem98 {
    /**
     * 暴力解法。。。太low了
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (null == root) {
            return true;
        }
        List<Integer> leftNodes = getNodes(root.left);
        boolean inValid = leftNodes.stream().anyMatch(e -> e >= root.val);
        if (inValid) {
            return false;
        }
        List<Integer> rightNodes = getNodes(root.right);
        inValid = rightNodes.stream().anyMatch(e -> e <= root.val);
        if (inValid) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    public List<Integer> getNodes(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        result.add(root.val);
        result.addAll(getNodes(root.left));
        result.addAll(getNodes(root.right));
        return result;
    }

    /**
     * 效果立竿见影： https://leetcode.com/submissions/detail/703373948/
     * 不过代码太啰嗦了。。。
     *
     * @param root
     * @return
     */
    public boolean isValidBSTV2(TreeNode root) {
        if (null == root) {
            return true;
        }
        Integer max = getMaxNode(root.left);
        if (null != max && root.val <= max) {
            return false;
        }
        Integer min = getMinNode(root.right);
        if (null != min && root.val >= min) {
            return false;
        }
        return isValidBSTV2(root.left) && isValidBSTV2(root.right);
    }

    public Integer getMaxNode(TreeNode root) {
        if (null == root) {
            return null;
        }
        Integer max = getMaxNode(root.right);
        if (null == max) {
            return root.val;
        } else {
            return Math.max(root.val, max);
        }
    }

    public Integer getMinNode(TreeNode root) {
        if (null == root) {
            return null;
        }
        Integer min = getMinNode(root.left);
        if (null == min) {
            return root.val;
        } else {
            return Math.min(root.val, min);
        }
    }

    /**
     * 树大多用递归解。需要找到一个简单的递归公式
     *
     * @param root
     * @return
     */
    public boolean isValidBSTV3(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (null == root) {
            return true;
        }
        if (root.val <= minVal || root.val >= maxVal) {
            return false;
        }
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }

    @Test
    public void test() {
        int[] nums = new int[] {5, 1, 4, 0, 0, 3, 6};
        TreeNode root = TreeUtils.buildTreeByLevelOrderArray(nums);
        boolean isValid = isValidBST(root);
        Assert.assertFalse(isValid);
        Assert.assertFalse(isValidBSTV2(root));

        nums = new int[] {2, 1, 3};
        root = TreeUtils.buildTreeByLevelOrderArray(nums);
        isValid = isValidBST(root);
        Assert.assertTrue(isValid);
        Assert.assertTrue(isValidBSTV2(root));

        nums = new int[] {Integer.MAX_VALUE};
        root = TreeUtils.buildTreeByLevelOrderArray(nums);
        isValid = isValidBST(root);
        Assert.assertTrue(isValid);
        Assert.assertTrue(isValidBSTV2(root));
    }

}
