package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * 90. Subsets II
 *
 * @author kufei.dxm
 * @date 2022/5/27
 */
public class Problem90 {
    private Problem90 solution;

    @Before
    public void setUp() {
        solution = new Problem90();
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        travel(result, new ArrayList<>(), 0, nums);
        return result;
    }

    public void travel(List<List<Integer>> result, List<Integer> curList, int pos, int[] nums) {
        result.add(curList);
        for (int i = pos; i < nums.length; i++) {
            if (i > pos && nums[i] == nums[i - 1]) {
                continue;
            }
            List<Integer> temp = new ArrayList<>();
            temp.addAll(curList);
            temp.add(nums[i]);
            travel(result, temp, i + 1, nums);
        }
    }

    @Test
    public void test() {
        int[] nums = new int[] {1, 2, 2};
        List<List<Integer>> result = solution.subsetsWithDup(nums);
        result.forEach(e -> {
            e.forEach(f -> System.out.print(f + " "));
            System.out.println();
        });
    }

}
