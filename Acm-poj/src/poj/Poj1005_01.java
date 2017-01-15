package poj;

import java.util.Scanner;

/**
 * Accepted
 * I Think I Need a Houseboat Problem
 * Input:
 *     The first line of input will be a positive integer 
 *     indicating how many data sets will be included (N). 
 *     Each of the next N lines will contain the X and Y Cartesian coordinates 
 *     of the land Fred is considering. 
 *     These will be floating point numbers measured in miles. 
 *     The Y coordinate will be non-negative. 
 *     (0,0) will not be given.
 * Output:
 *     For each data set, a single line of output should appear. 
 *     This line should take the form of: 
 *     “Property N: This property will begin eroding in year Z.” 
 *     Where N is the data set (counting from 1), 
 *     and Z is the first year (start from 1) 
 *     this property will be within the semicircle AT THE END OF YEAR Z. 
 *     Z must be an integer. After the last data set, 
 *     this should print out “END OF OUTPUT.”
 * Input Sample:
 *     2
 *     1.0 1.0
 *     25.0 0.0
 * Output Sample:
 *     Property 1: This property will begin eroding in year 1.
 *     Property 2: This property will begin eroding in year 20.
 *     END OF OUTPUT.
 * 
 * @author liuxd
 *
 */
public class Poj1005_01 {
	private static final double areaOfSemicircle = 50; //半圆面积
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		input.nextLine();
		
		String[] arr = new String[2];
		for(int i = 0; i < n; i++) {
			arr = input.nextLine().split(" ");
			int year = 1;
			while(!isErosion(Double.valueOf(arr[0]), 
					         Double.valueOf(arr[1]),
					         year)) {
				year++;
			}
			System.out.println("Property " 
			                   + (i + 1) 
			                   + ": This property will begin eroding in year " 
			                   + year + ".");
		}
		
		System.out.println("END OF OUTPUT.");
	}
	
	/**
	 * 判断是否腐蚀
	 * @param x x坐标值
	 * @param y y坐标值
	 * @param year 第几年
	 * @return 如果腐蚀返回true，否则false
	 */
	private static boolean isErosion(double x, double y, int year) {
		return x * x + y * y <= getRadiusSquare(year);
	}
	
	/**
	 * 得到半径的平方值
	 * @param year 第几年
	 * @return 半径平方值
	 */
	private static double getRadiusSquare(int year) {
		return areaOfSemicircle * year * 2 / Math.PI;
	}
}
