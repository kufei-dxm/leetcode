package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 79. Word Search
 *
 * @author kufei.dxm
 * @date 2022/6/11
 */
public class Problem79 {
    private Problem79 solution;

    @Before
    public void setUp() {
        solution = new Problem79();
    }

    private int row;
    private int col;
    boolean[][] visited;

    /**
     * 回溯法能AC，不过代码比较冗余。
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        row = board.length;
        col = board[0].length;
        visited = new boolean[row][col];
        List<Integer[]> startPoints = new ArrayList<>();
        //定位起始点的代码可以去掉，可以直接遍历递归整个board。
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    Integer[] start = new Integer[2];
                    start[0] = i;
                    start[1] = j;
                    //visited[i][j] = true;
                    startPoints.add(start);
                }
            }
        }
        if (startPoints.size() == 0) {
            return false;
        }
        for (Integer[] start : startPoints) {
            if (search(start[0], start[1], board, word, 0)) {
                return true;
            }
        }
        return false;
    }

    public boolean search(int i, int j, char[][] board, String word, int idx) {
        if (i >= row || j >= col || i < 0 || j < 0) {
            return false;
        }
        if (visited[i][j] || board[i][j] != word.charAt(idx)) {
            return false;
        }
        if (idx == word.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        boolean exist = search(i, j + 1, board, word, idx + 1) || search(i + 1, j, board, word, idx + 1) || search(
            i - 1, j,
            board, word, idx + 1) || search(i, j - 1, board, word, idx + 1);
        if (!exist) {
            visited[i][j] = false;
        }
        return exist;
    }

    @Test
    public void test() {
        char[][] board = new char[][] {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        boolean exist = solution.exist(board, "ABCB");
        Assert.assertTrue(exist);
    }
}
