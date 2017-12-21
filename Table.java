import java.util.ArrayList;

//可依需求增加field和method，但新增加的field和method一定為private層級
//Table.java所需field (除static field外全為private)與method，若無寫明變數名稱，表示可自行取名
public class Table {
	private final int MAXPLAYER = 4;//最多一張牌桌能坐幾個人
	private Deck allDeck;//存放所有的牌
	private Player[] allPlayers;//players的位置
	private Dealer dealer;//創dealer 
	private int[] pos_betArray=new int[MAXPLAYER];//存放每個玩家在一局下的注
	//private ArrayList<Card> dealer_card= new ArrayList<Card>();
	ArrayList<Card> allCard= new ArrayList<Card>();	//	暫存用
	public Table(int nDeck){
		allDeck=new Deck(nDeck);//初使化Deck
		allPlayers=new Player[MAXPLAYER];//定義玩家人數
	}
	public void set_player(int pos, Player p){
		allPlayers[pos]=p;//將Player放到牌桌上 pos=0~3
	}
	public Player[] get_player(){ //回傳所有在牌桌上的player
		return allPlayers;
	}
	public void set_dealer(Dealer d){//將Dealer放到牌桌上
		dealer=d;
	}
	public Card get_face_up_card_of_dealer(){//回傳dealer打開的那張牌，也就是第二張牌
		return dealer.getOneRoundCard().get(1);//第一張是蓋著的牌，第二張是打開的
		  
	}
	private void ask_each_player_about_bets(){	
		for(int i=0;i<allPlayers.length;i++){
			allPlayers[i].sayHello();//請每個玩家打招呼
			//allPlayers[i].makeBet();//請每個玩家下注（不需要）
			pos_betArray[i]=allPlayers[i].makeBet();//每個玩家下的注要存在pos_betArray供之後使用
		}
	}
	
	private void distribute_cards_to_dealer_and_players(){
		for(int i =0;i<allPlayers.length;i++){
			ArrayList<Card> allCard= new ArrayList<Card>(); //暫存用，每次重新實體化
			allCard.add(allDeck.getOneCard(true));
			allCard.add(allDeck.getOneCard(true));//每個玩家發兩張打開的牌
			allPlayers[i].setOneRoundCard(allCard);//放入手牌
			//淨空，免得疊加到allCard.clear();
		}
		ArrayList<Card> allCard= new ArrayList<Card>();//暫存用，每次重新實體化
		allCard.add(allDeck.getOneCard(false));	//再一張蓋著的牌，以及一張打開的牌給莊家，dealer的第一張牌
		allCard.add(allDeck.getOneCard(true));//dealer的第二張牌
		dealer.setOneRoundCard(allCard);
		//淨空，免得疊加到allCard.clear();
		System.out.println("Dealer's face up card is");
		get_face_up_card_of_dealer().printCard();//printCard
	}
	private void ask_each_player_about_hits(){
		for(int i =0;i<allPlayers.length;i++){
			ArrayList<Card> allCard= new ArrayList<Card>();//暫存用，每次重新實體化
			if(allPlayers[i].getTotalValue()<17||allPlayers[i].getOneRoundCard().size()==2){ //根據要牌原則判斷是否要hit!
			System.out.print("Hit! ");
		}
		while(allPlayers[i].hit_me(this)){  //判斷是否要牌，若符合要牌原則，則繼續要牌
				allCard.add(allDeck.getOneCard(true));//拿牌
				allPlayers[i].setOneRoundCard(allCard);//加入手牌
				}
		
		System.out.println(allPlayers[i].getName()+"'s Cards now:");
		allPlayers[i].printAllCard();	//印出手牌
		//allCard.clear();
		System.out.println(allPlayers[i].getName()+" Pass hit!!");	//不要牌了
			}
		}
	private void ask_dealer_about_hits(){
		ArrayList<Card> allCard= new ArrayList<Card>();//暫存用，每次重新實體化
		while(dealer.hit_me(this)){//判斷是否要牌，若符合要牌原則，則繼續要牌
			allCard.add(allDeck.getOneCard(true));//拿牌
			dealer.setOneRoundCard(allCard);//加入手牌
			}
		//allCard.clear();
		System.out.println("Dealer's hit is over!!");//結束文字
	}
	private void calculate_chips(){
		System.out.println("Dealer's card value is "+dealer.getTotalValue()+" ,Cards:");//印出莊家的牌跟點數
		dealer.printAllCard();
		for(int i=0;i<allPlayers.length;i++){
			System.out.println(allPlayers[i].getName()+" card value is "+allPlayers[i].getTotalValue());
			//平手，若為dealer跟player點數相同或 dealer跟player都超過21點  
			if(dealer.getTotalValue()==allPlayers[i].getTotalValue()||(dealer.getTotalValue()>21 && allPlayers[i].getTotalValue()>21)){
				System.out.println(allPlayers[i].getName()+",chips have no change! The Chips now is: "+allPlayers[i].getCurrentChips());
			  }
			//dealer win dealer一定要小於21且dealer大於player或player大於21點
			else if(dealer.getTotalValue()<=21&&(dealer.getTotalValue()>allPlayers[i].getTotalValue() || allPlayers[i].getTotalValue()>21)){
				allPlayers[i].increaseChips(-allPlayers[i].makeBet());
				System.out.println(allPlayers[i].getName()+", Loss "+allPlayers[i].makeBet()+" Chips, the Chips now is: "+allPlayers[i].getCurrentChips());
			  }
			//player win player小於21且player大於dealer的點數，或dealer大於21點
			else if(allPlayers[i].getTotalValue()<=21 && (dealer.getTotalValue()>21 || allPlayers[i].getTotalValue()>dealer.getTotalValue())){
				allPlayers[i].increaseChips((allPlayers[i].makeBet()));
				System.out.println(allPlayers[i].getName()+", Get "+allPlayers[i].makeBet()+" Chips, the Chips now is: "+allPlayers[i].getCurrentChips());
			  }	
		}
	}
	public int[] get_palyers_bet(){
		return pos_betArray;	//回傳每個player地下注值
		
	}
	public void play(){ //執行
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}

}
