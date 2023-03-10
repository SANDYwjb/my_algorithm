package com.junbin.algorithm_81_100;

import com.junbin.model.ListNode;

/**
 * 83. 删除排序链表中的重复元素-简单
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 * 思路：一次遍历
 * 由于给定的链表是排好序的，因此重复的元素在链表中出现的位置是连续的，因此我们只需要对链表进行一次遍历，就可以删除重复的元素。
 * 具体地，我们从指针 cur指向链表的头节点，随后开始对链表进行遍历。如果当前 cur与 cur.next对应的元素相同，那么我们就将 cur.next从链表中移除；
 * 否则说明链表中已经不存在其它与 cur对应的元素相同的节点，因此可以将 cur 指向 cur.next。当遍历完整个链表之后，我们返回链表的头节点即可。
 * 细节：
 * 当我们遍历到链表的最后一个节点时，cur.next为空节点，如果不加以判断，访问 cur.next对应的元素会产生运行错误。因此我们只需要遍历到链表的最后一个节点，
 * 而不需要遍历完整个链表。
 * 时间复杂度：O(n)，其中 n 是链表的长度。
 * 空间复杂度：O(1)。
 *
 * @author junbin.wang
 * @date 2023/2/25上午10:20
 */
public class DeleteDuplicates_83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
