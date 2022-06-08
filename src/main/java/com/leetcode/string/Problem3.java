package com.leetcode.string;

import org.junit.Before;

/**
 * 3. Longest Substring Without Repeating Characters
 *
 * @author kufei.dxm
 * @date 2022/6/7
 */
public class Problem3 {
    private Problem3 solution;

    @Before
    public void setUp() {
        solution = new Problem3();
    }

    public int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        //dp[i] = i位置结尾的最长不重复字符串
        int[] dp = new int[s.length()];
        dp[0] = 1;
        int maxLen = dp[0];
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            int start = i - dp[i - 1];
            for (int j = i - 1; j >= start; j--) {
                char cc = s.charAt(j);
                if (cc == c) {
                    dp[i] = i - j;
                    break;
                }
            }
            if (dp[i] == 0) {
                dp[i] = dp[i - 1] + 1;
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
            }
        }
        return maxLen;
    }
}
