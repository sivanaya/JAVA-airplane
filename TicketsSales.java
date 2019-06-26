
 public class TicketsSales {
		
	protected String FlightNumber;
	protected int SoldTo;
	protected int numberOfTickets;


	public TicketsSales( String FlightNumber,  int SoldTo , int numberOfTickets){
		this.FlightNumber = FlightNumber;
		this.SoldTo = SoldTo;
		this.numberOfTickets = numberOfTickets;
	}  // Constructor

	

     public int getSoldTo() {
		return SoldTo;
	}

     public int getNumberOfTickets(){
    	 return numberOfTickets;
     }

     



	public String getFlightNumber() {
		return FlightNumber;
	}

	public int getSoldBy() {
		return getSoldBy();
	}



	public static TicketsSales kindOfSales(String FlightNumber ,int SoldTo, int SoldBy,int numberOfTickets ,String Site){
		//Check Which kind of Sale.
    	 if(SoldBy==0){
    		 TicketsSales t= new OnlineSale(FlightNumber ,SoldTo, numberOfTickets , Site);
    	 return t;
     }
     else {
    	 TicketsSales t= new MarketingSale(FlightNumber ,SoldTo, numberOfTickets , SoldBy);
    	 
    	 return t;
    	 
    	 	 
     }
     }
}
     
	


