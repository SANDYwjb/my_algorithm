package com.junbin.algorithm.second.twenty;

/**
 * 4. 寻找两个正序数组的中位数-困难
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 算法的时间复杂度应该为 O(log (m+n)) 。有log的存在，一般都不能整体遍历，只能是二分查询
 * <p>
 * 二分法求解
 * 这个方案的思路是遮掩的，两个数组 nums1 和 nums2 的长度已经确定了，那么中位数的的位置肯定是 (nums1.length + nums2.length) / 2 ，
 * 假设长度正好是奇数哈，如果定义刚才那个位置是 k 的话，这个问题就可以成功的转化成另一个问题，如何在两个有序数组中寻找第 k 小的数字。
 * <p>
 * 可以分别为两个数组定义两个指针，分别指向两个数组开头的位置，然后移动两个指针，直到两个指针总共第 k 次移动，那么我们就找到了第 k 小的数字。
 * 这种方案相当于还是要循环两个数组，接着想办法减少循环次数。
 * <p>
 * 那么就不能是使用循环了，这里就用到了 「二分法」 。
 * 两个数组 nums1 和 nums2 ，先取 nums1[k/2 - 1] 记为 pivot1 和 nums2[k/2 - 1] 记为 pivot2 。
 * 接着取 pivot = min(pivot1, pivot2) ，那么这时两个数组中小于等于 pivot 的最多不会超过 k - 2 个。
 * 这时 pivot 最大也可能是第 k-1 小的元素。
 * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
 * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
 * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
 * 这样 pivot 本身最大也只能是第 k-1 小的元素
 * 如果 pivot = pivot1 ，那么在 nums1 中，从 0 到 k / 2 - 1 都不会是第 k 个元素，把这些元素全部 "删除"，剩下的作为新的 nums1 数组。
 * 如果 pivot = pivot2 ，那么在 nums2 中，从 0 到 k / 2 - 1 都不会是第 k 个元素，把这些元素全部 "删除"，剩下的作为新的 nums2 数组。
 * 由于前面删除了一些元素，这时我们需要修改 k 的值，减去我们删掉的元素。
 * 重复上面的过程，就可以找到第 k 个元素了。
 * <p>
 * 还不懂的话，看这篇讲解
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/solutions/258842/xun-zhao-liang-ge-you-xu-shu-zu-de-zhong-wei-s-114/
 *
 * @author junbin.wang
 * @date 2023/2/17上午8:32
 */
public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        // 奇数的话，中位数只有一个
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            // 这里为什么要midIndex加1，---因为k代表第k小，不加1实际是代表下标从0开始，加了1才能代表从1开始的现实意义，所以下面当下标时就要减1
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        }
        // 偶数的话，中位数有2个，k-1和k
        else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    public int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取  和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */
        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        while (true) {
            // 边界情况 如果一个数组为空，说明该数组中的所有元素都被排除，我们可以直接返回另一个数组中第 k 小的元素。
            // index表示前面的都已经被排除了，所以下面取k小的也要加上index2
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            // 其实就是pivot2 = nums2[k/2-1]，取min是怕越界
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                // 排除掉前面不符合的元素
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }
}
