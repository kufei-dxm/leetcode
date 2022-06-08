package com.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 15. 3Sum
 * @author kufei.dxm
 * @date 2022/5/20
 */
public class Problem15 {
    private Problem15 solution;

    @Before
    public void setUp() {
        solution = new Problem15();
    }

    /**
     * 这个解法不够巧妙，且去重比较麻烦
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int target = nums[i];
            List<List<Integer>> otherTwos = twoSum(nums, i, -target);
            result.addAll(otherTwos);
        }
        //TODO：去重
        return result;
    }

    /**
     * 精简的版本
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSumV2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i>0 && nums[i] != nums[i - 1])) {
                int lo = i + 1, hi = nums.length - 1, sum = -nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo + 1]) {lo++;}
                        while (lo < hi && nums[hi] == nums[hi - 1]) {hi--;}
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] < sum) {
                        lo++;
                    } else {
                        hi--;
                    }
                }
            }
        }
        return null;
    }

    private List<List<Integer>> twoSum(int[] nums, int excludeIndex, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i == excludeIndex) {
                continue;
            }
            if (map.containsKey(target - nums[i])) {
                List<Integer> oneMatch = new ArrayList<>();
                oneMatch.add(nums[excludeIndex]);
                oneMatch.add(nums[i]);
                oneMatch.add(nums[map.get(target - nums[i])]);
                result.add(oneMatch);
            }
            map.put(nums[i], i);
        }
        return result;
    }

    @Test
    public void test() {
        int[] nums = new int[] {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = solution.threeSum(nums);
        Assert.assertTrue(result.size() > 0);
    }
}
