package com.leetcode.dp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author kufei.dxm
 * @date 2022/5/19
 */
public class Problem53 {
    private Problem53 solution;

    @Before
    public void setUp() {
        solution = new Problem53();
    }

    /**
     * 解题思路：维护一个数组set[i] =j,表示i位置结尾的最大子数组的和。
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] subSet = new int[nums.length];
        subSet[0] = nums[0];
        int maxSubSet = subSet[0];
        for (int i = 1; i < nums.length; i++) {
            if (subSet[i - 1] >= 0) {
                subSet[i] = nums[i] + subSet[i - 1];
            } else {
                subSet[i] = nums[i];
            }
            if (subSet[i] > maxSubSet) {
                maxSubSet = subSet[i];
            }
        }
        return maxSubSet;
    }

    @Test
    public void test() {
        int[] nums = new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSubSet = solution.maxSubArray(nums);
        Assert.assertEquals(maxSubSet, 6);

        int[] nums2 = new int[] {5, 4, -1, 7, 8};
        maxSubSet = solution.maxSubArray(nums2);
        Assert.assertEquals(maxSubSet, 23);
    }
}
