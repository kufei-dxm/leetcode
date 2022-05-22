package com.leetcode.sort;

import org.junit.Before;
import org.junit.Test;

/**
 * @author kufei.dxm
 * @date 2022/5/21
 */
public class BubbleSort {
    private BubbleSort solution;

    @Before
    public void setUp() {
        solution = new BubbleSort();
    }

    /**
     * 每一轮把最大(或最小)值交换到数组尾部
     * @param nums
     */
    public void sort(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }
    @Test
    public void  test(){
        int [] nums = new int[]{4,3,5,1};
        solution.sort(nums);
        for (int i: nums){
            System.out.print(i+" ");
        }
    }
}
