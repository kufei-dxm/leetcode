package com.leetcode.backtracking;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * 39. Combination Sum
 */
public class Problem39 {
    private Problem39 solution;

    @Before
    public void setUp() {
        solution = new Problem39();
    }

    /**
     * 第一个版本的代码，超时了。大概率是组合的去重判断上。得有更优雅的方式来避免重复解。
     * 常规回溯存在问题：递归深度；重复解；
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        findComb(result, new ArrayList<>(), candidates, target);
        return result;
    }

    public void findComb(List<List<Integer>> result, List<Integer> curComb, int[] candidates, int target) {
        if (target > 0 && target < candidates[0]) {
            return;
        }
        /**
         * find a combination
         */
        if (target == 0) {
            List<Integer> comb = new ArrayList<>(curComb);
            Collections.sort(comb);
            if (!listExist(result, comb)) {
                result.add(comb);
            }
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                curComb.add(candidates[i]);
                findComb(result, curComb, candidates, target - candidates[i]);
                curComb.remove(curComb.size() - 1);
            }
        }
    }

    public boolean listExist(List<List<Integer>> result, List<Integer> can) {
        for (List<Integer> list : result) {
            if (listEquals(list, can)) {
                return true;
            }
        }
        return false;
    }

    private boolean listEquals(List<Integer> listA, List<Integer> listB) {
        if (listA.size() != listB.size()) {
            return false;
        }
        for (int i = 0; i < listA.size(); i++) {
            if (!listA.get(i).equals(listB.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSumV2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        findComb(result, new ArrayList<>(), candidates, 0, target);
        return result;
    }

    /**
     * 贪心式回溯，性能相当好。看了下讨论区里的[高票解法](https://leetcode.com/problems/combination-sum/discuss/1777569/FULL-EXPLANATION-WITH-STATE-SPACE-TREE-oror-Recursion-and-Backtracking-oror-Well-Explained-oror-C%2B%2B)
     * 性能远没这个好。。
     * @param result
     * @param curComb
     * @param candidates
     * @param idx
     * @param target
     */
    public void findComb(List<List<Integer>> result, List<Integer> curComb, int[] candidates, int idx, int target) {
        if (idx >= candidates.length) {
            return;
        }
        if (target > 0 && target < candidates[idx]) {
            return;
        }
        int freq = 0;
        while (candidates[idx] <= target) {
            curComb.add(candidates[idx]);
            target -= candidates[idx];
            freq++;
        }
        if (target == 0) {
            result.add(new ArrayList<>(curComb));
        }
        while (freq > 0) {
            curComb.remove(curComb.size() - 1);
            target += candidates[idx];
            findComb(result, curComb, candidates, idx + 1, target);
            freq--;
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{100, 200, 4, 12};
        List<List<Integer>> result = solution.combinationSumV2(nums, 400);
        result.forEach(e -> {
            e.forEach(f -> System.out.print(f + " "));
            System.out.println();
        });
    }
}
