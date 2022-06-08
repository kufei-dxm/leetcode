package com.leetcode.other;

import org.junit.Before;
import org.junit.Test;

/**
 * 190. Reverse Bits
 *
 * @author kufei.dxm
 * @date 2022/6/6
 */
public class Problem190 {
    private Problem190 solution;

    @Before
    public void setUp() {
        solution = new Problem190();
    }

    public int reverseBits(int n) {
        int revered = 0;
        int idx = 0;
        for (int i = 0; i < 32; i++) {
            int bit = n & 1;
            revered <<= 1;
            if (bit == 1) { revered++; }
            n = n >> 1;
            idx++;
        }
        return revered;
    }

    @Test
    public void test() {
        int result = solution.reverseBits(43261596);
        System.out.println(result);
    }
}
