package com.zp.demo.algorithm;//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i,
//ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 说明：你不能倾斜容器，且 n 的值至少为 2。 
//
// 
//
// 
//
// 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 
//
// 示例： 
//
// 输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
// Related Topics 数组 双指针


//leetcode submit region begin(Prohibit modification and deletion)
class MaxArea {
    public int maxArea(int[] height) {
        if (null == height) {
            return 0;
        }
        if (height.length < 2) {
            return height[0];
        }
        int i = 0;
        int j = height.length - 1;
        int maxArea = Math.min(height[i], height[j]) * (j - i);
        do {
            if (height[i] < height[j]) {
                if (height[i] < height[++i]) {
                    maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
                }
            } else {
                if (height[j] < height[--j]) {
                    maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
                }
            }
        } while (i < j);
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = new int[]{2,3,4,5,18,17,6};
        System.out.println(new MaxArea().maxArea(heights));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
