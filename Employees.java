import java.util.Vector;

abstract public class Employees implements Comparable {

	int ID;
	String Name;
	int age;

	public Employees(int ID, String Name, int age) {

		this.ID = ID;
		this.Name = Name;
		this.age = age;
	}// constructor

	public int getID() {
		return ID;
	}
	

	public String getName() {
		return Name;
	}


	public int getAge() {
		return age;
	}


	public static Employees kindOfEmployees(int ID, String Profession, String Name, int age, double bonusRateForSale,
			String Phone) throws IllegalPhoneException {// check whoce kind of Employee 
		                                                // and creating him accordingly.

		if (Profession.equals("Maintenance")) {
			Employees c = new MaintenanceEmployees(ID, Name, age, bonusRateForSale);
			return c;

		}
		if (Profession.equals("Marketing")) {
			Employees c;
			c = new MarketingEmployees(Name, ID, age, Phone);
			return c;

		}

		if (Profession.equals("Pilot") || Profession.equals("Security") || Profession.equals("Flight Attendant")) {
			Employees c = new StaffWorkers(ID, Profession, age, Name, bonusRateForSale);
			return c;

		}
		return null;

	}
	

	public int compareTo(Object o) { // implements Comparable, compare between Employee calculateSalary.
		if (o instanceof Employees) {
			if (this.getSalary() > ((Employees) o).getSalary())
				return 1;
			else if (this.getSalary() < ((Employees) o).getSalary())
				return -1;
			else
				return 0;
		}
		return 999;
	}

	abstract public int getBroughtBy();
	abstract public int getBaseSalary();
	abstract public int getSalary(); 
	abstract public void calculateSalary();

}



