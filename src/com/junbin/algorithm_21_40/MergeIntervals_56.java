package com.junbin.algorithm_21_40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. 合并区间-中等
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，
 * 并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 画图可知，可以被合并的区间一定是有交集的区间，前提是区间按照左端点排好序，这里的交集可以是一个点。
 * 那么只需要对所有的区间按照左端点升序排序，然后遍历。
 * 如果当前遍历到的区间的左端点 > 结果集中最后一个区间的右端点，说明它们没有交集，此时把区间添加到结果集；
 * 如果当前遍历到的区间的左端点 <= 结果集中最后一个区间的右端点，说明它们有交集，此时产生合并操作，即：对结果集中最后一个区间的右端点更新（取两个区间的最大值）。
 * 就是说我们可以这么想：只需要假设 第一个区间的起始位置 ≤ 第二个区间的起始位置，如果不满足这个假设，交换这两个区间
 * <p>
 * ----看不懂可以看微信公众号收藏的文章
 *
 * @author junbin.wang
 * @date 2023/2/17上午8:49
 */
public class MergeIntervals_56 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
