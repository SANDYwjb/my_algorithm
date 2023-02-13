package com.junbin.model;

/**
 * @author junbin.wang
 * @description: TODO
 * @date 2023/2/13下午9:38
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
