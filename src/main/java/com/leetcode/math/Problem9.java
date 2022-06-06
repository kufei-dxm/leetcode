package com.leetcode.math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 9. Palindrome Number
 *
 * @author kufei.dxm
 * @date 2022/6/6
 */
public class Problem9 {
    private Problem9 solution;

    @Before
    public void setUp() {
        solution = new Problem9();
    }

    /**
     * 反转后判断是否相同。注意溢出的判断。讨论区的优化解法是只需要判断一半大小即可，不过提升一般。
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int rev = 0;
        while (x > rev) {
            int tail = x % 10;
            int newRes = rev * 10 + tail;
            if ((newRes - tail) / 10 != rev) {
                newRes = 0;
            }
            x /= 10;
            rev = newRes;
        }
        return x == rev || x == rev / 10;
    }

    @Test
    public void test() {
        boolean res = solution.isPalindrome(12321);
        Assert.assertTrue(res);

        res = solution.isPalindrome(1232);
        Assert.assertFalse(res);

        res = solution.isPalindrome(10);
        Assert.assertFalse(res);

        res = solution.isPalindrome(1);
        Assert.assertTrue(res);
    }
}
