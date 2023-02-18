package com.junbin.algorithm.third.twenty;

/**
 * 8. 字符串转换整数 (atoi)-中等
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * <p>
 * 函数 myAtoi(string s) 的算法如下：
 * <p>
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−2^31,  2^31 − 1] ，需要截断这个整数，使其保持在这个范围内。
 * 具体来说，小于 −2^31 的整数应该被固定为 −2^31 ，大于 2^31 − 1 的整数应该被固定为 2^31 − 1 。
 * 返回整数作为最终结果。
 * <p>
 * 官方答案用什么状态机，感觉很麻烦，其实可以直接写。
 *
 * @author junbin.wang
 * @date 2023/2/18下午3:56
 */
public class StringToIntegerAtoi {
    public int myAtoi(String s) {
        // 标识是正是负
        int sign = 1;
        // 返回的转换结果
        int res = 0;
        int m = s.length();
        int i = 0;
        // 先去掉前面的无效空格
        while (i < m && s.charAt(i) == ' ') {
            i++;
        }
        int start = i;
        for (; i < m; i++) {
            char c = s.charAt(i);
            if (i == start && c == '+') {
                sign = 1;
            } else if (i == start && c == '-') {
                sign = -1;
            } else if (Character.isDigit(c)) {
                int num = c - '0';
                // num是放在最右边的，每加一个num，前面的res就要乘以10，所以先判断res有没有大于MAX_VALUE除以10后的数，才避免溢出。
                if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10)) {
                    return Integer.MAX_VALUE;
                }

                if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && -num < Integer.MIN_VALUE % 10)) {
                    return Integer.MIN_VALUE;
                }
                res = res * 10 + sign * num;
            } else {
                break;
            }
        }
        return res;
    }
}
