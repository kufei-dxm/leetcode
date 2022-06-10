package com.leetcode.array;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Before;
import org.junit.Test;

/**
 * 239. Sliding Window Maximum
 *
 * @author kufei.dxm
 * @date 2022/6/9
 */
public class Problem239 {
    private Problem239 solution;

    @Before
    public void setUp() {
        solution = new Problem239();
    }

    /**
     * 思路和暴力解的差不多，只是用了Deque的数据结构。
     * 维持一个小队列，效率上还是高，关键能实现滑动的处理。处理起来也方便很多。手写deque的实现还是挺麻烦的(有很多重复遍历）。
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            return new int[0];
        }
        int n = nums.length;
        int[] r = new int[n - k + 1];
        int ri = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            // remove numbers out of range k
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
                q.pollLast();
            }
            // q contains index... r contains content
            q.offer(i);
            if (i >= k - 1) {
                r[ri++] = nums[q.peek()];
            }
        }
        return r;
    }

    /**
     * 不使用Deque的实现
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindowV2(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];

        return result;
    }

    @Test
    public void test() {
        int[] nums = new int[] {1, 3, -1, -3, 5, 3, 6, 7};
        int[] result = solution.maxSlidingWindow(nums, 3);
        for (int n : result) {
            System.out.print(n + " ");
        }
    }
}
