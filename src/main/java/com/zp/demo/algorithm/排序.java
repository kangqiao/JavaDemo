package com.zp.demo.algorithm;

public class 排序 {
    public static void main(String[] args) {
        int[] nums = new int[] {4, 5, 3, 1, 2};
        //bubbleSort(nums);
        //selectSort(nums);
        insertSort(nums);
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


    private static void swap(int[] nums, int m, int n) {
        if (nums != null && m < nums.length && n < nums.length) {
            int t = nums[m];
            nums[m] = nums[n];
            nums[n] = t;
        }
    }
}
