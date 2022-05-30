package com.leetcode.math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author kufei.dxm
 * @date 2022/5/23
 */
public class Problem204 {
    private Problem204 solution;

    @Before
    public void setUp() {
        solution = new Problem204();
    }

    /**
     * 常规套路居然会超时。重复算了。。。
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    private boolean isPrime(int i) {
        if (i <= 1) {
            return false;
        }
        for (int j = 2; j * j <= i; j++) {
            if (i % j == 0) {
                return false;
            }
        }
        return true;
    }

    public int countPrimesV2(int n) {
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                cnt++;
            }
        }
        return cnt;
    }

    @Test
    public void test() {
        int cnt = solution.countPrimesV2(10);
        Assert.assertEquals(4, cnt);
    }
}
