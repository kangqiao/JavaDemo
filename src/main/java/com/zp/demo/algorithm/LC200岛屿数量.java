package com.zp.demo.algorithm;

public class LC200岛屿数量 {

    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};

    public static int numIslands(char[][] grid) {
        int count = 0;
        if (grid == null || grid.length == 0) {
            return count;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '0') continue;
                count += sink(grid, i, j);
            }
        }
        return count;
    }

    private static int sink(char[][] grid, int i, int j) {
        if (grid[i][j] == '0') return 0;

        grid[i][j] = '0';

        for (int k = 0; k < dx.length; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[i].length) {
                if (grid[x][y] == '0') continue;
                sink(grid, x, y);
            }
        }
        return 1;
    }


    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        grid = new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.print(numIslands(grid));
    }
}
