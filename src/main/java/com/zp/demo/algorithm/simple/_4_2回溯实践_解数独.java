package com.zp.demo.algorithm.simple;

/**
 *
 */
public class _4_2回溯实践_解数独 {
    public static void main(String[] args) {
        _4_2回溯实践_解数独 instance = new _4_2回溯实践_解数独();
        char[][] board = new char[][]{
                {'.','5','.','.','6','4','7','3','2'},
                {'.','7','.','5','.','.','4','.','8'},
                {'4','.','.','.','.','.','.','6','.'},
                {'.','.','.','.','2','9','.','5','.'},
                {'2','.','4','.','.','7','1','9','.'},
                {'.','.','5','3','.','.','.','.','7'},
                {'.','.','.','.','.','.','.','7','.'},
                {'7','.','.','.','.','.','9','4','6'},
                {'6','.','.','.','.','.','8','.','5'},
        };
        instance.solveSudoku(board);
        print(board);
    }

    private static void print(char[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                sb.append(board[i][j]).append("  ");
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    public void solveSudoku(char[][] board) {
        backtrack(board, 0, 0);
    }

    public boolean backtrack(char[][] board, int i, int j) {
        int m = 9, n = 9;
        if (j == n) {
            //穷举到最后一列的话就换到下一行重新开始
            return backtrack(board, i + 1, 0);
        }

        if (i == m) {
            //找到一个可行解,触发base case
            return true;
        }

        if (board[i][j] != '.') {
            //如果有预设数字,不用我们穷举
            return backtrack(board, i, j + 1);
        }

        for (char ch = '1'; ch <= '9'; ch++) {
            //如果遇到不合法的数字,就跳过
            if (!isValid(board, i, j, ch)) {
                continue;
            }

            board[i][j] = ch;
            //如果找到一个可行解,立即结束
            if (backtrack(board, i, j + 1)) {
                return true;
            }
            board[i][j] = '.';
        }
        //穷举完1-9,依然没有找到可行解
        //需要前面的格子换个数字穷举
        return false;
    }

    private boolean isValid(char[][] board, int r, int c, char n) {
        for (int i = 0; i < 9; i++) {
            //判断行是否存在重复
            if (board[r][i] == n) return false;
            //判断列是否存在重复
            if (board[i][c] == n) return false;
            //判断3*3方框是否存在重复
            if (board[(r / 3) * 3 + i / 3][(c / 3) * 3 + i % 3] == n) return false;
        }
        return true;
    }
}
