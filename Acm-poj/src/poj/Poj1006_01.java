package poj;

import java.util.Scanner;

/**
 * Accepted
 * 生理周期问题（中国剩余定理）
 * Input:
 *     输入四个整数：p, e, i和d。 p, e, i分别表示体力、情感和智力高峰出现的时间（时间从当年的第一天开始计算）。
 *     d 是给定的时间，可能小于p, e, 或 i。 所有给定时间是非负的并且小于365, 所求的时间小于21252。 
 *     当p = e = i = d = -1时，输入数据结束。
 * Output:
 *     从给定时间起，下一次三个高峰同天的时间（距离给定时间的天数）。 
 *     采用以下格式： 
 *     Case 1: the next triple peak occurs in 1234 days. 
 *     注意：即使结果是1天，也使用复数形式“days”。
 * Sample Input:
 *     0 0 0 0
 *     0 0 0 100
 *     5 20 34 325
 *     4 5 6 7
 *     283 102 23 320
 *     203 301 203 40
 *     -1 -1 -1 -1
 * Sample Output:
 *     Case 1: the next triple peak occurs in 21252 days.
 *     Case 2: the next triple peak occurs in 21152 days.
 *     Case 3: the next triple peak occurs in 19575 days.
 *     Case 4: the next triple peak occurs in 16994 days.
 *     Case 5: the next triple peak occurs in 8910 days.
 *     Case 6: the next triple peak occurs in 10789 days.
 *     
 * @author Administrator
 *
 */
public class Poj1006_01 {
	private static final int P = 23;
	private static final int E = 28;
	private static final int I = 33;
	private static final int PEI = 21252;
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String str = "";
		String[] arr = new String[4];
		int id = 1;
		while(!(str = input.nextLine()).equals("-1 -1 -1 -1")) {
			arr = str.split(" ");
			int p = Integer.valueOf(arr[0]) % P;
			int e = Integer.valueOf(arr[1]) % E;
			int i = Integer.valueOf(arr[2]) % I;
			int d = Integer.valueOf(arr[3]);
			int x = (invert(E * I, P) * E * I * p 
				  + invert(P * I, E) * P * I * e
				  + invert(P * E, I) * P * E * i
				  - d  + PEI) % PEI;
			if(x == 0) {
				x = PEI;
			}
			System.out.println("Case " + id++ + ": the next triple peak occurs in " + x + " days.");		
			
		}
	}
	
	/**
	 * 求e在模mod下的数论倒数
	 * @param e
	 * @param mod 模数
	 * @return 数论倒数
	 */
	private static int invert(int e, int mod) {
		int a = mod, b = e, t1 = 0, t2 = 1;
		while(b != 0) {
			int t = a; //临时
			a = b;
			int q = t / b; //商
			b = t % b; //余数
			
			t = t1 - q * t2;
			t1 = t2;
			t2 = t;
		}
		
		//扩展欧几里得算法得到的结果可能为负数，所以需要把它“掰正”
		if(t1 < 0) {
			t1 += mod;
		}
		
		return t1;
	}
}
