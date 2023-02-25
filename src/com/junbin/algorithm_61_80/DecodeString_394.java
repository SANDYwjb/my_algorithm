package com.junbin.algorithm_61_80;

import java.util.Stack;

/**
 * 394. 字符串解码-中等
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * <p>
 *
 * @author junbin.wang
 * @date 2023/2/24上午8:30
 */
public class DecodeString_394 {
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c != ']')
                stack.push(c); // 把所有的字母push进去，除了]
            else {
                //step 1: 取出[] 内的字符串
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty() && Character.isLetter(stack.peek())) {
                    sb.insert(0, stack.pop());
                }
                String sub = sb.toString(); //[ ]内的字符串
                stack.pop(); // 去除[
                //step 2: 获取倍数数字
                sb = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    sb.insert(0, stack.pop());
                }
                int count = Integer.valueOf(sb.toString()); //倍数
                //step 3: 根据倍数把字母再push回去
                while (count > 0) {
                    for (char ch : sub.toCharArray()) {
                        stack.push(ch);
                    }
                    count--;
                }
            }
        }

        //把栈里面所有的字母取出来，完事L('ω')┘三└('ω')｣
        StringBuilder retv = new StringBuilder();
        while (!stack.isEmpty()) {
            retv.insert(0, stack.pop());
        }

        return retv.toString();
    }
}
