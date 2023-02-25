package com.junbin.algorithm_81_100;

/**
 * 14. 最长公共前缀-简单
 * 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 思路： 纵向扫描
 * 纵向扫描时，从前往后遍历所有字符串的每一列，比较相同列上的字符是否相同，如果相同则继续对下一列进行比较，如果不相同则当前列不再属于公共前缀，
 * 当前列之前的部分为最长公共前缀。
 * 时间复杂度：O(m x n)
 * 空间复杂度：O(1)
 *
 * @author junbin.wang
 * @date 2023/2/25上午8:53
 */
public class LongestCommonPrefix_14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 以第一个字符串的长度进行遍历即可
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                // 如果i已经超了后面的字符串长度，或者后面字符串的i下标字符不相等
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
