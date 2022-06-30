package com.leetcode.dp;

/**
 * 70. Climbing Stairs
 *
 * @author kufei.dxm
 * @date 2022/6/30
 */
public class Problem70 {
    /**
     * 和斐波那契数列解法一样
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int f0 = 1;
        int f1 = 1;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = f0 + f1;
            f0 = f1;
            f1 = sum;
        }
        return sum;
    }
}
