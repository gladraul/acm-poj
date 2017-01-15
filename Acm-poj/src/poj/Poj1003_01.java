package poj;

import java.util.Scanner;

/**
 * Accepted
 * Hangover Problem
 * @author Administrator
 * 
 * Input:
 *     The input consists of one or more test cases, 
 *     followed by a line containing the number 0.00 that signals the end of the input. 
 *     Each test case is a single line containing a positive floating-point number c 
 *     whose value is at least 0.01 and at most 5.20; 
 *     c will contain exactly three digits.
 * Output:
 *     For each test case, 
 *     output the minimum number of cards necessary to achieve an overhang of at least c card lengths. 
 *     Use the exact output format shown in the examples.
 * Sample Input:
 *     1.00
 *     3.71
 *     0.04
 *     5.19
 *     0.00
 * Sample Output:
 *     3 card(s)
 *     61 card(s)
 *     1 card(s)
 *     273 card(s)
 */
public class Poj1003_01 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		float f = 0;
		while((f = input.nextFloat()) != 0.00) {
			System.out.println(cards(f) + " card(s)");
		}
	}
	
	/**
	 * 计算需要的纸牌数
	 * @param f 长度和
	 * @return 纸牌数
	 */
	private static int cards(float f) {
		int n = 1;
		while(true) {
			if(hangover(n) >= f) {
				return n;
			}
			n++;
		}
	}
	
	/**
	 * 递归计算n张纸牌的长度和
	 * @param n 纸牌数
	 * @return 长度和
	 */
	private static float hangover(int n) {
		if(n == 1) {
			return 0.5f;
		}
		else {
			return hangover(n - 1) + 1.0f / ( n + 1); 
		}
	}
	
}
