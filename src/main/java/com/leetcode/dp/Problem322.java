package com.leetcode.dp;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 322. Coin Change
 *
 * @author kufei.dxm
 * @date 2022/6/1
 */
public class Problem322 {
    private Problem322 solution;

    @Before
    public void setUp() {
        solution = new Problem322();
    }

    /**
     * 设计子结构没问题，状态转移方程稍微麻烦一点。
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        //凑足i的钱需要的最少的硬币数量
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, 100000);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return (dp[amount] == 100000 ? -1 : dp[amount]);
    }

    @Test
    public void test() {
        int[] coins = new int[] {1, 2, 5};
        int changeCnt = solution.coinChange(coins, 15);
        Assert.assertEquals(changeCnt, 3);

        coins = new int[] {186, 419, 83, 408};
        changeCnt = solution.coinChange(coins, 6249);
        Assert.assertEquals(changeCnt, 20);
    }
}
