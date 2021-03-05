package com.zp.demo.algorithm.dp;

/**
 * 72. 编辑距离
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 *
 * 示例 1：
 *
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2：
 *
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 */
public class LC72编辑距离 {

    /**
     * val 记录到当前的操作次数;
     * choice 记录这一次的选择是什么, 其中:
     * 0 代表什么都不做
     * 1 代表插入
     * 2 代表删除
     * 3 代表替换
     */
    public static class Node {
        public int val;
        public int choice;

        public Node(int val, int choice) {
            this.val = val;
            this.choice = choice;
        }
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];

        for (int i=1; i<=m; i++) {
            dp[i][0] = i;
        }

        for (int j=1; j<=n; j++) {
            dp[0][j] = j;
        }

        for (int i=1; i<=m; i++) {
            for (int j=1; j<=n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = min(
                            dp[i-1][j] + 1,
                            dp[i][j-1] + 1,
                            dp[i-1][j-1] + 1
                    );
                }
            }
        }
        return dp[m][n];
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    private Node minNode(Node a, Node b, Node c) {
        Node res = new Node(a.val, 2);
        if (res.val > b.val) {
            res.val = b.val;
            res.choice = 1;
        }
        if (res.val > c.val) {
            res.val = c.val;
            res.choice = 3;
        }
        return res;
    }

    /**
     * 输出操作步骤
     * 0 代表什么都不做
     * 1 代表插入
     * 2 代表删除
     * 3 代表替换
     */
    public int minDistance2(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        Node[][] dp = new Node[m+1][n+1];
        for (int i=0; i<=m; i++) {
            //将s1转化为s2只需要删除一个字符(因为此时列=0, 表示s2是空串).
            dp[i][0] = new Node(i, 2);
        }
        for (int j=1; j<=n; j++) {
            //将s1转化为s2只需要插入一个字符(因为此时行=0, 表示s1空串).
            dp[0][j] = new Node(j, 1);
        }

        for (int i=1; i<=m; i++) {
            for (int j=1; j<=n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    //如果两个字符相同, 则什么都不需要做; choice=0
                    dp[i][j] = new Node(dp[i-1][j-1].val, 0);
                } else {
                    dp[i][j] = minNode(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]);
                    //将编辑距离加1
                    dp[i][j].val++;
                }
            }
        }

        //根据dp table反推具体的操作过程并打印
        printResult(dp, word1, word2);

        return dp[m][n].val;
    }

    private void printResult(Node[][] dp, String word1, String word2) {
        int rows = dp.length;
        int cols = dp[0].length;
        int i=rows - 1, j=cols-1;
        System.out.println("Change word1="+word1+" to word2="+word2+" : ");
        while (i!=0 && j!=0) {
            char c1 = word1.charAt(i-1);
            char c2 = word2.charAt(j-1);
            int choice = dp[i][j].choice;
            System.out.print("word1["+(i-1)+"]: ");
            switch (choice) {
                case 0: //跳过, 则两个指针同时前进
                    System.out.println("skip '" + c1 + "'");
                    i--;j--;
                    break;
                case 1: //将word2[j]插入word1[i], 则word2指针前进
                    System.out.println("insert '" + c2 + "'");
                    j--;
                    break;
                case 2: //将word1[i]删除, 则word1指针前进
                    System.out.println("delete '" + c1 + "'");
                    i--;
                    break;
                case 3: //将word1[i]替换成word2[j], 则两个指针同时前进
                    System.out.println("replace '" + c1 + "' with '" + c2 + "'");
                    i--;j--;
                    break;
            }
        }
        //如果word1还没有走完, 则剩下的都是需要删除的
        while (i > 0) {
            System.out.print("word1[" + (i-1)+"]: ");
            System.out.println("delete '" + word1.charAt(i-1) + "'");
            i--;
        }
        //如果word2还没有走完, 则剩下的都是需要插入的word1的
        while (j > 0) {
            System.out.print("word1[0]: ");
            System.out.println("insert '" + word2.charAt(j-1) + "'");
            j--;
        }
    }

    public static void main(String[] args) {
        LC72编辑距离 instance = new LC72编辑距离();
        String word1 = "horse", word2 = "ros";
        System.out.println("minDistance: "+instance.minDistance(word1, word2));
        System.out.println("minDistance2: "+instance.minDistance2(word1, word2));

        word1 = "intention";
        word2 = "execution";
        System.out.println("minDistance: "+instance.minDistance(word1, word2));
        System.out.println("minDistance2: "+instance.minDistance2(word1, word2));
    }
}
