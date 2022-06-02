package com.leetcode.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.leetcode.common.Interval;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.INACTIVE;
import sun.jvm.hotspot.runtime.InterpretedVFrame;

/**
 * 56. Merge Intervals
 *
 * @author kufei.dxm
 * @date 2022/6/2
 */
public class Problem56 {
    private Problem56 solution;

    @Before
    public void setUp() {
        solution = new Problem56();
    }

    /**
     * 这个代码不太靠谱。相邻的数被染色，会被认为是可合并的
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        int upperBound = 10002;
        int[] colorArray = new int[upperBound];
        int maxNum = 0;
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            if (end > maxNum) {
                maxNum = end;
            }
            while (start <= end) {
                colorArray[start++] = 1;
            }
        }
        //regen the intervals
        boolean startF = false, isAdd = true;
        int start = 0, end = 0;
        int idx = 0;
        for (int i = 0; i <= maxNum + 1; i++) {
            if (colorArray[i] == 1) {
                if (!startF) {
                    startF = true;
                    start = i;
                }
                end = i;
                isAdd = false;
            } else {
                startF = false;
                if (!isAdd) {
                    int[] interval = new int[2];
                    interval[0] = start;
                    interval[1] = end;
                    intervals[idx++] = interval;
                    isAdd = true;
                }
            }
        }
        int[][] result = new int[idx][2];
        for (int i = 0; i < idx; i++) {
            result[i] = intervals[i];
        }
        return result;
    }

    /**
     * 性能虽然低，但是能AC！。
     * 讨论区里的解法，快在排序上。合并的思路差不多。不过多元素结构的排序裸写避免复杂。。
     *
     * @param intervals
     * @return
     */
    public int[][] mergeV2(int[][] intervals) {
        boolean[] isMerged = new boolean[intervals.length];
        int num = intervals.length;
        for (int i = 0; i < intervals.length; i++) {
            if (isMerged[i]) {
                continue;
            }
            int[] interval = intervals[i];
            for (int j = i + 1; j < intervals.length; j++) {
                if (isMerged[j] || isMerged[i]) {
                    continue;
                }
                int[] interval2 = intervals[j];
                if (isOverLap(interval, interval2)) {
                    intervals[j] = mergeOneInterval(interval, interval2);
                    isMerged[i] = true;
                    num--;
                }
            }
        }
        int[][] result = new int[num][2];
        int j = 0;
        for (int i = 0; i < intervals.length && j < num; i++) {
            if (!isMerged[i]) {
                result[j++] = intervals[i];
            }
        }
        return result;
    }

    /**
     * 讨论区里的解法。排序后再合并。
     * 可是，发现没？ 排序后合并的逻辑非常简单。。。。
     * 所以。。。为啥不排个序先呢？？？反而苦思冥想那么复杂的解法。。。。
     * @param intervals
     * @return
     */
    public int[][] mergeV3(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, Comparator.comparingInt(t -> t[0]));
        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);
        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) {// Overlapping intervals, move the end if needed
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            } else {                             // Disjoint intervals, add the new interval to the list
                newInterval = interval;
                result.add(newInterval);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public boolean isOverLap(int[] a, int[] b) {
        return a[1] - a[0] + b[1] - b[0] >= (a[1] > b[1] ? a[1] - b[0] : b[1] - a[0]);
    }

    public int[] mergeOneInterval(int[] a, int[] b) {
        int[] c = new int[2];
        c[0] = Math.min(a[0], b[0]);
        c[1] = Math.max(a[1], b[1]);
        return c;
    }

    @Test
    public void test() {
        int[][] intervals = new int[5][2];
        intervals[0] = new int[] {1, 3};
        intervals[1] = new int[] {2, 6};
        intervals[2] = new int[] {8, 10};
        intervals[3] = new int[] {15, 18};
        intervals[4] = new int[] {3, 7};
        int[][] result = solution.mergeV2(intervals);
        Assert.assertNotNull(result);
    }
}
