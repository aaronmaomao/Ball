package com.ball.m.commom;

import java.util.Arrays;

public class Util {
	public static int[] orderAsc(int[] arr) {
		for (int j = arr.length; j > 0; j--)
			for (int i = 0; i < j - 1; i++) {
				if (arr[i] > arr[i + 1]) {
					int temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
				}
			}
		return arr;
	}

	public static int[] concat(int[] arr, int newNum) {
		int[] temp = new int[arr.length + 1];
		for (int i = 0; i < arr.length; i++) {
			temp[i] = arr[i];
		}
		temp[arr.length] = newNum;
		return temp;
	}

	public static int[] concat(int[] arr1, int[] arr2) {
		int[] temp = new int[arr1.length + arr2.length];
		for (int i = 0; i < arr1.length; i++) {
			temp[i] = arr1[i];
		}
		for (int i = 0; i < arr2.length; i++) {
			temp[arr1.length + i] = arr2[i];
		}
		return temp;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(orderAsc(new int[] { 3, 0, 1, -1, 6, 9, 2, 1 })));
	}
}
