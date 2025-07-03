package com.leetcode.dp;

import org.junit.Before;
import org.junit.Test;

/**
 * @Author dxm
 * @Date 2025/7/3
 */
public class Problem53A {
    private Problem53A solution;

    @Before
    public void setUp() {
        solution = new Problem53A();
    }

    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxSum = 0;
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            }
            if (dp[i] > maxSum) {
                maxSum = dp[i];
            }
        }
        return maxSum;
    }

    @Test
    public void test() {

    }
}
