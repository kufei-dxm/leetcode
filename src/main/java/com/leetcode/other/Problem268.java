package com.leetcode.other;

import org.junit.Before;
import org.junit.Test;

/**
 * 268. Missing Number
 *
 * @author kufei.dxm
 * @date 2022/6/6
 */
public class Problem268 {
    private Problem268 solution;

    @Before
    public void setUp() {
        solution = new Problem268();
    }

    /**
     * 占领用Integer.Max表示，未占领用MIN表示
     * 费老半天劲。。。
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int last = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == nums.length) {
                last = Integer.MAX_VALUE;
                nums[i] = Integer.MIN_VALUE;
            }
            int temp = nums[i];
            if (temp >= 0 && temp < nums.length) {
                nums[i] = Integer.MIN_VALUE;
            }
            while (temp >= 0 && temp < nums.length) {
                int next = nums[temp];
                nums[temp] = Integer.MAX_VALUE;
                temp = next;
            }
        }
        int mis = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == Integer.MIN_VALUE) {
                mis = i;
                break;
            }
        }
        if (mis == Integer.MIN_VALUE) {
            mis = nums.length;
        }
        return mis;
    }

    /**
     * 太牛X了！
     * @param nums
     * @return
     */
    public int missingNumberV2(int[] nums) {
        int xor = 0, i = 0;
        for (; i < nums.length; i++) {
            xor = xor ^ nums[i] ^ i;
        }
        return xor ^ i;
    }

    @Test
    public void test() {
        int mis = solution.missingNumber(new int[] {7, 6, 4, 2, 3, 5, 9, 8, 0});
        System.out.println(mis);

        mis = solution.missingNumber(new int[] {0, 1});
        System.out.println(mis);

        mis = solution.missingNumber(new int[] {3, 0, 1});
        System.out.println(mis);
    }
}
