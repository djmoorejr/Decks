//Import my Classes
import javax.ws.rs.core.Application;

//My Classes
import Classes.DefaultShuffler;
import Controllers.DeckController;
import Classes.Deck;

//Testing Classes
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ApiTests extends JerseyTest {
	//Resource: 
	//http://www.logicbig.com/tutorials/java-ee-tutorial/jax-rs/jax-rs-unit-testing/
	//https://memorynotfound.com/test-jersey-rest-service-with-junit/

    private final int successCode = 200;
    
    @Override
    protected Application configure(){
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(DeckController.class);
    }

    //Generate default content to the Data point
    @Before
    public void Setup(){
        target("/Decks/Create/BobRoss").request().put(Entity.entity("BobRoss", MediaType.APPLICATION_JSON));
    }

    //Remove any changes that may have occurred
    @After
    public void TearDown(){
        target("/Decks/Delete/BobRoss").request().delete();
    }

    @Test
    public void GetAllDecksSuccessStatus() throws Exception {
		//Arrange
		
		//Act 
        int output = target("/Decks/GetAll").request().get().getStatus();
		
		//Assert
		Assert.assertTrue(output == successCode);
    }

    @Test
    public void GetAllDecksRetrievesContent() throws Exception {
        //Arrange

        //Act
        String content = target("/Decks/GetAll").request().get(String.class);

        //Assert
        Assert.assertTrue(content.length() > 0);
    }

    @Test
    public void GetDeckSuccessStatus() throws Exception {
		//Arrange
			
		//Act 
        int output = target("/Decks/Get/BobRoss").request().get().getStatus();

        //Assert
        Assert.assertTrue(output == successCode);
    }

    @Test
    public void GetDeckGets() throws Exception {
        //Arrange
        Deck d = new Deck("BobRoss", new DefaultShuffler());

        //Act
        String content = target("/Decks/Get/BobRoss").request().get(String.class);

        //Assert
        Assert.assertTrue(d.toString().equals(content));
    }

    @Test
    public void CreateDeckSuccessStatus() throws Exception {
		//Arrange
		
		//Act 		
		int output = target("/Decks/Create/BobRoss2").request().put(Entity.entity("BobRoss2", MediaType.APPLICATION_JSON)).getStatus();
		
		//Assert
        Assert.assertTrue(output == successCode);
    }

    @Test
    public void CreateDeckCreates() throws Exception {
        //Arrange
        target("/Decks/Create/BobRoss2").request().put(Entity.entity("BobRoss2", MediaType.APPLICATION_JSON));
        Deck d = new Deck("BobRoss2", new DefaultShuffler());

        //Act
        String retrieved = target("/Decks/Get/BobRoss2").request().get(String.class);

        //Assert
        Assert.assertTrue(d.toString().equals(retrieved));
    }

    @Test
    public void ShuffleDeckSuccessStatus() throws Exception {
		//Arrange
			
		//Act 
        int output = target("/Decks/Shuffle/BobRoss").request().post(Entity.entity("BobRoss", MediaType.APPLICATION_JSON)).getStatus();

		//Assert
        Assert.assertTrue(output == successCode);
    }

    @Test
    public void ShuffleDeckShuffles() throws Exception {
        //Arrange
        String original = target("/Decks/Get/BobRoss").request().get(String.class);
        target("/Decks/Shuffle/BobRoss").request().post(Entity.entity("BobRoss", MediaType.APPLICATION_JSON)).getStatus();

        //Act
        String shuffled = target("/Decks/Get/BobRoss").request().get(String.class);

        //Assert
        Assert.assertTrue(!original.equals(shuffled));
    }

    @Test
    public void DeleteDeckSuccessStatus() throws Exception {
		//Arrange
			
		//Act 
        int output = target("/Decks/Delete/BobRoss").request().delete().getStatus();
		
		//Assert
        Assert.assertTrue(output == successCode);
    }

    @Test
    public void DeleteDeckDeletes() throws Exception {
        //Arrange
        String original = target("/Decks/Get/BobRoss").request().get(String.class);
        target("/Decks/Delete/BobRoss").request().delete();

        //Act
        int status = target("/Decks/Get/BobRoss").request().get().getStatus();

        //Assert
        Assert.assertTrue(original.length() > 0 && status == 404);
    }
}