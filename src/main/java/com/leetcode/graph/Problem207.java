package com.leetcode.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 207. Course Schedule
 *
 * @author kufei.dxm
 * @date 2022/6/10
 */
public class Problem207 {
    private Problem207 solution;

    @Before
    public void setUp() {
        solution = new Problem207();
    }

    /**
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            return true;
        }
        Graph graph = new Graph(numCourses);
        for (int[] course : prerequisites) {
            graph.addEdge(new Edge(course[1], course[0]));
        }
        if (!graph.isValid()) {
            return false;
        }
        Set<Integer> topo = graph.topoSort();
        return topo.size() == numCourses;
    }

    class Graph {
        Set<Integer> nodes;
        Set<Edge> edges;

        public Graph(int nodeNum) {
            nodes = new HashSet<>();
            edges = new HashSet<>();
            for (int i = 0; i < nodeNum; i++) {
                nodes.add(i);
            }
        }

        Set<Integer> getZeroInNodes() {
            Set<Integer> tmp = new HashSet<>(nodes);
            for (Edge edge : edges) {
                if (edge.getEnd() != null) {
                    tmp.remove(edge.getEnd());
                }
            }
            return tmp;
        }

        /**
         * @return
         */
        Set<Integer> topoSort() {
            Set<Integer> result = new HashSet<>();
            Set<Integer> startNodes = getZeroInNodes();
            Deque<Integer> deque = new ArrayDeque<>();
            for (Integer node : startNodes) {
                deque.offer(node);
            }
            while (!deque.isEmpty()) {
                Integer node = deque.poll();
                result.add(node);
                List<Edge> edges = getEdges(node);
                for (Edge edge : edges) {
                    if (!result.contains(edge.getEnd())) {
                        deque.offer(edge.getEnd());
                    }
                }
            }
            return result;
        }

        List<Edge> getEdges(Integer node) {
            List<Edge> result = new ArrayList<>();
            for (Edge edge : edges) {
                if (edge.getStart().equals(node)) {
                    result.add(edge);
                }
            }
            return result;
        }

        boolean isValid() {
            if (edges.isEmpty() || nodes.isEmpty()) {
                return true;
            }
            Set<Integer> zeroInNodes = getZeroInNodes();
            if (zeroInNodes.isEmpty()) {
                return false;
            }

            for (Integer node : zeroInNodes) {
                if (!DFS(node, new ArrayList<>())) {
                    return false;
                }
            }
            return true;
        }

        boolean DFS(Integer node, List<Integer> path) {
            if (path.contains(node) && path.lastIndexOf(node) != path.size() - 1) {
                return false;
            }
            List<Edge> edges = getEdges(node);
            if (edges.size() == 0) {
                return true;
            }
            for (Edge edge : edges) {
                path.add(node);
                if (!DFS(edge.getEnd(), path)) {
                    return false;
                }
                path.remove(path.size() - 1);
            }
            return true;
        }

        void addEdge(Edge edge) {
            if (edge.getEnd() != null) {
                nodes.add(edge.getEnd());
            }
            if (edge.getStart() != null) {
                nodes.add(edge.getStart());
            }
            edges.add(edge);
        }
    }

    class Edge {
        //start node
        Integer start;
        //end node
        Integer end;

        public Edge(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }

        public Integer getEnd() {
            return end;
        }

        public Integer getStart() {
            return start;
        }
    }

    /**
     * BFS。如果能完成图的拓扑排序，说明图是有效的。有环的情况下，组成环的节点的入度没法减到0，也就找不到起始的点了。
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinishV2(int numCourses, int[][] prerequisites) {
        //节点入度
        int[] incomingEdges = new int[numCourses];
        //邻接表
        List<Integer>[] adjLink = new List[numCourses];
        for (int i = 0; i < adjLink.length; i++) {
            adjLink[i] = new LinkedList<>();
        }
        for (int[] pair : prerequisites) {
            incomingEdges[pair[0]]++;
            adjLink[pair[1]].add(pair[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < incomingEdges.length; i++) {
            if (incomingEdges[i] == 0) {
                queue.add(i);
            }
        }
        Set<Integer> result = new HashSet<>();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result.add(cur);
            for (int goCrs : adjLink[cur]) {
                //节点指向的所有节点的入度减1
                if (--incomingEdges[goCrs] == 0) {
                    queue.add(goCrs);
                }
            }
        }
        return result.size() == numCourses;
    }

    @Test
    public void test() {
        int[][] pre = new int[][] {{1, 0}, {1, 2}, {0, 1}};
        boolean can = solution.canFinishV2(3, pre);
        Assert.assertFalse(can);
    }
}
