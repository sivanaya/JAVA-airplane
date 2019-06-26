
public class OnlineSale extends TicketsSales {

	public String Site;
	

	public OnlineSale( String FlightNumber,  int SoldTo , int numberOfTickets, String Site){
	
	super(FlightNumber, SoldTo , numberOfTickets);
	this.Site = Site;
	
	} // Constructor
	
	
}
