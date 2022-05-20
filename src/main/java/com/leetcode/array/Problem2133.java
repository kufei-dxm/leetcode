package com.leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Check if Every Row and Column Contains All Numbers
 *
 * @author kufei.dxm
 * @date 2022/5/21
 */
public class Problem2133 {
    private Problem2133 solution;

    @Before
    public void setUp() {
        solution = new Problem2133();
    }

    /**
     * 解法参考 Problem36 。不过运行效率不是太高
     *
     * @param matrix
     * @return
     */
    public boolean checkValid(int[][] matrix) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int cell = matrix[i][j];
                if (cell > matrix.length || cell < 1) {
                    return false;
                }
                set.add(matrix[i][j] + "in col" + i);
                set.add(matrix[i][j] + "in row" + j);
            }
        }
        if (set.size() != matrix.length * matrix.length * 2) {
            return false;
        }
        return true;
    }

    /**
     * 性能高一点，不过代码啰嗦了很多
     * @param matrix
     * @return
     */
    public boolean checkValidV2(int[][] matrix) {
        Map<Integer, Set<Integer>> cols = new HashMap<>();
        Map<Integer, Set<Integer>> rows = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                int cell = matrix[i][j];
                if (cell > matrix.length || cell < 1) {
                    return false;
                }
                if (checkInvalid(cols, i, cell)) { return false; }
                if (checkInvalid(rows, j, cell)) { return false; }
            }
        }
        return true;
    }

    private boolean checkInvalid(Map<Integer, Set<Integer>> cols, int i, int cell) {
        if (!cols.containsKey(i)) {
            Set<Integer> col = new HashSet<>();
            col.add(cell);
            cols.put(i, col);
        } else {
            Set<Integer> col = cols.get(i);
            if (!col.add(cell)) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        int[][] matric = new int[][] {{1, 2, 3}, {3, 1, 2}, {2, 3, 1}};
        boolean valid = solution.checkValid(matric);
        Assert.assertTrue(valid);
    }
}
