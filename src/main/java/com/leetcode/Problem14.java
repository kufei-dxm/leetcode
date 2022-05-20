package com.leetcode;

import org.junit.Before;

/**
 * @author kufei.dxm
 * @date 2022/5/20
 */
public class Problem14 {
    private Problem14 solution;

    @Before
    public void setUp() {
        solution = new Problem14();
    }

    /**
     * 简单解法。一个个比较。
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        String pre = strs[0];
        int i = 1;
        while (i < strs.length) {
            while (!strs[i].startsWith(pre)) {
                pre = pre.substring(0, pre.length() - 1);
            }
            if (pre.length() == 0) {
                return pre;
            }
            i++;
        }
        return pre;
    }

}
