package com.leetcode.greedy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1024. Video Stitching
 *
 * @Author dxm
 * @Date 2022/8/28
 * @see <a href="https://leetcode.com/problems/video-stitching/">《leetcode: 1024.Video Stitching》</a>
 */
public class Problem1024 {
    private Problem1024 solution;

    @Before
    public void setUp() {
        solution = new Problem1024();
    }

    /**
     * [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]]
     *
     * @param clips
     * @param time
     * @return
     */
    public int videoStitching(int[][] clips, int time) {
        Arrays.sort(clips, Comparator.comparingInt(o -> o[0]));
        int minClips = 0;
        int curTime = 0;
        int maxRange = 0;
        for (int i = 0; i < clips.length; i++) {
            int tempCurTime = curTime;
            if (curTime >= time) {
                break;
            }
            for (int j = i; j < clips.length; j++) {
                if (clips[j][0] > curTime || clips[j][1] <= curTime) {
                    continue;
                }
                if (clips[j][1] - curTime > maxRange) {
                    maxRange = clips[j][1] - curTime;
                    tempCurTime = clips[j][1];
                }
            }
            if (maxRange > 0) {
                minClips++;
                maxRange = 0;
                curTime = tempCurTime;
            }
        }
        if (curTime < time) {
            return -1;
        }
        return minClips > 0 ? minClips : -1;
    }

    public int videoStitchingV2(int[][] clips, int time) {
        Arrays.sort(clips, Comparator.comparingInt(o -> o[0]));
        int minClips = 0;
        int curTime = 0;
        for (int i = 0; i < clips.length && curTime < time; i++, minClips++) {
            int tempCurTime = curTime;
            for (int j = i; j < clips.length; j++) {
                if (clips[j][0] > curTime || clips[j][1] <= curTime) {
                    continue;
                }
                tempCurTime = Math.max(tempCurTime, clips[j][1]);
            }
            if (tempCurTime == curTime) {
                return -1;
            }
            curTime = tempCurTime;
        }
        if (curTime < time) {
            return -1;
        }
        return minClips > 0 ? minClips : -1;
    }

    @Test
    public void test() {
        int[][] clips = new int[][]{{0, 2}, {4, 6}, {8, 10}, {1, 9}, {1, 5}, {5, 9}};
        int minClips = solution.videoStitchingV2(clips, 10);
        Assert.assertEquals(minClips, 3);
    }

}
