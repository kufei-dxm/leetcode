package com.leetcode.array;

import org.junit.Before;
import org.junit.Test;

/**
 * @Author dxm
 * @Date 2025/7/2
 */
public class Problem283A {
    private Problem283A solution;

    @Before
    public void setUp() {
        solution = new Problem283A();
    }

    public void moveZeroes(int[] nums) {
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[idx++] = nums[i];
            }
        }
        for (int i = idx; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{0};
        solution.moveZeroes(nums);
    }
}
