package com.zp.demo.algorithm;


import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 移动零 {

    public static void main(String[] args) {
        int[] nums = new int[] {2,0,0,3,12};
        //int[] nums = new int[] {1,132,0,0,0,12};
        new 移动零().moveZeroes(nums);
        for (int i=0; i<nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        Map.Entry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(1, 1);
    }

    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i= 0; i< nums.length; i++) {
            if (nums[i] != 0) {
                if (i != j) {
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j++;
            }
        }
    }

    public void moveZeroes2(int[] nums) {
        int j = -1;
        for (int i= 0; i< nums.length; i++) {
            if (nums[i] == 0) {
                j = i;
            }
        }
    }
}
