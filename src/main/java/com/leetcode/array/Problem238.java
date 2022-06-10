package com.leetcode.array;

/**
 * 238. Product of Array Except Self
 *
 * @author kufei.dxm
 * @date 2022/6/9
 */
public class Problem238 {
    /**
     * 这神操作，实在是想不到。。。
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
}
