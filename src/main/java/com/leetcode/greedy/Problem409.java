package com.leetcode.greedy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author dxm
 * @Date 2022/10/16
 */
public class Problem409 {
    private Problem409 solution;

    @Before
    public void setUp() {
        solution = new Problem409();
    }

    /**
     * 思路比较简单，判断每个字符的奇偶个数即可。
     * 大小写字符的ASCII编码需要注意下。
     *
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        int[] charCnt = new int[52];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                charCnt[Character.toLowerCase(c) + 26 - 'a']++;
            } else {
                charCnt[c - 'a']++;
            }
        }
        int lpm = 0;
        int left = s.length();
        for (int i = 0; i < charCnt.length; i++) {
            if (charCnt[i] == 0) {
                continue;
            }
            if (charCnt[i] % 2 == 0) {
                lpm += charCnt[i];
                left -= charCnt[i];
            } else {
                lpm += charCnt[i] - 1;
                left -= (charCnt[i] - 1);
            }
        }
        if (left > 0) {
            lpm += 1;
        }
        return lpm;
    }

    /**
     * 优化解法。代码简洁很多。28行-> 11行
     * @param s
     * @return
     */
    public int longestPalindromeV2(String s) {
        int[] charCnt = new int[128];
        for (int i = 0; i < s.length(); i++) {
            charCnt[s.charAt(i)]++;
        }
        int lpm = 0;
        for (int i = 0; i < charCnt.length; i++) {
            lpm += charCnt[i] / 2 * 2;
        }
        if (lpm < s.length()) {
            lpm++;
        }
        return lpm ;
    }

    @Test
    public void test() {
        String s = "Abccccdd";
        int ret = solution.longestPalindrome(s);
        Assert.assertEquals(ret, 7);
        s = "a";
        ret = solution.longestPalindrome(s);
        Assert.assertEquals(ret, 1);
    }

}
