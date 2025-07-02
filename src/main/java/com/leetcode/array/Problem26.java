package com.leetcode.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author kufei.dxm
 * @date 2022/5/29
 * @title 26. Remove Duplicates from Sorted Array
 * @link https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
 */
public class Problem26 {
    private Problem26 solution;

    @Before
    public void setUp() {
        solution = new Problem26();
    }

    public int removeDuplicates(int[] nums) {
        int insertIndex = 1, currentIndex = 1;
        while (currentIndex < nums.length) {
            if (nums[currentIndex] != nums[currentIndex - 1]) {
                nums[insertIndex++] = nums[currentIndex];
            }
            currentIndex++;
        }
        return insertIndex;
    }

    @Test
    public void test() {
        int[] nums = new int[] {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k = solution.removeDuplicates(nums);
        Assert.assertEquals(k, 5);

        k = solution.removeDuplicates(new int[] {1, 1, 2});
        Assert.assertEquals(k, 2);

        k = solution.removeDuplicates(new int[] {1, 2});
        Assert.assertEquals(k, 2);
    }
}
