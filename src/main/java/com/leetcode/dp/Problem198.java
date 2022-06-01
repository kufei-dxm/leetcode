package com.leetcode.dp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 198. House Robber
 *
 * @author kufei.dxm
 * @date 2022/6/1
 */
public class Problem198 {
    private Problem198 solution;

    @Before
    public void setUp() {
        solution = new Problem198();
    }

    /**
     * 关键还是在设计子结构。dp[i]= 抢到第i家的最大收益。对于i，要么抢，要么不抢。状态转移方程也比较关键。
     * @param nums
     * @return
     */
    public int rob(int[] nums) {

        int[] dpRob = new int[nums.length];
        dpRob[0] = nums[0];
        if(nums.length==1){
            return nums[0];
        }
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            if (i < 2) {
                dpRob[i] = Math.max(nums[i], dpRob[i - 1]);
            } else {
                dpRob[i] = Math.max(nums[i] + dpRob[i - 2], dpRob[i - 1]);
            }
            if (max < dpRob[i]) {
                max = dpRob[i];
            }
        }
        return max;
    }

    @Test
    public void test() {
        int[] money = new int[] {2, 7, 9, 3, 1};
        int total = solution.rob(money);
        Assert.assertEquals(total, 12);

        total = solution.rob(new int[] {1, 2, 3, 1});
        Assert.assertEquals(total, 4);
    }
}
