package 執行;

import 練習.TWId;

public class TW_ID {

	public static void main(String[] args) {
		TWId t1 = new TWId();
		TWId t2 = new TWId(false);
		TWId t3 = new TWId(4);
		TWId t4 = new TWId(7, true);
		System.out.println(t1.getId() + ":" + (TWId.checkId(t1.getId()) ? "OK" : "XX"));
		System.out.println(t2.getId() + ":" + (TWId.checkId(t1.getId()) ? "OK" : "XX"));
		System.out.println(t3.getId() + ":" + (TWId.checkId(t1.getId()) ? "OK" : "XX"));
		System.out.println(t4.getId() + ":" + (TWId.checkId(t1.getId()) ? "OK" : "XX"));

		TWId id5 = TWId.createTWId("Y120299847");
		if (id5 != null) {
			System.out.println(id5.getId());
			} else {
				System.out.println("null");
			}

		
	}

}
