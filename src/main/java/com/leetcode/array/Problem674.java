package com.leetcode.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Longest Continuous Increasing Subsequence
 *
 * @author kufei.dxm
 * @date 2022/5/31
 */
public class Problem674 {
    private Problem674 solution;

    @Before
    public void setUp() {
        solution = new Problem674();
    }

    public int findLengthOfLCIS(int[] nums) {
        int maxLen = 1;
        int temp = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                temp++;
                if (temp > maxLen) {
                    maxLen = temp;
                }
            } else {
                temp = 1;
            }
        }
        return maxLen;
    }

    @Test
    public void test() {
        int[] nums = new int[] {1, 3, 5, 4, 7};
        int max = solution.findLengthOfLCIS(nums);
        Assert.assertEquals(max, 3);

        nums = new int[] {2, 2, 2, 2, 2};
        max = solution.findLengthOfLCIS(nums);
        Assert.assertEquals(max, 1);
    }
}
