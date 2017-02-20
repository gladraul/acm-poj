package poj;

import java.util.Scanner;

/**
 * Dividing问题
 * 描述：
 *   （1）分割大理石为相同的两份，每个大理石都有相应的价值，1~6
 * 输入：
 *   （1）n1 n2 n3 n4 n5 n6: ni表示价值i的大理石个数
 *   （2）0 0 0 0 0 0: 表示输入结束
 * 输出：
 *   （1）Collection #k:表示k组输入
 *   （2）存在两种结果：  "Can be divided." 或 "Can't be divided."
 *   （3）每组输出空一行
 * 思路：
 *   （1）输入总价值为偶数；
 *   （2）每次迭代，用总价值的一半减去较大价值的大理石的价值
 * @author liuxd
 *
 */
public class Poj1014_01 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int k = 0;
		while(input.hasNext()) {
			k++;
			
			int[] aValue = new int[6];
			String[] aInput = input.nextLine().split(" ");
			int sum = 0;
			for(int i = 0; i < 6; i++) {
				aValue[i] = Integer.parseInt(aInput[i]);
				sum += aValue[i] * (i + 1);
			}
			
			if(sum == 0) {
				break;
			}
			
			if(sum % 2 != 0) {
				System.out.println(new StringBuilder("Collection #").append(k).append(":"));
				System.out.println("Can't be divided.");
			}
			else {
				int half = sum / 2;
				int max = 5;
				while(true) {
					while(max >= 0 
							&& ((max + 1) > half || aValue[max] == 0)) {
						max--;
					}
					if(max < 0) {
						System.out.println(new StringBuilder("Collection #").append(k).append(":"));
						System.out.println("Can't be divided.");
						break;
					}
					else {
						half = half - (max + 1);
						aValue[max]--;
					}
					
					if(half == 0) {
						System.out.println(new StringBuilder("Collection #").append(k).append(":"));
						System.out.println("Can be divided.");
						break;
					}
				}
			}
			
			System.out.println();
		}
	}
}
