package com.chu.arithmetic;

import java.util.Arrays;

/**
 * 直接选择排序
 * 时间复杂度O(n2)
 * @author Administrator
 *
 */
public class InsertSort {

	public void sort(int[] arr) {
		System.out.println("排序前: " + Arrays.toString(arr));
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j-1] > arr[j]) {
					int temp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = temp;
				}else {
					break;
				}
			}
			System.err.println("第" + i + "次排序：" + Arrays.toString(arr));
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InsertSort obj = new InsertSort();
		int[] arr = {123,6,7,56,83,99,1000,101,4,3,2,1,14,53};
		obj.sort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
