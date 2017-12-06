package Classes;
import java.util.ArrayList;
import java.util.List;

public class Deck {
	String Name;
	List<Card> Cards;
	IShuffler shuffler;
	public Deck(String name, IShuffler s){
		Name = name;
		shuffler = s;
		Cards = new ArrayList<Card>();
		//Generate all possible combinations of cards. 
		//Makes it easy to add new suit/face values 
		for (Suit suit : Suit.values()) {
			for (Face face : Face.values()) {
				Card c = new Card(suit, face);
				Cards.add(c);
			}
		}

		//If we wanted to add jokers (which have no suit) I would do the following:
		//Overload the Card constructor
		//Create custom cards on an as needed basis

	}

	//Shuffle Cards
	public void Shuffle(){
		//Use shuffler's shuffle algorithm
		Cards = shuffler.Shuffle(Cards);
	}	
	
	public String GetName(){
		return Name;
	}

	@Override
	public  String toString(){
		String content =  "{Name: " + Name + ", Cards: [";
		
		for(int i = 0; i < Cards.size(); i++){
			if(i > 0){
				content += ",";
			}
			
			content += Cards.get(i).ToString();
		}
		content += "]}";
		return content;
	}
}