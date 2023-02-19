package com.junbin.algorithm.third.twenty;

import com.junbin.model.ListNode;

/**
 * 876. 链表的中间结点-简单
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 * <p>
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3
 * <p>
 * 思路：快慢指针法
 * 用两个指针 slow 与 fast 一起遍历链表。slow 一次走一步，fast 一次走两步。那么当 fast 到达链表的末尾时，slow 必然位于中间。
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author junbin.wang
 * @date 2023/2/19下午7:55
 */
public class MiddleOfTheLinkedList_876 {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
