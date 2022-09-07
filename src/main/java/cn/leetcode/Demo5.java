package cn.leetcode;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 默认标题
 * 默认描述
 *
 * @author Letro Liu
 * @date 2022-08-17
 */
public class Demo5 {
    public static void main(String[] args) {
        String[] strArr = {
                "babad",
                "cbbd",
                "abcdefggggggggfedcasdf",
                "abcdefgggggggfedcasdf",
        };
        Map<Integer, List<String>> resMap = new HashMap<Integer, List<String>>();
        resMap.put(0, Lists.newArrayList("bab", "aba"));
        resMap.put(1, Lists.newArrayList("bb"));
        resMap.put(2, Lists.newArrayList("cdefggggggggfedc"));
        resMap.put(3, Lists.newArrayList("cdefgggggggfedc"));

        Demo5 obj = new Demo5();
        String r;
        System.out.println("-----------center---------");
        for (int i = 0; i < strArr.length; i++) {
            r = obj.longestPalindrome_center(strArr[i]);
            System.out.println(i + ":" + strArr[i] + "==>" + r + "-->" + resMap.get(i).contains(r));
        }
        System.out.println("-----------dynamic---------");
        for (int i = 0; i < strArr.length; i++) {
            r = obj.longestPalindrome_dynamic(strArr[i]);
            System.out.println(i + ":" + strArr[i] + "==>" + r + "-->" + resMap.get(i).contains(r));
        }
    }

    public String longestPalindrome_dynamic(String s) {
        char[] cs = s.toCharArray();
        int len = cs.length;
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        int maxIdx = 0, maxLen = 1;
        for (int l = 1; l < len; l++) {
            for (int i = 0, j = l; i < len && j < len; i++, j = i + l) {
                if (cs[i] == cs[j] && (l == 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (l + 1 > maxLen) {
                        maxIdx = i;
                        maxLen = l + 1;
                    }
                }
            }
        }
        return s.substring(maxIdx, maxIdx + maxLen);
    }

    private String longestPalindrome_center(String s) {
        int idx = 0, ln = 1;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            //right move
            int r = i;
            while (++r < len && s.charAt(i) == s.charAt(r)) {

            }
            //center both move
            int l = i, ct = 1;
            r--;
            while (l - ct >= 0 && r + ct < len && s.charAt(l - ct) == s.charAt(r + ct)) {
                ct++;
            }
            ct--;
            l = l - ct;
            r = r + ct;
            if (ln < r - l + 1) {
                ln = r - l + 1;
                idx = l;
            }
        }
        return s.substring(idx, idx + ln);
    }
}
