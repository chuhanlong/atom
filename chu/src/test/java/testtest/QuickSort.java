package testtest;

import java.util.Arrays;

public class QuickSort {

	public static void sort(int[] arr, int start, int end) {
		if (arr == null || (end - start + 1) < 2) {
			return;
		}
		int position= getPosition(arr, start, end);
		if (position == start) {
			sort(arr, start + 1, end);
		}
		if (position == end) {
			sort(arr, start, position-1);
		} else {
			sort(arr, start, position -1);
			sort(arr, position+1, end);
		}
	}
	
	public static int getPosition(int[] arr, int start, int end) {
		int temp = arr[start];
		while (start < end ) {
			while (start < end && arr[end] > temp) {
				end--;
			}
			arr[start] = arr[end];
			while (start < end && arr[start] < temp) {
				start++;
			}
			arr[end] = arr[start];
		}
		arr[start] = temp;
		return start;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {17,5,6,2,4,8,9,134,156,122,11,45};
		System.out.println(Arrays.toString(arr));
		sort(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
	}

}
