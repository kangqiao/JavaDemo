package com.zp.demo.algorithm.dp;

public class LC651四个键盘 {

    public int maxA(int N) {
        int[] dp = new int[N+1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            //按A键, 就比上一次多一个A
            dp[i] = dp[i-1] + 1;
            System.out.println("-----"+i+"------");
            //按C-V, 穷举按完C-A C-C的时机 ***** j代表从2到i去穷举所有可能的C-A,C-C操作.
            for (int j = 2; j < i; j++) {
                int aaa = dp[i]; //
                //如果此时按完C-A C-C的话, 第i次按键盘时, 剪贴板中的A的数量为dp[j-2];
                //全选并复制dp[j-2], 连续粘贴i-j次; 屏幕上共dp[j-2] * (i-j+1) 个A
                int bbb = dp[j-2] * (i - j + 1); //(i-j+1)
                dp[i] = Math.max(aaa, bbb);
                System.out.println("当i="+i+", j="+j+"时, 使用剪贴板:dp["+(j-2)+"]="+dp[j-2]+"时, 需要连接粘贴(i-j+1)="+(i-j+1)+"次, 结果="+bbb+", 而上一次是"+aaa+", 所以最后max=dp["+i+"]="+dp[i]);
            }
        }
        return dp[N];
    }

    public static void main(String[] args) {
        LC651四个键盘 instance = new LC651四个键盘();
        int N = 0;
        N = 3;
        System.out.println("N = " + N + ", maxA = " + instance.maxA(N) + "\n");

        N = 7;
        System.out.println("N = " + N + ", maxA = " + instance.maxA(N) + "\n");

        N = 9;
        System.out.println("N = " + N + ", maxA = " + instance.maxA(N) + "\n");
    }
}
