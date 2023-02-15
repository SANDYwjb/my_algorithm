package com.junbin.algorithm.first.twenty;

import java.util.HashMap;
import java.util.Objects;

/**
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 * 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作
 * 需要实现的方法有：get、put，remove、add、refresh，因为需要知道最近使用和最近未使用，所以需要head和tail节点，分别指向最近使用和最近未使用。
 * get的时候，需要将当前节点移到头节点，就涉及删除节点和添加节点方法。
 *
 * @author junbin.wang
 * @date 2023/2/13下午9:28
 */
public class LRUCache {
    public static class RedisApplication {
        /**
         * 双向链表存储缓存数据
         */
        private class Node {
            private Node pre;
            private Node next;
            private int value;
            private int key;

            public Node(Integer key, Integer value) {
                this.key = key;
                this.value = value;
            }
        }

        /**
         * 缓存数据
         */
        private HashMap<Integer, Node> cache = new HashMap<>();
        /**
         * 头结点，最近使用节点
         */
        private Node head = null;
        /**
         * 尾节点，最近最少使用节点(即将淘汰节点)
         */
        private Node tail = null;
        /**
         * 最大容量
         */
        private int capacity = 2;
        /**
         * 当前容量
         */
        private int size = 0;

        /**
         * 头部插入最新数据
         *
         * @param node
         */
        private void add(Node node) {
            if (Objects.equals(head, null) && Objects.equals(tail, null)) {
                head = node;
                tail = node;
            } else {
                node.next = head;
                head.pre = node;
                head = node;
            }
            cache.put(node.key, node);
        }

        /**
         * 删除节点
         *
         * @param node 被删除的节点
         */
        private void delete(Node node) {
            // 只有一个节点时
            if (Objects.equals(head, tail)) {
                head = null;
                tail = null;
                return;
            }
            // 删除头结点
            if (Objects.equals(node, head)) {
                head = head.next;
                head.pre = null;
                return;
            }
            // 删除尾节点
            if (Objects.equals(node, tail)) {
                tail = tail.pre;
                tail.next = null;
                return;
            }
            // 删除中间节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        /**
         * 刷新被访问节点的位置
         *
         * @param node 被访问节点
         */
        private void refresh(Node node) {
            // 头点无需处理
            if (Objects.equals(node, head)) {
                return;
            }
            delete(node);
            add(node);
        }

        /**
         * 获取缓存中的数据
         *
         * @param key
         * @return
         */
        public int get(Integer key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            Node node = cache.get(key);
            refresh(node);
            return node.value;
        }

        private void put(Integer key, Integer value) {
            if (Objects.isNull(key) || Objects.isNull(value)) {
                return;
            }

            // 已存在,更新数据
            if (cache.containsKey(key)) {
                cache.get(key).value = value;
                refresh(cache.get(key));
                return;
            }

            Node node = new Node(key, value);
            if (size >= capacity) {
                cache.remove(tail.key);
                delete(tail);
            } else {
                size++;
            }
            add(node);

        }
//
//        public static void main(String[] args) {
//            RedisApplication cache = new RedisApplication();
//
//            cache.put(1, 1);
//            cache.put(2, 2);
//            System.out.println(cache.get(1));      // 返回  1
//            cache.put(3, 3);    // 该操作会使得密钥 2 作废
//            System.out.println(cache.get(2));       // 返回 -1 (未找到)
//            cache.put(4, 4);    // 该操作会使得密钥 1 作废
//            System.out.println(cache.get(1));       // 返回 -1 (未找到)
//            System.out.println(cache.get(3));       // 返回  3
//            System.out.println(cache.get(4));      // 返回  4
//        }
    }


}
