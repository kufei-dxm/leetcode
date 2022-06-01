package com.leetcode.dp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Problem53的一个变种。输出子数组而非和。 -具有最优子结构，和重叠子问题， 动态规划的算法思路。
 * 虾皮一面没写出来的题目。。。。
 * @author kufei.dxm
 * @date 2022/5/31
 */
public class MaxSubArray {
    private MaxSubArray solution;

    @Before
    public void setUp() {
        solution = new MaxSubArray();
    }

    public int[] solution(int[] nums) {
        if (nums.length == 1) {
            return nums;
        }
        int[] subSet = new int[nums.length];
        subSet[0] = nums[0];
        int start = 0, end = 0;
        int maxSubSet = subSet[0];
        for (int i = 1; i < nums.length; i++) {
            if (subSet[i - 1] >= 0) {
                subSet[i] = nums[i] + subSet[i - 1];
            } else {
                subSet[i] = nums[i];
                start = i;
            }
            if (subSet[i] > maxSubSet) {
                maxSubSet = subSet[i];
                end = i;
            }
        }
        int[] result = new int[end - start + 1];
        int j = 0;
        for (int i = start; i <= end; i++) {
            result[j++] = nums[i];
        }
        return result;
    }

    @Test
    public void test() {
        int[] nums = new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] maxSubSet = solution.solution(nums);
        Assert.assertNotNull(maxSubSet);

        int[] nums2 = new int[] {5, 4, -1, 7, 8};
        maxSubSet = solution.solution(nums2);
        Assert.assertNotNull(maxSubSet);
    }
}
