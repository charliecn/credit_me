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
	private String description;

	/**
	 * A location constructor.
	 * @param id - id of location
	 * @param name - name of location 
	 * @param section - section on map
	 * @param description - description of location
	 */
	public Location(String id, String name, String section, String description){
		this.id = id;
		this.name = name;
		this.section = section;
		this.description = description;
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

	public String getDescription() {
		return description;
	}


}
