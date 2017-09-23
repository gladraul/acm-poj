package poj;

import java.util.Scanner;
import java.util.Arrays;

/**
 * Enumeration Algorithm
 *
 * @author liuxd
 */
public class Poj1753_01 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		while(true) {
			char[] arrValue = new char[16];
			for(int i = 0; i < 4; i++) {
				if(input.hasNextLine()) {
					String tmp = input.next();
					for(int j = 0; j < 4; j++) {
						arrValue[4 * i + j] = tmp.charAt(j);
					}
				}
			}

			if(isGoal(arrValue)) {
				System.out.println("0");
				continue;
			}

			int num = 0;
			for(num = 1; num <= 16; num++) {
				int[] result = new int[num];
				if(flipByCombn(arrValue, 0, result, num, num)) {
					System.out.println(num);
					break;
				}
			}

			if(num == 17) {
				System.out.println("Impossible");
			}
		}
	}

	/**
	 * 使用组合的方式翻转数组
	 * @param arr 原始数组
	 * @param start 起始位置
	 * @param result 保留从原始数组中取出元素的索引
	 * @param count 待取出元素的数目
	 * @param num 组合元素总数目
	 */
	private static boolean flipByCombn(char[] arr, int start, int[] result, int count, int num) {
		for(int i = start; i < arr.length - count + 1; i++) {
			result[count - 1] = i;
			if(count - 1 == 0) {
				char[] tmpArray = Arrays.copyOf(arr, 16);
				for(int j = num - 1; j >= 0; j--) {
					flipArray(tmpArray, result[j]);
				}
				if(isGoal(tmpArray)) {
					return true;
				}
			}
			else {
				if(flipByCombn(arr, i + 1, result, count - 1, num)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 将数组按位置翻转
	 * @param arr
	 * @param index
	 */
	private static void flipArray(char[] arr, int index) {
		if(index - 1 >= 0
		&& (index - 1) % 4 != 3) {
			arr[index - 1] = flipChar(arr[index - 1]);
		}

		arr[index] = flipChar(arr[index]);

		if(index + 1 <= 15
		&& (index + 1) % 4 != 0) {
			arr[index + 1] = flipChar(arr[index + 1]);
		}

		if(index - 4 >= 0
		&& index / 4 > 0) {
			arr[index - 4] = flipChar(arr[index - 4]);
		}

		if(index + 4 <= 15
		&& index / 4 <  3) {
			arr[index + 4] = flipChar(arr[index + 4]);
		}
	}

	/**
	 * 判断是否是目标状态
	 * @param arr
	 * @return
	 */
	private static boolean isGoal(char[] arr) {
		for(int i = 1; i < arr.length; i++) {
			if(arr[i] != arr[0]) {
				return false;
			}
		}
		return true;
	}

	/**
	 *
	 * 翻转字符
	 * @param c
	 * @return
	 */
	private static char flipChar(char c) {
		if(c == 'w') {
			return 'b';
		}
		else {
			return 'w';
		}
	}
}
