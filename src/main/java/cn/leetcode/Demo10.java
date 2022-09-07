package cn.leetcode;

/**
 * 10. 正则表达式匹配
 *
 * @author Letro Liu
 * @date 2022-08-26
 */
public class Demo10 {
    public static void main(String[] args) {
        String[] arr = {
                "aa",
                "aa",
                "ab",
        };
        String[] ps = {
                "a",
                "a*",
                ".*",
        };
        boolean[] res = {
                false,
                true,
                true,
        };
        for (int i = 0; i < arr.length; i++) {
            boolean rt = isMatch(arr[i], ps[i]);
            System.out.println(arr[i] + "==>" + rt + "<==[exp]:" + res[i] + ",[rt]:" + (rt == res[i]));
        }
    }

    public static boolean isMatch(String s, String p) {

        return false;
    }
}
