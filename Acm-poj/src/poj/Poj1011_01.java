package poj;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 木棒问题
 * Memory: 5536K, Time: 204MS
 * 描述：
 *   1、原始木棒等长。
 *   2、每节木棍不超过50个单位长度。
 *   3、恢复后木棒长度尽可能小
 *   4、输入为两行，第一行是一个不超过64的整数，表示砍断之后共有多少节木棍。
 *      第二行是截断以后，所得到的各节木棍的长度。
 *      在最后一组数据之后，是一个零。
 *   5、输出原始木棒的可能最小长度。
 * 思路：
 *   1、缩小搜索范围
 *   2、可能最小长度是每节木棍长度的总和的约数（剪枝）
 *   3、总和的约数<sum, 并且总和的约数大于每节木棍的最大长度
 *   4、将小棍的长度由大到小排序，减少灵活度
 *   5、等长且不能用的木棒只搜索一次
 *   6、构建新棒时，对于新棒的第一节棒子，在搜索完所有棒子后都不存在组合，则说明该节棒子无法在当前组合方式下组合，不用往下搜索(往下搜索也会令该棒子被舍弃)，直接返回上一层
 *   7、构建完一个棒子后，如果在搜索完所有棒子后不存在组合，则说明该棒子无法在当前组合方式下组合，直接返回
 * @author liuxd
 *
 */

public class Poj1011_01 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		while(input.hasNext()) {
			int num = input.nextInt();
			if(num == 0) {
				break;
			}
			
			Integer[] arrStick = new Integer[num]; //木棍
			boolean[] arrVisit = new boolean[num]; //是否访问
			int sumLen = 0, maxLen = 0;
			for(int i = 0; i < num; i++) {
				arrStick[i] = input.nextInt();
				sumLen += arrStick[i];
				if(maxLen < arrStick[i]) {
					maxLen = arrStick[i];
				}
				arrVisit[i] = false;
			}
			
			//默认归并排序，至大到小排序，先使用长度大的木棍，减少灵活度
			Arrays.sort(arrStick, new ArrStickComparator());
			
			boolean flag = false; 
			//剪枝1，缩小范围，在[maxLen, sumLen-initLen]范围内找到initLen。
			//如果能在[maxLen, sumLen-initLen]找到最短的initLen，
			//那么该initLen也必然是[maxLen, sumLen]中最短的
			for(int initLen = maxLen; initLen <= sumLen - initLen; initLen++) {
				//剪枝2，initLen必是sumLen的约数
				if(sumLen % initLen == 0 && dfs(arrStick, arrVisit, 0, initLen, 0, 0, num)) {
					System.out.println(initLen);
					flag = true;
					break;
				}
			}
			
			if(!flag) {
				System.out.println(sumLen);
			}
		}
		
		return;
	}
	
	/**
	 * 至大到小比较策略
	 * @author liuxd
	 *
	 */
	private static class ArrStickComparator implements Comparator<Integer> {

		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
		
	}
	
	/**
	 * 深度搜索
	 * @param arrStick
	 * @param arrVisit
	 * @param len 当前正在组合的棒长
	 * @param initLen 目标棒长
	 * @param pre arrStick的搜索位置
	 * @param n 已使用的棒子数量
	 * @param num 木棒数量
	 * @return
	 */
	private static boolean dfs(Integer[] arrStick,
			                   boolean[] arrVisit,
			                   int len,
			                   int initLen, 
			                   int pre, 
			                   int n,
			                   int num) {
		if(num == n) {
			return true;
		}
		
		//int last = 0;
		for(int i = pre; i < num; i++) {
			if(arrVisit[i]) {
				continue;
			}
			
			if(len + arrStick[i] < initLen) {
				arrVisit[i] = true;
				if(dfs(arrStick, arrVisit, len + arrStick[i], initLen, i + 1, n + 1, num)) {
					return true;
				}
				arrVisit[i] = false;
				//剪枝4，构建新棒时，对于新棒的第一节棒子，在搜索完所有棒子后都不存在组合，则说明该节棒子无法在当前组合方式下组合
				//不用往下搜索(往下搜索也会令该棒子被舍弃)，直接返回上一层
				if(len == 0) { 
					return false;
				}
				//剪枝3，等长的木棒只搜索一次
				while(i + 1 < num && arrStick[i] == arrStick[i + 1]) {
					i++;
				}
			}
			else if(len + arrStick[i] == initLen) {
				arrVisit[i] = true;
				if(dfs(arrStick, arrVisit, 0, initLen, 0, n + 1, num)) {
					return true;
				}
				arrVisit[i] = false;
				//剪枝6，构建完一个棒子后，如果在搜索完所有棒子后不存在组合，则说明该棒子无法在当前组合方式下组合，直接返回
				return false;
			}
			
		}
		return false;
	}

}
