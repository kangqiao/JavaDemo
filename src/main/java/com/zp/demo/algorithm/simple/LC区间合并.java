package com.zp.demo.algorithm.simple;

import com.zp.demo.Zlog;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://labuladong.gitbook.io/algo/bi-du-wen-zhang/qu-jian-wen-ti-he-ji
 * 所谓区间问题，就是线段问题，让你合并所有线段、找出线段的交集等等。主要有两个技巧：
 * 1、排序。常见的排序方法就是按照区间起点排序，或者先按照起点升序排序，若起点相同，则按照终点降序排序。当然，如果你非要按照终点排序，无非对称操作，本质都是一样的。
 * 2、画图。就是说不要偷懒，勤动手，两个区间的相对位置到底有几种可能，不同的相对位置我们的代码应该怎么去处理。
 */
public class LC区间合并 {

    /**
     * 1288. 删除被覆盖区间
     * 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
     * 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
     * 在完成所有删除操作后，请你返回列表中剩余区间的数目。
     *
     * 示例：
     * 输入：intervals = [[1,4],[3,6],[2,8]]
     * 输出：2
     * 解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
     * @param intervals
     * @return
     */
    public int removeCoveredIntervals(int[][] intervals) {
        if (null == intervals || intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });

        int left = intervals[0][0];
        int right = intervals[0][1];
        int res = 0;
        for (int i=1; i<intervals.length; i++) {
            int[] interval = intervals[i];
            //情况1: 找到覆盖区间
            if (left <= interval[0] && interval[1] <= right) {
                res++;
            }
            //情况2: 找到相交区间，合并
            if (interval[0] <= right && right <= interval[1]) {
                right = interval[1];
            }
            //情况3: 完全不相交，更新起点和终点
            if (right < interval[1]) {
                left = interval[0];
                right = interval[1];
            }
        }
        return intervals.length - res;
    }

    /**
     * 56. 合并区间
     * 给出一个区间的集合，请合并所有重叠的区间。
     *
     * 示例 1:
     * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     *
     * 示例 2:
     * 输入: intervals = [[1,4],[4,5]]
     * 输出: [[1,5]]
     * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
     *
     * 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        // 按照起点升序排列，起点相同时降序排列
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });
        int left = intervals[0][0];
        int right = intervals[0][1];
        ArrayList<int[]> res = new ArrayList<>();
        //res.add(intervals[0]);
        for (int i=1; i<intervals.length; i++) {
            int[] inter = intervals[i];
            //情况1: 找到覆盖区间 => 删除
            if (left <= inter[0] && inter[1] <= right) {
                res.add(new int[]{left, right});
                continue;
            }
            //情况2: 找到相交区间，合并
            if (inter[0] <= right && right <= inter[1]) {
                res.add(new int[]{left, inter[1]});
                continue;
            }
            //情况3: 完全不相交，更新起点和终点
            if (right < inter[0]) {
                left = inter[0];
                right = inter[1];
                res.add(inter);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    /**
     * 986. 区间列表的交集
     * 给定两个由一些 闭区间 组成的列表，firstList 和 secondList ，
     * 其中 firstList[i] = [starti, endi] 而 secondList[j] = [startj, endj] 。每个区间列表都是成对 不相交 的，并且 已经排序 。
     * 返回这 两个区间列表的交集 。
     * 形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b 。
     * 两个闭区间的 交集 是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3] 。
     *
     * 示例 1：
     * 输入：firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
     * 输出：[[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     * @param firstList
     * @param secondList
     * @return
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int i = 0, j = 0;
        ArrayList<int[]> res = new ArrayList<>();
        while (i < firstList.length && j < secondList.length) {
            int left = Math.max(firstList[i][0], secondList[j][0]);
            int right = Math.min(firstList[i][1], secondList[j][1]);
            if (left <= right) {
                res.add(new int[]{left, right});
            }
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }

            /*
            int[] first = firstList[i];
            int[] second = secondList[j];
            //不相交
            if (first[0] > second[1]) {
                j++;
            } else if (second[0] > first[1]) {
                i++;
            } else {
                // 相交
                res.add(new int[]{Math.max(first[0], second[0]), Math.min(first[1], second[1])});
                if (first[1] < second[1]) {
                    i++; //second区间保留参与下次比较
                } else if (first[1] > second[1]) {
                    j++; //first区间保留参与下次比较
                } else {
                    i++; j++; //first和second区间均相略过
                }
            }
            */
        }
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        LC区间合并 instance = new LC区间合并();


        Zlog.log("==== 1288. 删除被覆盖区间===============");
        int[][] intervals = new int[][] {{1,4}, {3,6},{2,8}};
        int res = instance.removeCoveredIntervals(intervals);
        Zlog.log(toString(intervals) + " removeCoveredIntervals => " + res);

        Zlog.log("==== 56. 合并区间===============");
        intervals = new int[][]{{1,3},{2,6},{8,10},{15,18}};
        int[][] ret2 = instance.merge(intervals);
        Zlog.log(toString(intervals) + " merge => " + toString(ret2));


        Zlog.log("==== 986. 区间列表的交集===============");
        int[][] firstList = {{0,2},{5,10},{13,23},{24,25}};
        int[][] secondList = {{1,5},{8,12},{15,24},{25,26}};
        //输出：{{1,2},{5,5},{8,10},{15,23},{24,24},{25,25}}
        int[][] ret3 = instance.intervalIntersection(firstList, secondList);
        Zlog.log(toString(firstList) + " and \n" + toString(secondList) + " intervalIntersection => \n" + toString(ret3));

    }

    private static String toString(int[][] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int[] a: arr) {
            sb.append("[");
            for (int i=0; i<a.length-1; i++) {
                sb.append(a[i]).append(", ");
            }
            sb.append(a[a.length-1]).append("]");
        }
        return sb.append("]").toString();
    }
}
