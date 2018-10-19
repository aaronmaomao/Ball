package com.ball.web.commom;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ball.web.entity.Ssq;

public class Util {

	public static DateFormat df1 = new SimpleDateFormat("yy-MM-dd");
	public static SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");

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

	public static Map<String, String> formData2Dic(String formData) {
		Map<String, String> result = new HashMap<>();
		if (formData == null || formData.trim().length() == 0) {
			return result;
		}
		final String[] items = formData.split("&");
		Arrays.stream(items).forEach(item -> {
			final String[] keyAndVal = item.split("=");
			if (keyAndVal.length == 2) {
				try {
					final String key = URLDecoder.decode(keyAndVal[0], "utf8");
					final String val = URLDecoder.decode(keyAndVal[1], "utf8");
					result.put(key, val);
				} catch (UnsupportedEncodingException e) {
				}
			}
		});
		return result;
	}

	public static int halfSearch(int[] array, int value, boolean isApproch) {
		int l = 0, c = (array.length - 1) / 2, r = array.length - 1, temp;
		if (value > array[array.length - 1]) {
			c = array.length - 1;
		} else if (value < array[0]) {
			c = 0;
		} else {
			while (l != c || c != r) {
				long num = array[c];
				if (num == value) {
					break;
				} else if (num > value) {
					temp = c - 1;
					c = (temp - 1) / 2 + 1;
					r = temp;
				} else {
					temp = c + 1;
					c = (r - temp) / 2 + temp;
					l = temp;
				}
			}
		}
		if (!isApproch && value != array[c]) {
			return -1;
		}
		return c;
	}

	public static int halfSearch(Date[] array, Date value, boolean isApproch) {
		int l = 0, c = (array.length - 1) / 2, r = array.length - 1, temp;
		if (value.compareTo(array[array.length - 1]) == 0) {
			c = array.length - 1;
		} else if (value.compareTo(array[0]) < 0) {
			c = 0;
		} else {
			while (l != c || c != r) {
				Date date = array[c];
				if (date.compareTo(value) == 0) {
					break;
				} else if (date.compareTo(value) > 0) {
					temp = c - 1;
					c = (temp - 1) / 2 + 1;
					r = temp;
				} else {
					temp = c + 1;
					c = (r - temp) / 2 + temp;
					l = temp;
				}
			}
		}
		if (!isApproch && value.compareTo(array[c]) != 0) {
			return -1;
		}
		return c;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(orderAsc(new int[] { 3, 0, 1, -1, 6, 9, 2, 1 })));
	}
}
