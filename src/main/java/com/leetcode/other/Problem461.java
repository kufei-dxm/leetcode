package com.leetcode.other;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author kufei.dxm
 * @date 2022/6/6
 */
public class Problem461 {
    private Problem461 solution;

    @Before
    public void setUp() {
        solution = new Problem461();
    }

    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int d = 0;
        while (z != 0) {
            d += (z & 1);
            z = z >>> 1;
        }
        return d;
    }

    @Test
    public void test() {
        int dis = solution.hammingDistance(1, 4);
        Assert.assertEquals(dis, 2);

        dis = solution.hammingDistance(4, 4);
        Assert.assertEquals(dis, 0);
    }
}
