package com.junbin.algorithm_61_80;

import com.junbin.model.ListNode;

/**
 * 234. 回文链表-简单
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * 输入：head = [1,2,2,1]
 * 输出：true
 * 思路：快慢指针
 * 我们可以将链表的后半部分反转（修改链表结构），然后将前半部分和后半部分进行比较。比较完成后我们应该将链表恢复原样。虽然不需要恢复也能通过测试用例，
 * 但是使用该函数的人通常不希望链表结构被更改。
 * 该方法虽然可以将空间复杂度降到 O(1)，但是在并发环境下，该方法也有缺点。在并发环境下，函数运行时需要锁定其他线程或进程对链表的访问，
 * 因为在函数执行过程中链表会被修改。
 * 算法：
 * 整个流程可以分为以下五个步骤：
 * 1. 找到前半部分链表的尾节点。
 * 2. 反转后半部分链表。
 * 3. 判断是否回文。
 * 4. 恢复链表。
 * 5. 返回结果。
 * 执行步骤一，我们可以计算链表节点的数量，然后遍历链表找到前半部分的尾节点。
 * 我们也可以使用快慢指针在一次遍历中找到：慢指针一次走一步，快指针一次走两步，快慢指针同时出发。当快指针移动到链表的末尾时，慢指针恰好到链表的中间。
 * 通过慢指针将链表分为两部分。若链表有奇数个节点，则中间的节点应该看作是前半部分。
 * 步骤二可以使用「206. 反转链表」问题中的解决方法来反转链表的后半部分。
 * 步骤三比较两个部分的值，当后半部分到达末尾则比较完成，可以忽略计数情况中的中间节点。
 * 步骤四与步骤二使用的函数相同，再反转一次恢复链表本身。
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 *
 * @author junbin.wang
 * @date 2023/2/24上午7:52
 */
public class PalindromeLinkedList_234 {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode reverseList(ListNode head) {
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

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        // 如果是偶数个节点，slow会落在中位数第一个节点位置，奇数节点，slow正好落在中间节点
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
