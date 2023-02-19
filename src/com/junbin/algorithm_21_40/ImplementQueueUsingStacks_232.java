package com.junbin.algorithm_21_40;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 232. 用栈实现队列-简单
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 * <p>
 * 实现 MyQueue 类：
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素
 * boolean empty() 如果队列为空，返回 true ；否则，返回 false
 * <p>
 * 思路：
 * 将一个栈当作输入栈，用于压入 push 传入的数据；另一个栈当作输出栈，用于 pop 和 peek操作。
 * 每次 pop或 peek 时，若输出栈为空则将输入栈的全部数据依次弹出并压入输出栈，因为输入栈是先进后出，压到输出栈，又一次先进后出，就相当于先进先出了
 * 这样输出栈从栈顶往栈底的顺序就是队列从队首往队尾的顺序。
 *
 * @author junbin.wang
 * @date 2023/2/17上午8:24
 */
public class ImplementQueueUsingStacks_232 {
    /**
     * 负责入栈
     */
    Deque<Integer> in;
    /**
     * 负责出栈
     */
    Deque<Integer> out;

    /**
     * 从初始化数据结构
     */
    public ImplementQueueUsingStacks_232() {
        /** Java 语言这里未用 Stack 是因为 Stack 操作是加同步锁的，性能低，
         如果没有线程安全要求，用 ArrayDeque 替代 Stack，性能高，他们底层数据结构都是数组 */
        in = new ArrayDeque<Integer>();
        out = new ArrayDeque<Integer>();
    }

    public void push(int x) {
        in.push(x);
    }

    public int pop() {
        // 如果 out 为空，将 in 中元素全部放入 out 中
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    public int peek() {
        // 如果 out 为空，将 in 中元素全部放入 out 中
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.peek();
    }

    public boolean empty() {
        if (in.isEmpty() && out.isEmpty()) {
            return true;
        }

        return false;
    }

}
