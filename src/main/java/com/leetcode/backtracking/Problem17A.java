package com.leetcode.backtracking;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author dxm
 * @Date 2025/7/7
 */
public class Problem17A {
    private Problem17A solution;

    @Before
    public void setUp() {
        solution = new Problem17A();
    }

    public static Map<Character, List<Character>> numLetterMapings() {
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

    public List<String> letterCombinations(String digits) {
        if(digits.isEmpty()){
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        backtrace(digits, 0, "", result);
        return result;
    }

    private void backtrace(String digits, int digitIdx, String path, List<String> result) {
        if (path.length() == digits.length()) {
            result.add(path);
            return;
        }
        Character curNum = digits.charAt(digitIdx);
        List<Character> letters = numLetterMapings().get(curNum);
        for (int j = 0; j < letters.size(); j++) {
            backtrace(digits, digitIdx + 1, path + letters.get(j), result);
        }
    }

    @Test
    public void test() {
        List<String> res = solution.letterCombinations("23");
        for (int i = 0; i < res.size(); i++) {
            System.out.print("[" + res.get(i) + "],");
        }
        System.out.println();
    }
}
