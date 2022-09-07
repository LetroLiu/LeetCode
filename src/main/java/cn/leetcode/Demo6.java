package cn.leetcode;

import java.util.Arrays;

/**
 * Z 字形变换
 *
 * @author Letro Liu
 * @date 2022-08-24
 */
public class Demo6 {
    public static void main(String[] args) {
        String[] arr = {
                "PAYPALISHIRING",
                "PAYPALISHIRING",
                "A",
        };
        int[] rows = {
                3,
                4,
                1,
        };
        String[] rs = {
                "PAHNAPLSIIGYIR",
                "PINALSIGYAHRPI",
                "A",
        };
        for (int i = 0; i < arr.length; i++) {
            String rt = convert(arr[i], rows[i]);
            System.out.println(arr[i] + "<:>" + rows[i] + "==>" + rt + "->" + (rs[i].equals(rt)) + "<===" + rs[i]);
        }
        /*
        demo 1:=================
        P   A   H   N
        A P L S I I G
        Y   I   R
        demo 2:=================
        P     I    N
        A   L S  I G
        Y A   H R
        P     I
        demo 3:=================
        A
         */
    }

    public static String convert(String s, int numRows) {
        int len = s.length();
        if (numRows == 1 || numRows >= len) {
            return s;
        }
        //周期长度
        int cycleLen = numRows * 2 - 2;
        //周期数
//        int cycleQty = len / cycleLen + (len % cycleLen > 0 ? 1 : 0);
        char[] cs = new char[len];
        Arrays.fill(cs, '\0');
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sbs[i] = new StringBuilder();
        }
//        StringBuilder log = new StringBuilder();
        for (int i = 0; i < len; i++) {
            //int t = i / cycleLen;//周期序号0-n
            int n = i % cycleLen;//周期内序号
            int x = n < numRows ? n : (numRows - n % numRows - 2);//行号
//            log.append(x);
            //int y = n < numRows ? 0 : (n + 1) % numRows;//列号
            sbs[x].append(s.charAt(i));
        }
        for (int i = 1; i < sbs.length; i++) {
            sbs[0].append(sbs[i]);
        }
//        System.out.println("===>" + log.toString());
        return sbs[0].toString();
    }
}
