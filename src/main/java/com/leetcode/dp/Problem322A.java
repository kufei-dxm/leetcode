package com.leetcode.dp;

/**
 * @Author dxm
 * @Date 2025/7/4
 */
public class Problem322A {
    public int coinChange(int[] coins, int amount) {
        //凑足i的钱，需要的最少的数量
        int[] dp = new int[amount + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 100000;
        }
        dp[0] = 0;//
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return (dp[amount] == 100000 ? -1 : dp[amount]);
    }
}
