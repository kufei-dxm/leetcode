package com.leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 139. Word Break
 *
 * @author kufei.dxm
 * @date 2022/6/3
 */
public class Problem139 {
    private Problem139 solution;

    @Before
    public void setUp() {
        solution = new Problem139();
    }

    /**
     * 这个解法被例子给误导了。思路倒是和【140. Word Break II】的解法有点类似。
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        List<String> notMatch = new ArrayList<>();
        boolean can = canBreak(s, wordDict, notMatch);
        return can;
    }

    private boolean canBreak(String s, List<String> wordDict, List<String> notMatchPattern) {
        if (notMatchPattern.contains(s)) {
            return false;
        }
        for (String dict : wordDict) {
            if (s.contains(dict)) {
                String temp = s;
                temp = temp.replaceAll(dict, " ");
                if (temp.trim().equals("") || canBreak(temp, wordDict, notMatchPattern)) {
                    return true;
                }
            }
        }
        notMatchPattern.add(s);
        return false;
    }

    /**
     * 设计dp[i] = i长度的字符串，是否能被正确分解。如果能。i+k，只要k能在字典里找到，则也能被分解。
     * 问题在于没有发现最优子问题结构
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreakV2(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    @Test
    public void test() {
        List<String> wordDict = new ArrayList<>(Arrays.asList("dd", "ad", "da", "b"));
        boolean can = solution.wordBreak("ddadddbdddadd", wordDict);
        Assert.assertTrue(can);
    }
}
