package com.leetcode.dp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 279. Perfect Squares
 *
 * @author kufei.dxm
 * @date 2022/6/3
 */
public class Problem279 {
    private Problem279 solution;

    @Before
    public void setUp() {
        solution = new Problem279();
    }

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0 ;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            dp[i] = leastNumAgg(dp, i);
        }
        return dp[n];
    }

    /**
     * j * j ==i的情况容易漏掉，另外dp[0] = 1 也会遗漏。
     * @param dp
     * @param i
     * @return
     */
    private int leastNumAgg(int[] dp, int i) {
        int min = dp.length;
        for (int j = 1; j * j <= i; j++) {
            if (dp[i - j * j] < min) {
                min = dp[i - j * j];
            }
        }
        return min + 1;
    }

    @Test
    public void test(){
        int num = solution.numSquares(12);
        Assert.assertEquals(num, 3);
    }
}
