package com.leetcode.math;

import org.junit.Before;

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

    public int countPrimes(int n) {
        if (n < 2) {
            return 0;
        }
        return -1;
    }
}
