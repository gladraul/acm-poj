package poj;

import java.util.Scanner;

/**
 * Accepted 
 * Memory: 3376K 
 * Time: 1407MS
 * @author liuxd
 *
 */
public class Poj1007_02 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String[] arr = input.nextLine().split(" ");
		KeyValue[] list = new KeyValue[Integer.valueOf(arr[1])];
		for(int i = 0; i < Integer.valueOf(arr[1]); i++) {
			String str = input.next();
			list[i] = new KeyValue(str, inversions(str, Integer.valueOf(arr[0])));
		}

		quickSort(list, 0, Integer.valueOf(arr[1]) - 1);

		for(int i = 0; i < Integer.valueOf(arr[1]); i++) {
			System.out.println(list[i].getKey());
		}
	}

	/**
	 * 计算逆序数
	 * @param str 字符串
	 * @param n 字符串长度
	 * @return 逆序数
	 */
	private static int inversions(String str, int n) {
		int answer = 0;
		int A = 0, C = 0, G = 0;
		for(int i = n - 1; i >= 0; i--) {
			switch(str.charAt(i)) {
				case 'A': 
					A++; 
					break;
				case 'C': 
					C++;
					answer += A;
					break;
				case 'G':
					G++;
					answer += A + C;
					break;
				case 'T':
					answer += A + C + G;
					break;
			}
		}
		return answer;
	}
	
	/**
	 * 快速排序
	 * @param arr
	 * @param low
	 * @param high
	 */
	private static void quickSort(KeyValue[] arr, int low, int high) {
		if(low < high) {
			int mid = getMiddle(arr, low, high);
			quickSort(arr, low, mid - 1);
			quickSort(arr, mid + 1, high);
		}
	}

	/**
	 * 找中间值
	 * @param arr
	 * @param low
	 * @param high
	 * @return 中间值的位置
	 */
	private static int getMiddle(KeyValue[] arr, int low, int high) {
		KeyValue tmp = arr[low];

		while(low < high) {
			while(low < high && arr[high].getValue() >= tmp.getValue()) {
				high--;
			}
			arr[low] = arr[high];
			while(low < high && arr[low].getValue() <= tmp.getValue()) {
				low++;
			}
			arr[high] = arr[low];
		}

		arr[low] = tmp;
		return low;
	}
	
	private static class KeyValue {
		private String key;
		private int value;
		
		public KeyValue(String key, int value) {
			this.key = key;
			this.value = value;
		}
		
		public int getValue() {
			return this.value;
		}
		
		public String getKey() {
			return this.key;
		}
	}

}
