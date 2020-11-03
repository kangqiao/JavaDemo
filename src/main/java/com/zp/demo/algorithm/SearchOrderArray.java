package com.zp.test.leetcode;

//假设按照升序排序的数组在预先未知的某个点上进行了旋转。
//
// ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
//
// 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
//
// 你可以假设数组中不存在重复的元素。
//
// 你的算法时间复杂度必须是 O(log n) 级别。
//
// 示例 1:
//
// 输入: nums = [4,5,6,7,0,1,2], target = 0
//输出: 4
//
//
// 示例 2:
//
// 输入: nums = [4,5,6,7,0,1,2], target = 3
//输出: -1
// Related Topics 数组 二分查找
// 👍 832 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class SearchOrderArray {
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l < r) {
            int mid = (l + r) / 2;
            //判断左部分是有序的, 且target不在其中;
            if (nums[0] <= nums[mid] && (target < nums[0] || nums[mid] < target)) {
                l = mid + 1;
            }
            //判断左部分无序时, target不在其中;
            else if (target < nums[0] && target > nums[mid]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l == r && target == nums[l] ? l : -1;
    }

    public static void main(String[] args) {
        int[] nums1 = {4,5,6,7,0,1,2};
        int[] nums2 = {4,5,6,7,0,1,2};
        int[] nums3 = {4,5,6,7,8,1,2,3};
        SearchOrderArray search = new SearchOrderArray();
        System.out.println(search.search(nums1, 0));
        System.out.println(search.search(nums2, 3));
        System.out.println(search.search(nums3, 5));
        System.out.println(search.search(nums3, 8));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
