package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * 78. Subsets
 *
 * @author kufei.dxm
 * @date 2022/5/27
 */
public class Problem78 {
    private Problem78 solution;

    @Before
    public void setUp() {
        solution = new Problem78();
    }

    /**
     * 常规DFS的套路。保存遍历过程中不重复的路径。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        travel(result, new ArrayList<>(), 0, nums);
        return result;
    }

    public void travel(List<List<Integer>> result, List<Integer> curList, int pos, int[] nums) {
        result.add(new ArrayList<>(curList));
        for (int i = pos; i < nums.length; i++) {
            curList.add(nums[i]);
            travel(result, curList, i + 1, nums);
            curList.remove(curList.size() - 1);
        }
    }

    @Test
    public void test() {
        int[] nums = new int[] {1,2,3};
        List<List<Integer>> result = solution.subsets(nums);
        result.forEach(e -> {
            e.forEach(f -> System.out.print(f + " "));
            System.out.println();
        });
    }
}
