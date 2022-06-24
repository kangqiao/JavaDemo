package com.zp.demo.algorithm;

public class LC1791_找出星型图的中心节点 {

    public static int findCenter(int[][] edges) {
        int n = edges.length + 1;
        int[] degrees = new int[n + 1];
        for (int[] edge : edges) {
            degrees[edge[0]]++;
            degrees[edge[1]]++;
        }
        for (int i = 1; ; i++) {
            if (degrees[i] == n - 1) {
                return i;
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = {{1,2},{2,3},{4,2}};
        System.out.println(findCenter(edges));
        int[][] edges2 = {{1,2},{5,1},{1,3},{1,4}};
        System.out.println(findCenter(edges2));
    }
}
