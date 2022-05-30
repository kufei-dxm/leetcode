package com.leetcode.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 122. Best Time to Buy and Sell Stock II
 *
 * @author kufei.dxm
 * @date 2022/5/30
 */
public class Problem122 {
    private Problem122 solution;

    @Before
    public void setUp() {
        solution = new Problem122();
    }

    /**
     * 此题受121的启发，写起来非常快。5min搞定
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            if (prices[i] - min > 0) {
                max = max + prices[i] - min;
                min = prices[i];
            }
        }
        return max;
    }

    @Test
    public void test() {
        int[] prices = new int[] {7, 1, 5, 3, 6, 4};
        int max = solution.maxProfit(prices);
        Assert.assertEquals(max, 7);
    }
}
