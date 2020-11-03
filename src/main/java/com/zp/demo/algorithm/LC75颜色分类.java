package com.zp.demo.algorithm;

public class LC75颜色分类 {

    public static void main(String[] args) {
        //int[] nums = new int[] {0,2,1,2,0,2,1,1,0};
        int[] nums = new int[] {2,0,2,1,1,0};
        //int[] nums = new int[] {1,2,0,2,1,0};
        sortColors(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
        }
    }

    public static void sortColors(int[] nums) {
        int a = 0;
        int b = nums.length - 1;
        int c = 0;
        while (c <= b) {
            if (nums[c] == 0) {
                int t = nums[c];
                nums[c] = nums[a];
                nums[a] = t;
                a++;
                c++;
            } else if (nums[c] == 2) {
                int t = nums[b];
                nums[b] = nums[c];
                nums[c] = t;
                b--;
            } else {
                c++;
            }
        }
    }
}
