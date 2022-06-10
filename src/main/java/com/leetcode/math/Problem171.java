package com.leetcode.math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 171. Excel Sheet Column Number
 *
 * @author kufei.dxm
 * @date 2022/6/9
 */
public class Problem171 {
    private Problem171 solution;

    @Before
    public void setUp() {
        solution = new Problem171();
    }

    public int titleToNumber(String columnTitle) {
        int sum = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            int tail = columnTitle.charAt(i) - 'A' + 1;
            sum = sum * 26 + tail;
        }
        return sum;
    }

    @Test
    public void test() {
        int sum = solution.titleToNumber("ZY");
        Assert.assertEquals(701, sum);

        sum = solution.titleToNumber("AB");
        Assert.assertEquals(28, sum);

        sum = solution.titleToNumber("A");
        Assert.assertEquals(1, sum);
    }
}
