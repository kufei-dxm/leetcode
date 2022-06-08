package com.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * 49. Group Anagrams
 *
 * @author kufei.dxm
 * @date 2022/6/7
 */
public class Problem49 {
    private Problem49 solution;

    @Before
    public void setUp() {
        solution = new Problem49();
    }

    /**
     * 暴力两两比较的方法会超时。。。代码还啰嗦。。。
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList<>();
        }
        boolean[] grouped = new boolean[strs.length];
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            if (grouped[i]) {
                continue;
            }
            List<String> curList = new ArrayList<>();
            String oriStr = strs[i];
            grouped[i] = true;
            curList.add(oriStr);
            int[] dict = genDict(oriStr);
            for (int j = i + 1; j < strs.length; j++) {
                if (grouped[j]) {
                    continue;
                }
                if (isAna(dict.clone(), strs[j])) {
                    curList.add(strs[j]);
                    grouped[j] = true;
                }
            }
            result.add(curList);
        }
        return result;
    }

    private int[] genDict(String str) {
        int[] dict = new int[26];
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            dict[c - 'a']++;
        }
        return dict;
    }

    private boolean isAna(int[] dict, String oriStr) {
        for (int i = 0; i < oriStr.length(); i++) {
            char character = oriStr.charAt(i);
            dict[character - 'a']--;
        }
        for (int i = 0; i < dict.length; i++) {
            if (dict[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public List<List<String>> groupAnagramsV2(String[] strs) {
        if (null == strs || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            int[] dict = genDict(strs[i]);
            String key = Arrays.toString(dict);
            if (map.containsKey(key)) {
                List<String> list = map.get(key);
                list.add(strs[i]);
            } else {
                List<String> newList = new ArrayList<>();
                newList.add(strs[i]);
                map.put(key, newList);
            }
        }
        return new ArrayList<>(map.values());
    }

    @Test
    public void test() {
        String[] strs = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = solution.groupAnagramsV2(strs);
        result.forEach(e -> {
            System.out.print("[");
            e.forEach(f -> System.out.print(f + " "));
            System.out.print("]");
        });
    }
}
