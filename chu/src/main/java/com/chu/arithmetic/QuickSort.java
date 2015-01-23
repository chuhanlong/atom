package com.chu.arithmetic;

import java.util.Arrays;


public class QuickSort {

		public static void main(String[] args) {
			int[] array = {7,8,4,2,1,9,50,11,-1,-8};

			System.out.println("Before sort:");
			System.out.println(Arrays.toString(array));

			quickSort(array);

			System.out.println("After sort:");
			System.out.println(Arrays.toString(array));
		}

		public static void quickSort(int[] array) {
			subQuickSort(array, 0, array.length - 1);
		}

		private static void subQuickSort(int[] array, int start, int end) {
			if (array == null || (end - start + 1) < 2) {
				return;
			}

			int part = getMiddle(array, start, end);

			if (part == start) {
				subQuickSort(array, part + 1, end);
			} else if (part == end) {
				subQuickSort(array, start, part - 1);
			} else {
				subQuickSort(array, start, part - 1);
				subQuickSort(array, part + 1, end);
			}
		}

		public static int getMiddle(int[] list, int low, int high) {
			int tmp = list[low];    //数组的第一个作为中轴
			while (low < high) {
				while (low < high && list[high] > tmp) {
					high--;
				}
				list[low] = list[high];   //比中轴小的记录移到低端
				while (low < high && list[low] < tmp) {
					low++;
				}
				list[high] = list[low];   //比中轴大的记录移到高端
			}
			list[low] = tmp;              //中轴记录到尾
			return low;                   //返回中轴的位置
		}
	}