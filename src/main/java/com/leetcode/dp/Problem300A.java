package com.leetcode.dp;

/**
 * @Author dxm
 * @Date 2025/7/4
 */
public class Problem300A {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLen = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
            }
        }
        return maxLen;
    }
}
