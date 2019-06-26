import java.util.Vector;

public class StaffWorkers extends Employees {
	String profession;
	double bonusRateForSale;
	int baseSalary;
	int Salary;
	private Vector<Employees> FriendsBroughtEvent;
	protected int broughtBy;

	public StaffWorkers(int ID, String profession, int age, String Name, double bonusRateForSale) {
		super(ID, Name, age);
		this.profession = profession;
		this.bonusRateForSale = bonusRateForSale;
		this.FriendsBroughtEvent = new Vector<Employees>();
		broughtBy=(int) ((Math.random() * 63) + 1);
		
		if(profession.equals("Pilot")){ // put the basaSalary accordingly.
			baseSalary=7500;}
			else if(profession.equals("Security")){
				baseSalary=5500;}
				else{
					baseSalary=3500;}
		this.Salary=Salary; // The final salary is determined according to the calculation.	
	}

	public Vector<Employees> getFrindsBroughtEvent() {
		return FriendsBroughtEvent;
	}

	public void setFrindsBroughtEvent(Employees e) {
		this.FriendsBroughtEvent.addElement(e);
	}
	
	public int getBaseSalary() {
		return baseSalary;
	}

	public String getProfession() {
		return profession;
	}

	public int getBroughtBy() {
		return broughtBy;
	}

	public int getSalary() {
		return Salary;
	}

	public void setSalary(int salary) {
		this.Salary = salary;
	}

	public void calculateSalary() { //Calculate the salary with base salary+ bonus.
		int bonus=0;
		for(int i=0;i<this.FriendsBroughtEvent.size();i++){ // bonus calculate.
			bonus+=(this.FriendsBroughtEvent.elementAt(i).getBaseSalary()*this.bonusRateForSale);
		}
		this.setSalary(baseSalary+bonus);
	}

}
