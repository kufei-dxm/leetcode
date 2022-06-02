package com.leetcode.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * 347. Top K Frequent Elements
 *
 * @author kufei.dxm
 * @date 2022/6/2
 */
public class Problem347 {
    private Problem347 solution;

    @Before
    public void setUp() {
        solution = new Problem347();
    }

    /**
     * 频次统计结果，放置在一个bucket数组是比较巧妙的思路，可达到一次遍历排序的目的。
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            if (freqMap.containsKey(num)) {
                freqMap.put(num, freqMap.get(num) + 1);
            } else {
                freqMap.put(num, 1);
            }
        }
        List<Integer>[] bucket = new List[nums.length+1];
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();
            if (bucket[freq] == null) {
                List<Integer> bucketNum= new ArrayList<>();
                bucketNum.add(num);
                bucket[freq] = bucketNum ;
            } else {
                bucket[freq].add(num);
            }
        }
        int[] result = new int[k];
        for (int i = bucket.length - 1; i >= 0 & k > 0; i--) {
            if (bucket[i] != null) {
                List<Integer> keys = bucket[i];
                for (int key : keys) {
                    if (k-- <= 0) {
                        break;
                    }
                    result[k] = key ;
                }
            }
        }
        return result;
    }

    @Test
    public void test() {
        int[] nums = new int[] {1, 1, 1, 2, 2, 3};
        int[] result = solution.topKFrequent(nums, 2);
        for (int i: result){
            System.out.print(i+ " ");
        }
    }
}
