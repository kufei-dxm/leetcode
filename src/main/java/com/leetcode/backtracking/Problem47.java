package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * 47. Permutations II
 *
 * @author kufei.dxm
 * @date 2022/5/27
 */
public class Problem47 {
    private Problem47 solution;

    @Before
    public void setUp() {
        solution = new Problem47();
    }

    /**
     * DFS可解，不过去重逻辑复杂，看来不是最优解法
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        travel(result, new ArrayList<>(), nums);
        return result;
    }

    public void travel(List<List<Integer>> result, List<Integer> curList, int[] nums) {
        if (curList.size() == nums.length) {
            result.add(curList);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                continue;
            }
            if (shouldSkip(curList, nums[i], nums)) {
                continue;
            }
            List<Integer> temp = new ArrayList<>();
            temp.addAll(curList);
            temp.add(nums[i]);
            travel(result, temp, nums);
        }
    }

    private boolean shouldSkip(List<Integer> curList, int node, int[] nums) {
        if (!curList.contains(node)) {
            return false;
        }
        int al = 0;
        int tl = 0;
        for (int temp : curList) {
            if (temp == node) {
                al++;
            }
        }
        for (int temp : nums) {
            if (temp == node) {
                tl++;
            }
        }
        if (al >= tl) {
            return true;
        }
        return false;
    }

    /**
     * https://leetcode.com/problems/permutations/discuss/18239/A-general-approach-to-backtracking-questions-in-Java-
     * (Subsets-Permutations-Combination-Sum-Palindrome-Partioning)
     * 的解法。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUniqueV2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> curList, int[] nums, boolean[] used) {
        if (curList.size() == nums.length) {
            result.add(new ArrayList<>(curList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if ((i < nums.length - 1 && nums[i] == nums[i + 1] && !used[i + 1]) || used[i]) {
                    continue;
                }
                used[i] = true;
                curList.add(nums[i]);
                backtrack(result, curList, nums, used);
                curList.remove(curList.size() - 1);
                used[i] = false;
            }
        }
    }

    @Test
    public void test() {
        int[] arr = new int[]{1, 1, 2};
        List<List<Integer>> result = solution.permuteUnique(arr);
        List<List<Integer>> resultV2 = solution.permuteUniqueV2(arr);
        result.forEach(e -> {
            e.forEach(f -> System.out.print(f + " "));
            System.out.println();
        });
        System.out.println("===========");
        resultV2.forEach(e -> {
            e.forEach(f -> System.out.print(f + " "));
            System.out.println();
        });
    }
}
