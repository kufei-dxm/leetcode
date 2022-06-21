package com.leetcode.sort;

import org.junit.Before;
import org.junit.Test;

/**
 * 插入排序
 *
 * @author kufei.dxm
 * @date 2022/6/21
 */
public class InsertionSort {
    private InsertionSort solution;

    @Before
    public void setUp() {
        solution = new InsertionSort();
    }

    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int now = nums[i];
            int j = i;
            for (; j > 0 && now < nums[j - 1]; j--) {
                nums[j] = nums[j - 1];
            }
            nums[j] = now;
        }
    }

    @Test
    public void test() {
        int[] nums = new int[] {8, 1, 6, 4, 3, 2, 9};
        solution.sort(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
