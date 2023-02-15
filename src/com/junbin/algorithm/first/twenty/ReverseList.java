package com.junbin.algorithm.first.twenty;

import com.junbin.model.ListNode;

/**
 * 题目：给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * 思路：迭代
 * 假设存在链表 1→2→3→∅，我们想要把它改成 ∅←3←2←1。
 * 在遍历列表时，将当前节点的 next 指针改为指向前一个元素。由于节点没有引用其上一个节点，因此必须事先存储其前一个元素。
 * 在更改引用之前，还需要另一个指针来存储下一个节点。不要忘记在最后返回新的头引用！
 *
 * @author junbin.wang
 * @date 2023/2/13下午9:24
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
}
