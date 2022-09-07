package cn.leetcode;

/**
 * 9. 回文数
 *
 * @author Letro Liu
 * @date 2022-08-26
 */
public class Demo9 {
    public static void main(String[] args) {
        Integer[] arr = {
                121,
                -121,
                10,
                88888,
                0,
        };
        boolean[] res = {
                true,
                false,
                false,
                true,
                true,
        };
        for (int i = 0; i < arr.length; i++) {
            boolean rt = isPalindrome(arr[i]);
            System.out.println(arr[i] + "==>" + rt + "<==[exp]:" + res[i] + ",[rt]:" + (rt == res[i]));
        }
    }

    public static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        } else if (x <= 9) {
            return true;
        }
        String s = String.valueOf(x);
        int len = s.length();
        int times = len / 2;
        for (int i = 0; i < times; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
