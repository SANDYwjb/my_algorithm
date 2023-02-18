package com.junbin.algorithm.second.twenty;

import com.junbin.model.ListNode;

/**
 * 19. 删除链表的倒数第 N 个结点-中等
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * <p>
 * 双指针
 * 我们也可以在不预处理出链表的长度，以及使用常数空间的前提下解决本题。
 * <p>
 * 由于我们需要找到倒数第 n 个节点，因此我们可以使用两个指针 first 和 second 同时对链表进行遍历，
 * 并且 first 比 second 超前 n 个节点。当 first 遍历到链表的末尾时，second 就恰好处于倒数第 n 个节点。
 *
 * @author junbin.wang
 * @date 2023/2/15下午9:25
 */
public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        /**
         * 在对链表进行操作时，一种常用的技巧是添加一个哑节点（dummy node），它的next 指针指向链表的头节点。这样一来，
         * 我们就不需要对头节点进行特殊的判断了。
         * 例如，在本题中，如果我们要删除节点y，我们需要知道节点y的前驱节点x，并将x的指针指向y的后继节点。但由于头节点不存在前驱节点，
         * 因此我们需要在删除头节点时进行特殊判断。但如果我们添加了哑节点，那么头节点的前驱节点就是哑节点本身，此时我们就只需要考虑通用的情况即可。
         */
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        // 这里second在头结点的前一个节点上
        ListNode second = dummy;
        // 循环结束first在n节点的上一个节点
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        // 这里first永远比second前面n个节点
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        // 当first = null时，second正好在n-1节点处
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
}
