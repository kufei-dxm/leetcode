package com.leetcode.sort;

/**
 * 35. Search Insert Position
 *
 * @author kufei.dxm
 * @date 2022/7/14
 */
public class Problem35 {
    /**
     * 果然，简洁的代码也是能处理好边界问题的.
     * 算是领略到clean code的皮毛了，简洁的代码易理解，易维护，逻辑清晰，更高效。复杂的代码通常是没想清楚，边界情况打满补丁，导致核心逻辑不突出。
     * 这算是做算法题的收益之一：永远以最简洁的代码来解决问题。
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
