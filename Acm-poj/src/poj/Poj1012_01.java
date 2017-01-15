package poj;

/**
 * Joseph问题
 * 描述：
 *   （1）人围成一圈，被编号为1,2,...,n，处死第m位置的人。
 *   （2）前k个好人+后k个坏人，在杀掉第一个好人之前杀掉所有的坏人，找到那个最小的m。
 * 输入：
 *   每行一个k值，0结束输入。0<k<14。
 * 输出：
 *   输出输入行相应的m值。
 * 思路：
 *   （1）m > k
 * @author liuxd
 *
 */
public class Poj1012_01 {

	public static void main(String[] args) {

		System.out.println(getJosephNum(6, 5));
		
	}
	
	private static int getJosephNum(int n, int m) {
		int f = 0;
		for(int i = 1; i <= n; i++) {
			f = (f + m) % i;
			System.out.println((f - 1 + m) % i);
			
		}
		return f + 1;
	}
	
}
