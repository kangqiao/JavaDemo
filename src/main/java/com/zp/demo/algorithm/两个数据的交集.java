package com.zp.demo.algorithm;

import java.util.*;

public class 两个数据的交集 {

    public static void main(String[] args) {
        //outOfOrder();
        order();
    }

    private static void order() {
        int[] num1 = new int[] {1,2,3,4,4,13};
        int[] num2 = new int[] {1,2,3,9,10};
        int[] ret = intersectOrder(num1, num2);
        for (int i=0; i<ret.length; i++) {
            System.out.print(ret[i] + " ");
        }
        System.out.println();
        for (int i=0; i<num1.length; i++) {
            System.out.print(num1[i] + " ");
        }
        System.out.println();
        for (int i=0; i<num2.length; i++) {
            System.out.print(num2[i] + " ");
        }
    }

    private static int[] intersectOrder(int[] num1, int[] num2) {
        int k = 0;
        for (int m = 0, n = 0; m<num1.length && n<num2.length; ) {
           if (num1[m] > num2[n]) {
               n++;
           } else if (num1[m] < num2[n]) {
               m++;
           } else {
               num1[k++] = num1[m];
               m++;
               n++;
           }
        }

        return Arrays.copyOf(num1, k);
    }

    private static void outOfOrder() {
        int[] num1 = new int[] {4,9,5};
        int[] num2 = new int[] {9,4,9,8,4};
        int[] ret = intersect(num1, num2);
        for (int i=0; i<ret.length; i++) {
            System.out.print(ret[i] + " ");
        }
    }

    public static int[] intersect(int[] num1, int[] num2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n: num1) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        int k = 0;
        for (int n: num2) {
            int v = map.getOrDefault(n, 0);
            if (v > 0) {
                map.put(n, v-1);
                num2[k] = n;
                k++;
            }
        }
        return Arrays.copyOf(num2, k);
    }
}
