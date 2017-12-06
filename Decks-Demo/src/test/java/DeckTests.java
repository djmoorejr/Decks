//My Classes
import Classes.DefaultShuffler;
import Classes.HandShuffler;
import Classes.Deck;

//Testing Classes
import org.junit.Assert;
import org.junit.Test;

public class DeckTests{
    @Test
    public void DeckInitializes() throws Exception {
        //Arrange

        //Act
        Deck d = new Deck("BobRoss", new DefaultShuffler());

        //Assert
        Assert.assertTrue(d != null);
    }

    @Test
    public void DeckDefaultShuffles() throws Exception {
        //Arrange
        Deck d = new Deck("BobRoss", new DefaultShuffler());
        String original = d.toString();

        //Act
        d.Shuffle();
        String shuffled = d.toString();

        //Assert
        Assert.assertTrue(!original.equals(shuffled));
    }

    @Test
    public void DeckHandShuffles() throws Exception {
        //Arrange
        Deck d = new Deck("BobRoss", new HandShuffler());
        String original = d.toString();

        //Act
        d.Shuffle();
        String shuffled = d.toString();

        //Assert
        Assert.assertTrue(!original.equals(shuffled));
    }
}
