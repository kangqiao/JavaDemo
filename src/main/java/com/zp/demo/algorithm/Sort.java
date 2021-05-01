package com.zp.demo.algorithm;

public class Sort {

    public void insert_sort(int[] nums) {
        for (int i=1; i<nums.length; i++) {
            for (int j=i; j>0; j--) {
                if(nums[j] < nums[j-1]) {
                    int t = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = t;
                }
            }
        }
    }

    public void insertSort(int[] nums) {
        for (int i=1; i<nums.length; i++) {
            int t = nums[i];
            int j = i;
            for (; j>0; j--) {
                if (nums[j] < nums[j-1]) {
                    nums[j] = nums[j-1];
                } else {
                    break;
                }
            }
            nums[j] = t;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,2,6,8,9,1,7,3};
        quickSort(nums, 0, nums.length-1);
        for (int i=0; i<nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static void quickSort(int[] nums, int l, int r) {
        int i = l, j = r, t = nums[l];
        while (i < j) {
            while (i < j && nums[j] > t) j--;
            if (i < j) nums[i++] = nums[j];
            while (i < j && nums[i] < t) i++;
            if (i < j) nums[j--] = nums[i];
        }
        nums[i] = t;
        if (l < i-1) quickSort(nums, l, i - 1);
        if (r > i+1) quickSort(nums, i + 1, r);
    }

    private static void swap(int[] nums, int i, int j) {
        if (i == j || i >= nums.length || j >= nums.length) return;
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
