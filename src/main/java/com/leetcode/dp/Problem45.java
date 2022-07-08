package com.leetcode.dp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 45. Jump Game II
 *
 * @author kufei.dxm
 * @date 2022/7/5
 */
public class Problem45 {
    private Problem45 solution;

    @Before
    public void setUp() {
        solution = new Problem45();
    }

    /**
     * 解题耗时：25min
     * ac了，不过性能较差。。。只比5%的人快。。
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = nums.length;
        }
        dp[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] > 0 && nums[j] + j >= i && dp[j] + 1 < dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        return dp[nums.length - 1];
    }

    /**
     * 耗时：15min
     * 性能：提升20倍，到中位数
     * 参考55题 jump game 的反向遍历思路看看是否能优化.
     * 反向遍历 + 贪心思路
     * 讨论区里O（n）的贪心思路，理解起来不是太直观
     *
     * @param nums
     * @return
     */
    public int jumpV2(int[] nums) {
        int minIdx = nums.length;
        int last = nums.length - 1;
        if (minIdx == 1) {
            return 0;
        }
        int step = 0;
        while (minIdx > 0) {
            for (int i = last - 1; i >= 0; i--) {
                if (nums[i] + i >= last) {
                    minIdx = i;
                }
            }
            last = minIdx;
            step++;
        }
        return step;
    }

    @Test
    public void test() {
        int[] nums = new int[] {2, 3, 1, 1, 4};
        int minSteps = solution.jump(nums);
        Assert.assertEquals(minSteps, 2);

        minSteps = solution.jumpV2(nums);
        Assert.assertEquals(minSteps, 2);
    }
}
