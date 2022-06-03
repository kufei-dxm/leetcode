package com.leetcode.search;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 33. Search in Rotated Sorted Array
 *
 * @author kufei.dxm
 * @date 2022/6/3
 */
public class Problem33 {
    private Problem33 solution;

    @Before
    public void setUp() {
        solution = new Problem33();
    }

    public int search(int[] nums, int target) {
        int rot = findRot(nums);
        int l = 0, r = nums.length - 1, n = nums.length;
        while (l <= r) {
            int mid = (l + r) / 2;
            int realMid = (mid + rot) % n;
            if (target == nums[realMid]) {
                return realMid;
            }
            if (target > nums[realMid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public int findRot(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid ;
            }
        }
        return l;
    }

    @Test
    public void test() {
        int[] nums = new int[] {4, 5, 6, 7, 0, 1, 2};
        int rot = solution.findRot(nums);
        Assert.assertEquals(4, rot);
        int idx = solution.search(nums, 0);
        Assert.assertEquals(4, idx);

        idx = solution.search(new int[]{4,5,6,7,0,1,2}, 3);
        Assert.assertEquals(-1, idx);

        idx = solution.search(new int[]{1,3,5}, 3);
        Assert.assertEquals(1, idx);

    }
}
