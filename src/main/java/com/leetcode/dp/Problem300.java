package com.leetcode.dp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 300. Longest Increasing Subsequence
 *
 * @author kufei.dxm
 * @date 2022/6/1
 */
public class Problem300 {
    private Problem300 solution;

    @Before
    public void setUp() {
        solution = new Problem300();
    }

    /**
     * 改解法比较直观，不过时间复杂度是n的平方。
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length]; //以i位置结尾的最大递增序列的长度
        dp[0] = 1;
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = findMaxSub(dp, nums, i);
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    /**
     * 如果i之前的有小于它的最大长度，则取它，否则返回1；
     *
     * @param dp
     * @param nums
     * @param i
     * @return
     */
    private int findMaxSub(int[] dp, int[] nums, int i) {
        int max = 0;
        for (int j = 0; j < i; j++) {
            if (nums[i] > nums[j] && dp[j] > max) {
                max = dp[j];
            }
        }
        return max + 1;
    }

    /**
     * 通过设计不同的子结构，巧妙的达到n logn的时间复杂度
     * 算法解释看这里：https://blog.csdn.net/u012505432/article/details/52228945
     * tail[i] = 长度为i+1的最长子串的最小值。
     *
     * @param nums
     * @return
     */
    public int lengthOfLISV2(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int i = binaryFindTailIndex(tails, x, size);
            tails[i] = x;
            if (i == size) { ++size;}
        }
        return size;
    }

    private int binaryFindTailIndex(int[] tails, int currentNum, int tailSize) {
        int i = 0;
        while (i != tailSize) {
            int mid = (i + tailSize) / 2;
            if (tails[mid] < currentNum) {
                i = mid + 1;
            } else {
                tailSize = mid;
            }
        }
        return i;
    }

    @Test
    public void test() {
        int[] nums = new int[] {0, 1, 0, 3, 2, 3};
        int max = solution.lengthOfLIS(nums);
        Assert.assertEquals(max, 4);

        max = solution.lengthOfLISV2(nums);
        Assert.assertEquals(max, 4);

        nums = new int[] {10, 9, 2, 5, 3, 7, 101, 18};
        max = solution.lengthOfLIS(nums);
        Assert.assertEquals(max, 4);

        nums = new int[] {7, 7, 7, 7, 7};
        max = solution.lengthOfLIS(nums);
        Assert.assertEquals(max, 1);

    }
}
