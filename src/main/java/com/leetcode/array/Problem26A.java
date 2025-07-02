package com.leetcode.array;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author dxm
 * @Date 2025/7/1
 */
public class Problem26A {
    private Problem26A solution;

    @Before
    public void setUp() {
        solution = new Problem26A();
    }

    public int removeDuplicates(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[index]) {
                index++;
                nums[index] = nums[i];
            }
        }
        return index + 1;
    }

    @Test
    public void test() {
        int[] nums = new int[] {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        Assert.assertEquals(5, solution.removeDuplicates(nums));
    }
}
