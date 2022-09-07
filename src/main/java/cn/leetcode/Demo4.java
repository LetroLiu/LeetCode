package cn.leetcode;

/**
 * 默认标题
 * 默认描述
 *
 * @author Letro Liu
 * @date 2022-08-18
 */
public class Demo4 {
    public static void main(String[] args) {
        int[] arr1 = {1, 3};
        int[] arr2 = {2};
        System.out.println("1====2>" + calculate(arr1, arr2));
        arr1 = new int[]{1, 2};
        arr2 = new int[]{3, 4};
        System.out.println("2====2.5>" + calculate(arr1, arr2));
        arr1 = new int[]{2, 2, 2, 2, 2, 2, 2, 2};
        arr2 = new int[]{1, 1, 2, 3, 3, 3, 3, 4};
        System.out.println("3====2>" + calculate(arr1, arr2));
        arr1 = new int[]{};
        arr2 = new int[]{1, 3, 4};
        System.out.println("4====3>" + calculate(arr1, arr2));
        arr1 = new int[]{};
        arr2 = new int[]{1, 2, 3, 4};
        System.out.println("5====2.5>" + calculate(arr1, arr2));
        arr1 = new int[]{};
        arr2 = new int[]{1};
        System.out.println("6====1>" + calculate(arr1, arr2));
        arr1 = new int[]{3, 7, 9};
        arr2 = new int[]{1, 4, 5, 8};
        System.out.println("6====5>" + calculate(arr1, arr2));
        arr1 = new int[]{1,3,9,16,27};
        arr2 = new int[]{2,5,8,13,19};
        System.out.println("7====8.5>" + calculate(arr1, arr2));
        arr1 = new int[]{1,3,9,16,27};
        arr2 = new int[]{2,5,8,13,19,23};
        System.out.println("8====9>" + calculate(arr1, arr2));
        arr1 = new int[]{1};
        arr2 = new int[]{2,3,4,5};
        System.out.println("9====3>" + calculate(arr1, arr2));
        arr1 = new int[]{1};
        arr2 = new int[]{2,3,4,5,6,7};
        System.out.println("10====4>" + calculate(arr1, arr2));
        arr1 = new int[]{};
        arr2 = new int[]{1,2,3,4,5};
        System.out.println("11====3>" + calculate(arr1, arr2));
        arr1 = new int[]{};
        arr2 = new int[]{1,2,3,4,5,6};
        System.out.println("12====3.5>" + calculate(arr1, arr2));
    }

    private static double calculate(int[] nums1, int[] nums2) {
        int l1 = nums1.length, l2 = nums2.length;
        int ct = l1 + l2;
        int k = (ct + 1) >> 1;
        boolean isEven = (ct & 1) == 0;

        int s1 = 0, s2 = 0;
        boolean notFirst = false;
        while (k > 1) {
            //when has empty arr
            if (l1 == 0 || s1 == l1) {
                if (isEven) {
                    return (nums2[s2 + k - 1] + nums2[s2 + k]) / 2.0d;
                } else {
                    return nums2[s2 + k - 1];
                }
            } else if (l2 == 0 || s2 == l2) {
                if (isEven) {
                    return (nums1[s1 + k - 1] + nums1[s1 + k]) / 2.0d;
                } else {
                    return nums1[s1 + k - 1];
                }
            }
            //二分查找
            int n = k >> 1;
            int a, b, i, j;
            if (l1 > n) {
                i = s1 + n - 1;
            } else {
                i = l1 - 1;
            }
            a = nums1[i];
            if (l2 > n) {
                j = s2 + n - 1;
            } else {
                j = l2 - 1;
            }
            b = nums2[j];
            if (a >= b) {
                k -= j - s2 + 1;
                //arr2 remove 0..j
                s2 += j - s2 + 1;
            } else {
                k -= i - s1 + 1;
                //arr1 remove 0..i
                s1 += i - s1 + 1;
            }
        }
        //奇偶情况结果获取
        System.out.println(s1 + ":" + s2);
        if (isEven) {
            int x = 2;
            int d = 0;
            while (x-- > 0) {
                if (s1 <= l1 - 1 && s2 <= l2 - 1) {
                    if (nums1[s1] < nums2[s2]) {
                        d += nums1[s1++];
                    } else {
                        d += nums2[s2++];
                    }
                } else if (s1 <= l1 - 1) {
                    d += nums1[s1++];
                } else {
                    d += nums2[s2++];
                }
            }
            return d / 2.0;
        } else {
            if (s1 <= l1 - 1 && s2 <= l2 -1) {
                return Math.min(nums1[s1], nums2[s2]);
            } else if (s1 <= l1 - 1) {
                return nums1[s1];
            } else {
                return nums2[s2];
            }
        }
    }



    public double findMedianSortedArrays_demo2(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        int midIndex = totalLength / 2;
        if (totalLength % 2 == 1) {
            return getKthElement(nums1, nums2, midIndex + 1);
        } else {
            return (getKthElement(nums1, nums2, midIndex) + getKthElement(nums1, nums2, midIndex + 1)) / 2.0;
        }
    }

    public int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }
}
