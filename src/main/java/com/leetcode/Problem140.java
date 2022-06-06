package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 140. Word Break II
 *
 * @author kufei.dxm
 * @date 2022/6/4
 */
public class Problem140 {
    private Problem140 solution;

    @Before
    public void setUp() {
        solution = new Problem140();
    }

    /**
     * 其实从解题思路能看出有点穷举的味道，那么回溯法是最恰当的了。以树遍历的思路来解。
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        dfs(s, wordDict, "", result);
        return result;
    }

    private void dfs(String s, List<String> wordDict, String str, List<String> result) {
        if (s.equals("")) {
            result.add(str);
            return;
        }
        for (String prefix : wordDict) {
            if (s.startsWith(prefix)) {
                String temp = str;
                if (str.equals("")) {
                    str += prefix;
                } else {
                    str += " " + prefix;
                }
                dfs(s.substring(prefix.length()), wordDict, str, result);
                str = temp; //dfs可能不成功，需要清理状态。回溯的套路。
            }
        }
    }

    @Test
    public void test() {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));
        List<String> ret = solution.wordBreak(s, wordDict);
        Assert.assertTrue(ret.size() > 0);

        s = "pineapplepenapple";
        wordDict = new ArrayList<>(Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"));
        ret = solution.wordBreak(s, wordDict);
        Assert.assertTrue(ret.size() > 0);
    }
}
