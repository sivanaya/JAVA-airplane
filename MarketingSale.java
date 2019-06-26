
public class MarketingSale extends TicketsSales {
	
	protected int soldBy;
	
	public MarketingSale( String FlightNumber,  int SoldTo , int numberOfTickets, int soldBy){
		super(FlightNumber,  SoldTo , numberOfTickets);
			this.soldBy = soldBy;
		} // Constructor

	public int getSoldBy() {
		return soldBy;
	}

	

	
	
	
	
	
	

}
