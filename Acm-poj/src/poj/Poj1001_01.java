package poj;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Accepted
 * Exponentiation Problem
 * @author liuxd
 *
 */
public class Poj1001_01 {

	/**
	 * 对数值很大、精度很高的数进行高精度计算是一类十分常见的问题。
	 * 比如，对国债进行计算就是属于这类问题。
	 * 现在要你解决的问题是：对一个实数R( 0.0 < R < 99.999 )，
	 * 要求写程序精确计算 R的 n次方，其中n是整数并且 0 < n <= 25。
	 * 输入：
	 *     输入包括多组 R和 n。 R 的值占第 1 到第 6 列，n 的值占第 8 和第 9 列。
	 * 输出：
	 *     对于每组输入，要求输出一行，该行包含精确的 R的 n次方。
	 *     输出需要去掉前导的 0和 后面不要的 0 。
	 *     如果输出是整数，不要输出小数点。
	 * 输入例如：
	 *     95.123 12
	 *     0.4321 20
	 *     5.1234 15
	 *     6.7592  9
	 *     98.999 10
	 *     1.0100 12
	 * 输出例如：
	 *     548815620517731830194541.899025343415715973535967221869852721
	 *     .00000005148554641076956121994511276767154838481760200726351203835429763013462401
	 *     43992025569.928573701266488041146654993318703707511666295476720493953024
	 *     29448126.764121021618164430206909037173276672
	 *     90429072743629540498.107596019456651774561044010001
	 *     1.126825030131969720661201
	 * 提示：
	 *     可用BigDecimal的toPlainString()方法将科学计数法改为普通的数字表示。
	 *     可用BigDecimal的stripTrailingZeros()方法去掉后面的0
	 *     去掉前导的0，即把一个数字小数点前的0去掉。可以将该数字转换为字符串，然后把小数点前的数字与0比较，若为0，去掉。
	 *     与0比较可用BigDecimal类下的compareTo()方法。
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			String str = input.nextLine();
			BigDecimal a = new BigDecimal(str.substring(0, 6));
			int b = 0;
			if(str.charAt(7) == ' ') {
				b = Integer.parseInt(str.substring(8));
			}
			else {
				b = Integer.parseInt(str.substring(7, 9));
			}
			BigDecimal tmp = a.pow(b);
			String[] arr = tmp.toPlainString().split("\\."); 
			BigDecimal preZero = new BigDecimal(arr[0]);
			BigDecimal zero = new BigDecimal("0");
			if(preZero.compareTo(zero) == 0) {
				System.out.println("." + arr[1]);
			}
			else {
				System.out.println(tmp.stripTrailingZeros().toPlainString());
			}
			
		}
	}

}
