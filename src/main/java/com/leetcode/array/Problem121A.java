package com.leetcode.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author dxm
 * @Date 2025/7/2
 */
public class Problem121A {
    private Problem121A solution;

    @Before
    public void setUp() {
        solution = new Problem121A();
    }

    /**
     * 设置2个索引，标记买入和卖出的时间。就非常简单了。
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int buyIndex = 0, sellIndex = 0;
        int max =0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < prices[buyIndex]) {
                buyIndex = i;
                sellIndex = buyIndex;
            }
            if(prices[i] > prices[sellIndex]){
               sellIndex = i;
            }
            if(prices[sellIndex] - prices[buyIndex] > max){
                max = prices[sellIndex] - prices[buyIndex];
            }
        }
        return max;
    }
    @Test
    public void test(){
        int[] prices = new int[] {7, 1, 5, 3, 6, 4};
        int max = solution.maxProfit(prices);
        Assert.assertEquals(max, 5);

        max = solution.maxProfit(new int[] {7, 6, 4, 3, 1});
        Assert.assertEquals(max, 0);

        max = solution.maxProfit(new int[] {7});
        Assert.assertEquals(max, 0);

        max = solution.maxProfit(new int[] {7, 3, 8, 1, 5, 2});
        Assert.assertEquals(max, 5);

        max = solution.maxProfit(new int[] {7, 2, 3, 8, 1, 5, 4});
        Assert.assertEquals(max, 6);

        max = solution.maxProfit(new int[] {1, 2, 4});
        Assert.assertEquals(max, 3);
    }
}
