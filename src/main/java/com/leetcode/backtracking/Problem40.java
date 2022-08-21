package com.leetcode.backtracking;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. Combination Sum II
 */
public class Problem40 {
    private Problem40 solution;

    @Before
    public void setUp() {
        solution = new Problem40();
    }

    /**
     * 参考Problem39的思路，代码一把就AC了！历史上首次Medium的题目一把过的！
     * 实现代码稍微冗余了点，尤其是getNextGreaterThanIndex函数。
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        findComb(result, new ArrayList<>(), candidates, 0, target);
        return result;
    }

    public void findComb(List<List<Integer>> result, List<Integer> curList, int[] candidates, int idx, int target) {
        if (idx >= candidates.length) {
            return;
        }
        if (target > 0 && target < candidates[idx]) {
            return;
        }
        int freq = 0;
        for (int i = idx; i < candidates.length && candidates[i] <= target; i++) {
            curList.add(candidates[i]);
            target -= candidates[i];
            freq++;
        }
        if (target == 0) {
            result.add(new ArrayList<>(curList));
        }
        while (freq > 0) {
            int last = curList.remove(curList.size() - 1);
            target += last;
            findComb(result, curList, candidates, getNextGreaterThanIndex(candidates, idx, last), target);
            freq--;
        }
    }

    /**
     * candidates数组元素可能重复
     *
     * @param candidates
     * @param curIndex
     * @param value
     * @return
     */
    private int getNextGreaterThanIndex(int[] candidates, int curIndex, int value) {
        for (int i = curIndex; i < candidates.length; i++) {
            if (candidates[i] > value) {
                return i;
            }
        }
        return candidates.length;
    }

    /**
     * 常规回溯更简洁，且更容易写。
     *
     * @param result
     * @param curList
     * @param candidates
     * @param idx
     * @param target
     */
    public void findCombV2(List<List<Integer>> result, List<Integer> curList, int[] candidates, int idx, int target) {
        if (target < 0) {
            //do nothing
        } else if (target == 0) {
            result.add(new ArrayList<>(curList));
        } else { //>0
            for (int i = idx; i < candidates.length; i++) {
                if (i > idx && candidates[i] == candidates[i - 1]) continue; //skip
                curList.add(candidates[i]);
                findCombV2(result, curList, candidates, i + 1, target - candidates[i]);
                curList.remove(curList.size() - 1);//backtrack
            }
        }
    }

    @Test
    public void test() {
        int[] numArr = new int[]{2, 5, 2, 1, 2};
        List<List<Integer>> result = solution.combinationSum2(numArr, 5);
        result.forEach(e -> {
            e.forEach(f -> System.out.print(f + " "));
            System.out.println();
        });
    }

}
