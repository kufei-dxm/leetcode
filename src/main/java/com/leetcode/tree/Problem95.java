package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

import com.leetcode.common.TreeNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 95. Unique Binary Search Trees II
 *
 * @author kufei.dxm
 * @date 2022/7/8
 */
public class Problem95 {
    private Problem95 solution;

    @Before
    public void setUp() {
        solution = new Problem95();
    }

    /**
     * 写了1小时。。。AC了，不过代码实在是看不下去。。。。
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        List<Integer> nodes = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nodes.add(i);
        }
        return buildTrees(nodes);
    }

    private List<TreeNode> buildTrees(List<Integer> nodes) {
        List<TreeNode> trees = new ArrayList<>();
        if (nodes.isEmpty()) {
            return trees;
        }
        if (nodes.size() == 1) {
            trees.add(new TreeNode(nodes.get(0)));
            return trees;
        }
        for (int node : nodes) {
            List<TreeNode> lefts = buildTrees(getLeft(node, nodes));
            List<TreeNode> rights = buildTrees(getRight(node, nodes));
            if (!lefts.isEmpty()) {
                for (TreeNode left : lefts) {
                    if (!rights.isEmpty()) {
                        for (TreeNode right : rights) {
                            TreeNode root = new TreeNode(node);
                            root.left = left;
                            root.right = right;
                            trees.add(root);
                        }
                    } else {
                        TreeNode root = new TreeNode(node);
                        root.left = left;
                        trees.add(root);
                    }
                }
            } else {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(node);
                    root.right = right;
                    trees.add(root);
                }
            }
        }
        return trees;
    }

    private List<Integer> getLeft(int node, List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (int i : list) {
            if (i < node) {
                result.add(i);
            }
        }
        return result;
    }

    private List<Integer> getRight(int node, List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (int i : list) {
            if (i > node) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 讨论区里的解法，递归式设计得太精妙了，省去了getLeft/getRight的左右区间操作，省去了左右子树的判空操作。。。
     * 写得差的代码，果然是各种漏洞，各种填补边界值的情况。。。
     *
     * 讨论区里的另外一种dp的解法也不错，效率应该更高。不过这个解法理解起来更直观。
     * @param n
     * @return
     */
    public List<TreeNode> generateTreesV2(int n) {
        return genTrees(1, n);
    }

    private List<TreeNode> genTrees(int s, int e) {
        List<TreeNode> result = new ArrayList<>();
        if (s > e) {
            result.add(null);
            return result;
        }
        for (int i = s; i <= e; i++) {
            List<TreeNode> lefts = genTrees(s, i - 1);
            List<TreeNode> rights = genTrees(i + 1, e);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }

    @Test
    public void test() {
        List<TreeNode> trees = solution.generateTrees(4);
        Assert.assertEquals(14, trees.size());
    }
}
