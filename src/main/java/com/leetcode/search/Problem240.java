package com.leetcode.search;

import org.junit.Before;

/**
 * @Author dxm
 * @Date 2025/7/7
 */
public class Problem240 {
    private Problem240 solution;

    @Before
    public void setUp() {
        solution = new Problem240();
    }

    /**
     * 关键在遍历的顺序。不能从起点开始（没法判断行进方向）
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int col = matrix[0].length - 1;
        int row = 0;
        while (col >= 0 && row <= matrix.length - 1) {
            if (target == matrix[row][col]) {
                return true;
            } else if (target < matrix[row][col]) {
                col--;
            } else if (target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }
}
