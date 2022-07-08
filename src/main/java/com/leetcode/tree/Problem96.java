package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 96. Unique Binary Search Trees
 *
 * @author kufei.dxm
 * @date 2022/7/8
 */
public class Problem96 {
    private Problem96 solution;

    @Before
    public void setUp() {
        solution = new Problem96();
    }

    private int cnt;

    /**
     * 折腾1小时没写出来。。。蛋疼。。。
     * 看着是个树的题目，实际上是个DP。。。。坑了。。。
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        List<Integer> nodes = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nodes.add(i);
        }
        for (int node : nodes) {
            List<Integer> left = getLeft(node, nodes);
            List<Integer> right = getRight(node, nodes);
            buildSubTree(left, right);
        }
        return cnt;
    }

    public void buildSubTree(List<Integer> leftNodes, List<Integer> rightNodes) {
        if ((leftNodes.isEmpty() && rightNodes.isEmpty()) || (leftNodes.size() == 1 && rightNodes.size() == 1)) {
            cnt++;
            return;
        }
        List<Integer> nodes = new ArrayList<>();
        nodes.addAll(leftNodes);
        nodes.addAll(rightNodes);
        for (int node : nodes) {
            List<Integer> left = getLeft(node, nodes);
            List<Integer> right = getRight(node, nodes);
            buildSubTree(left, right);
        }
    }

    private List<Integer> getLeft(int node, List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (int i : list) {
            if (i < node) {
                result.add(i);
            }
        }
        return result;
    }

    private List<Integer> getRight(int node, List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (int i : list) {
            if (i > node) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 参考讨论区的dp解法。
     *
     * @return
     */
    public int numTreesV2(int n) {
        /**
         * 长度为n的bts的数量
         */
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[i - j] * dp[j - 1];
            }
        }
        return dp[n];
    }

    @Test
    public void test() {
        int num = solution.numTreesV2(4);
        Assert.assertEquals(14, num);
    }
}
