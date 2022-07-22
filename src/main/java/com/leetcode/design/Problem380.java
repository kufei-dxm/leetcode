package com.leetcode.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 380. Insert Delete GetRandom O(1)
 *
 * @author kufei.dxm
 * @date 2022/7/22
 */
public class Problem380 {
    /**
     * getRandom要做到O(1)的时间复杂度，想不出来....
     * 借住Map结构基本是肯定的，不过删除元素后要以常数时间来维持val的列表，这个太难想到了...
     * List里remove元素会导致后续元素移位，做不到常数时间，除非是移除首尾的元素。
     */
    class RandomizedSet {
        private Map<Integer, Integer> valMap;
        private List<Integer> valList;
        private Random rand;

        public RandomizedSet() {
            valMap = new HashMap<>();
            valList = new ArrayList<>();
            rand = new Random();
        }

        public boolean insert(int val) {
            if (valMap.containsKey(val)) {
                return false;
            }
            valMap.put(val, valList.size());
            valList.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!valMap.containsKey(val)) {
                return false;
            }
            int idx = valMap.get(val);
            if (idx < valList.size() - 1) {
                int last = valList.get(valList.size() - 1);
                valList.set(idx, last);
                valMap.put(last, idx);
            }
            valMap.remove(val);
            valList.remove(valList.size() - 1);
            return true;
        }

        public int getRandom() {
            return valList.get(rand.nextInt(valList.size()));
        }
    }
}
