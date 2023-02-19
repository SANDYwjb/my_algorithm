package com.junbin.algorithm_21_40;

import com.junbin.model.ListNode;

import java.util.PriorityQueue;

/**
 * 23.合并K个升序链表-困难
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * <p>
 * 思路：
 * 使用优先队列合并
 * 我们需要维护当前每个链表没有被合并的元素的最前面一个，k 个链表就最多有 k 个满足这样条件的元素，
 * 每次在这些元素里面选取 val属性最小的元素合并到答案中。在选取最小元素的时候，我们可以用优先队列来优化这个过程
 *  时间复杂度：O(n∗log(k))，n 是所有链表中元素的总和，k 是链表个数。
 * @author junbin.wang
 * @date 2023/2/15下午9:14
 */
public class MergeKSortedLists_23 {
    class Status implements Comparable<Status> {
        int val;
        ListNode ptr;

        Status(int val, ListNode ptr) {
            this.val = val;
            this.ptr = ptr;
        }

        public int compareTo(Status status2) {
            // -1 升序，1 降序
            return this.val - status2.val;
        }
    }

    PriorityQueue<Status> queue = new PriorityQueue<Status>();

    public ListNode mergeKLists(ListNode[] lists) {
        for (ListNode node : lists) {
            // 先把每条链表的头指针放进去
            if (node != null) {
                queue.offer(new Status(node.val, node));
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!queue.isEmpty()) {
            // 取出来头指针中最小的节点
            Status f = queue.poll();
            tail.next = f.ptr;
            tail = tail.next;
            // 取出来后再把下一个节点当头节点，放进队列
            if (f.ptr.next != null) {
                queue.offer(new Status(f.ptr.next.val, f.ptr.next));
            }
        }
        return head.next;
    }
}
