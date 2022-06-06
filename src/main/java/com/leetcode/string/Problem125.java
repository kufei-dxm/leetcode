package com.leetcode.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 125. Valid Palindrome
 *
 * @author kufei.dxm
 * @date 2022/6/4
 */
public class Problem125 {
    private Problem125 solution;

    @Before
    public void setUp() {
        solution = new Problem125();
    }

    public boolean isPalindrome(String s) {
        s = format(s);
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    private String format(String source) {
        source = source.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            Character c = source.charAt(i);
            if ((c >= 'a' && c <= 'z') || c >= '0' && c <= '9') {
                sb.append(source.charAt(i));
            }
        }
        return sb.toString();
    }

    @Test
    public void test() {
        String str = solution.format("A man, a plan, a canal: Panama");
        Assert.assertEquals(str, "amanaplanacanalpanama");
        boolean isP = solution.isPalindrome(str);
        Assert.assertTrue(isP);

        isP = solution.isPalindrome("race a car");
        Assert.assertFalse(isP);

        isP = solution.isPalindrome("  ");
        Assert.assertTrue(isP);

        isP = solution.isPalindrome("0P");
        Assert.assertFalse(isP);
    }
}
