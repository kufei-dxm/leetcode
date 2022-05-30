package com.leetcode.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author kufei.dxm
 * @date 2022/5/29
 */
public class Problem121 {
    private Problem121 solution;

    @Before
    public void setUp() {
        solution = new Problem121();
    }

    /**
     * 暴力解法会Time Limit Exceeded。。。单次遍历的思路半天也没想出来。。。。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > max) {
                    max = prices[j] - prices[i];
                }
            }
        }
        return max;
    }

    public int maxProfitV2(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            }
            if (prices[i] - min > max) {
                max = prices[i] - min;
            }
        }
        return max;
    }

    private int getBuyIndex(int curIdx, int sellIdx, int[] nums) {
        int buyPrice = nums[curIdx];
        int buyIndex = curIdx;
        for (int i = curIdx + 1; i < nums.length; i++) {
            if (i < sellIdx) {
                if (nums[i] < buyPrice) {
                    buyIndex = i;
                    return buyIndex;
                }
            } else {
                if (nums[i] <= nums[sellIdx]) {
                    buyIndex = i;
                    return buyIndex;
                }
            }
        }
        return buyIndex;
    }

    private int getSellIndex(int buyIdx, int[] nums) {
        if (buyIdx >= nums.length - 1) {
            return nums.length - 1;
        }
        int max = nums[buyIdx + 1];
        int sellIdx = buyIdx + 1;
        for (int i = buyIdx + 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                sellIdx = i;
            }
        }
        return sellIdx;
    }

    @Test
    public void test() {
        int[] prices = new int[] {7, 1, 5, 3, 6, 4};
        int max = solution.maxProfitV2(prices);
        Assert.assertEquals(max, 5);

        max = solution.maxProfitV2(new int[] {7, 6, 4, 3, 1});
        Assert.assertEquals(max, 0);

        max = solution.maxProfitV2(new int[] {7});
        Assert.assertEquals(max, 0);

        max = solution.maxProfitV2(new int[] {7, 3, 8, 1, 5, 2});
        Assert.assertEquals(max, 5);

        max = solution.maxProfitV2(new int[] {7, 2, 3, 8, 1, 5, 4});
        Assert.assertEquals(max, 6);

        max = solution.maxProfitV2(new int[] {1, 2, 4});
        Assert.assertEquals(max, 3);
    }
}
