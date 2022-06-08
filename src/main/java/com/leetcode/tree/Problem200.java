package com.leetcode.tree;

/**
 * 200. Number of Islands
 * @author kufei.dxm
 * @date 2022/6/7
 */
public class Problem200 {
    private int row;
    private int col;

    public int numIslands(char[][] grid) {
        row = grid.length;
        if (row == 0) {
            return 0;
        }
        col = grid[0].length;
        int cnt = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    DFSMarking(grid, i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public void DFSMarking(char[][] grid, int rowI, int colI) {
        if (rowI < 0 || colI < 0 || rowI >= row || colI >= col || grid[rowI][colI] != '1') {
            return;
        }
        grid[rowI][colI] = '0';
        DFSMarking(grid, rowI + 1, colI);
        DFSMarking(grid, rowI - 1, colI);
        DFSMarking(grid, rowI, colI + 1);
        DFSMarking(grid, rowI, colI - 1);
    }
}
