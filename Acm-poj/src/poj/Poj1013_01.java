package poj;

import java.util.Scanner;

/**
 * Accepted
 * Memory: 3024K, Time: 141MS
 * 描述：
 *   （1）12个硬币，其中11个是真品；
 *   （2）赝品与真品重量不同，但不知道重或轻，真品的重量是相同的；
 *   （3）使用天平秤，三次内找到赝品硬币；
 * 输入：
 *   （1）第一行是情况数目n，从1开始；
 *   （2）每一个情况包括三行输入，代表每次测量；
 *   （3）编号使用A--L。每一行的测量包括三个字符串，
 *        第一个代表天平左侧，第二个代表天平右侧，
 *        第三个代表测量结果，右侧up, down, even；
 *   （4）天平两侧硬币个数相同，但不一定是四个。
 * @author liuxd
 *
 */
public class Poj1013_01 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		while(num != 0) {
			boolean [] aCoin = new boolean [12]; //标记绝对为真币的字母，默认都是false
			int [] aWeigh = new int [12]; //标记硬币的轻重程度，轻--，重++
			char aLeft[][] = new char[3][6]; //天平左侧
			char aRight[][] = new char[3][6]; //天平右侧
			char aStatus[][] = new char[3][4]; //天平状态
			for(int i = 0; i < 3; i++) {
				aLeft[i] = input.next().toCharArray();
				aRight[i] = input.next().toCharArray();
				aStatus[i] = input.next().toCharArray();
			}
			for(int i = 0; i < 3; i++) {
				switch(aStatus[i][0]) {
					case 'u':  //up， 天平左重
						for(int j = 0; j < aLeft[i].length; j++) {
							aWeigh[aLeft[i][j] - 'A']++; //左重
							aWeigh[aRight[i][j] - 'A']--; //右轻
						}
						break;
						
					case 'd':  //down，天平右重
						for(int j = 0; j < aLeft[i].length; j++) {
							aWeigh[aLeft[i][j] - 'A']--; //左轻
							aWeigh[aRight[i][j] - 'A']++; //右重
						}
						break;
						
					case 'e':  //even，天平平衡
						for(int j = 0; j < aLeft[i].length; j++) {
							aCoin[aLeft[i][j] - 'A'] = true; //绝对真币
							aCoin[aRight[i][j] - 'A'] = true; //绝对真币
						}
						break;
				}
			}
			
			int index = 0, max = 1;
			for(int i = 0; i < 12; i++) {
				if(aCoin[i] == true)
					continue;

				if(Math.abs(aWeigh[i]) >= max) {
					index = i;
					max = Math.abs(aWeigh[i]);
				}
			}

			System.out.print((char)('A' + index) + " is the counterfeit coin and it is ");
			if(aWeigh[index] < 0) {
				System.out.println("light.");
			} else {
				System.out.println("heavy.");
			}
			num--;
		}
	}

}
