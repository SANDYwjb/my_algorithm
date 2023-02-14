package com.junbin.algorithm.first;

import com.junbin.model.ListNode;

/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构 。
 * 思路：
 * 为了方便理解，假设链表 A 的节点数为 a（包含公共节点），链表 B 的节点数为 b（包含公共节点），两链表的公共尾部节点数为 c ，
 * 第一个公共节点为 c1。（可以自己画一个有交叉的两条链表）
 *
 * 让指针 PA 和 pB 分别指向链表 A（包含公共节点） 和链表 B 的头结点（包含公共节点），之后两个指针分别以步幅为 1 的速度向链表的尾部遍历。
 *
 * - 当指针 pA 遍历到链表 A 的尾节点时，此时指针 pA 走了 a 个节点，将指针 pA 指向链表 B 的头部，继续向后遍历，直至走到 c1，此时指针 PA 总共走了 a + ( b - c ) 步。
 * - 当指针 pB 遍历到链表 B 的尾节点时，此时指针 pB 走了 b 个节点，将指针 pB 指向链表 A 的头部，继续向后遍历，直至走到 c1，此时指针 PB 总共走了 b + ( a - c ) 步。
 *
 * 根据数学知识，a + ( b - c ) = b + ( a - c )，所以两个指针将同时走到C1，如果 c > 0，表明两链表有公共尾部， c1 存在，两两链表同时到达 c1；
 * 如果 c = 0，表明两链表没有公共尾部，指针 PA 和 pB 都指向 NULL。也就是说，只要走到这里，不管有没有相交点，两个指针对应的节点都将相同。
 * 所以下面代码中的while截止条件才会成立。
 * @author junbin.wang
 * @description: TODO
 * @date 2023/2/14下午9:00
 */
public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 边界判断
        if (headA == null || headB == null) return null;
        ListNode pA = headA, pB = headB;
        //指针 PA 和 指针 PB 不断向后遍历，直到找到相交点
        while (pA != pB) {
            // 指针 PA 一开始在链表 A 上遍历，当走到链表 A 的尾部即 null 时，跳转到链表 B 上
            pA = pA == null ? headB : pA.next;
            // 指针 PB 一开始在链表 B 上遍历，当走到链表 B 的尾部即 null 时，跳转到链表 A 上
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
