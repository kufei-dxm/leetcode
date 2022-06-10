package com.leetcode.array;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * 54. Spiral Matrix
 *
 * @author kufei.dxm
 * @date 2022/6/9
 */
public class Problem54 {
    private Problem54 solution;

    @Before
    public void setUp() {
        solution = new Problem54();
    }

    /**
     * 一遍能写出遍历式的绝对是神。。。
     * 边界条件得好好判断。。。
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        boolean[][] flag = new boolean[row][col];
        for (; i <= row && j <= col; i++, j++, row--, col--) {
            for (int k = j; k < col; k++) {
                if (!flag[i][k]) {
                    result.add(matrix[i][k]);
                    flag[i][k] = true;
                }
            }
            for (int k = i; k < row; k++) {
                if (!flag[k][col - 1]) {
                    result.add(matrix[k][col - 1]);
                    flag[k][col - 1] = true;
                }
            }
            for (int k = col - 1; k >= j; k--) {
                if (!flag[row - 1][k]) {
                    result.add(matrix[row - 1][k]);
                    flag[row - 1][k] = true;
                }
            }
            for (int k = row - 1; k >= i; k--) {
                if (!flag[k][j]) {
                    result.add(matrix[k][j]);
                    flag[k][j] = true;
                }
            }

        }
        return result;
    }

    @Test
    public void test() {
        int[][] matrix = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix2 = new int[][] {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}};
        List<Integer> result = solution.spiralOrder(matrix);
        result.forEach(e -> System.out.print(e + " "));
        System.out.println();
        result = solution.spiralOrder(matrix2);
        result.forEach(e -> System.out.print(e + " "));
    }
}
