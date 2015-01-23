package com.chu.arithmetic;

public class BinarySearch {

	public int search(int[] arr, int target, int start, int end) {
		int mid = (start + end)/2;
//		System.out.println(mid);
		if (arr[mid] == target) {
			return mid;
		} else if (arr[mid] < target) {
			return search(arr, target, mid + 1, end);
		} else if (arr[mid] > target) {
			return search(arr, target, start, mid-1);
		}
		return 0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearch search = new BinarySearch();
		
		int[]	 arr = {1,2,3,4,5,6,7,8,9,10};
		System.out.println(search.search(arr, 4, 0, arr.length-1));
	}

}
