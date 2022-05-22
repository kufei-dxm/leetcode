package com.leetcode.array;

import org.junit.Before;
import org.junit.Test;

/**
 * Move Zeroes
 *
 * @author kufei.dxm
 * @date 2022/5/21
 */
public class Problem283 {
    private Problem283 solution;

    @Before
    public void setUp() {
        solution = new Problem283();
    }

    /**
     * 每一轮把0和后面非0的元素进行交换。
     * 思路还是太常规
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            if (nums[i] == 0) {
                while (j < nums.length) {
                    if (nums[j] != 0) {
                        int tmp = nums[j];
                        nums[j] = nums[i];
                        nums[i] = tmp;
                        break;
                    }
                    j++;
                }
            }
        }
    }

    /**
     * 单次遍历，把非0元素往前填充。填充完了，后续的全部填0 。
     * 思路太巧妙了
     * @param nums
     */
    public void moveZeroesV2(int[] nums) {
        int index = 0 ;
        for (int i = 0; i < nums.length; i++) {
           if(nums[i] !=0){
               nums[index] = nums[i];
               index++;
           }
        }
        while (index < nums.length){
            nums[index ++] = 0 ;
        }
    }

    @Test
    public void test() {
        int[] nums = new int[] {1, 0, 0, 3, 12};
        moveZeroes(nums);
        for (int i : nums) {
            System.out.print(i + " ");
        }
        int[] nums2 = new int[] {1, 0, 0, 3, 12};
        moveZeroesV2(nums2);
    }
}
