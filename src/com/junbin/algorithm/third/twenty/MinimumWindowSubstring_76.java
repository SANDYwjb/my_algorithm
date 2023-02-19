package com.junbin.algorithm.third.twenty;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 76. 最小覆盖子串-困难
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意子串和子序列的区别，子串不一定按照原有序列顺序
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 * <p>
 * 思路：滑动窗口
 * 本问题要求我们返回字符串 s 中包含字符串 t 的全部字符的最小窗口。我们称包含 t 的全部字母的窗口为「可行」窗口。
 * 我们可以用滑动窗口的思想解决这个问题。在滑动窗口类型的问题中都会有两个指针，一个用于「延伸」现有窗口的 r 指针，和一个用于「收缩」窗口的 l 指针。
 * 在任意时刻，只有一个指针运动，而另一个保持静止。我们在 s 上滑动窗口，通过移动 r 指针不断扩张窗口。当窗口包含 t 全部所需的字符后，如果能收缩，
 * 我们就收缩窗口直到得到最小窗口。
 * <p>
 * 如何判断当前的窗口包含所有 t 所需的字符呢？我们可以用一个哈希表表示 t 中所有的字符以及它们的个数，用一个哈希表动态维护窗口中所有的字符以及它们的个数，
 * 如果这个动态表中包含 t 的哈希表中的所有字符，并且对应的个数都不小于 t 的哈希表中各个字符的个数，那么当前的窗口是「可行」的。
 *
 * @author junbin.wang
 * @date 2023/2/19上午10:08
 */
public class MinimumWindowSubstring_76 {
    Map<Character, Integer> ori = new HashMap<Character, Integer>();
    Map<Character, Integer> cnt = new HashMap<Character, Integer>();

    public String minWindow(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            // getOrDefault: 如果存在key, 则返回其对应的value, 否则返回给定的默认值
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            // 先是r不断右移，l不动，直到找到可行窗口
            ++r;
            // 检查如果元素存在t中，就放入cnt并次数+1
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            // 如果当前是可行窗口，就尝试收缩窗口，即l开始右移
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                // 如果要被右移的l处有满足ori的元素，就先尝试-1看看还能不能满足可行窗口
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        // 知道找到l和r最小间距的可行窗口，ansL == -1 的情况是一开始就没有找到r合适的位置
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    /**
     * 判断可行窗口是否已找到，即cnt中的元素及出现次数，
     * 是否已满足ori中的元素和次数要求
     */
    public boolean check() {
        Iterator iter = ori.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }

    // 下面这个据说是比官方解法更好的，但是一时半会没看懂，有空可以研究下
//    public String minWindow(String s, String t) {
//        if (s == null || s == "" || t == null || t == "" || s.length() < t.length()) {
//            return "";
//        }
//        //维护两个数组，记录已有字符串指定字符的出现次数，和目标字符串指定字符的出现次数
//        //ASCII表总长128
//        int[] need = new int[128];
//        int[] have = new int[128];
//
//        //将目标字符串指定字符的出现次数记录
//        for (int i = 0; i < t.length(); i++) {
//            need[t.charAt(i)]++;
//        }
//
//        //分别为左指针，右指针，最小长度(初始值为一定不可达到的长度)
//        //已有字符串中目标字符串指定字符的出现总频次以及最小覆盖子串在原字符串中的起始位置
//        int left = 0, right = 0, min = s.length() + 1, count = 0, start = 0;
//        while (right < s.length()) {
//            char r = s.charAt(right);
//            //说明该字符不被目标字符串需要，此时有两种情况
//            // 1.循环刚开始，那么直接移动右指针即可，不需要做多余判断
//            // 2.循环已经开始一段时间，此处又有两种情况
//            //  2.1 上一次条件不满足，已有字符串指定字符出现次数不满足目标字符串指定字符出现次数，那么此时
//            //      如果该字符还不被目标字符串需要，就不需要进行多余判断，右指针移动即可
//            //  2.2 左指针已经移动完毕，那么此时就相当于循环刚开始，同理直接移动右指针
//            if (need[r] == 0) {
//                right++;
//                continue;
//            }
//            //当且仅当已有字符串目标字符出现的次数小于目标字符串字符的出现次数时，count才会+1
//            //是为了后续能直接判断已有字符串是否已经包含了目标字符串的所有字符，不需要挨个比对字符出现的次数
//            if (have[r] < need[r]) {
//                count++;
//            }
//            //已有字符串中目标字符出现的次数+1
//            have[r]++;
//            //移动右指针
//            right++;
//            //当且仅当已有字符串已经包含了所有目标字符串的字符，且出现频次一定大于或等于指定频次
//            while (count == t.length()) {
//                //挡窗口的长度比已有的最短值小时，更改最小值，并记录起始位置
//                if (right - left < min) {
//                    min = right - left;
//                    start = left;
//                }
//                char l = s.charAt(left);
//                //如果左边即将要去掉的字符不被目标字符串需要，那么不需要多余判断，直接可以移动左指针
//                if (need[l] == 0) {
//                    left++;
//                    continue;
//                }
//                //如果左边即将要去掉的字符被目标字符串需要，且出现的频次正好等于指定频次，那么如果去掉了这个字符，
//                //就不满足覆盖子串的条件，此时要破坏循环条件跳出循环，即控制目标字符串指定字符的出现总频次(count）-1
//                if (have[l] == need[l]) {
//                    count--;
//                }
//                //已有字符串中目标字符出现的次数-1
//                have[l]--;
//                //移动左指针
//                left++;
//            }
//        }
//        //如果最小长度还为初始值，说明没有符合条件的子串
//        if (min == s.length() + 1) {
//            return "";
//        }
//        //返回的为以记录的起始位置为起点，记录的最短长度为距离的指定字符串中截取的子串
//        return s.substring(start, start + min);
//    }
}
