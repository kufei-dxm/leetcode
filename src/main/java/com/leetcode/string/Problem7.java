package com.leetcode.string;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Reverse Integer
 *
 * @author kufei.dxm
 * @date 2022/5/23
 */
public class Problem7 {
    private Problem7 solution;

    @Before
    public void setUp() {
        solution = new Problem7();
    }

    /**
     * @param x
     * @return
     */
    public int reverse(int x) {
        List<Integer> nums = new ArrayList<>();
        while (x != 0) {
            nums.add(x % 10);
            x /= 10;
        }
        long result = 0;

        for (int i = 0; i < nums.size(); i++) {
            result = result * 10 + nums.get(i);
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            result = 0;
        }
        return (int)result;
    }

    /**
     * 来自讨论区的解法，更精简，且对溢出的判断很巧妙
     *
     * @param x
     * @return
     */
    public int reverseV2(int x) {
        int result = 0;
        while (x != 0) {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result) {
                return 0;
            }
            x /= 10;
            result = newResult;
        }
        return result;
    }

    @Test
    public void test() {
        int result = solution.reverse(-123);
        result = solution.reverse(120);
        result = solution.reverse(Integer.MAX_VALUE);
    }
}
