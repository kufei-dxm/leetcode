package com.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 91. Decode Ways
 *
 * @author kufei.dxm
 * @date 2022/6/17
 */
public class Problem91 {
    private Problem91 solution;

    @Before
    public void setUp() {
        solution = new Problem91();
    }

    /**
     * 常规的递归居然会超时。。。看来得用dp解
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        List<String> result = new ArrayList<>();
        decode("", result, 0, s);
        return result.size();
    }

    private void decode(String curStr, List<String> result, int idx, String oriStr) {
        if (idx >= oriStr.length()) {
            result.add(curStr);
            return;
        }
        char c = oriStr.charAt(idx);
        if (c == '0') {
            return;
        }
        decode(curStr + " " + c + " ", result, idx + 1, oriStr);
        if (idx + 1 < oriStr.length()) {
            String temp = oriStr.substring(idx, idx + 2);
            int num = Integer.parseInt(temp);
            if (num >= 10 && num <= 26) {
                decode(curStr + " " + temp + " ", result, idx + 2, oriStr);
            }
        }
    }

    /**
     * 这里的状态转移方程，一开始写错了。和爬楼梯是有点类似的。
     * 中间0值的处理，代码很冗余。
     *
     * @param s
     * @return
     */
    public int numDecodingsV2(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[s.length()];
        dp[0] = 1;
        int pre = Integer.parseInt(s.substring(0, 1));
        for (int i = 1; i < s.length(); i++) {
            String cur = s.substring(i, i + 1);
            int curInt = Integer.parseInt(cur);
            if (curInt == 0) {
                if (pre == 0 || pre > 2) {
                    return 0;
                } else {
                    dp[i] = i >= 2 ? dp[i - 2] : 1;
                }
            } else {
                if (pre != 0) {
                    int tmpInt = pre * 10 + curInt;
                    if (tmpInt <= 26) {
                        dp[i] = i >= 2 ? dp[i - 1] + dp[i - 2] : dp[i - 1] + 1;
                    } else {
                        dp[i] = dp[i - 1];
                    }
                } else {
                    dp[i] = dp[i - 1];
                }
            }
            pre = curInt;
        }
        return dp[s.length() - 1];
    }

    /**
     * 讨论区的这个解法牛X。。。状态转移公式一般也不容易想到
     * @param s
     * @return
     */
    public int numDecodingsV3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            int first = Integer.parseInt(s.substring(i - 1, i));
            int second = Integer.parseInt(s.substring(i - 2, i));
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i - 1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    @Test
    public void test() {
        int num = solution.numDecodings("226");
        Assert.assertEquals(3, num);

        num = solution.numDecodings("12");
        Assert.assertEquals(2, num);

        num = solution.numDecodingsV2("1111001");
        System.out.println(num);

        num = solution.numDecodingsV3("1111001");
        System.out.println(num);
    }
}
