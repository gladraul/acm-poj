package poj;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * Accepted
 * Financial Management Problem
 * Input:
 *     The input will be twelve lines. 
 *     Each line will contain the closing balance 
 *     of his bank account for a particular month. 
 *     Each number will be positive and displayed to the penny. 
 *     No dollar sign will be included.
 * Output:
 *     The output will be a single number, 
 *     the average (mean) of the closing balances for the twelve months. 
 *     It will be rounded to the nearest penny, 
 *     preceded immediately by a dollar sign, 
 *     and followed by the end-of-line. 
 *     There will be no other spaces or characters in the output.
 * Sample Input:
 *     100.00
 *     489.12
 *     12454.12
 *     1234.10
 *     823.05
 *     109.20
 *     5.27
 *     1542.25
 *     839.18
 *     83.99
 *     1295.01
 *     1.75
 * Sample Output:
 *     $1581.42
 * @author liuxd
 *
 */
public class Poj1004_01 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		float sum = 0;
		for(int i = 0; i < 12; i++) {
			sum += input.nextFloat();
		}
		DecimalFormat decimalFormat = new DecimalFormat(".00");
		System.out.println("$" + decimalFormat.format(sum / 12));
	}

}
