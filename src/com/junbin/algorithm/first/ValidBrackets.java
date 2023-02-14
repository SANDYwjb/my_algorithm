package com.junbin.algorithm.first;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * <p>
 * 左括号必须以正确的顺序闭合。
 * <p>
 * **解题思路**：
 * <p>
 * 算法原理
 * <p>
 * ​	栈先入后出特点恰好与本题括号排序特点一致，即若遇到左括号入栈，遇到右括号时将对应栈顶左括号出栈，则遍历完所有括号后 stack 仍然为空；
 * <p>
 * ​	建立哈希表 dic 构建左右括号对应关系：key左括号，value 右括号；这样查询 2 个括号是否对应只需 O(1)时间复杂度；建立栈 stack，遍历字符串 s 并按照算法流程一一判断。
 * <p>
 * 算法流程
 * <p>
 * ​	如果 c 是左括号，则入栈 push；
 * <p>
 * ​	否则通过哈希表判断括号对应关系，若 stack 栈顶出栈括号 stack.pop() 与当前遍历括号 c 不对应，则提前返回 false。
 * <p>
 * 提前返回 false
 * <p>
 * 提前返回优点： 在迭代过程中，提前发现不符合的括号并且返回，提升算法效率。
 * <p>
 * **解决边界问题**：
 * <p>
 * ​	栈 stack 为空： 此时 stack.pop() 操作会报错；因此，我们采用一个取巧方法，给 stack 赋初值 ? ，并在哈希表 dic 中建立 key: '?'，value:'?'的对应关系予以配合。此时当 stack 为空且 c 为右括号时，可以正常提前返回 false；
 * <p>
 * ​	字符串s以左括号结尾： 此情况下可以正常遍历完整个 s，但 stack 中遗留未出栈的左括号；因此，最后需返回 len(stack) == 1，以判断是否是有效的括号组合。
 * <p>
 * 复杂度分析
 * <p>
 * 时间复杂度 O(N)：正确的括号组合需要遍历1遍 s；
 * <p>
 * 空间复杂度 O(N)：哈希表和栈使用线性的空间大小。
 *
 * @author junbin.wang
 * @description: TODO
 * @date 2023/2/14下午9:21
 */
public class ValidBrackets {
    static class Solution {
        private static final Map<Character, Character> map = new HashMap<Character, Character>() {{
            put('{', '}');
            put('[', ']');
            put('(', ')');
            put('?', '?');
        }};

        public boolean isValid(String s) {
            //如果第一个字符就不是左边括号开头，那肯定是false
            if (s.length() > 0 && !map.containsKey(s.charAt(0)))
                return false;
            LinkedList<Character> stack = new LinkedList<Character>() {{
                add('?');
            }};
            for (Character c : s.toCharArray()) {
                if (map.containsKey(c))
                    stack.add(c);
                else if (map.get(stack.removeLast()) != c)
                    return false;
            }
            //正常情况，只能保留？这个一个元素
            return stack.size() == 1;
        }
    }
}
