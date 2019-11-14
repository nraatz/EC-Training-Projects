import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Trip {

	private int resNumber;  //randomly generated in constructor
	private Date tripDate;  //taken as input at start, can be changed
	private int busNumber;  //1 or 2, for Rocket or Deluxe
	private int fareType;   //1, 2, or 3, for Premium, Business, and Coach
	private int seatNumber; //1-20 for each bus, managed in main class
	private int numBags;    //taken as input at start, can be changed
	private int totalPrice; //uses busNumber, fareType, and numBags to calculate this
	private Customer cust;  //Takes in Customer's object so it can check name
	
	public Trip(Customer cust, Date tripDate, int busNumber, int seatNumber, int fareType, int numBags) 
	{
		this.cust = cust;
		this.tripDate = tripDate;
		this.busNumber = busNumber; // 1 for Rocket Bus, 2 for Deluxe Bus
		this.numBags = numBags;
		this.fareType = fareType;   // 1 for Premium, 2 for Business, 3 for Coach
		
		this.totalPrice = 0;
		if (busNumber == 1)
		{
			if (fareType == 1)
			{
				totalPrice += 50;
			} else if (fareType == 2) {
				totalPrice += 35;
			} else if (fareType == 3) {
				totalPrice += 20;
			}
		} else if (busNumber == 2) 
		{
			if (fareType == 1)
			{
				totalPrice += 100;
			} else if (fareType == 2) {
				totalPrice += 70;
			} else if (fareType == 3) {
				totalPrice += 50;
			}
		}
		
		if (busNumber == 1)
		{
			totalPrice += (numBags * 7);
		} else if (busNumber == 2) {
			totalPrice += (numBags * 15);
		}
		
		
		this.seatNumber = seatNumber;
		this.resNumber = (int)(Math.random() * 1000 + 1);
	}

	public Date getTripDate() {
		return tripDate;
	}

	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}

	public int getNumBags() {
		return numBags;
	}

	public void setNumBags(int numBags) {
		this.numBags = numBags;
	}
	
	public int getFareType() {
		return this.fareType;
	}
	
	public void setFareType(int fare) {
		this.fareType = fare;
	}

	public int getResNumber() {
		return resNumber;
	}

	public int getBusNumber() {
		return busNumber;
	}
	
	public void setBusNumber(int busNumber) {
		this.busNumber = busNumber;
	}

	public int getSeatNumber() {
		return seatNumber;
	}
	
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public int getTotalPrice() {
		return totalPrice;
	}
	
	public void updateTotalPrice() {
		this.totalPrice = 0;
		if (busNumber == 1)
		{
			if (fareType == 1)
			{
				this.totalPrice += 50;
			} else if (fareType == 2) {
				this.totalPrice += 35;
			} else if (fareType == 3) {
				this.totalPrice += 20;
			}
		} else if (busNumber == 2) 
		{
			if (fareType == 1)
			{
				this.totalPrice += 100;
			} else if (fareType == 2) {
				this.totalPrice += 70;
			} else if (fareType == 3) {
				this.totalPrice += 50;
			}
		}
		
		if (busNumber == 1)
		{
			this.totalPrice += (numBags * 7);
		} else if (busNumber == 2) {
			this.totalPrice += (numBags * 15);
		}
	}
	
	public void displayTripDetails()
	{
		System.out.println("\nTrip Details:");
		System.out.println("- Reservation Number: " + resNumber);
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String dateStr = dateFormat.format(tripDate);
		System.out.println("- Trip Date: " + dateStr);
		System.out.print("- Bus Number: ");
		if (busNumber == 1)
		{
			System.out.println("Rocket Bus");
		} else if (busNumber == 2)
		{
			System.out.println("Deluxe Bus");
		}
		System.out.print("- Fare Type: ");
		if (fareType == 1)
		{
			System.out.println("Premium");
		} else if (fareType == 2)
		{
			System.out.println("Business");
		} else if (fareType == 3)
		{
			System.out.println("Coach");
		}
		System.out.println("- Seat Number: " + seatNumber);
		System.out.println("- Number of Bags: " + numBags);
		updateTotalPrice();
		System.out.println("- Total Price: $" + totalPrice);
		
	}
	
	
	
	
	
	
	
}
