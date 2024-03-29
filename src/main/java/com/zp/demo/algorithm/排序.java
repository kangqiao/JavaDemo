package com.zp.demo.algorithm;

public class 排序 {
    public static void main(String[] args) {
        //int[] nums = new int[] {4, 5, 3, 1, 2};
        int[] nums = new int[] {0, 4, 5, 6, 8, 3, 7, 1, 2, 9};
        //bubbleSort(nums);
        //selectSort(nums);
        //insertSort(nums);
        quickSort(nums, 0, nums.length -1);
        for (int i=0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    //插入排序 稳定 O(n^2)
    public static void insertSort(int[] nums) {
        int preIndex;
        int current;
        for (int i=1; i<nums.length; i++) {
            preIndex = i - 1;
            current = nums[i];
            while (preIndex >= 0 && nums[preIndex] > current) {
                nums[preIndex + 1] = nums[preIndex];
                preIndex--;
            }
            nums[preIndex + 1] = current;
        }
    }

    //冒泡排序 稳定 O(n^2)
    public static void bubbleSort(int[] nums) {
        for (int i=0; i<nums.length - 1; i++) {
            boolean isSwap = false;
            for (int j=1; j<nums.length-i; j++) {
                if (nums[j] < nums[j-1]) {
                    isSwap = true;
                    swap(nums, j, j-1);
                }
            }
            if (!isSwap) break;
        }
    }

    //选择排序 不稳定 O(n^2)
    public static void selectSort(int[] nums) {
        for (int i=0; i<nums.length - 1; i++) {
            int minIndex = i;
            for (int j=i+1; j<nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(nums, minIndex, i);
            }
        }
    }

    //快速排序 O(nlog2n)
    //{0, 4, 5, 6, 8, 3, 7, 1, 2, 9}
    public static void quickSort(int[] nums, int l, int r) {
        int i=l, j=r, t=nums[l];
        while (i < j) {
            while (i < j && nums[j] > t) j--; //从右侧开始: 如果发现大于锚点t的数据, 结束循环, 将其存到锚点原来的位置i
            if (i<j) nums[i++] = nums[j];
            while (i < j && nums[i] < t) i++; //从左侧开始: 如果发现小于锚点t的数据, 结束循环, 将其存到右侧移入锚点中数据的位置中
            if (i<j) nums[j--] = nums[i];
        }
        nums[i] = t;
        if (l < i-1) quickSort(nums, l, i - 1);
        if (r > i+1) quickSort(nums, i + 1, r);
    }


    private static void swap(int[] nums, int m, int n) {
        if (nums != null && m < nums.length && n < nums.length) {
            int t = nums[m];
            nums[m] = nums[n];
            nums[n] = t;
        }
    }
}
