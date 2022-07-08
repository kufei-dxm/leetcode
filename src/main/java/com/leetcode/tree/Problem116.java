package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

import com.leetcode.common.Node;
import org.junit.Before;
import org.junit.Test;

/**
 * 116. Populating Next Right Pointers in Each Node
 *
 * @author kufei.dxm
 * @date 2022/6/24
 */
public class Problem116 {
    private Problem116 solution;

    @Before
    public void setUp() {
        solution = new Problem116();
    }

    /**
     * 层序遍历的思路。同一层后置节点是前置节点的next。
     * 此解法大材小用，同样能解117的问题。考虑完美二叉树的特性，讨论区里的解法更精简。
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (null == root) {
            return null;
        }
        Node newRoot = root;
        List<Node> queue = new ArrayList<>();
        queue.add(root);
        levelOrderTraversal(queue);
        return newRoot;
    }

    private void levelOrderTraversal(List<Node> list) {
        if (list.isEmpty()) {
            return;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            Node current = list.get(i);
            Node next = list.get(i + 1);
            current.next = next;
        }
        List<Node> newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Node cur = list.get(i);
            if (null != cur.left) {
                newList.add(cur.left);
            }
            if (null != cur.right) {
                newList.add(cur.right);
            }
        }
        list = newList;
        levelOrderTraversal(list);
    }

    /**
     * 每一层从最左侧的子节点开始串联
     * @param root
     * @return
     */
    public Node connectV2(Node root) {
        Node levelStart = root;
        while (levelStart != null) {
            Node cur = levelStart;
            while (cur != null) {
                if (cur.left != null) {
                    cur.left.next = cur.right;
                }
                if (cur.right != null && cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            levelStart = levelStart.left;
        }
        return root;
    }

    @Test
    public void test() {
        int[] nums = new int[] {1, 2, 3, 4, 5, 6, 7};

    }
}
