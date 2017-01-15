package poj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Accepted
 * Memory: 5380K
 * Time: 313MS
 * 
 * @author liuxd
 *
 */
public class Poj1008_01 {
	/*public enum HaabMonthEnum {
		pop(1), no(2), zip(3), zotz(4), 
		tzec(5), xul(6), yoxkin(7), mol(8), 
		chen(9), yax(10), zac(11), ceh(12), 
		mac(13), kankin(14), muan(15), pax(16), 
		koyab(17), cumhu(18), uayet(19);
		
		private int value;
		
		private HaabMonthEnum(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}*/
	public enum HaabMonthEnum {
		pop, no, zip, zotz, 
		tzec, xul, yoxkin, mol, 
		chen, yax, zac, ceh, 
		mac, kankin, muan, pax, 
		koyab, cumhu, uayet;
	}
	
	public enum TzolkinEnum {
		imix, ik, akbal, kan, 
		chicchan, cimi, manik, lamat, 
		muluk, ok, chuen, eb, 
		ben, ix, mem, cib, 
		caban, eznab, canac, ahau;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		input.nextLine();
		List<HaabDate> list = new ArrayList<HaabDate>();
		for(int i = 0; i < n; i++) {
			String[] date = input.nextLine().split(" ");
			HaabDate hd = new HaabDate(
					Integer.valueOf(date[0].substring(0, date[0].length() - 1)),
					HaabMonthEnum.valueOf(date[1]).ordinal(),
					Integer.valueOf(date[2]));
			list.add(hd);
		}
		
		System.out.println(n);
		for(int i = 0; i < n; i++) {
			int days = getDays(list.get(i));
			System.out.println(days % 260 % 13 + 1 
					         + " " 
			                 + TzolkinEnum.values()[days % 260 % 20] 
			                 + " " 
					         + days / 260);
		}
		/*System.out.println(HaabMonthEnum.no.name());
		System.out.println(HaabMonthEnum.no.ordinal());
		System.out.println(HaabMonthEnum.no.getValue());
		System.out.println(HaabMonthEnum.values()[1]);
		System.out.println(HaabMonthEnum.valueOf("no").getValue());*/
	}
	
	private static int getDays(HaabDate hd) {
		int days = 0;
		days += hd.getYear() * 365;
		days += 20 * hd.getMonth();
		days += hd.getDay();
		
		return days;
	}
	
	private static class HaabDate {
		private int day;
		private int month;
		private int year;
		
		public HaabDate(int day,
				        int month,
				        int year) {
			this.day = day;
			this.month = month;
			this.year = year;
		}
		
		public int getDay() {
			return this.day;
		}
		
		public int getMonth() {
			return this.month;
		}
		
		public int getYear() {
			return this.year;
		}
		
	}
}
