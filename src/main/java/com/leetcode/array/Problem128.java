package com.leetcode.array;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 128. Longest Consecutive Sequence
 *
 * @author kufei.dxm
 * @date 2022/6/29
 */
public class Problem128 {
    private Problem128 solution;

    @Before
    public void setUp() {
        solution = new Problem128();
    }

    /**
     * 代码稍微啰嗦，不过一遍过，性能也OK。
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Boolean> flag = new HashMap<>();
        for (int num : nums) {
            map.put(num, num);
            flag.put(num, false);
        }
        int maxSeq = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (flag.get(entry.getKey())) {
                continue;
            }
            flag.put(entry.getKey(), true);
            int curLen = 1;
            int key = entry.getKey();
            int upper = key + 1;
            while (map.containsKey(upper) && !flag.get(upper)) {
                curLen++;
                flag.put(upper, true);
                upper++;
            }
            int lower = key - 1;
            while (map.containsKey(lower) && !flag.get(lower)) {
                curLen++;
                flag.put(lower, true);
                lower--;
            }
            if (curLen > maxSeq) {
                maxSeq = curLen;
            }
        }
        return maxSeq;
    }

    @Test
    public void test() {
        int[] nums = new int[] {100, 4, 200, 1, 3, 2};
        int result = solution.longestConsecutive(nums);
        Assert.assertEquals(result, 4);

        nums = new int[] {0,3,7,2,5,8,4,6,0,1};
        result = solution.longestConsecutive(nums);
        Assert.assertEquals(result, 9);
    }
}
