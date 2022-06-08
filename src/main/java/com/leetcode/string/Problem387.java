package com.leetcode.string;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * @author kufei.dxm
 * @date 2022/6/6
 */
public class Problem387 {
    private Problem387 solution;

    @Before
    public void setUp() {
        solution = new Problem387();
    }

    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            if (map.containsKey(character)) {
                map.put(character, map.get(character) + 1);
            } else {
                map.put(character, 1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            if (map.containsKey(character) && map.get(character) == 1) {
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int fi = solution.firstUniqChar("aabb");
        System.out.println(fi);

        fi = solution.firstUniqChar("loveleetcode");
        System.out.println(fi);
    }
}
