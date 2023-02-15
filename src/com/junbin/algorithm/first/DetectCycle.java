package com.junbin.algorithm.first;

import com.junbin.model.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表1
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * **思路---双指针解法**
 * <p>
 * 想象一下，两名运动员以不同的速度在环形赛道上跑步会发生什么？
 * <p>
 * 算法
 * <p>
 * 通过使用具有 不同速度 的快、慢两个指针遍历链表，空间复杂度可以被降低至 *O(1)*。慢指针每次移动一步，而快指针每次移动两步。
 * <p>
 * 如果列表中不存在环，最终快指针将会最先到达尾部，此时我们可以返回 false。
 * <p>
 * 现在考虑一个环形链表，把慢指针和快指针想象成两个在环形赛道上跑步的运动员（分别称之为慢跑者与快跑者）。而快跑者最终一定会追上慢跑者。这是为什么呢？考虑下面这种情况（记作情况 A） - 假如快跑者只落后慢跑者一步，在下一次迭代中，它们就会分别跑了一步或两步并相遇。
 * <p>
 * 其他情况又会怎样呢？例如，我们没有考虑快跑者在慢跑者之后两步或三步的情况。但其实不难想到，因为在下一次或者下下次迭代后，又会变成上面提到的情况 A。
 * 复杂度：O（n）和O（1）
 *
 * @author junbin.wang
 * @description: TODO
 * @date 2023/2/14下午9:19
 */
public class DetectCycle {
    public class Solution {
        public boolean hasCycle(ListNode head) {
            Set<ListNode> seen = new HashSet<ListNode>();
            while (head != null) {
                if (!seen.add(head)) {
                    return true;
                }
                head = head.next;
            }
            return false;
        }
    }
}
