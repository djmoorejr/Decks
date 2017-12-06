package Classes;

import java.util.ArrayList;
import java.util.List;

public class HandShuffler implements IShuffler
{
	int cardVariance = 5;
	//public facing shuffle method
    public List<Card> Shuffle(List<Card> cards)
    {
    	//Save copy of cards
		List<Card> newOrder = cards;

		//Shuffle deck between 10-110 times
		int shuffleAmount = (int)(Math.random() * 100) + 10;
		for(int i = 0; i < shuffleAmount; i++)
		{
			//Split Shuffle deck
			newOrder = HandShuffle(newOrder);
		}
		return newOrder;
    }
	
	private List<Card> HandShuffle(List<Card> cards){
    	//Save copy of order
		List<Card> oldOrder = cards;
		//Make return object for storage
		List<Card> newOrder = new ArrayList<Card>();
		
		//Split the list of cards
		List<Card> leftStack = oldOrder.subList(0, (int)oldOrder.size()/2);
		List<Card> rightStack = oldOrder.subList((int)oldOrder.size()/2, oldOrder.size());

		//Determine which stack is placed first
		boolean toggle = ((int)(Math.random() * 10) + 1) % 2 == 0;
		
		//Randomly select cards and add them to our new ordered list until we run out of cards
		while((leftStack.size() + rightStack.size()) > 0){
			//Left stack is chosen
			//Note: LeftStack needs to have cards to be chosen
			//Short cut if RightStack has no more cards.
			if((toggle && leftStack.size() > 0) || rightStack.size() == 0 ){
				//Grab random amount of cards
				int amount = Math.min(leftStack.size(), (int)(Math.random() * cardVariance) + 1);
				newOrder.addAll(leftStack.subList(0, amount));
				leftStack = leftStack.subList(amount, leftStack.size());
			}
			//Otherwise right stack is first
			else{
				int amount = Math.min(rightStack.size(), (int)(Math.random() * cardVariance) + 1);
				newOrder.addAll(rightStack.subList(0, amount));
				rightStack = rightStack.subList(amount, rightStack.size());
			}
			//Determine which stack is the first on the stack
			toggle = ((int)(Math.random() * 10) + 1) % 2 == 0;
		}
		
		//Save changes
		return newOrder;
	}
}