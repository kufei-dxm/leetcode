package com.leetcode.sort;

import java.util.Arrays;
import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author kufei.dxm
 * @date 2022/6/2
 */
public class Problem215 {
    private Problem215 solution;

    @Before
    public void setUp() {
        solution = new Problem215();
    }

    /**
     * 排序后返回
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int findKthLargestV2(int[] nums, int k) {

        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int val : nums) {
            pq.offer(val);

            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    @Test
    public void test() {
        int[] nums = new int[] {3, 2, 1, 5, 6, 4};
        int k = solution.findKthLargest(nums, 2);
        Assert.assertEquals(k, 5);

        nums = new int[] {3, 2, 3, 1, 2, 4, 5, 5, 6};
        k = solution.findKthLargest(nums, 4);
        Assert.assertEquals(k, 4);
    }
}
