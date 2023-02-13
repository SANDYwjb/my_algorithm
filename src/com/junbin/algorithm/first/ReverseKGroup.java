package com.junbin.algorithm.first;

/**
 * K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。k 是一个正整数，它的值小于或等于链表的长度。 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。 示例 :
 *
 * 给定这个链表：1->2->3->4->5 当 k = 2 时，应当返回: 2->1->4->3->5 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * 说明 :
 *
 * 你的算法只能使用常数的额外空间。 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 大致过程可以分解为 1、找到待翻转的k个节点（注意：若剩余数量小于k的话，则不需要反转，因此直接返回待翻转部分的头结点即可）。 2、对其进行翻转。并返回翻转后的头结点（注意：翻转为左闭右开区间，所以本轮操作的尾结点其实就是下一轮操作的头结点）。 3、对下一轮k个节点也进行翻转操作。 4、将上一轮翻转后的尾结点指向下一轮翻转后的头节点，即将每一轮翻转的k的节点连接起来。
 * @author junbin.wang
 * @description: TODO
 * @date 2023/2/13下午9:32
 */
public class ReverseKGroup {
//    public ListNode reverseKGroup(ListNode head, int k) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode tail = head;
//        for (int i = 0; i < k; i++) {
//            if (tail == null) {  //剩余数量小于k的话，则不需要反转。就是还没遍历完k步，tail就没了，说明剩余数量小于k
//                return head;
//            }
//            tail = tail.next; // 从这里可以看出是左闭右开
//        }
//        // 反转前 k 个元素
//        ListNode newHead = reverse(head, tail);
//        //下一轮的开始的地方就是tail
//        head.next = reverseKGroup(tail, k);  // 这里head已经是此轮最后一个节点了，newHead才是新的头节点了。
//
//        return newHead;
//    }
//
//    /*
//    左闭又开区间
//     */
//    private ListNode reverse(ListNode head, ListNode tail) {
//        ListNode pre = null;
//        ListNode next = null;
//        while (head != tail) {   // 最后一个节点不翻转
//            next = head.next;
//            head.next = pre;
//            pre = head;
//            head = next;
//        }
//        return pre;
//
//    }
//

}
