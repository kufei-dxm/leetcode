package com.leetcode.array;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 169. Majority Element
 * @author kufei.dxm
 * @date 2022/5/20
 */
public class Problem169 {
    private Problem169 solution;

    @Before
    public void setUp() {
        solution = new Problem169();
    }

    public int majorityElement(int[] nums) {
        int maj = nums[0];
        int num = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == maj) {
                num++;
            } else {
                if (num > 0) {
                    num--;
                } else {
                    maj = nums[i];
                }
            }
        }
        return maj;
    }

    @Test
    public void test() {
        int[] nums = new int[] {3, 2, 3};
        int maj = solution.majorityElement(nums);
        assertEquals(maj, 3);

        maj = solution.majorityElement(new int[] {2, 2, 1, 1, 1, 2, 2});
        assertEquals(maj, 2);

        maj = solution.majorityElement(new int[] {0});
        assertEquals(0, maj);
    }
}
