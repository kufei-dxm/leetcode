package com.leetcode.math;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * FizzBuzz
 * @author kufei.dxm
 * @date 2022/5/22
 */
public class Problem412 {
    private Problem412 solution;

    @Before
    public void setUp() {
        solution = new Problem412();
    }

    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add(i + "");
            }
        }
        return result;
    }

    @Test
    public void test() {
        List<String> res = solution.fizzBuzz(3);
        for (String str : res) {
            System.out.print(str + " ");
        }
    }
}
