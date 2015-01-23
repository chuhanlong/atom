package testtest;

public class Node {

	int val;
	Node next = null;
	
	public Node(int val) {
		this.val = val;
	}
	
	public static Node reverse(Node head) {
		Node p1 = head;
		Node p2 = head.next;
		Node p3 = p2.next;
		p1.next = null;
		while (p3 != null) {
			p2.next = p1;
			p1 = p2;
			p2 = p3;
			p3 = p3.next;
		}
		p2.next = p1;
		return p2;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Node head = new Node(0);
		Node tail = head;
		for (int i = 1; i < 4; i++) {
			Node p = new Node(i);
			tail.next = p;
			tail = p;
		}
		head = reverse(head);
		while (head != null) {
			System.out.println(head.val);
			head = head.next;
		}
	}

}
