package 練習;

//身份證產生器
public class TWId {
	private String id; // 沒有對外提供任隨意修改
	private static String Letters = "ABCDEFGHJKLMNPQRSTUVXYWZIO"; // 運用indexOf +10會等於縣市數值

	public TWId() { // 對外提供建構式,須與檔案名稱一致
		this((int) Math.random() * 2 == 0);// this(boolean)抓下面那串建構子
	}

	public TWId(boolean isMale) {
		this((int) (Math.random() * 26), isMale);// this(int,isMale)抓下面那串建構子,和自己的isMal參數
	}

	public TWId(int area) { // 問是哪個縣市 須思考對外提供哪些方法
		this(area, (int) Math.random() * 2 == 0);
	}

	// 給予亂數生成隨機身分證
	public TWId(int area, boolean isMale) {// area:玩家填寫的縣市 isMale:男生or女生
		StringBuffer sb = new StringBuffer(Letters.substring(area, area + 1));
		sb.append(isMale ? "1" : "2");// 假如是true就男生(1) fales就女生(2)
		for (int i = 0; i < 7; i++) {
			sb.append((int) (Math.random() * 10));
		}
		String first9 = sb.toString(); // 前9碼
		for (int i = 0; i < 10; i++) {
			if (checkId(first9 + i)) {// 讓最後一碼跑9次 9次以前配對身分證公式只要能被10整除 就會停止迴圈
				id = first9 + i;// 印出true被 驗證通過的身分證
				break;
			}
		}

	}

	private TWId(String id) { // 要先在內部驗證後,再呼叫提供至外部
		this.id = id;
	}

	public  static TWId createTWId(String id) {
		TWId temp = null; // null表示沒有任何指向。
		if (checkId(id)) {
			temp = new TWId(id); // 產生物件實體
		}else {
			temp =null;
		}
		return temp;
	}
	// return 出 TWId的物件

	public static boolean checkId(String id) {// 只有public沒有static須先有物件
		// 加上static 可能是屬性,可能是方法,完全與物件無關
		boolean ret = false;

		if (id.matches("^[A-Z][12][0-9]{8}$")) { // matches是在檢查是否完全一致。
			// 長度為10,第1碼為英文,第2碼為男女1或2,後面有8碼數字。
			char c0 = id.charAt(0); // 抽出第1碼
			int n12 = Letters.indexOf(c0) + 10;// 換算成身分證數字代碼 L:代碼20;
			int n1 = n12 / 10; // 求英文代碼的第1位數值 (n1 = 2;)
			int n2 = n12 % 10; // 求餘數 (n2 = 0;)

			// 抓你輸入的身分證第二碼
			int n3 = Integer.parseInt(id.substring(1, 2));
			int n4 = Integer.parseInt(id.substring(2, 3));
			int n5 = Integer.parseInt(id.substring(3, 4));
			int n6 = Integer.parseInt(id.substring(4, 5));
			int n7 = Integer.parseInt(id.substring(5, 6));
			int n8 = Integer.parseInt(id.substring(6, 7));
			int n9 = Integer.parseInt(id.substring(7, 8));
			int n10 = Integer.parseInt(id.substring(8, 9));
			int n11 = Integer.parseInt(id.substring(9, 10));
			int sum = n1 * 1 + n2 * 9 + n3 * 8 + n4 * 7 + n5 * 6 + n6 * 5 + n7 * 4 + n8 * 3 + n9 * 2 + n10 * 1
					+ n11 * 1;
			ret = sum % 10 == 0; // 被10整除
		}

		return ret;

	}

	public String getId() {//告訴使用者ID
		return id;
	}
}
