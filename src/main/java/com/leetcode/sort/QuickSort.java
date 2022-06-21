package com.leetcode.sort;

import org.junit.Before;
import org.junit.Test;

/**
 * 快排代码实现
 *
 * @author kufei.dxm
 * @date 2022/6/21
 */
public class QuickSort {
    private QuickSort solution;

    @Before
    public void setUp() {
        solution = new QuickSort();
    }

    public void sort(int[] nums) {
        qsort(0, nums.length - 1, nums);
    }

    private void qsort(int low, int high, int[] nums) {
        if (low >= high) {
            return;
        }
        int povit = low;
        for (int i = low + 1; i <= high; i++) {
            if (nums[i] < nums[low]) {
                povit++;
                int temp = nums[i];
                nums[i] = nums[povit];
                nums[povit] = temp;
            }
        }
        int temp = nums[low];
        nums[low] = nums[povit];
        nums[povit] = temp;
        qsort(low, povit - 1, nums);
        qsort(povit + 1, high, nums);
    }

    @Test
    public void test() {
        int[] nums = new int[] {55, 41, 59,26, 53, 58, 97, 93};
        solution.sort(nums);
        for (int n : nums) {
            System.out.print(n + " ");
        }
    }
}
