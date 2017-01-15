package poj;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Accepted
 * 487-3279 问题
 * @author liuxd
 *
 */
public class Poj1002_02 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		CollatorComparator comparator = new CollatorComparator();
		Map<String, Integer> map = new TreeMap<String, Integer>(comparator);
		for(int i = 0; i < n; i++) {
			String key = trans(input.next());
			Integer value;
			if(map.containsKey(key)) {
				value = map.get(key) + 1;
			}
			else {
				value = 1;
			}
			map.put(key, value);
		}
		
		Iterator<Entry<String, Integer>> it = map.entrySet().iterator();
		boolean flag = false;
		while(it.hasNext()) {
			Entry<String, Integer> entry = it.next();
			String key = entry.getKey();
			Integer value = entry.getValue();
			if(value > 1) {
				System.out.println(key + " " + value);
				flag = true;
			}
		}
		if(flag == false) {
			System.out.println("No duplicates.");
		}
	}
	
	public static class CollatorComparator implements Comparator {

		public int compare(Object o1, Object o2) {
			if(o1 == null || o2 == null) {
				return 0;
			}
			
			return String.valueOf(o1).compareTo(String.valueOf(o2));
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
}
