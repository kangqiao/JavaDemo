package com.zp.demo.algorithm;

public class 硬币找零 {


    //递归+回溯.
    int getMinCoinCountOfValue(int total, int[] values, int valueIndex) {
        int valueCount = values.length;
        if (valueIndex == valueCount) { return Integer.MAX_VALUE; }

        int minResult = Integer.MAX_VALUE;
        int currentValue = values[valueIndex];
        int maxCount = total / currentValue;

        for (int count = maxCount; count >= 0; count --) {
            int rest = total - count * currentValue;

            // 如果rest为0，表示余额已除尽，组合完成
            if (rest == 0) {
                minResult = Math.min(minResult, count);
                break;
            }

            // 否则尝试用剩余面值求当前余额的硬币总数
            int restCount = getMinCoinCountOfValue(rest, values, valueIndex + 1);

            // 如果后续没有可用组合
            if (restCount == Integer.MAX_VALUE) {
                // 如果当前面值已经为0，返回-1表示尝试失败
                if (count == 0) { break; }
                // 否则尝试把当前面值-1
                continue;
            }

            minResult = Math.min(minResult, count + restCount);
        }

        return minResult;
    }

    int getMinCoinCountLoop(int total, int[] values, int k) {
        int minCount = Integer.MAX_VALUE;
        int valueCount = values.length;

        if (k == valueCount) {
            return Math.min(minCount, getMinCoinCountOfValue(total, values, 0));
        }

        for (int i = k; i <= valueCount - 1; i++) {
            // k位置已经排列好
            int t = values[k];
            values[k] = values[i];
            values[i]=t;
            minCount = Math.min(minCount, getMinCoinCountLoop(total, values, k + 1)); // 考虑后一位

            // 回溯
            t = values[k];
            values[k] = values[i];
            values[i]=t;
        }

        return minCount;
    }

    int getMinCoinCountOfValue() {
        int[] values = { 5, 3 }; // 硬币面值
        int total = 11; // 总价
        int minCoin = getMinCoinCountLoop(total, values, 0);

        return (minCoin == Integer.MAX_VALUE) ? -1 : minCoin;  // 输出答案
    }


    //递归
    int getMinCountsHelper(int total, int[] values) {
        // 如果余额为0，说明当前组合成立，将组合加入到待选数组中
        if (0 == total) { return 0; }

        int valueLength = values.length;
        int minCount = Integer.MAX_VALUE;
        for (int i = 0;  i < valueLength; i ++) { // 遍历所有面值
            int currentValue = values[i];

            // 如果当前面值大于硬币总额，那么跳过
            if (currentValue > total) { continue; }

            int rest = total - currentValue; // 使用当前面值，得到剩余硬币总额
            int restCount = getMinCountsHelper(rest, values);

            // 如果返回-1，说明组合不可信，跳过
            if (restCount == -1) { continue; }

            int totalCount = 1 + restCount; // 保留最小总额
            if (totalCount < minCount) { minCount = totalCount; }
        }

        // 如果没有可用组合，返回-1
        if (minCount == Integer.MAX_VALUE) { return -1; }

        return minCount; // 返回最小硬币数量
    }


    public static void main(String[] args) {
        int[] values = { 3, 5 }; // 硬币面值的数组
        int total = 11; // 总值
        int ret = new 硬币找零().getMinCountsHelper(total, values);
        System.out.println(ret);
    }
}
