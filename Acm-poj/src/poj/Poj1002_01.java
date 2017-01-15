package poj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Accepted
 * 487-3279 问题
 * @author liuxd
 *
 */
public class Poj1002_01 {
	/**
	 * 487-3279电话号码簿问题
	 * 输入：
	 *     输入的格式是，第一行是一个正整数，指定电话号码薄中号码的数量（最多100000）。
	 *     余下的每行是一个电话号码。每个电话号码由数字，大写字母（除了Q和Z）以及连接符组成。
	 *     每个电话号码中只会刚好有7个数字或者字母。
	 * 输出：
	 *     对于每个出现重复的号码产生一行输出，输出是号码的标准格式紧跟一个空格然后是它的重复次数。
	 *     如果存在多个重复的号码，则按照号码的字典升序输出。
	 *     如果输入数据中没有重复的号码，输出一行： No duplicates.  
	 * 输入例如：
	 *     12
	 *     4873279
	 *     ITS-EASY
	 *     888-4567
	 *     3-10-10-10
	 *     888-GLOP
	 *     TUT-GLOP
	 *     967-11-11
	 *     310-GINO
	 *     F101010
	 *     888-1200
	 *     -4-8-7-3-2-7-9-
	 *     487-3279
	 * 输出例如：
	 *     310-1010 2
	 *     487-3279 4
	 *     888-4567 3
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		List<PhoneNums> lists = new ArrayList<PhoneNums>();
		for(int i = 0; i < n; i++) {
			PhoneNums pn = new PhoneNums(trans(input.next()));
			binaryInsert(lists, pn);
		}

		boolean flag = false;
		for(int i = 0; i < lists.size(); i++) {
			if(lists.get(i).nums > 1) {
				System.out.println(lists.get(i).phone + " " + lists.get(i).nums);
				flag = true;
			}
		}
		if(flag == false) {
			System.out.println("No duplicates.");
		}
	}
	
	/**
	 * 将电话号码转换成标准格式
	 * @param str
	 * @return
	 */
	private static String trans(String str) {
		String tmp = "";
		int count = 0;
		for(int i = 0; i < str.length(); i++) {
			if(count == 3) {
				tmp += '-';
				count++;
			}
			if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
				switch(str.charAt(i)) {
					case 'A': case 'B': case 'C': tmp += "2"; count++; break;
					case 'D': case 'E': case 'F': tmp += "3"; count++; break;
					case 'G': case 'H': case 'I': tmp += "4"; count++; break;
					case 'J': case 'K': case 'L': tmp += "5"; count++; break;
					case 'M': case 'N': case 'O': tmp += "6"; count++; break;
					case 'P': case 'R': case 'S': tmp += "7"; count++; break;
					case 'T': case 'U': case 'V': tmp += "8"; count++; break;
					case 'W': case 'X': case 'Y': tmp += "9"; count++; break;
				}
			}
			else if(str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				tmp += str.charAt(i);
				count++;
			}
			
		}

		return tmp;
	}

	/**
	 * 二分查找插入
	 * @param lists
	 * @param pn
	 */
	private static void binaryInsert(List<PhoneNums> lists, PhoneNums pn) {
		int low = 0, high = lists.size() - 1, mid;
		while(low <= high) {
			mid = (high + low) >> 1;
			if(pn.getPhone().compareTo(lists.get(mid).getPhone()) == 0) {
				lists.get(mid).addNums();
				return;
			}
			else if(pn.getPhone().compareTo(lists.get(mid).getPhone()) > 0) {
				low = mid + 1;
			}
			else {
				high = mid - 1;
			}
		}

		lists.add(low, pn);
	}

	//内部类：电话+重复次数
	private static class PhoneNums {
		private String phone;
		private int nums;
		
		public PhoneNums(String phone) {
			this.phone = phone;
			this.nums = 1;
		}

		public void addNums() {
			this.nums++;
		}

		public String getPhone() {
			return this.phone;
		}
	}
}
