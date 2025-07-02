package com.leetcode.array;

import com.leetcode.common.ListUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author dxm
 * @Date 2025/7/2
 */
public class Problem189 {
    private Problem189 solution;

    @Before
    public void setUp() {
        solution = new Problem189();
    }

    public void rotate(int[] nums, int k) {
//        for (int i = 0; i < nums.length; i++) {
//           System.out.print(nums[i]+"->");
//        }
//        System.out.println();
        k = k % nums.length;
        int[] res = new int[nums.length];
        int idx = 0 ;
        for (int i = nums.length - k; i < nums.length; i++) {
           res[idx] = nums[i];
           idx++;
        }
        for(int i = 0; i< nums.length -k ;i++){
            res[idx] = nums[i];
            idx++;
        }
        for(int i = 0; i< nums.length;i++){
            nums[i] = res[i];
        }
//        for (int i = 0; i < nums.length; i++) {
//            System.out.print(nums[i]+"->");
//        }
//        System.out.println();
    }

    @Test
    public void test() {
        int [] nums = new int[]{1,2,3,4,5,6,7};
        solution.rotate(nums,6);
    }
}
