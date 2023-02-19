package com.junbin.algorithm.third.twenty;

import com.junbin.model.ListNode;

/**
 * 剑指 Offer 22. 链表中倒数第k个节点-简单
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * 返回链表 4->5.
 * 思路：双指针
 * 1. 初始化： 前指针 faster 、后指针 slow ，双指针都指向头节点 head​ 。
 * 2. 构建双指针距离： 前指针 faster 先向前走 k 步（结束后，双指针 faster 和 slow 间相距 k 步）。
 * 3. 双指针共同移动： 循环中，双指针 faster 和 slow 每轮都向前走一步，直至 faster 走过链表 尾节点 时跳出（跳出后，
 * slow 与尾节点距离为 k−1，即 slow 指向倒数第 k 个节点）。
 * 4. 返回值： 返回 latter 即可。
 * 时间复杂度 O(N)
 * 空间复杂度 O(1)
 *
 * @author junbin.wang
 * @date 2023/2/19上午9:59
 */
public class GetKthFromEnd {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head, slow = head;
        // fast先走k步
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        // fast指向null时，slow对应的才是k个节点，可以画图走一遍
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
