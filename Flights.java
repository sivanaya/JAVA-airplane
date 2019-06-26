
public class Flights implements Comparable {
	String Number;
	String Type;
	String Destination;
	int PricePerTicket;
	int numOfTicketPerFlight;
	
	public Flights(	String Number, String Type, String Destination ,int PricePerTicket){
		this.Number=Number;
		this.Type=Type;
		this.Destination=Destination;
		this.PricePerTicket=PricePerTicket;
		this.numOfTicketPerFlight=numOfTicketPerFlight;
		
	}//constructor	

	public int getNumOfTicketPerFlight() {
		return numOfTicketPerFlight;
	}

	public void setNumOfTicketPerFlight(int numOfTicketPerFlight) {
		this.numOfTicketPerFlight = numOfTicketPerFlight;
	}

	public String getNumber() {
		return Number;
	}

	public int getPricePerTicket() {
		return PricePerTicket;
	}

	public String getDestination() {
		return Destination;
	}
	public int compareTo(Object o) {// implements Comparable, compare between numOfTicketPerFlight.
		if (o instanceof Flights) {
			if (this.numOfTicketPerFlight > ((Flights) o).numOfTicketPerFlight)
				return 1;
			else if (this.numOfTicketPerFlight < ((Flights) o).numOfTicketPerFlight)
				return -1;
			else
				return 0;
		}
		return 999;
	}

		
	}


