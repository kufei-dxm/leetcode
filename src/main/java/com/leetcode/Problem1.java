package com.leetcode;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author kufei.dxm
 * @date 2022/5/20
 */
public class Problem1 {
    private Problem1 solution;

    @Before
    public void setUp() {
        solution = new Problem1();
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] resultIndex = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                resultIndex[0] = map.get(nums[i]);
                resultIndex[1] = i;
            }
            map.put(target - nums[i], i);
        }
        return resultIndex;
    }

    @Test
    public void test() {
        int[] nums = new int[] {2, 11, 15, 7};
        int[] result = solution.twoSum(nums, 9);
        Assert.assertNotNull(result);
    }
}
