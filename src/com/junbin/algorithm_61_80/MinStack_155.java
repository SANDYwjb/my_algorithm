package com.junbin.algorithm_61_80;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 155. 最小栈-中等
 * 设计一个支持 push，pop，top操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * 实现 MinStack 类:
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 * <p>
 * 思路：辅助栈
 * 思路其实很清楚，那就是空间换时间
 * 要做出这道题目，首先要理解栈结构先进后出的性质。
 * 对于栈来说，如果一个元素 a 在入栈时，栈里有其它的元素 b, c, d，那么无论这个栈在之后经历了什么操作，只要 a 在栈中，b, c, d 就一定在栈中，
 * 因为在 a 被弹出之前，b, c, d 不会被弹出。
 * 因此，在操作过程中的任意一个时刻，只要栈顶的元素是 a，那么我们就可以确定栈里面现在的元素一定是 a, b, c, d。
 * 那么，我们可以在每个元素 a 入栈时把当前栈的最小值 m 存储起来。在这之后无论何时，如果栈顶元素是 a，我们就可以直接返回存储的最小值 m。
 * <p>
 * 按照上面的思路，我们只需要设计一个数据结构，使得每个元素 a 与其相应的最小值 m 时刻保持一一对应。因此我们可以使用一个辅助栈，与元素栈同步插入与删除，
 * 用于存储与每个元素对应的最小值。
 * 当一个元素要入栈时，我们取当前辅助栈的栈顶存储的最小值，与当前元素比较得出最小值，将这个最小值插入辅助栈中；
 * 当一个元素要出栈时，我们把辅助栈的栈顶元素也一并弹出；
 * 在任意一个时刻，栈内元素的最小值就存储在辅助栈的栈顶元素中。
 * <p>
 * 时间复杂度均为 O(1)，为栈的插入、删除与读取操作都是这个时间复杂度
 * 空间复杂度：O(n)
 *
 * @author junbin.wang
 * @date 2023/2/21上午8:14
 */
public class MinStack_155 {
    // deque比stack的性能更好，stack继承vector，线程安全，deque继承Queue接口
    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack_155() {
        xStack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        xStack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    /**
     * 面试可能还会问不需要辅助栈的做法：其实就是用一个变量保存当前栈中的最小值
     * 此时栈中保留的不是压入的元素值，而是每个元素代表的是要压入元素与当前栈中最小值的差值
     * 有个很重要问题：
     * 在弹出时如何维护min？
     * 因为每次压入新的元素时，压入的都是与当前栈中最小值的差值（还未压入当前元素），
     * 故在弹出元素时，若弹出了当前最小值，因为栈中记录了当前元素与【之前】最小值的差值，故根据这个记录可以更新弹出元素后的最小值。
     * 逻辑上会有点绕，画图走一遍会好点
     */
    public static class MinStack {
        // 记录每个元素与【未压入】该元素时栈中最小元素的差值
        LinkedList<Long> stack;
        // 当前【已压入】栈中元素的最小值
        private long min;

        public MinStack() {
            stack = new LinkedList();
        }

        public void push(int val) {
            // 压入第一个元素
            if (stack.isEmpty()) {
                min = val;
                stack.addFirst(0L);
                return;
            }
            // 栈不为空时，每次压入计算与min的差值后压入结果
            stack.push((long) val - min);
            // 更新min
            min = Math.min((long) val, min);
            // 上面两个语句是不能颠倒的！一定是先压入，在更新，因为min一定是当前栈中的最小值
        }

        public void pop() {
            long pop = stack.removeFirst();
            // 当弹出元素小于0时，说明弹出元素是当前栈中的最小值，要更新最小值，因为是和上一个min求差值，小于0表示比上一个min还要小，那就是最小值
            if (pop < 0) {
                // 因为对于当前弹出的元素而言，计算压入栈中的值时，计算的是该元素与【未压入】该元素时
                // 栈中元素的最小值的差值，故弹出该元素后栈中的最小值就是未压入该元素时的最小值
                // 即当前元素的值（min）减去两者的差值
                long lastMin = min;
                min = lastMin - pop;
            }
            // 若大于等于0，不会对min有影响
        }

        public int top() {
            long peek = stack.peek();
            // 若当前栈顶小于等于0，说明最小值就是栈顶元素
            if (peek <= 0) return (int) min;
            // 否则就是min+peek
            return (int) (min + peek);
        }

        public int getMin() {
            return (int) min;
        }
    }
}
