package cn.leetcode;

/**
 * 2. 两数相加
 *
 * @author Letro Liu
 * @date 2022-09-09
 */
public class Demo2 {
    public static void main(String[] args) {
        ListNode[] arr1 = {
                create(2,4,3),
                create(0),
                create(9,9,9,9,9,9,9),
        };
        ListNode[] arr2 = {
                create(5,6,4),
                create(0),
                create(9,9,9,9),
        };
        ListNode[] res = {
                create(7,0,8),
                create(0),
                create(8,9,9,9,0,0,0,1),
        };
        for (int i = 0; i < arr1.length; i++) {
            ListNode rt = addTwoNumbers(arr1[i], arr2[i]);
            System.out.println(arr1[i] + "," + arr2[i] + "==>" + rt + "<==[exp]:" + res[i] + ",[rt]:" + (rt.equals(res[i])));
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int res = l1.val + l2.val;
        ListNode ret = new ListNode(res % 10);
        ListNode cur = ret;
        while (l1.next != null || l2.next != null) {
            res = res / 10;
            if (l1.next != null) {
                res += l1.next.val;
                l1 = l1.next;
            }
            if (l2.next != null) {
                res += l2.next.val;
                l2 = l2.next;
            }
            cur = cur.next = new ListNode(res % 10);
        }
        if (res > 9) {
            cur.next = new ListNode(res / 10);
        }
        return ret;
    }

    private static ListNode create(int ...values) {
        if (values == null || values.length == 0) {
            return null;
        }
        ListNode ret = new ListNode(values[0]);
        ListNode cur = ret;
        for (int i = 1; i < values.length; i++) {
            cur.next = new ListNode(values[i]);
            cur = cur.next;
        }
        return ret;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(String.valueOf(val));
            ListNode cur = this;
            while (cur.next != null) {
                cur = cur.next;
                sb.append(cur.val);
            }
            return sb.toString();
        }

        public boolean equals(ListNode o2) {
            return this.toString().equals(o2.toString());
        }
    }
}
