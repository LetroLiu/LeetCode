package cn.leetcode;

/**
 * 8. 字符串转换整数 (atoi)
 *
 * @author Letro Liu
 * @date 2022-08-26
 */
public class DemoTemplate {
    public static void main(String[] args) {
        String[] arr = {
                "",
        };
        int[] res = {
                0,
        };
        for (int i = 0; i < arr.length; i++) {
            int rt = myAtoi(arr[i]);
            System.out.println(arr[i] + "==>" + rt + "<==[exp]:" + res[i] + ",[rt]:" + (rt == res[i]));
        }
    }

    public static int myAtoi(String s) {


        return 0;
    }
}
