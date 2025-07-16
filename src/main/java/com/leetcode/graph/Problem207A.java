package com.leetcode.graph;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * @Author dxm
 * @Date 2025/7/16
 */
public class Problem207A {
    private Problem207A solution;

    class DagGraph {
        private Set<Integer> vertexes; //点集
        private Set<Edge> edges;
    }

    class Edge {
        private Integer start;//起点
        private Integer end;

        public Edge(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }
    }

    @Before
    public void setUp() {
        solution = new Problem207A();
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //一个顶点的入度是指以其为终点的边数
        int[] inComingsDegrees = new int[numCourses];
        List<Integer>[] ajLink = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            ajLink[i] = new ArrayList<>();
        }
        for (int[] pair : prerequisites) {
            int end = pair[0];
            int start = pair[1];
            inComingsDegrees[end]++;
            ajLink[start].add(end);
        }
        //拓扑排序
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < inComingsDegrees.length; i++) {
            if (inComingsDegrees[i] == 0) {
                queue.add(i);
            }
        }
        Set<Integer> result = new HashSet<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            for (int endNode : ajLink[node]) {
                if (--inComingsDegrees[endNode] == 0) {
                    queue.add(endNode);
                }
            }
        }
        return result.size() == numCourses;
    }

    @Test
    public void test() {

    }

}
