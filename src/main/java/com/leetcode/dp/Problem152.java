package com.leetcode.dp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 152. Maximum Product Subarray
 *
 * @author kufei.dxm
 * @date 2022/6/3
 */
public class Problem152 {
    private Problem152 solution;

    @Before
    public void setUp() {
        solution = new Problem152();
    }

    /**
     * 这题目有意思。需要考虑负负得正的情况
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        if (nums.length == 1) {
            return nums[0];
        }
        dpMin[0] = nums[0];
        dpMax[0] = nums[0];
        int max = dpMax[0];
        for (int i = 1; i < nums.length; i++) {
            int a = dpMax[i - 1] * nums[i];
            int b = dpMin[i - 1] * nums[i];
            dpMax[i] = Math.max(nums[i], Math.max(a, b));
            dpMin[i] = Math.min(nums[i], Math.min(a, b));
            if (dpMax[i] > max) {
                max = dpMax[i];
            }
        }
        return max;
    }

    @Test
    public void test() {

        int max = solution.maxProduct(new int[] {-2, 3, -4});
        Assert.assertEquals(max, 24);

        max = solution.maxProduct(new int[] {2, 3, -2, 4});
        Assert.assertEquals(max, 6);

        max = solution.maxProduct(new int[] {-2, 0, -1});
        Assert.assertEquals(max, 0);
    }
}
