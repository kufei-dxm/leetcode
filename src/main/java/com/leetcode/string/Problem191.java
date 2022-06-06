package com.leetcode.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 191. Number of 1 Bits
 *
 * @author kufei.dxm
 * @date 2022/6/6
 */
public class Problem191 {
    private Problem191 solution;

    @Before
    public void setUp() {
        solution = new Problem191();
    }

    /**
     * 主要考察无符号的位移 ；以及逻辑运算符的操作。。
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int w = 0;
        while (n != 0) {
            w+= (n&1);
            n = n >>> 1;
        }
        return w;
    }

    @Test
    public void test() {
        int num = solution.hammingWeight(7);
        Assert.assertEquals(num, 3);
    }
}
