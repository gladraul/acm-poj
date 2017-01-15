package poj;

import java.util.Scanner;

/**
 * Accepted
 * Memory 5412K
 * Time 672MS
 * 思路：
 *   1.在变化的点及周围8个点编码，第一个位置和最后一个位置也需要编码。
 *   2.变化点及周围8个点，如果超出了第一个和最后一个范围可以忽略，
 *     但是超出了边界，右边超出的往下放，左边超出往上放，最后都是需要编码的。
 * @author liuxd
 *
 */
public class Poj1009_01 {
	private static InMap[] arrInMapPair;
	private static Pix[] arrOutMap;
	private static int total = 0;
	private static int width = 0;
	private static int size = 1000;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while((width = input.nextInt()) != 0) {
			
			arrInMapPair = new InMap[size]; 
			total = 0;
			int pair = 0;
			while(input.hasNext()) {
				int value = input.nextInt();
				int length = input.nextInt();
				if(value == 0 && length == 0) {
					break;
				}
				InMap im = new InMap(value, length);
				arrInMapPair[pair++] = im;
				total += length;
			}
			
			arrOutMap = new Pix[size * 9];
			int pos = 1, k = 0; //pos从1开始标号
			//这个地方需要注意，起始点和终止点都需要编码
			for(int i = 0; i <= pair; i++) { //枚举每个连续段
				int row = (pos - 1) / width; //起始行为第0行
				int col = (pos - 1) % width; //起始列为第0列

				for(int m = row - 1; m <= row + 1; m++) {
					for(int n = col - 1; n <= col + 1; n++) {
						int tPos = m * width + n + 1; 
						//这个地方需要注意，变化点及周围8个点，超出了也要编码
						if(tPos > total || tPos < 1) {
							continue;
						}
						
						Pix pix = new Pix(tPos, getCode(tPos));
						arrOutMap[k++] = pix; //答案存入OutMap
					}
				}
				if(i < pair) {
					pos += arrInMapPair[i].length; //跳跃到下一个位置
				}
			}
			
			//根据位置标号快排
			quickSort(arrOutMap, 0, k - 1);
		
			//输出
			System.out.println(width);
			Pix tmp = arrOutMap[0];
			for(int i = 0; i < k; i++) {
				if(tmp.getCode() == arrOutMap[i].getCode()) {
					continue;
				}
				System.out.println(tmp.getCode() 
					               + " " 
					               + (arrOutMap[i].getPos() - tmp.getPos()));
				tmp = arrOutMap[i];
			}
			System.out.println(tmp.getCode()
				               + " "
				               + (total - tmp.pos + 1));
			System.out.println("0 0");
		}

		System.out.println("0");
	}
	
	//返回第pos个像素点的编码
	private static int getCode(int pos) {
		int value = getValue(pos);

		int row = (pos - 1) / width;
		int col = (pos - 1) % width;
		int maxAbs = 0;
		for(int m = row - 1; m <= row + 1; m++) {
			for(int n = col - 1; n <= col + 1; n++) {
				int tPos = m * width + n;
				if(m < 0 
			    || n < 0 
			    || n >= width 
			    || tPos >= total 
			    || tPos == pos -1) {
					//这里计算差的绝对值时要排除pos自己
					continue;
			    }

			    int tValue = getValue(tPos + 1);
			    if(maxAbs < Math.abs(tValue - value)) {
			    	maxAbs = Math.abs(tValue - value);
			    }
			}
		}
		return maxAbs;
	}

	//返回第pos个像素点的像素值
	private static int getValue(int pos) {
		int p = 0, i = 0;
		while(p < pos) {
			p += arrInMapPair[i++].getLength();
		}

		return arrInMapPair[i - 1].getValue();
	}

	/**
	 * 快速排序
	 * @param arrOutMap
	 * @param low
	 * @param high
	 */
	private static void quickSort(Pix[] arrOutMap, int low, int high) {
		if(low < high) {
			int mid = getMiddle(arrOutMap, low, high);
			quickSort(arrOutMap, low, mid - 1);
			quickSort(arrOutMap, mid + 1, high);
		}
	}

	/**
	 * 找中间值
	 * @param arrOutMap
	 * @param low
	 * @param high
	 * @return 中间值的位置
	 */
	private static int getMiddle(Pix[] arrOutMap, int low, int high) {
		Pix tmp = arrOutMap[low];

		while(low < high) {
			while(low < high && arrOutMap[high].getPos() >= tmp.getPos()) {
				high--;
			}
			arrOutMap[low] = arrOutMap[high];
			while(low < high && arrOutMap[low].getPos() <= tmp.getPos()) {
				low++;
			}
			arrOutMap[high] = arrOutMap[low];
		}
		arrOutMap[low] = tmp;
		return low;
	}

	private static class Pix {
		private int pos;
		private int code;
		
		public Pix(int pos, int code) {
			this.pos = pos;
			this.code = code;
		}

		public int getPos() {
			return this.pos;
		}

		public int getCode() {
			return this.code;
		}
	}
	
	private static class InMap {
		private int value;
		private int length;
		
		public InMap(int value, int length) {
			this.value = value;
			this.length = length;
		}
		
		public int getLength() {
			return this.length;
		}

		public int getValue() {
			return this.value;
		}
	}
}
