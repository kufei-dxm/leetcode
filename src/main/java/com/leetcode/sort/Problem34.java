package com.leetcode.sort;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 *
 * @author kufei.dxm
 * @date 2022/6/2
 */
public class Problem34 {
    private Problem34 solution;

    @Before
    public void setUp() {
        solution = new Problem34();
    }

    /**
     * 实际单次遍历即可搞定。题目非得复杂化，要求nLogn的解法，肯定就是二分查找了。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int start = -1, end = -1;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num < target) {
                continue;
            }
            if (num == target) {
                if (start == -1) {
                    start = i;
                    end = i;
                } else {
                    end++;
                }
            }
        }
        return new int[] {start, end};
    }

    /**
     * 题目要求的二分查找。中间值等于target时左右遍历方式不同，需要小心处理。
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRangeV2(int[] nums, int target) {
        int[] result = new int[2];
        int left = findLeft(nums, target);
        int right = findRight(nums, target);
        result[0] = left;
        result[1] = right;
        return result;
    }

    private int findLeft(int[] nums, int target) {
        int idx = -1, l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m-1;
            }
            if (nums[m] == target) {
                idx = m;
            }
        }
        return idx;
    }

    private int findRight(int[] nums, int target) {
        int idx = -1, l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m]  <= target) {
                l = m + 1;
            } else {
                r = m-1;
            }
            if (nums[m] == target) {
                idx = m;
            }
        }
        return idx;
    }

    @Test
    public void test() {
        int[] nums = new int[] {5, 7, 7, 8, 8, 10};
        int[] result = solution.searchRangeV2(nums, 8);
        Assert.assertNotNull(result);

    }
}
