package locationfood;

/**
 * Represents any location 
 * @author lucieackley
 *
 */
public class Location {

	private String id;
	private String name;
	private String section;

	/**
	 * A location constructor.
	 * @param id - id of location
	 * @param name - name of location 
	 * @param section - section on map
	 * @param description - description of location
	 */
	public Location(String id, String name, String section){
		this.id = id;
		this.name = name;
		this.section = section;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}


	public String getSection() {
		return section;
	}
	
	@Override
	public boolean equals(Object o) {
		if(! (o instanceof Location)){
			return false;
		}
		Location that = (Location) o;
		return that.id.equals(this.id);
	}
	
	@Override
	public String toString() {
		return "Location:\nName: " + name + "\nID: " + id + "\nSection: " + section + "\n";
	}
}
