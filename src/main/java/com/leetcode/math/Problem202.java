package com.leetcode.math;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 202. Happy Number
 *
 * @author kufei.dxm
 * @date 2022/6/9
 */
public class Problem202 {
    private Problem202 solution;

    @Before
    public void setUp() {
        solution = new Problem202();
    }

    public boolean isHappy(int n) {
        int sum = 0;
        List<Integer> sumList = new ArrayList<>();
        while (true) {
            while (n != 0) {
                int tail = n % 10;
                sum += tail * tail;
                n /= 10;
            }
            if (sum == 1) {
                return true;
            } else {
                if (sumList.contains(sum)) {
                    return false;
                } else {
                    sumList.add(sum);
                    n = sum;
                    sum = 0 ;
                }
            }
        }
    }

    @Test
    public void test() {
        boolean isHappy = solution.isHappy(2);
        Assert.assertFalse(isHappy);
    }
}
