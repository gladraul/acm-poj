package poj;

import java.util.Scanner;

/**
 * 打表才AC的，没找到java可以通过的方法。
 * 
 * Joseph问题
 * 描述：
 *   （1）人围成一圈，被编号为1,2,...,n，处死第m位置的人。
 *   （2）前k个好人+后k个坏人，在杀掉第一个好人之前杀掉所有的坏人，找到那个最小的m。
 * 输入：
 *   每行一个k值，0结束输入。0<k<14。
 * 输出：
 *   输出输入行相应的m值。
 * 思路：
 *    此题不是传统的约瑟夫问题：
 *    （1）传统的约瑟夫环问题，每个人都是一个相互区别的个体，所以他们的编号相异这点很重要。
 *    （2）而这一题中，好人之间的编号是相对无所谓的，坏人也一样；所以杀了一个坏人由后一个坏人补齐就好了，只要保证前k个是好人就行，编号相对不重要。
 *    思路一共三点：
 *    （1）要kill的人的位置公式p=(p+m-1)%rest+1。
 *    （2）kill的位置<k就break，此时剩下的人rest等于k就成功。
 *    （3）m不要递增，m是k+1的整数倍或者k+1的整数倍加1，这样会提高不少。
 *    关于第三点的说明：
 *    考虑仅剩k+2个人时的情况，
 *    如果此时第k+1人出局，要使下次第k+2人出局，m%(k+1)=1
 *    如果此时第k+2人出局，要使下次第k+1人出局，m%(k+1)=0
 * @author liuxd
 *
 */
public class Poj1012_02 {
	private static final int size = 14;
	public static void main(String[] args) {
		int[] Joseph = new int[size];
		int k;
		Scanner input = new Scanner(System.in);
		while((k = input.nextInt()) != 0) {

			if(Joseph[k] > k) {
				System.out.println(Joseph[k]);
				continue;
			}
			int n = 2 * k;
			int pos = 0;
			int m = k + 1;
			for(int i = 1; i <= k; i++) {
				pos = (pos + m - 1) % (n - i + 1);
				if(pos < k) {
					i = 0;
					pos = 0;
					if(m % (k + 1) == 0) {
						m++;
					}
					else {
						m = m + k;
					}
				}
			}
			Joseph[k] = m;
			System.out.println(m);
		}
		
		return;
	}
	
}
