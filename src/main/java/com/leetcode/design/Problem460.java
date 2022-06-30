package com.leetcode.design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * 460. LFU Cache
 * 帷幄科技面试时居然考核到了这个题目
 * 原本基于map + 双链表的LRUCache的解题方式，其实不不适用。尤其是当很多key的使用次数一样时，链表的遍历成本达不到O(1)的复杂度。
 * O（1）复杂度只有map能做到。
 *
 * @author kufei.dxm
 * @date 2022/6/30
 */
public class Problem460 {

    @Test
    public void test() {
        LFUCache obj = new LFUCache(8);

    }
}

/**
 * 讨论区的解法，通过多个Map来达到O（1）的要求。不过操作起来稍微复杂一点。
 */
class LFUCache {
    /**
     * 容量
     */
    int capacity;
    /**
     * k-v数据
     */
    Map<Integer, Integer> nodeMap;
    /**
     * k对应的使用次数
     */
    Map<Integer, Integer> useCounts;
    /**
     * 使用次数对应的key
     */
    Map<Integer, LinkedHashSet<Integer>> countList;

    int min = -1;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.nodeMap = new HashMap<>();
        this.useCounts = new HashMap<>();
        countList = new HashMap<>();
        countList.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        if (!nodeMap.containsKey(key)) {
            return -1;
        }
        int useCnt = useCounts.get(key);
        useCounts.put(key, useCnt + 1);
        countList.get(useCnt).remove(key);
        if (useCnt == min && countList.get(useCnt).size() == 0) {
            min++;
        }
        if (!countList.containsKey(useCnt + 1)) {
            countList.put(useCnt + 1, new LinkedHashSet<>());
        }
        countList.get(useCnt + 1).add(key);
        return nodeMap.get(key);
    }

    public void put(int key, int value) {
        if (nodeMap.containsKey(key)) {
            nodeMap.put(key, value);
            get(key);
            return;
        }
        if (nodeMap.size() >= capacity) {
            int evit = countList.get(min).iterator().next();
            countList.get(min).remove(evit);
            nodeMap.remove(evit);
        }
        nodeMap.put(key, value);
        useCounts.put(key, 1);
        min = 1;
        countList.get(1).add(key);
    }

}

