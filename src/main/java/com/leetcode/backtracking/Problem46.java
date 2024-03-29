package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * 46. Permutation
 *
 * @author kufei.dxm
 * @date 2022/5/27
 * @see <a href="https://leetcode.com/problems/permutations/">leetcode-> 46. Permutations</a>
 */
public class Problem46 {
    private Problem46 solution;

    @Before
    public void setUp() {
        solution = new Problem46();
    }

    /**
     * 发现树遍历DFS的思路可以推广到这类穷举类型的回溯题目中，主要需要处理好递归式。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        travel(result, new ArrayList<>(), nums);
        return result;
    }

    public void travel(List<List<Integer>> result, List<Integer> prefix, int[] nums) {
        if (prefix.size() == nums.length) {
            result.add(prefix);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (prefix.contains(nums[i])) {
                continue;
            }
            List<Integer> temp = new ArrayList<>();
            temp.addAll(prefix);
            temp.add(nums[i]);
            travel(result, temp, nums);
        }
    }

    /**
     * 不需要临时变量。回溯的常规解法。
     *
     * @param result
     * @param prefix
     * @param nums
     */
    public void travelV2(List<List<Integer>> result, List<Integer> prefix, int[] nums) {
        if (prefix.size() == nums.length) {
            result.add(new ArrayList<>(prefix));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (prefix.contains(nums[i])) {
                continue;
            }
            prefix.add(nums[i]);
            travel(result, prefix, nums);
            prefix.remove(prefix.size() - 1);
        }
    }

    public void travelV3(List<List<Integer>> result, List<Integer> cur, int[] nums, boolean[] used) {
        if (cur.size() == nums.length) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            cur.add(nums[i]);
            used[i] = true;
            travelV3(result, cur, nums, used);
            cur.remove(cur.size() - 1);
            used[i] = false;
        }
    }

    public List<List<Integer>> permuteV3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        travelV3(result, new ArrayList<>(), nums, new boolean[nums.length]);
        return result;
    }

    @Test
    public void test() {
        List<List<Integer>> result = solution.permute(new int[]{1, 2, 3});
        result.forEach(e -> {
                    e.forEach(f -> System.out.print(f + " "));
                    System.out.println();
                }
        );
        System.out.println("===v3 solution ===");
        result = solution.permuteV3(new int[]{1, 2, 3});
        result.forEach(e -> {
                    e.forEach(f -> System.out.print(f + " "));
                    System.out.println();
                }
        );
    }
}
