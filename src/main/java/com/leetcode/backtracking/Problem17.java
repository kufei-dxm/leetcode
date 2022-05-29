package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

/**
 * 17. Letter Combinations of a Phone Number
 *
 * @author kufei.dxm
 * @date 2022/5/27
 */
public class Problem17 {
    private Problem17 solution;

    @Before
    public void setUp() {
        solution = new Problem17();
    }

    public static Map<Character, List<Character>> nums() {
        Map<Character, List<Character>> mapings = new HashMap<>();
        mapings.put('2', new ArrayList<Character>() {{
            add('a');
            add('b');
            add('c');
        }});
        mapings.put('3', new ArrayList<Character>() {{
            add('d');
            add('e');
            add('f');
        }});
        mapings.put('4', new ArrayList<Character>() {{
            add('g');
            add('h');
            add('i');
        }});
        mapings.put('5', new ArrayList<Character>() {{
            add('j');
            add('k');
            add('l');
        }});
        mapings.put('6', new ArrayList<Character>() {{
            add('m');
            add('n');
            add('o');
        }});
        mapings.put('7', new ArrayList<Character>() {{
            add('p');
            add('q');
            add('r');
            add('s');
        }});
        mapings.put('8', new ArrayList<Character>() {{
            add('t');
            add('u');
            add('v');
        }});
        mapings.put('9', new ArrayList<Character>() {{
            add('w');
            add('x');
            add('y');
            add('z');
        }});
        return mapings;
    }

    /**
     * 好不容易通过树遍历的递归思路解出来了，暴力搜索法。DFS的思路，递归或堆栈可实现
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) {
            return new ArrayList<>();
        }
        traversal(result, "", digits, 0);
        return result;
    }

    public void traversal(List<String> paths, String pathPrefix, String digits, int currentLevel) {
        if (pathPrefix.length() == digits.length()) {
            paths.add(pathPrefix);
            return;
        }
        List<Character> nodes = nums().get(digits.charAt(currentLevel));
        for (int i = 0; i < nodes.size(); i++) {
            traversal(paths, pathPrefix + nodes.get(i), digits, currentLevel + 1);
        }
    }

    /**
     * BFS
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinationsV2(String digits) {
        LinkedList<String> combs = new LinkedList<>();
        combs.add("");
        for (int i = 0; i < digits.length(); i++) {
            while (combs.peek().length() == i) {
                String prefix = combs.remove();
                for (Character c : nums().get(digits.charAt(i))) {
                    combs.add(prefix + c);
                }
            }
        }
        return combs;
    }

    @Test
    public void test() {
        List<String> combs = solution.letterCombinations("23");
        combs.forEach(e -> System.out.print(e + " "));

        combs = solution.letterCombinationsV2("23");
        System.out.println();
        combs.forEach(e -> System.out.print(e + " "));
    }
}

