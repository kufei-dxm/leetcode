package com.leetcode.sort;

import org.junit.Before;
import org.junit.Test;

/**
 * @author kufei.dxm
 * @date 2022/5/26
 */
public class MergeSorter {
    private MergeSorter mergeSort;

    @Before
    public void setUp() {
        mergeSort = new MergeSorter();
    }

    public void merge(int[] nums, int[] temp, int start, int mid, int end) {
        int k = 0;
        int i = start, j = mid + 1;
        while (i <= mid && j <= end) {
            if (nums[i] < nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= end) {
            temp[k++] = nums[j++];
        }
        for (int l = 0; l < k; l++) {
            nums[l + start] = temp[l];
        }
    }

    public void sort(int[] nums, int[] temp, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            sort(nums, temp, start, mid);
            sort(nums, temp, mid + 1, end);
            merge(nums, temp, start, mid, end);
        }
    }

    @Test
    public void test() {
        int[] nums = new int[] {3, 7, 5, 1, 6, 2, 4};
        mergeSort.sort(nums, new int[7], 0, 6);
        System.out.println(nums);
    }
}

