package com.leetcode;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * https://leetcode.com/problems/contains-duplicate/
 *
 * @author kufei.dxm
 * @date 2022/5/19
 */
public class Problem217 {
    private Problem217 solution;

    @Before
    public void setUp() {
        solution = new Problem217();
    }

    /**
     * 结果：Runtime: 29 ms, faster than 9.19% of Java online submissions for Contains Duplicate.
     * Memory Usage: 73.3 MB, less than 5.46% of Java online submissions for Contains Duplicate.
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicateV1(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            numSet.add(nums[i]);
        }
        return numSet.size() < nums.length;
    }

    /**
     * 运行结果：
     * Runtime: 12 ms, faster than 53.08% of Java online submissions for Contains Duplicate.
     * Memory Usage: 67.9 MB, less than 69.35% of Java online submissions for Contains Duplicate.
     * Set 换HashMap提升不大
     * @param nums
     * @return
     */
    public boolean containsDuplicateV2(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (numSet.contains(nums[i])) {
                return true;
            }
            numSet.add(nums[i]);
        }
        return numSet.size() < nums.length;
    }

    /**z
     * @param nums
     * @return
     */
    public boolean containsDuplicateV3(int[] nums) {
        return true;
    }

    @Test
    public void test1() {
        int[] nums = new int[] {1, 2, 3, 1};
        Assert.assertTrue(solution.containsDuplicateV1(nums));
        Assert.assertTrue(solution.containsDuplicateV2(nums));
    }
}