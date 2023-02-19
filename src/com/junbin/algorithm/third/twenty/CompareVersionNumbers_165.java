package com.junbin.algorithm.third.twenty;

/**
 * 165. 比较版本号-中等
 * 给你两个版本号 version1 和 version2 ，请你比较它们。
 * 版本号由一个或多个修订号组成，各修订号由一个 '.' 连接。每个修订号由 多位数字 组成，可能包含 前导零 。每个版本号至少包含一个字符。
 * 修订号从左到右编号，下标从 0 开始，最左边的修订号下标为 0 ，下一个修订号下标为 1 ，以此类推。例如，2.5.33 和 0.1 都是有效的版本号。
 * 比较版本号时，请按从左到右的顺序依次比较它们的修订号。比较修订号时，只需比较 忽略任何前导零后的整数值 。也就是说，修订号 1 和修订号 001 相等 。
 * 如果版本号没有指定某个下标处的修订号，则该修订号视为 0 。例如，版本 1.0 小于版本 1.1 ，因为它们下标为 0 的修订号相同，
 * 而下标为 1 的修订号分别为 0 和 1 ，0 < 1 。
 * 输入：version1 = "1.01", version2 = "1.001"
 * 输出：0
 * 解释：忽略前导零，"01" 和 "001" 都表示相同的整数 "1"
 * <p>
 * 输入：version1 = "1.0", version2 = "1.0.0"
 * 输出：0
 * 解释：version1 没有指定下标为 2 的修订号，即视为 "0"
 * <p>
 * 输入：version1 = "0.1", version2 = "1.1"
 * 输出：-1
 * 解释：version1 中下标为 0 的修订号是 "0"，version2 中下标为 0 的修订号是 "1" 。0 < 1，所以 version1 < version2
 * <p>
 * 思路：字符串分割 + 双指针
 * 我们可以将版本号按照点号分割成修订号，然后从左到右比较两个版本号的相同下标的修订号。在比较修订号时，需要将字符串转换成整数进行比较。
 * 注意根据题目要求，如果版本号不存在某个下标处的修订号，则该修订号视为 0。同时需要存储分割后的修订号，为了优化空间复杂度，
 * 我们可以在分割版本号的同时解析出修订号进行比较。
 * <p>
 * 时间复杂度：O(n+m)，其中 n 是字符串 version1的长度，m 是字符串 version2 的长度。
 * 空间复杂度：O(1)
 *
 * @author junbin.wang
 * @date 2023/2/19下午3:13
 */
public class CompareVersionNumbers_165 {
    public int compareVersion(String version1, String version2) {
        int n = version1.length(), m = version2.length();
        int i = 0, j = 0;
        while (i < n || j < m) {
            int x = 0;
            // 将每一部分转换为整数
            for (; i < n && version1.charAt(i) != '.'; ++i) {
                x = x * 10 + version1.charAt(i) - '0';
            }
            // 跳过点号
            ++i;
            int y = 0;
            for (; j < m && version2.charAt(j) != '.'; ++j) {
                y = y * 10 + version2.charAt(j) - '0';
            }
            // 跳过点号
            ++j;
            if (x != y) {
                return x > y ? 1 : -1;
            }
        }
        return 0;
    }
}
