package com.leetcode.dp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 62. Unique Paths
 *
 * @author kufei.dxm
 * @date 2022/6/1
 */
public class Problem62 {
    private Problem62 solution;

    @Before
    public void setUp() {
        solution = new Problem62();
    }

    /**
     * dp数组的边界容易搞错。
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    @Test
    public void test() {
        int cnt = solution.uniquePaths(3, 7);
        Assert.assertEquals(cnt, 28);
    }
}
