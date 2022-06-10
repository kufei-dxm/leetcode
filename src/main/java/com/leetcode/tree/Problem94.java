package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.leetcode.common.TreeNode;
import com.leetcode.common.TreeUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * 94. Binary Tree Inorder Traversal
 * @author kufei.dxm
 * @date 2022/5/26
 */
public class Problem94 {
    private Problem94 solution;

    @Before
    public void setUp() {
        solution = new Problem94();
    }

    /**
     * 递归法。[左子树，节点，右子树]
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (null == root) {
            return result;
        }
        traversal(result, root);
        return result;
    }

    public List<Integer> inorderTraversalV2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            while (null != root) {//左子树
                stack.push(root.left);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val); //节点
            root = root.right; //右子树
        }
        return res;
    }

    /**
     * @param resultList
     * @param node
     */
    public void traversal(List<Integer> resultList, TreeNode node) {
        if (null == node) {
            return;
        }
        traversal(resultList, node.left);
        resultList.add(node.val);
        traversal(resultList, node.right);
    }

    @Test
    public void test() {
        List<Integer> result = new ArrayList<>();
        TreeNode tree = TreeUtils.buildTreeByLevelOrderArray(new int[] {1, 0, 2, 0, 0, 3});
        solution.traversal(result, tree);
        result.forEach(e -> System.out.print(e + " "));
    }
}
