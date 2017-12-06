package Repositories;

//My Classes
import Classes.Deck;

//Other Classes
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeckRepository {
	Map<String, Deck> Decks;
	
	public DeckRepository(){
		//Initialize class variables
		Decks = new HashMap<String, Deck>();
	}
	
	public ArrayList<Deck> GetAll(){
		return new ArrayList<Deck>(Decks.values());
	}
	
	public Deck Get(String s){
		return Decks.get(s);
	}
	
	public boolean Add(String s, Deck d){
		//If value already exists, exit early
		if(Decks.get(s) != null){
			//Inform caller whether operation was successful
			return false;
		}
		//Otherwise, add value to storage
		Decks.put(s, d);
		return true;
	}
	
	public void Remove(String s){
		Decks.remove(s);
	}
}