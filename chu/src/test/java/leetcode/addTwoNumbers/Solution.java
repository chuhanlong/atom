package leetcode.addTwoNumbers;
/**
 * 2
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
 * @author Administrator
 *
 */
public class Solution {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode ln = new ListNode(0);
		if (l1 == null) {
			l1 = new ListNode(0);
		}
		if (l2 == null) {
			l2 = new ListNode(0);
		}
		int result = l1.val + l2.val;
		ln.val = result % 10;
		if ((result /10) >= 1 || l1.next != null || l2.next != null) {
			ln.next = new ListNode(0);
			int nextresult = 0;
			if ((result /10) >= 1) {
				nextresult = ln.next.val + 1;
			}
			if (l1.next != null) {
				nextresult = nextresult + l1.next.val;
			} else {
				l1.next = new ListNode(0);
			}
			if (l2.next != null) {
				nextresult = nextresult + l2.next.val;
			} else {
				l2.next = new ListNode(0);
			}
			ln.next.val = nextresult % 10;
			if ((nextresult / 10) >= 1 || l1.next.next != null || l2.next.next != null) {
				ListNode l1next2 = null;
				ListNode l2next2 = null;
				if (l1.next == null) {
					l1next2 = null;
				} else {
					l1next2 = l1.next.next;
				}
				if (l2.next == null) {
					l2next2 = null;
				} else {
					l2next2 = l2.next.next;
				}
				ln.next.next  = addTwoNumbers(l1next2,l2next2);
				if ((nextresult / 10) >= 1) {
					ln.next.next.val = ln.next.next.val + 1;
					if (ln.next.next.val / 10 >= 1) {
						ListNode l1next3 = null;
						ListNode l2next3 = null;
						if (l1next2.next == null) {
							l1next3 = null;
						} else {
							l1next3 = l1.next.next;
						}
						if (l2next2.next == null) {
							l2next3 = null;
						} else {
							l2next3 = l2.next.next;
						}
						ln.next.next.next = addTwoNumbers(l1next3, l2next3);
					}
				}
			}
		}
		return ln;
	}
	
	public static void main(String[] args) {
		Solution ss = new Solution();
		ListNode l1 = new ListNode(8);
		l1.next = new ListNode(9);
		l1.next.next = new ListNode(9);
		ListNode l2 = new ListNode(2);
		ListNode ln = ss.addTwoNumbers(l1, l2);
		System.out.println(ln.val);
		System.out.println(ln.next.val);
		System.out.println(ln.next.next.val);
	}
}
