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

	public ListNode addTowNumbers(ListNode l1, ListNode l2) {
		ListNode ln = new ListNode(0);
		int result = (l1==null?0:l1.val) + (l2==null?0:l2.val);
		ln.val = result % 10;
		if ((result /10) >= 1) {
			ln.next = new ListNode(0);
			ln.val = (result % 10);
			if (l1.next != null) {
				ln.next.val = ln.next.val + 1;
			}
		}
	
		if (l1.next != null || l2.next != null) {
			ln.next = addTowNumbers(l1.next, l2.next);
		}
		return ln;
	}
	
	public static void main(String[] args) {
		Solution ss = new Solution();
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(8);
		ListNode l2 = new ListNode(0);
		ListNode ln = ss.addTowNumbers(l1, l2);
		System.out.println(ln.val);
		System.out.println(ln.next.val);
	}
}
