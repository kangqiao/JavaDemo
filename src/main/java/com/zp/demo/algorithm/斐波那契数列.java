package com.zp.demo.algorithm;

import java.util.HashMap;
import java.util.Map;

public class 斐波那契数列 {
    public int fib(int n) {
        if (n < 2) return n;

        int f0 = 0, f1 = 1, sum;
        for (int i = 2; i < n; i++) {
            sum = (f0 + f1)  % 1000000007;
            f0 = f1;
            f1 = sum;
        }

        return f0;
    }

    public int fib2(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        return fib2(n, map);

    }

    private int fib2(int n, Map<Integer, Integer> map) {
        if (n <= 1) return n;

        if (!map.containsKey(n)) {
           map.put(n, (fib2(n-1, map) + fib2(n-2, map)) % 1000000007);
        }

        return map.get(n);
    }

    public int fibonacci(int n, int[] memo) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        if (memo[n] == 0) {
            memo[n] = fibonacci(n - 1, memo) + fibonacci(n - 2, memo);
        }
        return memo[n];
    }

    public int fibonacciAdvance(int n) {
        return fibonacci(n, new int[n + 1]);
    }
}
