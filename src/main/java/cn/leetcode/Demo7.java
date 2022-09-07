package cn.leetcode;

/**
 * 7. 整数反转
 *
 * @author Letro Liu
 * @date 2022-08-25
 */
public class Demo7 {
    public static void main(String[] args) {
        int[] params = {
                123,
                -123,
                120,
                0,
                2147483647,
                -2147483648,
        };
        int[] results = {
                321,
                -321,
                21,
                0,
                0,
                0,
        };
        for (int i = 0; i < params.length; i++) {
            int rt = reverse(params[i]);
            System.out.println(params[i] + "=>" + rt + "-->" + results[i] + ":" + (results[i] == rt));
        }
    }

    public static int reverse(int x) {
        boolean flag = x > 0;
        int r = 0;
        for (int i = 0; i < 100; i++) {
            r = r * 10 + x % 10;
            x = x / 10;
            if (x == 0) {
                break;
            }
            if ((!flag && (r < Integer.MIN_VALUE / 10 || r * 10 < Integer.MIN_VALUE - x % 10))
                    || (flag && (r > Integer.MAX_VALUE / 10 || r * 10 > Integer.MAX_VALUE - x % 10))) {
                return 0;
            }
        }
        return r;
    }
}
