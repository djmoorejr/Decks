package Controllers;

//My Classes
import Classes.Deck;
import Classes.DefaultShuffler;
import Classes.IShuffler;
import Repositories.DeckRepository;

//Utility Classes
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/Decks")
public class DeckController {
	private static DeckRepository DeckList = new DeckRepository();
	
	@GET
	@Path("/GetAll")
    @Produces(MediaType.TEXT_PLAIN)
    public Response GetAll(){
		//Get all values from repository
		ArrayList<Deck> val = DeckList.GetAll();

		//Format values from resource
		String response = "[";
		for(int i = 0; i < val.size(); i++){
			if(i > 0){
				response += ", ";
			}
			Deck item = val.get(i);
			response += item.toString();
		}
		response += "]";

		//Return formatted value with 200 response
        return Response.status(200).entity(response).build();
    }
	
    @GET
    @Path("/Get/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response Get(@PathParam("name") String name){
		//Retrieve deck from repository
		Deck val = DeckList.Get(name);
		if(val != null){
			return Response.status(200).entity(val.toString()).build();
		}else{
			//Inform user that the deck could not be found
			return Response.status(404).entity("Deck not found.").build();
		}
		
    }
	
    @PUT
    @Path("/Create/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response Create(@PathParam("name") String name){
		//Generate a new deck
		IShuffler shuffler = new DefaultShuffler();
		Deck deck = new Deck(name, shuffler);

		//Add deck to repository
		boolean status = DeckList.Add(name, deck);

		//Check status
		if(status){
			//Return confirmation to user that the deck was created.
			return Response.status(200).entity("Deck created.").build();
		}else{
			//Inform user that the deck already exists.
			return Response.status(403).entity("Deck already exists. Could not create new deck.").build();
		}
	}
	
	@POST
    @Path("/Shuffle/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response Shuffle(@PathParam("name") String name){
		//Retrieve deck from repository
		Deck val = DeckList.Get(name);

		//If deck exists
		if(val != null){
			//Shuffle and inform user that their deck was shuffled
			val.Shuffle();
			return Response.status(200).entity("Deck shuffled.").build();
		}else{
			return Response.status(404).entity("Deck not found.").build();
		}
	}
	
	@DELETE
    @Path("/Delete/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response Delete(@PathParam("name") String name){
		//Remove deck from repository
		DeckList.Remove(name);

		//Note: The end result is the same if the deck exists or not. As such, there was no current requirement
		//that dictates the user needs to know more than it does not exist in the repository
		return Response.status(200).entity("Deck removed.").build();
	}

}