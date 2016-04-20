package user;

/**
 * a guest user is a user who does not have a brown email address
 * @author lucieackley
 *
 */
public class GuestUser extends User {

	/**
	 * a guest user is a user is constructed exactly like a user
	 * @param name - name of user
	 * @param email - email of user
	 * @param id - id of user
	 * @param rating - rating of user
	 */
	public GuestUser(String name, String email, String password, boolean subscribe) {
	  super(name, email, password, subscribe);
		
	}
}
