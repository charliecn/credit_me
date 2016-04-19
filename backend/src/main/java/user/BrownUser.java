package user;
import deal.Offer;
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
	public BrownUser(String name, String email, String password, boolean subscribe) {
		super(name, email, password, subscribe);
		
	}
	
	/**
	 * a brown user can post an offer to sell a meal credit
	 * @param offer
	 */
	public Offer postOffer(Offer offer){
    return offer;
	}
}
