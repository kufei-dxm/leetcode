package com.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author kufei.dxm
 * @date 2022/5/20
 */
public class Problem136 {
    private Problem136 solution;

    /**
     * 解题思路：异或操作符。相同为0 ，不同为1.
     * 参考：https://www.ruanyifeng.com/blog/2021/01/_xor.html
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int n = nums[0];
        for (int i = 1; i < nums.length; i++) {
            n ^= nums[i];
        }
        return n;
    }

    @Test
    public void test() {
        solution = new Problem136();
        int[] nums = new int[] {4, 1, 2, 1, 2};
        int n = solution.singleNumber(nums);
        Assert.assertEquals(n, 4);

    }
}
