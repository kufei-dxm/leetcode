package com.leetcode.sort;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

/**
 * Merge Sorted Array
 *
 * @author kufei.dxm
 * @date 2022/5/23
 */
public class Problem88 {
    private Problem88 solution;

    @Before
    public void setUp() {
        solution = new Problem88();
    }

    /**
     * 常规合并后排序的思路。
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m; i < m + n; i++) {
            nums1[i] = nums2[i - m];
        }
        Arrays.sort(nums1);
    }

    /**
     * 参考讨论区的牛X的后向遍历解法
     * 数组的边界情况得小心处理
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void mergeV2(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (j >= 0 && i >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }

    @Test
    public void test() {
        solution.mergeV2(new int[] {0, 0, 3, 0, 0, 0, 0, 0, 0}, 3, new int[] {-1, 1, 1, 1, 2, 3}, 6);
        solution.merge(new int[] {1, 2, 3, 0, 0, 0}, 3, new int[] {2, 5, 6}, 3);
        solution.mergeV2(new int[] {1, 2, 3, 0, 0, 0}, 3, new int[] {2, 5, 6}, 3);
    }
}
