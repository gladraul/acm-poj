package poj;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Acceptd
 * Memory: 3356K
 * Time: 1579MS
 * @author liuxd
 * Description:
 *     One measure of ``unsortedness'' in a sequence is the number 
 *     of pairs of entries that are out of order with respect to each other. 
 *     For instance, in the letter sequence ``DAABEC'', 
 *     this measure is 5, since D is greater than four letters to its right 
 *     and E is greater than one letter to its right. 
 *     This measure is called the number of inversions in the sequence. 
 *     The sequence ``AACEDGG'' has only one inversion (E and D)
 *     ---it is nearly sorted---while the sequence ``ZWQM'' has 6 inversions 
 *     (it is as unsorted as can be---exactly the reverse of sorted). 
 *     You are responsible for cataloguing a sequence of DNA strings
 *     (sequences containing only the four letters A, C, G, and T). 
 *      However, you want to catalog them, not in alphabetical order, 
 *      but rather in order of ``sortedness'', 
 *      from ``most sorted'' to ``least sorted''. 
 *      All the strings are of the same length. 
 * Input:
 *     The first line contains two integers: a positive integer n (0 < n <= 50) 
 *     giving the length of the strings; and a positive integer m (0 < m <= 100) 
 *     giving the number of strings. These are followed by m lines, 
 *     each containing a string of length n.
 * Output:
 *     Output the list of input strings, arranged from ``most sorted'' 
 *     to ``least sorted''. Since two strings can be equally sorted, 
 *     then output them according to the original order.
 * Sample Input:
 *     10 6
 *     AACATGAAGG
 *     TTTTGGCCAA
 *     TTTGGCCAAA
 *     GATCAGATTT
 *     CCCGGGGGGA
 *     ATCGATGCAT
 * Sample Output:
 *     CCCGGGGGGA
 *     AACATGAAGG
 *     GATCAGATTT
 *     ATCGATGCAT
 *     TTTTGGCCAA
 *     TTTGGCCAAA
 */
public class Poj1007_01 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String[] arr = input.nextLine().split(" ");
		List<KeyValue> lists = new LinkedList<KeyValue>();
		for(int i = 0; i < Integer.valueOf(arr[1]); i++) {
			String str = input.next();
			KeyValue kv = new KeyValue(str, inversions(str, Integer.valueOf(arr[0])));
			binaryInsert(lists, kv);
		}
		
		for(int i = 0; i < Integer.valueOf(arr[1]); i++) {
			System.out.println(lists.get(i).getKey());
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
	 * 二分插入
	 * @param lists
	 * @param kv
	 */
	private static void binaryInsert(List<KeyValue> lists, KeyValue kv) {
		int low = 0, high = lists.size() - 1, mid;
		while(low <= high) {
			mid = (high + low) >> 1;
			if(kv.getValue() == lists.get(mid).getValue()) {
				lists.add(mid + 1, kv);
				return;
			}
			else if(kv.getValue() > lists.get(mid).getValue()) {
				low = mid + 1;
			}
			else {
				high = mid - 1;
			}
		}

		lists.add(low, kv);
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
