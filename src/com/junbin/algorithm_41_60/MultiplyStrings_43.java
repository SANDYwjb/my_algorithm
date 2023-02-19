package com.junbin.algorithm_41_60;

/**
 * 43. 字符串相乘-中等
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 思路：
 * 令 m 和 n 分别表示 num1和 num2 的长度，并且它们均不为 0，则 num1和 num2的乘积的长度为 m+n−1 或 m+n。这个可以证明，只需要记住就行。
 * 由于 num1和 num2的乘积的最大长度为 m+n，因此创建长度为 m+n 的数组 ansArr 用于存储乘积。对于任意 0≤i<m 和 0≤j<n，
 * num1[i]×num2[j]的结果位于 ansArr[i+j+1]，如果 ansArr[i+j+1]≥10，则将进位部分加到 ansArr[i+j]。
 * <p>
 * 最后，将数组 ansArr转成字符串，如果最高位是 0 则舍弃最高位。
 *
 * @author junbin.wang
 * @date 2023/2/19下午3:32
 */
public class MultiplyStrings_43 {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        // 要从后往前遍历
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                // 这里不能直接赋值，要算加法，因为i + j + 1可能会多次得到同一个结果
                ansArr[i + j + 1] += x * y;
            }
        }
        // 统计进阶
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        // 高位0要去掉
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuffer ans = new StringBuffer();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }
}
