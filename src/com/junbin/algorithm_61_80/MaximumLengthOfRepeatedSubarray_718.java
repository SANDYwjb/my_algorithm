package com.junbin.algorithm_61_80;

/**
 * 718. 最长重复子数组-中等
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。子数组在原数组中连续
 * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * 输出：3
 * 解释：长度最长的公共子数组是 [3,2,1] 。
 * 思路：滑动窗口
 * 以 A = [3, 6, 1, 2, 4], B = [7, 1, 2, 9] 为例，它们的最长重复子数组是 [1, 2]，在 A 与 B 中的开始位置不同。
 * 但如果我们知道了开始位置，我们就可以根据它们将 A 和 B 进行「对齐」，即：
 * A = [3, 6, 1, 2, 4]
 * B =    [7, 1, 2, 9]
 * ↑  ↑
 * 此时，最长重复子数组在 A 和 B 中的开始位置相同，我们就可以对这两个数组进行一次遍历，得到子数组的长度。我们可以枚举 A 和 B 所有的对齐方式。
 * 对齐的方式有两类：第一类为 A 不变，B 的首元素与 A 中的某个元素对齐；第二类为 B 不变，A 的首元素与 B 中的某个元素对齐。对于每一种对齐方式，
 * 我们计算它们相对位置相同的重复子数组即可。
 * <p>
 * 时间复杂度： O((N+M)×min⁡(N,M))。
 * 空间复杂度： O(1)。
 *
 * @author junbin.wang
 * @date 2023/2/24上午8:49
 */
public class MaximumLengthOfRepeatedSubarray_718 {
    public int findLength(int[] A, int[] B) {
        int ans = 0;
        int ansT = 0;
        //i表示的滑动的数字范围
        for (int i = 0; i <= A.length + B.length - 2; i++) {
            //以下j先以A的坐标为准
            for (int j = Math.max(0, i + 1 - B.length); j <= Math.min(A.length - 1, i); j++) {
                if (A[j] == B[B.length - 1 - i + j]) {
                    ansT++;
                } else {
                    ansT = 0;
                }
                ans = Math.max(ans, ansT);
            }
            ansT = 0;//注意初始化
        }
        return ans;
    }
}
