package com.junbin.algorithm.third.twenty;

import com.junbin.model.ListNode;

/**
 * 143. 重排链表-中等
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L[0] → L[1] → … → L[n - 1] → L[n]
 * 请将其重新排列后变为：
 * L[0] → L[n] → L[1] → L[n - 1] → L[2] → L[n - 2] → …
 * 输入：head = [1,2,3,4]
 * 输出：[1,4,2,3]
 * <p>
 * 输入：head = [1,2,3,4,5]
 * 输出：[1,5,2,4,3]
 * <p>
 * 思路：寻找链表中点 + 链表逆序 + 合并链表
 * 注意到目标链表即为将原链表的左半端和反转后的右半端合并后的结果。
 * 这样我们的任务即可划分为三步：
 * 1. 找到原链表的中点：我们可以使用快慢指针来 O(N) 地找到链表的中间节点。参考876题
 * 2. 将原链表的右半端反转：我们可以使用迭代法实现链表的反转。
 * 3. 将原链表的两端合并：因为两链表长度相差不超过 1，因此直接合并即可。
 * <p>
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author junbin.wang
 * @date 2023/2/19下午7:50
 */
public class ReorderList_143 {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        // 断开mid和后面节点的联系
        mid.next = null;
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    /**
     * 用两个指针 slow 与 fast 一起遍历链表。slow 一次走一步，fast 一次走两步。
     * 那么当 fast 到达链表的末尾时，slow 必然位于中间。
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 迭代：在遍历链表时，将当前节点的 next 指针改为指向前一个节点。
     * 由于节点没有引用其前一个节点，因此必须事先存储其前一个节点。
     * 在更改引用之前，还需要存储后一个节点。最后返回新的头引用。
     *
     * @param head
     * @return
     */
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

    // 合并两个链表
    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            // 暂存当前节点的下一个节点
            l1_tmp = l1.next;
            l2_tmp = l2.next;
            // l1当前节点指向l2的当前节点，连接起来了
            l1.next = l2;
            // 再把l1的头结点换成前面暂存的next节点
            l1 = l1_tmp;
            // 然后l2的当前再指向l1的当前节点，也就是上面的next节点
            l2.next = l1;
            // 再把l2的头结点换成前面暂存的next节点
            l2 = l2_tmp;
        }
    }
}
