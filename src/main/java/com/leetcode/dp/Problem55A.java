package com.leetcode.dp;

/**
 * @Author dxm
 * @Date 2025/7/4
 */
public class Problem55A {
    /**
     * 能解，不过边界条件较多。子结构设计得不太好
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        //dp[i] 表示当前i位置，最多能往前多少步
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        if (dp[0] <= 0) {
            return false;
        }
        for (int i = 1; i < nums.length - 1; i++) {
            dp[i] = Math.max(dp[i - 1] - 1, nums[i]);
            if (dp[i] <= 0) {
                return false;
            }
        }
        return true;
    }
}
