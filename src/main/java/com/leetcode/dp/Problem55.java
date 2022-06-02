package com.leetcode.dp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 55. Jump Game
 *
 * @author kufei.dxm
 * @date 2022/6/1
 */
public class Problem55 {
    private Problem55 solution;

    @Before
    public void setUp() {
        solution = new Problem55();
    }

    /**
     * dp[i] = 当前位置能跳到的最大位置。需要判断前置节点能不能到，已经当前能不能跳出。代码稍微有点啰嗦。
     * 边界条件试错多次。。。
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        if (dp[0] == 0 && nums.length > 1) {
            return false;
        }
        for (int i = 1; i < nums.length - 1; i++) {
            dp[i] = Math.max(dp[i - 1], i + nums[i]);
            if (dp[i] <= i) {
                return false;
            }
        }
        return true;
    }

    /**
     * 思路太牛X了。反向遍历，记录能跳到last的最小索引。如果最小索引到0，说明能跳到最后。
     *
     * @param nums
     * @return
     */
    public boolean canJumpV2(int nums[]) {
        int last = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= last) {
                last = i;
            }
        }
        return last <= 0;
    }

    @Test
    public void test() {
        int[] nums = new int[] {3, 2, 1, 0, 4};
        boolean ret = solution.canJump(nums);
        Assert.assertFalse(ret);

        nums = new int[] {2, 3, 1, 1, 4};
        ret = solution.canJump(nums);
        Assert.assertTrue(ret);
    }
}
