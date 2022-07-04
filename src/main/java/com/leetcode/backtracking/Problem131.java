package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * 131. Palindrome Partitioning（回文分割）
 *
 * @author kufei.dxm
 * @date 2022/7/4
 */
public class Problem131 {
    private Problem131 solution;

    @Before
    public void setUp() {
        solution = new Problem131();
    }

    /**
     * 回溯法解，AC了。运行时间中位数，运行内存消耗较大。
     * subString的边界值很容易搞错。
     * updated： 该解法和solution中的回溯法一模一样；优化解法中通过dp数组维护回文子字符串，减少每次递归的回文判断，运行时间提升较大。
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        iterPar(result, new ArrayList<>(), 0, s);
        return result;
    }

    public void iterPar(List<List<String>> result, List<String> curPartition, int idx, String s) {
        if (idx >= s.length()) {
            result.add(new ArrayList<>(curPartition));
            return;
        }
        for (int i = idx + 1; i <= s.length(); i++) {
            if (isParam(s.substring(idx, i))) {
                curPartition.add(s.substring(idx, i));
                iterPar(result, curPartition, i, s);
                curPartition.remove(curPartition.size() - 1);
            }
        }
    }

    private boolean isParam(String str) {
        if (str.length() == 1) {
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

    @Test
    public void test() {
        String s = "cbbbcc";
        List<List<String>> result = solution.partition(s);
        result.forEach(e -> {
            e.forEach(f -> System.out.print(f + " "));
            System.out.println();
        });
    }
}
