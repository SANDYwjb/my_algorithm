package com.junbin.algorithm.first.twenty;

import com.junbin.model.ListNode;

/**
 * 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 * <p>
 * 说明：不允许修改给定的链表。
 * 进阶：
 * 你是否可以使用 O(1) 空间解决此题？
 * 这道题和之前的环形链表1，区别在于那个只要判断是否有环就行；而这个还需要找到入环的第一个节点。因此难度加大很多。算法解析很复杂，直接看原文！
 * 核心概念就是需要两次相遇！不一定要记住为什么要两次相遇，但是两次相遇的逻辑得记住！！
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/
 *
 * @author junbin.wang
 * @date 2023/2/14下午9:17
 */
public class DetectCycle2 {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            // 存在null则表示肯定没有环
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            //第一次相遇后退出，slow位置不变，fast重新从头结点开始。这里可以判断一定有环
            if (fast == slow) {
                break;
            }
        }
        fast = head;
        // 第二次的时候，两个步长要一致都为1，直到再次相遇，画个图就懂了
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        // 返回fast或者slow都可以。
        return fast;
    }
}
