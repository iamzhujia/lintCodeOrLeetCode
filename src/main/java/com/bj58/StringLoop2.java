package com.bj58;

import java.util.List;

public class StringLoop2 {
    public String splitLoopedString(List<String> strs) {
        int n = strs.size();

        // 每个字符串翻转/不翻转, 选择字典序更大的那个, 连接入循环字符串中
        String loopstr = "";
        for (int i = 0; i < n; i++) {
            String s = strs.get(i);
            String rs = (new StringBuffer(s)).reverse().toString();
            if (s.compareTo(rs) < 0) {
                loopstr += rs;
            }
            else {
                loopstr += s;
            }
        }

        String ans = "";
        int sumlen = 0;
        for (int i = 0; i < n; i++) { // 枚举断点所在位置, 而断点所在位置的字符串可能需要翻转, 可能不需要
            int len = strs.get(i).length();
            String rloopstr = loopstr.substring(0, sumlen) +
                    (new StringBuffer(loopstr.substring(sumlen, sumlen + len))).reverse().toString() +
                    loopstr.substring(sumlen + len);
            // loopstr 是按照每个字符串翻转/不翻转字典序比较大的那个连接而得的循环字符串
            // rloopstr 是将当前枚举的断点所在的字符串与loopstr中相反的方向存在的循环字符串

            // 枚举断点
            for (int j = 0; j < len; j++) {
                String splitLoop = "";

                // 在两个循环字符串中取最大值
                splitLoop = loopstr.substring(sumlen + j) + loopstr.substring(0, sumlen + j);
                if (ans.compareTo(splitLoop) < 0) {
                    ans = splitLoop;
                }

                splitLoop = rloopstr.substring(sumlen + j) + rloopstr.substring(0, sumlen + j);
                if (ans.compareTo(splitLoop) < 0) {
                    ans = splitLoop;
                }
            }

            sumlen += len;
        }
        return ans;
    }
}
