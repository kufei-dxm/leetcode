package com.leetcode.array;

import org.junit.Before;
import org.junit.Test;

/**
 * 66. Plus One
 *
 * @author kufei.dxm
 * @date 2022/5/30
 */
public class Problem66 {
    private Problem66 solution;

    @Before
    public void setUp() {
        solution = new Problem66();
    }

    /**
     * 主要是进位的处理，尤其是第一个位置。还有进位时个位数的值需要和10取模；
     * 下面的实现代码稍显得啰嗦
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int i = digits.length;
        boolean needJin = true;
        while (i-- > 0 && needJin) {
            if (digits[i] + 1 < 10) {
                needJin = false;
            }
            digits[i] = (digits[i] + 1) % 10;
        }
        if (i <= 0 && needJin) {
            int[] newDigits = new int[digits.length + 1];
            int j = 0;
            while (j < digits.length) {
                newDigits[j + 1] = digits[j++];
            }
            newDigits[0] = 1;
            return newDigits;
        } else {
            return digits;
        }
    }

    /**
     * 来自讨论区的答案，当真精妙
     *
     * @param digits
     * @return
     */
    public int[] plusOneV2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] newNumber = new int[digits.length + 1];
        newNumber[0] = 1;
        return newNumber;
    }

    @Test
    public void test() {
        int[] digits = new int[] {9, 9};
        int[] result = solution.plusOneV2(digits);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
