package com.zp.demo.algorithm;

public class LC367有效的完全平方数 {
    public boolean isPerfectSquare(int num) {
        if (num < 2) return true;

        long left = 2, right = num / 2, middle, guessSquare;
        while (left <= right) {
            middle = left + (right - left) / 2;
            guessSquare = middle * middle;
            if (num == guessSquare) {
                return true;
            } else if (guessSquare > num) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return false;
    }
}
