package com.leetcode.array;

import org.junit.Before;
import org.junit.Test;

/**
 * 48. Rotate Image
 *
 * @author kufei.dxm
 * @date 2022/5/30
 */
public class Problem48 {
    private Problem48 solution;

    @Before
    public void setUp() {
        solution = new Problem48();
    }

    /**
     * 常规暴力解。四个位置的交换逻辑有点难。。。
     * solution里的先转置，再按左右映射的奇淫技巧，学不会啊。。
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];

                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];

                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }

    @Test
    public void test() {
        int[][] matrix = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        solution.rotate(matrix);

    }
}
