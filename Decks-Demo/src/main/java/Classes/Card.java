package Classes;

public class Card {
	Suit suit;
	Face face;
	public Card(Suit s, Face f){
		suit = s;
		face = f;
	}
	
	public String ToString(){
		String content = "{";
		content += "Suit: " + suit.name() + ", ";
		content += "Face: " + face.name();
		content += "}";
		return content;
	}
}