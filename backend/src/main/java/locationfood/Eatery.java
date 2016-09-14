package locationfood;

/**
 * A class for places to eat at
 * @author lucieackley
 *
 */
public class Eatery {
	
	private int id;
	private String eateryName;
	private String section;
	private int start;
	private int end;
	private String description;

	/**
	 * Construct for Eatery
	 * @param id - id of the eatry
	 * @param eateryName - name  of the eatery
	 * @param section - map section
	 * @param start - when it opens
	 * @param end - when it closes
	 * @param description - description of eatery 
	 */
	public Eatery(int id, String eateryName, String section, 
			int start, int end, String description){
		this.id = id;
		this.eateryName = eateryName;
		this.section = section;
		this.start = start;
		this.end = end;
		this.description = description;
	}

	public int getId() {
		return id;
	}
	
	public String getEateryName(){
		return eateryName;
	}

	public String getSection() {
		return section;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public boolean equals(Object o){
		if(! (o instanceof Eatery)){
			return false;
		}
		Eatery that = (Eatery) o;
		return that.id == this.id;
	}
	
	@Override
	public String toString() {
		return "Eatery:\nName: " + eateryName + "\nID: " + id + "\nSection: " + section + "\n";
	}

}
