import java.util.Vector;

public class MaintenanceEmployees extends Employees {

	double bonusRateForSale;
	private Vector<Employees> FriendsBroughtEvent;
	int baseSalary;
	int broughtBy;
	int Salary;

	public MaintenanceEmployees(int ID, String Name, int age, double bonusRateForSale) {
		super(ID, Name, age);
		this.bonusRateForSale = bonusRateForSale;
		this.FriendsBroughtEvent = new Vector<Employees>();
		baseSalary = 2500;
		broughtBy = (int) ((Math.random() * 63) + 1);
		this.Salary=Salary;
	}

	public Vector<Employees> getFriendsBroughtEvent() {
		return FriendsBroughtEvent;
	}

	public void setFriendsBroughtEvent(Employees e) {
		this.FriendsBroughtEvent.addElement(e);
	}

	public int getBroughtBy() {
		return broughtBy;
	}
	
	public int getBaseSalary() {
		return baseSalary;
	}
	public int getSalary() {
		return Salary;
	}
	public void setSalary(int salary) {
		this.Salary = salary;
	}

	public void calculateSalary() {// calculate the salary.
		int bonus=0;
		for(int i=0;i<this.FriendsBroughtEvent.size();i++){
			bonus+=(this.FriendsBroughtEvent.elementAt(i).getBaseSalary()*this.bonusRateForSale);
		}
		this.setSalary(baseSalary+bonus);
	}

}
