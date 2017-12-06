package Classes;

import java.util.ArrayList;
import java.util.List;

public class DefaultShuffler implements IShuffler
{
    // Implementing the capabilities of
    // interface.
    public List<Card> Shuffle(List<Card> cards)
    {
		//Keep changes inside method till completion
		List<Card> oldOrder = cards;
		List<Card> newOrder = new ArrayList<Card>();
		
		//We are going to randomly select cards and add them to our new ordered list until we run out
		while(oldOrder.size() > 0){
			int randomValue = (int)(Math.random() * oldOrder.size());
			newOrder.add(oldOrder.get(randomValue));
			oldOrder.remove(randomValue);
		}
		
		//Save changes
		return newOrder;
    }
}