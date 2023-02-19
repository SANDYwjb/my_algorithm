package com.junbin.algorithm_41_60;

/**
 * 151. 反转字符串中的单词-中等
 * 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
 * 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
 * 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
 * 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
 * <p>
 * 输入：s = "the sky is blue"
 * 输出："blue is sky the"
 * <p>
 * 输入：s = "  hello world  "
 * 输出："world hello"
 * 解释：反转后的字符串中不能存在前导空格和尾随空格。
 * <p>
 * 输入：s = "a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
 *
 * @author junbin.wang
 * @date 2023/2/19上午11:10
 */
public class ReverseWordsInAString_151 {
    public String reverseWords(String s) {
        // 每个单词的开始和结束索引（左闭右开）
        int start, end;
        StringBuilder sb = new StringBuilder();
        // 从尾部开始循环
        for (int i = s.length() - 1; i >= 0; i--) {
            //跳过空格
            if (s.charAt(i) == ' ') {
                continue;
            }
            //找到结束索引
            end = i + 1;
            //跳过空格
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            //找到开始索引
            start = i + 1;
            //将每个单词按开始结束索引赋值到StringBuilder
            for (int j = start; j < end; j++) {
                sb.append(s.charAt(j));
            }
            //加上单词间的空格
            sb.append(' ');
        }
        //删掉最后一个多余的空格
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
