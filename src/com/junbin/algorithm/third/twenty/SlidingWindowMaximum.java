package com.junbin.algorithm.third.twenty;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 239. 滑动窗口最大值-困难
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------             -----
 * [1  3  -1] -3  5  3  6  7      3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * 思路：
 * 我们可以想到，对于两个相邻（只差了一个位置）的滑动窗口，它们共用着 k−1 个元素，而只有 1 个元素是变化的。我们可以根据这个特点进行优化。
 * 由于我们需要求出的是滑动窗口的最大值，如果当前的滑动窗口中有两个下标 i 和 j，其中 i 在 j 的左侧（i<j），并且 i 对应的元素不大于 j 对应的元素
 * （nums[i]≤nums[j]），那么会发生什么呢？
 * 1. 当滑动窗口向右移动时，只要 i 还在窗口中，那么 j 一定也还在窗口中，这是 i 在 j 的左侧所保证的。因此，由于 nums[j] 的存在，
 * nums[i] 一定不会是滑动窗口中的最大值了，我们可以将 nums[i]永久地移除。
 * 2. 因此我们可以使用一个队列存储所有还没有被移除的下标。在队列中，这些下标按照从小到大的顺序被存储，并且它们在数组 nums中对应的值是严格单调递减的。
 * 因为如果队列中有两个相邻的下标，它们对应的值相等或者递增，那么令前者为 i，后者为 j，就对应了上面所说的情况，即 nums[i] 会被移除，这就产生了矛盾。
 * 3. 当滑动窗口向右移动时，我们需要把一个新的元素放入队列中。为了保持队列的性质，我们会不断地将新的元素与队尾的元素相比较，如果前者大于等于后者，
 * 那么队尾的元素就可以被永久地移除，我们将其弹出队列。我们需要不断地进行此项操作，直到队列为空或者新的元素小于队尾的元素。
 * 4. 由于队列中下标对应的元素是严格单调递减的，因此此时队首下标对应的元素就是滑动窗口中的最大值。但是，此时的最大值可能在滑动窗口左边界的左侧，
 * 并且随着窗口向右移动，它永远不可能出现在滑动窗口中了。因此我们还需要不断从队首弹出元素，直到队首元素在窗口中为止。
 * 5. 为了可以同时弹出队首和队尾的元素，我们需要使用双端队列。满足这种单调性的双端队列一般称作「单调队列」。
 * <p>
 * 时间复杂度：O(n)
 * 空间复杂度：O(k)
 *
 * @author junbin.wang
 * @date 2023/2/18下午9:42
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<Integer>();
        // 遍历第一个窗口
        for (int i = 0; i < k; ++i) {
            // 当前值大于等于队尾的值，对尾元素就要被剔除
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        // 窗口的数量为 n−k+1
        int[] ans = new int[n - k + 1];
        // 队列单调递减，队首值就是当前窗口的最大值
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // 移除已经在当前窗口左侧的元素
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            // 队首值就是当前窗口的最大值，i+1，就代表前进一个窗口
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}