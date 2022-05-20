package com.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Valid Sudoku
 * @author kufei.dxm
 * @date 2022/5/20
 */
public class Problem36 {
    private Problem36 solution;

    @Before
    public void setUp() {
        solution = new Problem36();
    }

    /**
     * 3 * 3 区块的索引位置计算容易出错
     * 解法太啰嗦。思路太直白了。
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        Map<Integer, List<Integer>> cols = new HashMap<>();
        Map<Integer, List<Integer>> rows = new HashMap<>();
        Map<Integer, List<Integer>> subs = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char cell = board[i][j];
                if (cell == '.') {
                    continue;
                }
                Integer cellInt = Integer.parseInt(String.valueOf(cell));
                if (!checkCell(cols, cellInt, i)) {
                    return false;
                }
                if (!checkCell(rows, cellInt, j)) {
                    return false;
                }
                int subIndex = (i / 3) * 3 + (j / 3);
                if (!checkCell(subs, cellInt, subIndex)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkCell(Map<Integer, List<Integer>> cols, int cellNum, int cellIndex) {
        List<Integer> oneSub = cols.get(cellIndex);
        if (null == oneSub) {
            oneSub = new ArrayList<>();
            oneSub.add(cellNum);
            cols.put(cellIndex, oneSub);
        } else {
            if (oneSub.contains(cellNum)) {
                return false;
            } else {
                oneSub.add(cellNum);
            }
        }
        return true;
    }

    /**
     * 把数独每个数字位置转换为一个字符串，这个思路太巧妙了。
     *
     * @param board
     * @return
     */
    public boolean isValidSudokuV2(char[][] board) {
        Set<String> dup = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char number = board[i][j];
                if (number == '.') {
                    continue;
                }
                if (!dup.add(number + "in row" + i) || !dup.add(number + "in col" + j) || !dup.add(
                    number + "in block" + i / 3 + "-" + j / 3)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void test() {
        char[][] cells = new char[][] {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        boolean valid = solution.isValidSudoku(cells);
        Assert.assertTrue(valid);

        char[][] cells2 = new char[][] {{'8', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        Assert.assertFalse(solution.isValidSudoku(cells2));
    }
}
