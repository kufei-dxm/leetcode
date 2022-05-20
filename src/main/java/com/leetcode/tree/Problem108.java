package com.leetcode.tree;

import com.leetcode.common.TreeNode;
import org.junit.Before;
import org.junit.Test;

/**
 * @author kufei.dxm
 * @date 2022/5/20
 */
public class Problem108 {
    private Problem108 solution;

    @Before
    public void setUp() {
        solution = new Problem108();
    }

    /**
     * 递归式调试了好一会。。。
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildHbBTS(nums.length / 2, 0, nums.length - 1, nums);
    }

    public TreeNode buildHbBTS(int rootIndex, int leftIndex, int rightIndex, int[] nums) {
        if (leftIndex > rightIndex) {
            return null;
        }
        TreeNode node = new TreeNode(nums[rootIndex]);
        node.left = buildHbBTS((rootIndex + leftIndex - 1) / 2, leftIndex, rootIndex - 1, nums);
        node.right = buildHbBTS((rootIndex + rightIndex + 1) / 2, rootIndex + 1, rightIndex, nums);
        return node;
    }

    @Test
    public void test() {
        int[] nums = new int[] {-10, -3, 0, 5, 9};
        TreeNode root = solution.sortedArrayToBST(nums);
    }
}
