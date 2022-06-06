package com.leetcode.string;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 20. Valid Parentheses
 *
 * @author kufei.dxm
 * @date 2022/6/6
 */
public class Problem20 {
    private Problem20 solution;

    @Before
    public void setUp() {
        solution = new Problem20();
    }

    /**
     * 我擦。。。居然在easy的题目上翻车。。。
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        int v1 = 0, v2 = 0, v3 = 0;
        for (int i = 0; i < s.length(); i++) {
            Character curChar = s.charAt(i);
            if (curChar == '(') {
                v1++;
            }
            if (curChar == ')') {
                v1--;
            }
            if (curChar == '{') {
                v2++;
            }
            if (curChar == '}') {
                v2--;
            }
            if (curChar == '[') {
                v3++;
            }
            if (curChar == ']') {
                v3--;
            }
            if (v1 < 0 || v2 < 0 || v3 < 0) {
                return false;
            }
        }
        return v1 == 0 && v2 == 0 & v3 == 0;
    }

    /**
     * stack 的典型场景
     * @param s
     * @return
     */
    public boolean isValidV2(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char character = s.charAt(i);
            if (character == '(' || character == '[' || character == '{') {
                stack.push(character);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if ((character == ')' && stack.peek().equals('(')) || (character == ']' && stack.peek().equals('['))
                    || (character == '}' && stack.peek().equals('{'))) {
                    stack.pop();
                } else {
                    stack.push(character);
                }
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void test() {
        boolean valid = solution.isValidV2("]");
        Assert.assertFalse(valid);

        valid = solution.isValid("()[]{}");
        Assert.assertTrue(valid);

    }
}
