
public class Card {//B0544243 鄒涵如
	enum Suit{Club,Diamond,Heart,Spade} //改成enum	

		private Suit suit; //Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
		private int rank; //1~13
		/**
		 * @param s suit
		 * @param r rank
		 * @return 
		 */
		public Card(Card.Suit s,int r){ //constructor
			suit=s;
			rank=r;
		}	
		//TODO: 1. Please implement the printCard method (20 points, 10 for suit, 10 for rank)
		public void printCard(){
			//Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as Clubs Ace
			if(suit==suit.Club){
				System.out.print("Clubs");
			}
			else if(suit==suit.Diamond){
				System.out.print("Diamonds");
			}
			else if(suit==suit.Heart){
				System.out.print("Hearts");
			}
			else if(suit==suit.Spade){
				System.out.print("Spades");
			}
			if(rank==1){
				System.out.println(" Ace");
			}
			else if(rank==13){
				System.out.println(" King");
			}
			else if(rank==12){
				System.out.println(" Queen");
			}
			else if(rank==11){
				System.out.println(" Jack");
			}
			else
			System.out.println(" "+rank);
		}
		
		public Suit getSuit(){
			return suit;
		}
		public int getRank(){
			return rank;
		}
	}


