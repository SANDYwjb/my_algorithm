package com.junbin.algorithm_61_80;

import java.util.Random;

/**
 * 470. 用 Rand7() 实现 Rand10() -中等
 * 给定方法 rand7 可生成 [1,7] 范围内的均匀随机整数，试写一个方法 rand10 生成 [1,10] 范围内的均匀随机整数。
 * 你只能调用 rand7() 且不能调用其他方法。请不要使用系统的 Math.random() 方法。
 * 输入: 1
 * 输出: [2]
 * 输入: 3
 * 输出: [3,8,10]
 *
 * 思路：独立随机事件+古典概型
 * 很复杂，看这篇文章：https://leetcode.cn/problems/implement-rand10-using-rand7/solutions/979495/mo-neng-gou-zao-fa-du-li-sui-ji-shi-jian-9xpz/
 * @author junbin.wang
 * @date 2023/2/23上午8:13
 */
public class ImplementRand10UsingRand7_470 {
    public int rand10() {
        int first, second;
        while ((first = rand7()) > 6) ;
        while ((second = rand7()) > 5) ;
        return (first & 1) == 1 ? second : 5 + second;
    }

    public int rand7() {
        Random r = new Random();
        // nextInt(n)将返回一个大于等于0小于n的随机数
        return r.nextInt(7) + 1;
    }
}
