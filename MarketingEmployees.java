import java.util.Vector;

public class MarketingEmployees extends Employees {

	String Phone;
	private Vector<Customers> costomerEvent;
	private Vector<TicketsSales> ticketsEvent;
	private Vector<Customers> recommendEvent;
	int baseSalary;
	int broughtBy;
	int Salary;

	public MarketingEmployees(String Name, int ID, int age, String Phone) throws IllegalPhoneException {
		super(ID, Name, age);
		baseSalary = 0;
		broughtBy = (int) ((Math.random() * 63) + 1); // broughtBy Randomaly.

		if (Phone.charAt(0) != '0' || Phone.length() != 10) { // it there is IllegalPhone throw IllegalPhoneException.

			throw new IllegalPhoneException();
		}
		this.Phone = Phone; // to do exception e.
		this.costomerEvent = new Vector<Customers>();
		this.ticketsEvent = new Vector<TicketsSales>();
		this.recommendEvent= new Vector<Customers>();
		this.Salary=Salary; // The final salary is determined according to the calculation.	
	}

	public Vector<Customers> getCostomerEvent() {
		return costomerEvent;
	}

	public void setCostomerEvent(Customers c) {
		this.costomerEvent.addElement(c);
	}

	public Vector<TicketsSales> getTicketsEvent() {
		return ticketsEvent;
	}

	public void setTicketsEvent(TicketsSales t) {
		this.ticketsEvent.addElement(t);

	}
	
	public Vector<Customers> getRecommendEvent() {
		return recommendEvent;
	}

	public void setRecommendEvent(Customers c) {
		this.recommendEvent.addElement(c);;
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

	public void calculateSalary() { // calculate the salary.
		int bonusCustomer=(this.costomerEvent.size())*20;
		int bonusRecommend= (this.recommendEvent.size()*2);
		int bonusTickets=0;
		for(int i=0; i<this.ticketsEvent.size();i++)
			bonusTickets+=((this.ticketsEvent.elementAt(i).getNumberOfTickets())*15);
		this.setSalary(bonusTickets+bonusCustomer+bonusRecommend);
		}
	}

	
	
	
       

