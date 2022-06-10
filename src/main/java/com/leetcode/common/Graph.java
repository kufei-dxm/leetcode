package com.leetcode.common;

import java.util.Set;

/**
 * @author kufei.dxm
 * @date 2022/6/10
 */
public class Graph {
    private Set<Vertix> nodes;
    private Set<Edge> edges;


    public Set<Vertix> getNodes() {
        return nodes;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    static class Vertix {
        private int val;

        public int getVal() {
            return val;
        }

        public Vertix(int val) {
            this.val = val;
        }
    }

    static class Edge {
        private Vertix in;
        private Vertix out;

        public Vertix getIn() {
            return in;
        }

        public Vertix getOut() {
            return out;
        }

        public Edge(Vertix in, Vertix out) {
            this.in = in;
            this.out = out;
        }
    }
}
