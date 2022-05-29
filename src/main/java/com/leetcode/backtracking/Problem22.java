package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * 22. Generate Parentheses
 *
 * @author kufei.dxm
 * @date 2022/5/27
 */
public class Problem22 {
    private Problem22 solution;

    @Before
    public void setUp() {
        solution = new Problem22();
    }

    /**
     * 套路就比较简单了。DFS遍历，中间剪枝避免穷举，剪枝逻辑需要留意下
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        String head = "";
        traversal(result, head, 2 * n);
        return result;
    }

    public void traversal(List<String> paths, String prefix, int totalLevel) {
        if (prefix.length() == totalLevel) {
            if (!prefix.endsWith("(")) {
                paths.add(prefix);
            }
            return;
        }
        if (!validParenthesis(prefix, totalLevel)) {
            return;
        }
        traversal(paths, prefix + "(", totalLevel);
        traversal(paths, prefix + ")", totalLevel);
    }

    private boolean validParenthesis(String parenthesis, int totalLevel) {
        if (parenthesis.length() == 0) {
            return true;
        }
        if (parenthesis.startsWith(")")) {
            return false;
        }
        int rightCnt = 0;
        for (int i = 0; i < parenthesis.length(); i++) {
            if (parenthesis.charAt(i) == ')') {
                rightCnt++;
            }
        }
        if (rightCnt > parenthesis.length() - rightCnt) {
            return false;
        }
        if (Math.abs(rightCnt - (parenthesis.length() - rightCnt)) > totalLevel - parenthesis.length()) {
            return false;
        }
        return true;
    }

    /**
     * 不需要判定valid的非穷举版本。
     * @param n
     * @return
     */
    public List<String> generateParenthesisV2(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int total) {
        if (cur.length() == 2 * total) {
            ans.add(cur.toString());
            return;
        }
        if (open < total) {
            cur.append("(");
            backtrack(ans, cur, open + 1, close, total);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(")");
            backtrack(ans, cur, open, close + 1, total);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    @Test
    public void test() {
        List<String> result = solution.generateParenthesis(1);
        result.forEach(e -> System.out.print(e + "  "));

        result = solution.generateParenthesisV2(3);
        result.forEach(e -> System.out.print(e + "  "));
    }
}
