package com.leetcode.sort;

import org.junit.Before;
import org.junit.Test;

/**
 * 75. Sort Colors
 *
 * @author kufei.dxm
 * @date 2022/6/2
 */
public class Problem75 {
    private Problem75 solution;

    @Before
    public void setUp() {
        solution = new Problem75();
    }

    /**
     * 比较直观的是两次遍历的思路。第一次记录各种颜色的数量，第二次重新赋值。
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int redCnt = 0, whiteCnt = 0, blueCnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                redCnt++;
            }
            if (nums[i] == 1) {
                whiteCnt++;
            }
            if (nums[i] == 2) {
                blueCnt++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
        }
        int i = 0;
        while (redCnt > 0) {
            nums[i++] = 0;
            redCnt--;
        }
        while (whiteCnt > 0) {
            nums[i++] = 1;
            whiteCnt--;
        }
        while (blueCnt > 0) {
            nums[i++] = 2;
            blueCnt--;
        }
    }

    /**
     * @param nums
     */
    public void sortColorsOnePass(int[] nums) {
        int left = 0, right = nums.length - 1;
        int temp;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                temp = nums[i];
                nums[i] = nums[left];
                nums[left] = temp;
                left++;
            }
            if (nums[i] == 2) {
                temp = nums[i];
                nums[i] = nums[right];
                nums[right] = temp;
                right--;
                i-- ;//这里的回退很关键
            }
        }
    }

    @Test
    public void test() {
        int[] nums = new int[] {2, 0, 2, 1, 1, 0};
        solution.sortColors(nums);
        for (int n : nums) {
            System.out.print(n + " ");
        }
        System.out.println("========");

        nums = new int[] {2, 0, 1};
        solution.sortColors(nums);
        for (int n : nums) {
            System.out.print(n + " ");
        }

    }
}
