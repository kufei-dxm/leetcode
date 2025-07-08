package com.leetcode.backtracking;

import org.junit.Before;
import org.junit.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author dxm
 * @Date 2025/7/7
 */
public class Problem46A {
    private Problem46A solution;

    @Before
    public void setUp() {
        solution = new Problem46A();
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backTrace(nums, result, new ArrayList<>());
        return result;
    }

    private void backTrace(int[] nums, List<List<Integer>> result, List<Integer> cur) {
        if (cur.size() == nums.length) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (cur.contains(nums[i])) {
                continue;
            }
            cur.add(nums[i]);
            backTrace(nums, result, cur);
            cur.remove(cur.size() - 1);
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3};
        solution.permute(nums);
    }
}
