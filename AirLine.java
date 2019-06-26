import java.util.Collections;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class AirLine {//The airline maintains data about her various customers, 
	//employees, flights and sales operations performed. Files are being read to
	//receive this data.
	
	
	static Vector<Flights> flightsVector = new Vector<Flights>();
	static Vector<Employees> employeesVector = new Vector<Employees>();
	static Vector<Customers> customersVector = new Vector<Customers>();
	static Vector<TicketsSales> ticketsSalesVector = new Vector<TicketsSales>();

	private FileReader FileReader;

	public void readFromFile(String path) {
		String[] str;
		try {

			FileReader fr = new FileReader(path);
			BufferedReader in = new BufferedReader(fr);
			String temp = in.readLine();
			while ((temp = in.readLine()) != null) {
				str = temp.split("\t"); // c

				if (path.contains("Employees")) {
					Employees(str);
				}

				if (path.contains("Customers")) {
					Customers(str);
				}

				if (path.contains("Flight")) {
					Flight(str);
				}

				if (path.contains("TicketSales")) {
					TicketSales(str);
				}
			}
			in.close();
			fr.close();
		}

		catch (IOException e) {
			System.out.println("couldn't read file" + path);

		}
	}

	public void Customers(String[] str) {
		int id = Integer.parseInt(str[0]);
		String name = str[1];
		int age = Integer.parseInt(str[2]);
		char Gender = str[3].charAt(0);
		int RegisteredByEmployeeID = Integer.parseInt(str[4]);
		Customers c;
		try {
			c = new Customers(id, name, age, Gender, RegisteredByEmployeeID);
			customersVector.addElement(c);

		} catch (GenderException e) {

			e.printStackTrace();

		}
	} // add Customers to vector

	public void Employees(String[] str) {
		int id = Integer.parseInt(str[0]);
		String Profession = str[1];
		String name = str[2];
		int age = Integer.parseInt(str[3]);

		if (Profession.equals("Marketing")) {
			String phone = str[5];

			try {
				Employees c = Employees.kindOfEmployees(id, Profession, name, age, 0, phone);
				employeesVector.addElement(c);

			} catch (IllegalPhoneException e) {
				e.printStackTrace();
			}

		}

		else {
			double bonusRateForSale = Double.parseDouble(str[4]);
			try {
				Employees c = Employees.kindOfEmployees(id, Profession, name, age, bonusRateForSale, "");
				employeesVector.addElement(c);
			} catch (IllegalPhoneException e) {
				e.printStackTrace();
			}
		}

	} // add Employees to vector

	public void Flight(String[] str) {
		String Number = str[0];
		String Type = str[1];
		String Destination = str[2];
		int PricePerTicket = Integer.parseInt(str[3]);
		Flights c = new Flights(Number, Type, Destination, PricePerTicket);
		flightsVector.addElement(c);

	} // add Flight to vector

	public void TicketSales(String[] str) {
		int SoldBy; 
		String Site;
		String FlightNumber = str[0];
		int SoldTo = Integer.parseInt(str[1]);
		int numberOfTickets = Integer.parseInt(str[3]);

		if (str[2].equals("")) {
			SoldBy = 0;
			Site = str[4];
			TicketsSales c = TicketsSales.kindOfSales(FlightNumber, SoldTo, SoldBy, numberOfTickets, Site);
			ticketsSalesVector.addElement(c);
		} else {
			SoldBy = Integer.parseInt(str[2]);
			Site = "";
			TicketsSales c = TicketsSales.kindOfSales(FlightNumber, SoldTo, SoldBy, numberOfTickets, Site);
			ticketsSalesVector.addElement(c);
		}

		if (SoldBy == 0) {
			Site = str[4];
		} else {
			Site = "";
		}
	}

	public AirLine(String flights, String employees, String customers, String ticketsSales) {
		readFromFile(customers);
		readFromFile(flights);
		readFromFile(employees);
		readFromFile(ticketsSales);
		setEmployeesSalaryEvents();

	}//AirLine constructor.

	public void setEmployeesSalaryEvents() {//Running on an employee vector and sending an ID.

		for (int i = 0; i < employeesVector.size(); i++) {
			this.setSalaryEventsToEmployee(employeesVector.elementAt(i).getID());

		}
	}

	public void setSalaryEventsToEmployee(int employeeID) {//Gets ID, running on employee vector
		                                                    //and sending according to type found

		for (int i = 0; i < employeesVector.size(); i++)
			if (employeesVector.elementAt(i) instanceof MarketingEmployees)

				setSalaryEventsToMarketEmployees(employeeID, i);

			else if (employeesVector.elementAt(i) instanceof MaintenanceEmployees)
				setSalaryEventsToMainEmployees(employeeID, i);

			else
				setSalaryEventsToStaffWorkers(employeeID, i);

	}

	public void setSalaryEventsToMarketEmployees(int employeeID, int i) {
		MarketingEmployees MarketEmployees;
		MarketEmployees = (MarketingEmployees) (employeesVector.elementAt(i));
		setCostomerEventMarketEmployees(employeeID, i);
		setTicketsEventMarketEmployees(employeeID, i);
	}

	public void setCostomerEventMarketEmployees(int employeeID, int i) {

		MarketingEmployees MarketEmployees;
		MarketEmployees = (MarketingEmployees) (employeesVector.elementAt(i));
		for (int j = 0; j < customersVector.size(); j++) {
			if (customersVector.elementAt(j).getRegisteredByEmployeeID() == employeeID) {

				MarketEmployees.setCostomerEvent(customersVector.get(j));

			}
		}
	}

	public void setTicketsEventMarketEmployees(int employeeID, int i) {
		MarketingEmployees MarketEmployees;
		MarketEmployees = (MarketingEmployees) (employeesVector.elementAt(i));
		for (int k = 0; k < ticketsSalesVector.size(); k++) {
			if (ticketsSalesVector.elementAt(k) instanceof MarketingSale) {
				if (ticketsSalesVector.elementAt(k).getSoldBy() == employeeID) {
					MarketEmployees.setTicketsEvent(ticketsSalesVector.get(k));
				}
			}
		}
	}

	public void setSalaryEventsToMainEmployees(int employeeID, int i) {
		MaintenanceEmployees MainEmployees;
		MainEmployees = (MaintenanceEmployees) (employeesVector.elementAt(i));
		for (int k = 0; k < employeesVector.size(); k++) {
			if (employeesVector.elementAt(k).getBroughtBy() == employeeID) {
				MainEmployees.setFriendsBroughtEvent(employeesVector.get(k));

			}
		}
	}

	public void setSalaryEventsToStaffWorkers(int employeeID, int i) {
		StaffWorkers StEmplyees;
		StEmplyees = (StaffWorkers) (employeesVector.elementAt(i));
		for (int k = 0; k < employeesVector.size(); k++) {
			if (employeesVector.elementAt(k).getBroughtBy() == employeeID) {
				StEmplyees.setFrindsBroughtEvent(employeesVector.get(k));
			}
		}

	}

	public void printCustomerPastActivity(int customerID) {//Print the amount and price of the flights sold to
		                                                    //the customer and return the total amount.

		printName(customerID);
		int total = printCustomerPastActivityPrinter(customerID);
		System.out.println("total amount for customer-" + total);

	}

	public void printName(int customerID) {// print the Customer Name.

		for (int i = 0; i < customersVector.size(); i++) {
			if (customersVector.elementAt(i).getID() == customerID) {
				System.out.println("Customer name:" + customersVector.elementAt(i).getName());
			}

		}
		System.out.println("Flight list:");
	}

	public int printCustomerPastActivityPrinter(int customerID) {// Printing customers' rankings according to 
		                                                          //their profitable potential.
		int counter = 1, total = 0, numOfTickets, flightCost;
		String FlightNum;
		for (int j = 0; j < ticketsSalesVector.size(); j++) {

			if (ticketsSalesVector.elementAt(j).getSoldTo() == customerID) {
				System.out.print("Flight " + counter + "-" + ticketsSalesVector.elementAt(j).getFlightNumber());
				counter++;
				numOfTickets = ticketsSalesVector.elementAt(j).getNumberOfTickets();
				FlightNum = ticketsSalesVector.elementAt(j).getFlightNumber();
				for (int k = 0; k < flightsVector.size(); k++) {
					if (flightsVector.elementAt(k).getNumber().equals(FlightNum)) {
						flightCost = flightsVector.elementAt(k).getPricePerTicket();
						System.out.println("  price per Ticket - " + flightCost);
						total += (flightCost * numOfTickets);
					}
				}

			}

		}
		return total;
	}

	public void printPotentialCustomersDetails(int[] CusomersIDs) {//Print after receiving a reply 
		                                                            //from the other functions 
		int[] CustomerPotential = new int[CusomersIDs.length];

		calculateCustomerPotential(CusomersIDs, CustomerPotential);
		BubbleSortCustomerPotential(CusomersIDs, CustomerPotential);
		System.out.println("customer potential:");
		String rankName = null;

		for (int s = 0; s < CusomersIDs.length; s++) {
			for (int y = 0; y < customersVector.size(); y++) {
				if (CusomersIDs[s] == customersVector.elementAt(y).getID()) {
					rankName = customersVector.elementAt(y).getName();
					System.out.println("Rank-" + (s + 1) + "." + rankName);
					break;
				}

			}

		}

	}

	public void calculateCustomerPotential(int[] CusomersIDs, int[] CustomerPotential) {//Calculate the profit potential 
		                                                                                 //of each customer
		int numOfTickets;
		String FlightNum;
		int flightCost;

		int total = 0;

		for (int i = 0; i < CusomersIDs.length; i++) {
			for (int j = 0; j < ticketsSalesVector.size(); j++) {
				if (ticketsSalesVector.elementAt(j).getSoldTo() == CusomersIDs[i]) {
					numOfTickets = ticketsSalesVector.elementAt(j).getNumberOfTickets();
					FlightNum = ticketsSalesVector.elementAt(j).getFlightNumber();
					for (int k = 0; k < flightsVector.size(); k++) {
						if (flightsVector.elementAt(k).getNumber().equals(FlightNum)) {
							flightCost = flightsVector.elementAt(k).getPricePerTicket();
							total += (flightCost * numOfTickets);
						}
					}
				}
			}

			CustomerPotential[i] = total;
			total = 0;
		}
	}

	public void BubbleSortCustomerPotential(int[] CusomersIDs, int[] CustomerPotential) {//Bubble Sort.

		int tempSum = 0;
		int tempID = 0;
		for (int g = 0; g < CustomerPotential.length; g++) {
			for (int h = 1; h < (CustomerPotential.length - g); h++) {
				if (CustomerPotential[h - 1] > CustomerPotential[h]) {
					// swap elements
					tempSum = CustomerPotential[h - 1];
					tempID = CusomersIDs[h - 1];

					CustomerPotential[h - 1] = CustomerPotential[h];
					CusomersIDs[h - 1] = CusomersIDs[h];

					CustomerPotential[h] = tempSum;
					CusomersIDs[h] = tempID;
				}

			}
		}
	}

	public void printAgeReport(String a) {//print
		
		int[] customerAge = new int[5];
		
		customerAge= checkcustomerAge(a, customerAge);
		int total=gettingTotal( customerAge);
		print(customerAge, a,total);

	}
	
	public static int gettingTotal(int [] customerAge){
		int total=0;
		for(int i=0; i<customerAge.length;i++)
			total+=customerAge[i];
		
		return total;
		
	}

	public int[] checkcustomerAge(String a, int[] customerAge) {//Checking customer ages by comparing the age and index 
		                                                                  //in the customerAge Array we created.
		String numFlight = null;
		int customerID = 0;
		int age;

		for (int i = 0; i < flightsVector.size(); i++) 
			if (flightsVector.elementAt(i).getDestination().equals(a)) {
				numFlight = flightsVector.elementAt(i).getNumber();
			}

			for (int j = 0; j < ticketsSalesVector.size(); j++) {

				if (ticketsSalesVector.elementAt(j).getFlightNumber().equals(numFlight)) {
					customerID = ticketsSalesVector.elementAt(j).getSoldTo();

					for (int h = 0; h < customersVector.size(); h++) {
						if (customersVector.elementAt(h).getID() == customerID) {
							age = customersVector.elementAt(h).getAge();
							if (age >= 0 && age <= 15)
								customerAge[0]++;
							if (age >= 16 && age <= 21)
								customerAge[1]++;
							if (age >= 22 && age <= 30)
								customerAge[2]++;
							if (age >= 31 && age <= 45)
								customerAge[3]++;
							if (age >= 46 && age <= 85)
								customerAge[4]++;

							
						}
					}
				}
			}
			return customerAge;
		}

	

	public void print(int[] customerAge, String a,int total) {//print

		System.out.println("Destination: " + a);
		System.out.println("0-15:" + (int) (((double) customerAge[0] / (double) total) * 100) + "%");
		System.out.println("16-21:" + (int) (((double) customerAge[1] / (double) total) * 100) + "%");
		System.out.println("22-30:" + (int) (((double) customerAge[2] / (double) total) * 100) + "%");
		System.out.println("31-45:" + (int) (((double) customerAge[3] / (double) total) * 100) + "%");
		System.out.println("46-85:" + (int) (((double) customerAge[4] / (double) total) * 100) + "%");

	}

	public double getOnlineProportion() {//The percentage of online sales made out of the Company's total sales.
		double total = 0;
		for (int i = 0; i < ticketsSalesVector.size(); i++) {

			if (ticketsSalesVector.elementAt(i) instanceof OnlineSale)
				total++;
		}
		return total / ticketsSalesVector.size();
	}

	public double getBalance() { //balance==income - outcome.

		double outcome = getOutCome();
		double income = getIncome();
		double Balance = (income - outcome);
		return Balance;

	}

	public double getOutCome() {//get
		double outcome = 0;
		for (int i = 0; i < employeesVector.size(); i++)
			outcome += employeesVector.elementAt(i).getSalary();

		return outcome;
	}

	public double getIncome() {//get
		double income = 0;
		int numOfTickets;
		String FlightNum;

		for (int i = 0; i < ticketsSalesVector.size(); i++) {
			numOfTickets = ticketsSalesVector.elementAt(i).getNumberOfTickets();
			FlightNum = ticketsSalesVector.elementAt(i).getFlightNumber();
			for (int j = 0; j < flightsVector.size(); j++) {
				if (flightsVector.elementAt(j).getNumber().equals(FlightNum))
					income += (numOfTickets * flightsVector.elementAt(j).getPricePerTicket());
			}
		}
		return income;
	}
	
	public void airlineReportWorkerList(){
		setSalary();
		System.out.println("Workers list");
		Vector<Employees> empVector = (Vector<Employees>) employeesVector.clone();
		Collections.sort(empVector);
		Collections.reverse(empVector);
		printEmployeesList(empVector);
		
	}
	
	public void airlineReportFlightsList(){
		setTicketPerFlight();
		System.out.println("Flights list:");
		Vector<Flights> flyVector = (Vector<Flights>) flightsVector.clone();
		Collections.sort(flyVector);
		Collections.reverse(flyVector);
		printFlightList(flyVector);
		
	}
	public void airlineReportCustomersList(){
		 setNumOfTicketsBought();
		System.out.println("Customers list:");
		Vector<Customers> cusVector = (Vector<Customers>) customersVector.clone();
		Collections.sort(cusVector);
		Collections.reverse(cusVector);
		printCustomersList(cusVector);

	}
	
	public void airlineReport() {//Detailed report on the various items available in the company.

		System.out.println("Airline Report");
		airlineReportWorkerList();
		airlineReportFlightsList();
		airlineReportCustomersList();
	
	}

	public static void printEmployeesList(Vector<Employees> emp) {//print
		for (int i = 0; i < emp.size(); i++)
			System.out.println("Name: " + emp.elementAt(i).getName() + "; age: " + emp.elementAt(i).getAge());
	}

	public static void printFlightList(Vector<Flights> fli) {//print
		for (int i = 0; i < fli.size(); i++)
			System.out.println(fli.elementAt(i).getNumber());
	}

	public static void printCustomersList(Vector<Customers> cus) {//print
		for (int i = 0; i < cus.size(); i++)
			System.out.println("Name: " + cus.elementAt(i).getName() + "; age: " + cus.elementAt(i).getAge()
					+ "; gender: " + cus.elementAt(i).getGender());
	}

	public boolean recommend(int employeeID, int customerID) {//Check whether a recommendation has been made.

		setTicketPerFlight();
		if (isRealMarketingEmployee(employeeID) && isRealCustomer(customerID)) {
			MarketingEmployees MarketEmployees = getEmployee(employeeID);
			MarketEmployees.setRecommendEvent(getCustomer(customerID));
			Vector<Flights> flyVector = (Vector<Flights>) flightsVector.clone();
			Collections.sort(flyVector);
			Collections.reverse(flyVector);
			printTopTen(flyVector);

			return true;
		}
		return false;
	}

	public void setTicketPerFlight() {//Checking  Amount Of Tickets per Flight. 
		int totalAmountOfTickets = 0;
		for (int i = 0; i < flightsVector.size(); i++) {
			for (int j = 0; j < ticketsSalesVector.size(); j++) {
				if (flightsVector.elementAt(i).getNumber().equals(ticketsSalesVector.elementAt(j).getFlightNumber()))
					totalAmountOfTickets += ticketsSalesVector.elementAt(j).getNumberOfTickets();
			}
			flightsVector.elementAt(i).setNumOfTicketPerFlight(totalAmountOfTickets);
			totalAmountOfTickets=0;
		}
	}
	
	public void setNumOfTicketsBought() {//Checking  Amount Of Tickets per Customer. 
		int totalAmountOfTickets = 0;
		for (int i = 0; i < customersVector.size(); i++) {
			for (int j = 0; j < ticketsSalesVector.size(); j++) {
				if (customersVector.elementAt(i).getID()==ticketsSalesVector.elementAt(j).getSoldTo())
					totalAmountOfTickets += ticketsSalesVector.elementAt(j).getNumberOfTickets();
			}
			customersVector.elementAt(i).setNumOfTichetsBought(totalAmountOfTickets);
			totalAmountOfTickets=0;
		}
	}
	
	public void setSalary() {//Checking  Amount Of Tickets per Flight. 
		
		for (int i = 0; i < employeesVector.size(); i++) {
			employeesVector.elementAt(i).calculateSalary();
			
		}
	}

	public void NumOfTichetsBought() {//Checking the amount of tickets sold to the customer.

		int totalAmountOfTickets = 0;
		for (int i = 0; i < customersVector.size(); i++) {
			for (int j = 0; j < ticketsSalesVector.size(); j++) {
				if (ticketsSalesVector.elementAt(j).getSoldTo() == customersVector.elementAt(i).getID())
					totalAmountOfTickets += ticketsSalesVector.elementAt(j).getNumberOfTickets();
			}
			customersVector.elementAt(i).setNumOfTichetsBought(totalAmountOfTickets);
		}
	}

	public boolean isRealCustomer(int customerID) {//Check whether the customer is in 
                                                   //accordance with the ID and return a
                                                   //response accordingly
		for (int i = 0; i < customersVector.size(); i++) {
			if (customersVector.elementAt(i).getID() == customerID)
				return true;
		}
		return false;
	}

	public boolean isRealMarketingEmployee(int employeeID) {//Check whether the marketing Employee
		                                                    //is in accordance with the ID and return
		                                                     //a response accordingly.
		for (int i = 0; i < employeesVector.size(); i++) {
			if (employeesVector.elementAt(i).getID() == employeeID)
				if (employeesVector.elementAt(i) instanceof MarketingEmployees)
					return true;
		}
		return false;
	}

	public MarketingEmployees getEmployee(int employeeID) {//Receiving the employee if the 
		                                                   //conditions are met, otherwise returns null.
		for (int i = 0; i < employeesVector.size(); i++) {
			if (employeesVector.elementAt(i).getID() == employeeID)
				return (MarketingEmployees) employeesVector.elementAt(i);
		}
		return null;
	}

	public Customers getCustomer(int customerID) {//Receiving the customer if the conditions are met,
		                                         //otherwise returns null.
		for (int i = 0; i < customersVector.size(); i++) {
			if (customersVector.elementAt(i).getID() == customerID)
				return customersVector.elementAt(i);
		}
		return null;
	}

	public static void printTopTen(Vector<Flights> topflightnum) {// Print

		for (int i = 0; i < 10; i++)
			System.out.println(topflightnum.get(i).getDestination());
	}

	public static void main(String[] args) {
		String flights = ("Flights.txt");
		String employees = ("Employees.txt");
		String customers = ("Customers.txt");
		String ticketsSales = ("TicketSales.txt");
		AirLine a = new AirLine(flights, employees, customers, ticketsSales);
		a.printCustomerPastActivity(6);
		int[] gg = { 12, 45, 1, 4 };
		a.printPotentialCustomersDetails(gg);
		a.printAgeReport("Melbourne");
		System.out.println(a.getOnlineProportion());
		boolean t = a.recommend(11, 1);
		System.out.println(t);
		a.airlineReport();
		
		System.out.println(a.getBalance());
		System.out.println(AirLine.employeesVector.elementAt(10).getSalary()  );

	}
}