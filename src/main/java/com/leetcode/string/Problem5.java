package com.leetcode.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 5. Longest Palindromic Substring
 *
 * @author kufei.dxm
 * @date 2022/6/7
 */
public class Problem5 {
    private Problem5 solution;

    @Before
    public void setUp() {
        solution = new Problem5();
    }

    /**
     * 代码太长了。。。可是，居然AC了。。。！！dp的思路比较常规
     *
     * 讨论区里以中心点展开的思路不太容易想得到。
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        //dp[i] = 以i位置结尾的最长回文字符串长度
        int[] dp = new int[s.length()];
        dp[0] = 1;
        int maxLen = dp[0], idx = 0;
        for (int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            int preLen = dp[i - 1];
            if (i - preLen - 1 >= 0 && s.charAt(i - preLen - 1) == ch) {
                dp[i] = dp[i - 1] + 2;
            } else {
                for (int j = i - preLen; j < i; j++) {
                    if (isPalindrome(s.substring(j, i + 1))) {
                        dp[i] = i - j + 1;
                        break;
                    }
                }
                if (dp[i] == 0) {
                    dp[i] = 1;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                idx = i;
            }
        }
        return s.substring(idx - dp[idx] + 1, idx + 1);
    }

    private boolean isPalindrome(String str) {
        if (null == str || str.length() <= 1) {
            return true;
        }
        int s = 0, e = str.length() - 1;
        while (s <= e) {
            if (str.charAt(s) != str.charAt(e)) {
                return false;
            }
            s++;
            e--;
        }
        return true;
    }

    private int lo, maxLen;

    /**
     * 以中心点展开的思路。代码精简非常多。
     * @param s
     * @return
     */
    public String longestPalindromeV2(String s) {
        if (null == s || s.length() <= 1) {
            return s;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            expandAroundCenter(s, i, i);
            expandAroundCenter(s, i, i + 1);
        }
        return s.substring(lo, lo + maxLen);
    }

    private void expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        if (maxLen < right - left - 1) {
            lo = left + 1;
            maxLen = right - left - 1;
        }
    }

    @Test
    public void test() {
        String str = solution.longestPalindrome("babad");
        Assert.assertEquals(str, "bab");
    }
}
