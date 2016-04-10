package user;
import deal.Offer;
import deal.Request;
/**
 * a BrownUser is anyone with a Brown email address
 * who can post offers to sell meal credits
 * @author lucieackley
 *
 */
public class BrownUser extends User {

	/**
	 * a brown user is constructed in the same way as a user
	 * @param name - name of user
	 * @param email - must be a brown email address
	 * @param id - id of user
	 * @param rating - rating of user
	 */
	public BrownUser(String name, String email, String id, int rating, gender gend) {
		super(name, email, id, rating, gend);
		
	}
	
	/**
	 * a brown user can post an offer to sell a meal credit
	 * @param offer
	 */
	public Offer postOffer(Offer offer){
    return offer;
	}

  @Override
  public void deleteUser() {    
  }

  @Override
  public Request postRequest() {
    return null;
  }

  @Override
  protected void updateTitles() {
  }
	
	
	

}
