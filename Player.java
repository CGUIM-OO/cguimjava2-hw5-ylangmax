import java.util.ArrayList;

public class Player extends Person {//B0544243 鄒涵如
	private String name;//玩家姓名
	private int chips; //玩家有的籌碼
	private int bet; //玩家此局下注的籌碼
	
	public Player(String name, int chips){ //constructor
		this.name=name;
		this.chips=chips;
	}
	public String getName(){//呼叫名字
		return name;
	}
	public int makeBet(){//下注
		bet=1;
		if(chips==0){//判斷是否還有錢
			return 0;
		}
		return bet;
	}
	@Override
	public boolean hit_me(Table table){ //是否要牌，多加table參數
		if(getTotalValue()>=17)//判斷機制
			return false;	
		else
			return true;
	}
	/*public int getTotalValue() {  //取得所有的值
		int total=0;
		for(Card card:oneRoundCard){//取卡
			if(card.getRank()==11||card.getRank()==12||card.getRank()==13)//後面幾張都是10點
				total+=10;
			else 
				total+=card.getRank();
		}
		return total;
	}*/
	public int getCurrentChips(){//回傳籌碼
		return chips;
	}
	public void increaseChips (int diff){//增加籌碼
		chips+=diff;
	}
	public void sayHello(){//sayHello
		System.out.println("Hello, I am " + name + ".");
		System.out.println("I have " + chips + " chips.");
	}
	
	
	
	
}
