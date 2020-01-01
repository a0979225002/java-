package 練習;

import javax.swing.JOptionPane;

public class Guess_the_number {
	// 印出隨機數
	String createAnswer(int d) {
		// 1-1(先給予陣列內放0~9)
		int[] poker = new int[10];
		for (int i = 0; i < poker.length; i++) {
			poker[i] = i;
		}
		// 1-2(讓poker數字隨機,且不能重複)
		for (int i = 9; i > 0; i--) {
			int rand = (int) (Math.random() * 9);
			int temp = poker[i];
			poker[i] = poker[rand];
			poker[rand] = temp;
		}
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < d; i++) {
			sb.append(poker[i]);
		}
		return sb.toString();
	}
	//偵測 幾A幾B(?A?B)
   String checkAB(String a,String g) {
	   //給予A和B 有疊加的狀態
	   int A, B; 
	   A = B =0;
	   
	   for (int i = 0; i < a.length(); i++) { //對於字串屬性的length需要加()
		if(a.charAt(i)==g.charAt(i)) {
			A++;
		}else if (a.indexOf(g.charAt(i))!=-1) {
			B++;
		}
	}
	   
	   return A+"A"+B+"B";
   }
   
   //判斷玩家不能輸入重複的字
   boolean checkGuess(String g , int len) {
	   boolean ret = false;
	   if (g.matches("[0-9]{"+len+"}")) {//matches(裡面放正則表達式) 檢查使用,有過就會true
		boolean isDup = true;
		for (int i = 0; i < len-1; i++) {
			char c = g.charAt(i);//第一次迴圈檢查第一個字,第二次第二個字......
			if (g.substring(i+1).indexOf(c)!=-1) {
				isDup = false;
				break;
			}
		}
		ret = isDup;
	}else {
		ret = false;//一開始判斷輸入不是數字 會是false
	}
	   return ret;
   }	
	
   
   public static void main(String[] args) {
//		answer = 隨機產生的數字
//		guess = 玩家輸入的數字
//		result = 判斷後幾A幾B
		
		int len = 0;// 讓玩家選擇想要的難度(填入的數字=要猜幾個字)
		
		Guess_the_number obj = new Guess_the_number();
		String Difficulty = JOptionPane.showInputDialog("遊戲開始前難度調整:你想要猜幾個字\n"+"數字請小於10");
	    len = Integer.parseInt(Difficulty);
		
		String answer = obj.createAnswer(len);
		System.out.println(answer);
        
		//提示框重複次數
		int nb = 0; //玩家選擇重複的次數
		String number  = JOptionPane.showInputDialog("你想猜幾次");
		nb = Integer.parseInt(number);
		boolean isWinner = false;
		StringBuffer log = new StringBuffer();
		for (int i = 0; i < nb; i++) {
			String guess = JOptionPane.showInputDialog((i + 1) + " 輸入數字\n" + log.toString());
			//String guess = JOptionPane.showInputDialog("裡面放的是跳出的跳會提示的字")
	        //解說:輸入的字會傳進guess內
			System.out.println(obj.checkGuess(guess, len));
			if (!obj.checkGuess(guess, len)) {
				JOptionPane.showMessageDialog(null,"請輸入正確數字,數字不得重複");
				continue;
			}
			String result = obj.checkAB(answer, guess);
			log.append(guess + " => " + result + "\n");
			
			
			if (!result.equals(len+"A0B")) {
				JOptionPane.showMessageDialog(null, result);
				//JOptionPane.showMessageDialog = 不顯示text的提示框
				
			}else {
				JOptionPane.showMessageDialog(null, result+"\n"+"恭喜過關");
				isWinner = true;
				break;
			}
		}
		if (!isWinner) {
			JOptionPane.showMessageDialog(null, "抱歉次數已用光\n"+"答案是:"+answer);
		}
	
	}

}
