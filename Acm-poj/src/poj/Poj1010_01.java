package poj;

import java.util.Scanner;

/**
 * Accepted
 * Memory: 3156K
 * Time: 157MS
 * 
 * Stamps问题
 * 深度优先搜索 Depth-First-Search
 * 描述:
 *   1、邮票种类不多于25种。
 *   2、顾客一次最多买4张邮票。
 *   3、输入为两行：第一行为邮票的种类，以0结束；第二行为顾客的需求，以0结束。
 *   4、输出最优解，如果没有最优解，则输出“none”。
 *   5、最优解：
 *    （1）不同种类的邮票数目尽可能的多；
 *    （2）如果（1）tie，则邮票数目越少越好；
 *    （3）如果（2）tie，则最优解集合中含有最大值的邮票；
 *    （4）如果（3）tie，则输出tie。
 * 思路：
 *   1、深度优先搜索 + 剪枝。
 *   2、对输入的邮票按面值升序排序（保证最优解邮票种类最多），DFS到面值k时，不再搜索面值小于k的邮票。
 *   3、如果DFS的深度超过了4，那么就返回。（最多四张邮票）。
 *   4、最多拿四张邮票，如果同一面值的邮票种类超过5，以5计算。
 * @author liuxd
 *
 */
public class Poj1010_01 {
	private static final int SIZE1 = 128;
	private static final int SIZE2 = 1000;
	private static int[] arrValue;		//第i+1种邮票面值value[i]
	private static int ptrArrValue;		//arrValue位置指针
	private static int[] arrTime;		//标记第i+1种邮票被分配的次数arrTime[i]
	private static int[] arrSolve;		//可行解
	private static int[] arrBestSolve;	//最优解
	
	private static boolean flag;		//标记是否已经出现过解
	private static boolean flagTie;		//标记是否为tie
	
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			//邮票
			arrValue = new int[SIZE1];
			int[] arrType = new int[SIZE2]; //面值为i+1的邮票的种数arrType[i];
			ptrArrValue = 0;
			while(true) {
				int value = input.nextInt();
				if(value == 0) {
					break;
				}
				//最多拿四张邮票，如果同一面值的邮票种类超过5，以5计算
				if(arrType[value] < 5) {
					arrValue[ptrArrValue++] = value;
					arrType[value]++;
				}
			}
			
			//对输入的邮票按面值升序排序
			quickSort(arrValue, 0, ptrArrValue - 1);
			
			//顾客需求
			while(true) {
				int need = input.nextInt();
				if(need == 0) {
					break;
				}
				
				flag = false;
				flagTie = false;
				arrSolve = new int[6];  //arrSolve[0]: 邮票张数
				                        //arrSolve[5]: 邮票种类
				                        //arrSolve[1..4]: 持有的邮票面值
				arrBestSolve = new int[6];
				arrTime = new int[SIZE1];

				dfs(need, 0, 0, 0);
				
				System.out.print(need);
				if(!flag) {
					System.out.println(" ---- none");
				}
				else {
					System.out.print(" (" + arrBestSolve[5] + "):");
					if(flagTie) {
						System.out.println(" tie");
					}
					else {
						//quickSort(arrBestSolve, 0, 5);
						for(int j = 1; j <= arrBestSolve[0]; j++) {
							System.out.print(" " + arrBestSolve[j]);
						}
						System.out.println();
					}
				}
			}
		}
	}
	
	/**
	 * 深度优先搜索
	 * @param need 剩余总面值
	 * @param num 邮票张数
	 * @param type 邮票种类
	 * @param pre 搜索位置
	 */
	private static void dfs(int need, int num, int type, int pre) {
		
		//达到深度优先搜索的目标状态
		if(need == 0) {//找到可行解
			if(!flag) {
				flag = true;
				best(num, type);
			}
			else {
				if(type > arrBestSolve[5]) { //种类多的优先
					flagTie = false;
					best(num, type);
				}
				else if(type == arrBestSolve[5]) { //最优解的种类相同
					if(num < arrBestSolve[0]) {//张数少的优先
						flagTie = false;
						best(num, type);
					}
					else if(num == arrBestSolve[0]) { //最优解的张数相同
						int maxS = max(arrSolve);
						int maxBs = max(arrBestSolve);
						if(maxS > maxBs) {//面值更大的优先
							flagTie = false;
							best(num, type);
						}
						else if(maxS == maxBs) {//存在多个最优解
							flagTie = true;
						}
					}
				}
			}
		}
		else {//扩展状态
			for(int i = pre; i < ptrArrValue; i++) {
				if(num == 4) {
					return;
				}
				if(need >= arrValue[i]) {
					arrSolve[num + 1] = arrValue[i];
					if(arrTime[i] != 0) {
						arrTime[i]++;
						dfs(need - arrValue[i], num + 1, type, i);
					}
					else {
						arrTime[i]++;
						dfs(need - arrValue[i], num + 1, type + 1, i);
					}
					//回溯
					arrSolve[num + 1] = 0;
					arrTime[i]--;
				}
			}
		}
	}
	
	/**
	 * 更新最优解
	 * @param num 张数
	 * @param type 种类
	 */
	private static void best(int num, int type) {
		arrBestSolve[0] = num;
		arrBestSolve[5] = type;
		for(int i = 1; i <= 4; i++) {
			arrBestSolve[i] = arrSolve[i];
		}
		return;
	}
	
	/**
	 * 返回arr中最大值
	 * @param arr
	 * @return
	 */
	private static int max(int[] arr) {
		int a = arr[1] > arr[2] ? arr[1] : arr[2];
		int b = arr[3] > arr[4] ? arr[3] : arr[4];
		return a > b ? a : b;
	}
	
	/**
	 * 快速排序
	 * @param arr
	 * @param low
	 * @param high
	 */
	private static void quickSort(int[] arr, int low, int high) {
		if(low < high) {
			int mid = getMiddle(arr, low, high);
			quickSort(arr, low, mid - 1);
			quickSort(arr, mid + 1, high);
		}
		return;
	}

	/**
	 * 找中间值
	 * @param arr
	 * @param low
	 * @param high
	 * @return 中间值的位置
	 */
	private static int getMiddle(int[] arr, int low, int high) {
		int tmp = arr[low];

		while(low < high) {
			while(low < high && arr[high] >= tmp) {
				high--;
			}
			arr[low] = arr[high];
			while(low < high && arr[low] <= tmp) {
				low++;
			}
			arr[high] = arr[low];
		}
		arr[low] = tmp;
		return low;
	}

}
