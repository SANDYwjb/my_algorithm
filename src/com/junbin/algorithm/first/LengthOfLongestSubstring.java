package com.junbin.algorithm.first;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目：给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 输入: s = "abcabcbb"
 * <p>
 * 输出: 3
 * <p>
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 解法：滑动窗口解决
 * <p>
 * 假设我们选择字符串中的第k个字符作为起始位置，并且得到了不包含重复字符的最长子串的结束位置为 r_k。
 * 那么当我们选择第 k+1个字符作为起始位置时，首先从 k+1到 r_k的字符显然是不重复的，并且由于少了原本的第 k个字符，我们可以尝试继续增大 r_k，
 * 直到右侧出现了重复字符为止。这样一来，我们就可以使用「滑动窗口」来解决这个问题了：
 * <p>
 * ​ 1. 我们使用两个指针表示字符串中的某个子串（或窗口）的左右边界，其中左指针代表着上文中「枚举子串的起始位置」，而右指针即为上文中的 r_k；
 * <p>
 * ​ 2. 在每一步的操作中，我们会将左指针向右移动一格，表示 我们开始枚举下一个字符作为起始位置，然后我们可以不断地向右移动右指针，
 * 但需要保证这两个指针对应的子串中没有重复的字符。在移动结束后，这个子串就对应着 以左指针开始的，不包含重复字符的最长子串。我们记录下这个子串的长度；
 * <p>
 * ​ 3. 在枚举结束后，我们找到的最长的子串的长度即为答案。
 * <p>
 * 判断重复字符
 * <p>
 * 在上面的流程中，我们还需要使用一种数据结构来判断是否有重复的字符，常用的数据结构为哈希集合（即 C++ 中的 std::unordered_set，Java 中的 HashSet，Python 中的 set,
 * JavaScript 中的 Set）。在左指针向右移动的时候，我们从哈希集合中移除一个字符，在右指针向右移动的时候，我们往哈希集合中添加一个字符。
 *
 * @date 2023/2/13下午9:04
 */
public class LengthOfLongestSubstring {
    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            // 哈希集合，记录每个字符是否出现过
            Set<Character> occ = new HashSet<Character>();
            int n = s.length();
            // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
            int rk = -1, ans = 0;
            for (int i = 0; i < n; ++i) {
                if (i != 0) {
                    // 左指针向右移动一格，移除一个字符,i-1是因为要移除的是前面一格的元素
                    occ.remove(s.charAt(i - 1));
                }
                //右指针向右移动的同时，需要保证集合中没有重复元素
                while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                    // 不断地移动右指针
                    occ.add(s.charAt(rk + 1));
                    ++rk;
                }
                // 第 i 到 rk 个字符是一个极长的无重复字符子串
                ans = Math.max(ans, rk - i + 1);
            }
            return ans;
        }
    }
}
