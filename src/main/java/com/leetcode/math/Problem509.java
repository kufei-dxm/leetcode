package com.leetcode.math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 509. Fibonacci Number
 *
 * @author kufei.dxm
 * @date 2022/6/30
 */
public class Problem509 {
    private Problem509 solution;

    @Before
    public void setUp() {
        solution = new Problem509();
    }

    /**
     * 递归式，中间会有很多重复计算。性能不高。
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 非递归版本。性能杠杠的！
     *
     * @param n
     * @return
     */
    public int fibV2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int f1 = 0;
        int f2 = 1;
        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = f1 + f2;
            f1 = f2;
            f2 = sum;
        }
        return sum;
    }

    @Test
    public void test() {
        int result = solution.fib(10);
        Assert.assertEquals(result, 55);
        result = solution.fibV2(10);
        Assert.assertEquals(result, 55);
    }
}
