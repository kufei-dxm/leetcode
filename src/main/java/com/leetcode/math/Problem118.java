package com.leetcode.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * 118. Pascal's Triangle
 *
 * @author kufei.dxm
 * @date 2022/6/17
 */
public class Problem118 {
    private Problem118 solution;

    @Before
    public void setUp() {
        solution = new Problem118();
    }

    public List<List<Integer>> generate(int numRows) {
        List<Integer> one = new ArrayList<>(Arrays.asList(1));
        List<List<Integer>> result = new ArrayList<>();
        result.add(one);
        if (numRows == 1) {
            return result;
        }
        for (int i = 2; i <= numRows; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) {
                    list.add(j, 1);
                } else {
                    int sum = one.get(j - 1) + one.get(j);
                    list.add(j, sum);
                }
            }
            result.add(list);
            one = list;
        }
        return result;
    }

    @Test
    public void test() {
        List<List<Integer>> result = solution.generate(5);
        result.forEach(e -> {
            e.forEach(f -> System.out.print(f + " "));
            System.out.println();
        });
    }
}
