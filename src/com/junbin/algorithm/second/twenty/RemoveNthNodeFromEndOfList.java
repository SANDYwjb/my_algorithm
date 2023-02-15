package com.junbin.algorithm.second.twenty;

import com.junbin.model.ListNode;

/** 19. 删除链表的倒数第 N 个结点-中等
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 双指针
 * 我们也可以在不预处理出链表的长度，以及使用常数空间的前提下解决本题。
 *
 * 由于我们需要找到倒数第 n 个节点，因此我们可以使用两个指针 first 和 second 同时对链表进行遍历，
 * 并且 first 比 second 超前 n 个节点。当 first 遍历到链表的末尾时，second 就恰好处于倒数第 n 个节点。

 * @author junbin.wang
 * @description: TODO
 * @date 2023/2/15下午9:25
 */
public class RemoveNthNodeFromEndOfList {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0, head);
            ListNode first = head;
            ListNode second = dummy;
            for (int i = 0; i < n; ++i) {
                first = first.next;
            }
            while (first != null) {
                first = first.next;
                second = second.next;
            }
            second.next = second.next.next;
            ListNode ans = dummy.next;
            return ans;
        }
}
