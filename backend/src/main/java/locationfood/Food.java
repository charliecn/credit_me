package locationfood;

public class Food {
	public final double price;
	public final String name;
	public Food(double pr, String n) {
		price = pr;
		name = n;
	}
	
	@Override
	public boolean equals(Object o) {
		if(! (o instanceof Food)){
			return false;
		}
		Food that = (Food) o;
		return that.name.equals(this.name);
	}
	
	@Override
	public String toString() {
		return "Food:\nName: " + name + "\nprice: " + price + "\n";
	}
}
