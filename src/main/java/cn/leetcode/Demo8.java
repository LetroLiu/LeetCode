package cn.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 8. 字符串转换整数 (atoi)
 *
 * @author Letro Liu
 * @date 2022-08-26
 */
public class Demo8 {
    public static void main(String[] args) {
        String[] arr = {
                "",
                "42",
                "   -42",
                "4193 with words",
                "  +009.abc",
                "x1",
                "-91283472332",
                "00000-42a1234",
                "+-12",
        };
        int[] res = {
                0,
                42,
                -42,
                4193,
                9,
                0,
                -2147483648,
                0,
                0
        };
        System.out.println("==============状态流=====================");
        for (int i = 0; i < arr.length; i++) {
            int rt = myAtoi_state_flow(arr[i]);
            System.out.println(arr[i] + "==>" + rt + "<==[exp]:" + res[i] + ",[rt]:" + (rt == res[i]));
        }
        System.out.println("==============自动机=====================");
        for (int i = 0; i < arr.length; i++) {
            int rt = myAtoi_automaton(arr[i]);
            System.out.println(arr[i] + "==>" + rt + "<==[exp]:" + res[i] + ",[rt]:" + (rt == res[i]));
        }
    }

    public static int myAtoi_state_flow(String s) {
        int len = s.length();
        int state = -1;
        int sign = 1;
        int res = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == ' ' && state <= 0) {
                state = 0;
            } else if (c == '+' && state <= 0) {
                state = 1;
            } else if (c == '-' && state <= 0) {
                state = 1;
                sign = -1;
            } else if (Character.isDigit(c) && state <= 2) {
                state = 2;
                int v = c - '0';
                if (sign == 1 && (res > Integer.MAX_VALUE / 10 || res * 10 > Integer.MAX_VALUE - v)) {
                    return Integer.MAX_VALUE;
                } else if (sign == -1 && (-res < Integer.MIN_VALUE / 10 || -res * 10 < Integer.MIN_VALUE + v)) {
                    return Integer.MIN_VALUE;
                }
                res = res * 10 + v;
            } else {
                break;
            }
        }
        return res * sign;
    }

    public static int myAtoi_automaton(String s) {
        Automaton.reset();
        for (int i = 0; i < s.length(); i++) {
            Automaton.get(s.charAt(i));
        }
        return Automaton.getResult();
    }

    static class Automaton {
        static int res = 0;
        static int sign = 1;
        static String state = "start";
        static Map<String, String[]> autoMap = new HashMap<String, String[]>(4) {{
            put("start", new String[] {"start", "signed", "number", "end"});
            put("signed", new String[] {"end", "end", "number", "end"});
            put("number", new String[] {"end", "end", "number", "end"});
            put("end", new String[] {"end", "end", "end", "end"});
        }};

        static int getResult() {
            if (sign == 0) {
                return res;
            }
            return sign * res;
        }

        static void get(char c) {
            if ("end".equals(state)) {
                return;
            }
            state = autoMap.get(state)[getCol(c)];
            if ("signed".equals(state) && c == '-') {
                sign = -1;
            } else if ("number".equals(state)) {
                int v = c - '0';
                if (sign == 1 && (res > Integer.MAX_VALUE / 10 || res * 10 > Integer.MAX_VALUE - v)) {
                    state = "end";
                    res = Integer.MAX_VALUE;
                    sign = 0;
                } else if (sign == -1 && (-res < Integer.MIN_VALUE / 10 || -res * 10 < Integer.MIN_VALUE + v)) {
                    state = "end";
                    res = Integer.MIN_VALUE;
                    sign = 0;
                } else {
                    res = res * 10 + v;
                }
            }
        }

        static int getCol(char c) {
            if (c == ' ') {
                return 0;
            } else if (c == '-' || c == '+') {
                return 1;
            } else if (c >= '0' && c <= '9') {
                return 2;
            }
            return 3;
        }

        static void reset() {
            state = "start";
            res = 0;
            sign = 1;
        }
    }
}
