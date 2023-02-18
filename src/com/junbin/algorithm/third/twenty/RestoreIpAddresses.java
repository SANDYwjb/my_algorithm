package com.junbin.algorithm.third.twenty;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 93. 复原 IP 地址-中等
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * <p>
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。
 * 你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 * <p>
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 思路：回溯算法
 * IP的规则就是：除第一段可以为0，其他段第一个数字都不能为0，且每一段不能大于255.
 * 回溯算法事实上就是在一个树形问题上做深度优先遍历，因此 首先需要把问题转换为树形问题。这里请大家一定要拿起纸和笔。
 *
 * @author junbin.wang
 * @date 2023/2/18下午8:52
 */
public class RestoreIpAddresses {
    //画图理解
    public List<String> restoreIpAddresses(String s) {
        //定义表示一个字符长度的变量
        int len = s.length();
        //定义一个返回结果的集合
        List<String> res = new ArrayList<>();
        //如果当前字符长度大于12或者小于4都不满足
        if (len > 12 || len < 4) {
            return res;
        }
        //定义一个保存路径上的变量，Deque双端队列，允许两头都进，两头都出，队尾和队首都可以操作
        Deque<String> path = new ArrayDeque<>();
        //深度优先搜索
        dfs(s, len, 0, 4, path, res);
        //返回结果
        return res;
    }

    // residue是残留的意思，这里表示剩下的需要遍历的IP段，有4段，4,3,2,1
    public void dfs(String s, int len, int begin, int residue, Deque<String> path, List<String> res) {
        //如果字符串已经遍历到最后了，并且已经切分为4段了，
        //就把当前路径上的元素加入到返回的结果集中
        if (begin == len) {
            if (residue == 0) {
                res.add(String.join(".", path));
            }
            return;
        }
        //begin表示遍历字符串从哪里开始
        for (int i = begin; i < begin + 3; i++) {
            //如果超出字符串的长度，就直接退出
            //begin，每次选择都是从之前选择的元素的下一个元素开始，
            if (i >= len) {
                break;
            }
            //如果剩余元素大于ip最多能容纳的个数，就剪枝。
            if (len - i > residue * 3) {
                continue;
            }
            //判断当前截取字符是否是小于0或者大于255
            //这里的begin和i，代表的是，这时候截取了几个字符
            //begin从哪里开始，i到哪里结束
            if (judgeIpSegment(s, begin, i)) {
                //保留当前截取字符
                String currentIpSegment = s.substring(begin, i + 1);
                //将当前路径上的元素加入到路径队列中
                path.addLast(currentIpSegment);
                //递归下一层
                dfs(s, len, i + 1, residue - 1, path, res);
                //剪枝，这里应该不叫剪枝，就是遍历过的结果就不遍历了而已吧
                path.removeLast();
            }
        }
    }

    private boolean judgeIpSegment(String s, int left, int right) {
        //定义一个表示整个字符的长度
        int len = right - left + 1;
        //如果截取的大于等于2的字符的开头为0，就直接false
        if (len > 1 && s.charAt(left) == '0') {
            return false;
        }
        //定义返回结果的集合
        int res = 0;
        //拼接字符
        while (left <= right) {
            //res*10 是为了将先加的字符默认比后面的字符大10倍，也就是位数是从小到大
            res = res * 10 + s.charAt(left) - '0';
            left++;
        }
        return res >= 0 && res <= 255;
    }
}
