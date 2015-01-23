package com.chu.arithmetic;

import java.util.Arrays;

public class BubbleSort {

	public void sort(int[]	arr) {
		System.out.println("排序前" + Arrays.toString(arr));
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j+1]) {
					int tmp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = tmp;
				}
			}
			System.err.println("第" + i + "次排序：" + Arrays.toString(arr));
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BubbleSort obj = new BubbleSort();
		int[] arr = {123,6,7,56,83,99,1000,101,4,3,2,1,14,53};
		obj.sort(arr);
		System.out.println(Arrays.toString(arr));
	}

}
