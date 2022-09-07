package cn.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. 两数之和
 *
 * @author Letro Liu
 * @date 2022-08-15
 */
public class Demo1 {

    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;
        nums = twoSum(nums, target);
        System.out.println(nums[0] + ":" + nums[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(nums.length);
        int idx = 0;
        for(int n : nums) {
            map.put(target - n, idx++);
        }
        idx = 0;
        for(int n : nums) {
            Integer i = map.get(n);
            if (i != null && i != idx) {
                return i < idx ? new int[] {i, idx} : new int[] {idx, i};
            }
            idx++;
        }
        return new int[0];
    }
}
