
public class Customers implements Comparable {
	
	

	int ID;
	String Name;
	int Age;
	char Gender;
	int RegisteredByEmployeeID;
	int NumOfTichetsBought;
	
	public Customers(int ID, String Name, int Age, char Gender,int RegisteredByEmployeeID) throws GenderException {
	
	this.ID=ID;
	this.Name=Name;
	this.Age=Age;
	this.RegisteredByEmployeeID=RegisteredByEmployeeID;
	
	if (Gender!= 'f' && Gender!='m'){ // if Customers Gender no m or f throw GenderException.
		throw new GenderException();}
	this.Gender=Gender;
	this.NumOfTichetsBought=NumOfTichetsBought;
	

}//constructor.
	public String getName() {
		return Name;
	}


	public int getNumOfTichetsBought() {
		return NumOfTichetsBought;
	}
	public void setNumOfTichetsBought(int numOfTichetsBought) {
		this.NumOfTichetsBought = numOfTichetsBought;
	}
	public int getID() {
		return ID;
	}
	public int getAge() {
		return Age;
	}
	public char getGender() {
		return Gender;
	}
	public int getRegisteredByEmployeeID() {
		return RegisteredByEmployeeID;
	}
	
	public int compareTo(Object o) {// implements Comparable, compare between Customers NumOfTichetsBought.
		if (o instanceof Customers) {
			if (this.NumOfTichetsBought > ((Customers) o).NumOfTichetsBought)
				return 1;
			else if (this.NumOfTichetsBought < ((Customers) o).NumOfTichetsBought)
				return -1;
			else
				return 0;
		}
		return 999;
	}
	
	
	

}

