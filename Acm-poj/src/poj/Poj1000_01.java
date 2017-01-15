package poj;

import java.util.Scanner;

/**
 * Accepted
 * A+B 问题
 * 输入：两个整数a和b，其中0≤a, b≤10
 * 输出：a与b的和
 * 输入例如：1 2
 * 输出例如：3
 * @author liuxd
 *
 */
public class Poj1000_01 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int a = s.nextInt(), b = s.nextInt();
		System.out.println(a + b);
	}

}
