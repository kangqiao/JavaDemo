package com.zp.demo.algorithm.simple;

import java.util.*;

public class _4_4BFS暴力破解智力题 {
    public static void main(String[] args) {
        _4_4BFS暴力破解智力题 instance = new _4_4BFS暴力破解智力题();
        System.out.println(instance.slidingPuzzle(new int[][]{
                {2, 4, 1},
                {5, 0, 3},
        }));
        System.out.println("===========================");
        System.out.println(instance.slidingPuzzle(new int[][]{
                {4, 1, 2},
                {5, 0, 3},
        }));
    }

    private static void print(List<List<Integer>> list) {
        for (List<Integer> l: list) {
            System.out.println(l.toString());
        }
    }

    private int slidingPuzzle(int[][] board) {
        int m = board.length, n = board[0].length;
        StringBuilder startSb = new StringBuilder();
        StringBuilder targetSb = new StringBuilder();
        int k = 0;
        List<List<Integer>> neighbor = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                startSb.append(board[i][j]);
                if (k < m*n -1) targetSb.append(++k);

                List<Integer> list = new ArrayList<>();
                //上
                if (i > 0) list.add((i - 1)*n + j);
                //左
                if (j > 0) list.add(n * i + j - 1);
                //下
                if (i < m - 1) list.add((i + 1)*n + j);
                //右
                if (j < n - 1) list.add(n * i + j + 1);
                neighbor.add(list);
            }
        }
        String start = startSb.toString();
        targetSb.append('0');
        String target = targetSb.toString();
        print(neighbor);
        System.out.println(start);
        System.out.println(target);

        ArrayDeque<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        q.push(start);
        visited.add(start);

        neighbor.clear();
        List<Integer> list = new ArrayList<>();
        list.add(1);list.add(3);
        neighbor.add(list);

        list = new ArrayList<>();
        list.add(0);list.add(4);list.add(2);
        neighbor.add(list);

        list = new ArrayList<>();
        list.add(1);list.add(5);
        neighbor.add(list);

        list = new ArrayList<>();
        list.add(0);list.add(4);
        neighbor.add(list);

        list = new ArrayList<>();
        list.add(3);list.add(1);list.add(5);
        neighbor.add(list);

        list = new ArrayList<>();
        list.add(4);list.add(2);
        neighbor.add(list);

        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int i=0; i<sz; i++) {
                String cur = q.pop();
                //判断是否达到目标局面
                if (target.equals(cur)) return step;

                //找到数字0的索引
                int idx = 0;
                for (; cur.charAt(idx) != '0'; idx++);
                //将数字0和相邻的数字交换位置
                for (int adj: neighbor.get(idx)) {
                    char[] chArr = cur.toCharArray();
                    swap(chArr, adj, idx);
                    String newBoard = new String(chArr);
                    //防止走回头路
                    if (!visited.contains(newBoard)) {
                        q.push(newBoard);
                        visited.add(newBoard);
                    }
                }
            }
            step++;
        }

        return -1;
    }

    private void swap(char[] newBoard, int adj, int idx) {
        char tmp = newBoard[adj];
        newBoard[adj] = newBoard[idx];
        newBoard[idx] = tmp;
    }
}

