import java.util.*;

public class MainApp {
	
	public static HashMap<Customer, Trip> masterList = new HashMap<Customer, Trip>();
	public static boolean[] rocketArray = new boolean[10];
	public static boolean[] deluxeArray = new boolean[10];
	public static Scanner scan = new Scanner(System.in);
	public static int selection = 0;
	public static int currID = 1;
	public static String textSelection = "";

	public static void OpeningMenu() 
	{
		
		System.out.println("Bus Reservation Opening Menu:\n\n");
		System.out.println("1) New Customer");
		System.out.println("2) Returning Customer");
		System.out.print("\nPlease Select An Option: ");
		
		selection = scan.nextInt();
		
		if (selection == 1) // New Customer
		{
			CreateCustomer();
		} 
		else if (selection == 2) // Returning Customer
		{
			int selectNum = 1;
			Customer[] selectionArray = new Customer[masterList.size()];
			System.out.println("\n");
			
			for (Map.Entry<Customer, Trip> entry : masterList.entrySet())
			{
				System.out.println(selectNum + ") " + entry.getKey().getName());
				selectionArray[selectNum - 1] = entry.getKey();
				selectNum += 1;
			}
			
			System.out.println(selectNum + ") Exit");
			
			System.out.print("Please Select An Option: ");
			
			selection = scan.nextInt();
			
			if (selection == selectNum)
			{
				System.out.println("Now Returning to Opening Menu");
				textSelection = scan.nextLine();
				OpeningMenu();
			} else if (selection > selectNum || selection < 1) {
				System.out.println("Invalid Selection, returning to Opening Menu.");
				OpeningMenu();
			} else {
				LoggedInMenu(selectionArray[selection - 1]);
			}
			
		}
		
		
	}
	
	public static void CreateCustomer()
	{
		String tempName, tempAddress, tempCredit;
		System.out.print("Hello, New Customer!\nPlease Enter Your Name: ");
		tempName = scan.nextLine();
		tempName = scan.nextLine();
		System.out.println(">The tempName is " + tempName);
		
		System.out.print("\nPlease Enter Your Address: ");
		tempAddress = scan.nextLine();
		System.out.println(">The tempAddress is " + tempAddress);
		
		System.out.print("\nPlease Enter Your Credit Card Number: ");
		tempCredit = scan.nextLine();
		System.out.println(">The tempCredit is " + tempCredit);
		
		Customer tempCust = new Customer(tempName, (currID), tempAddress, tempCredit);
		
		masterList.put(tempCust, null);
		
		System.out.print("\n\nThank you, " + tempCust.getName() + ", please press Enter to log in.");
		
		textSelection = scan.nextLine();
		LoggedInMenu(tempCust);
		
	}
	
	public static void LoggedInMenu(Customer customer)
	{
		System.out.println("This is the Logged In Menu");
		System.out.println("You are logged in as: " + customer.getName() + "\n");
		textSelection = scan.nextLine();
		
		if (masterList.get(customer) == null) // If Customer has No Reservation
		{
			System.out.println("\n1) Create New Reservation");
			System.out.println("2) Log Out");
			
			System.out.print("\nPlease Enter an Option: ");
			selection = scan.nextInt();
			
			if (selection == 1) // Create New Reservation
			{
				CreateReservation(customer);
			} 
			else if (selection == 2) // Log Out
			{
				System.out.println("Have a good day!\n");
				OpeningMenu();
			} 
			else 
			{
				System.out.println("Invalid Selection, Please Try Again");
				LoggedInMenu(customer);
			}
			
		}
		else // If Customer already has a Reservation
		{
			System.out.println("\n1) View Reservation Details");
			System.out.println("2) Change Reservation");
			System.out.println("3) View Available Seats");
			System.out.println("4) Cancel Reservation");
			System.out.println("5) Log Out");
			
			System.out.print("\nPlease Enter an Option: ");
			selection = scan.nextInt();
			
			if (selection == 1) //View Reservation Details
			{
				masterList.get(customer).displayTripDetails();
				textSelection = scan.nextLine();
				LoggedInMenu(customer);
			} 
			else if (selection == 2) //Change Reservation
			{
				ChangeReservation(customer);
			} 
			else if (selection == 3) //View Available Seats
			{
				if (masterList.get(customer).getBusNumber() == 1)
				{
					System.out.println("Current Seat Map for Rocket Bus: ");
					for (int i = 1; i <= 10; i++)
					{
						System.out.print("Seat " + i + ": ");
						if (rocketArray[i-1] == true)
						{
							System.out.println("[Filled]");
						}
						else
						{
							System.out.println("[ ]");
						}
					}
					System.out.println("Press Enter To Return To Menu.");
					textSelection = scan.nextLine();
					textSelection = scan.nextLine();
					LoggedInMenu(customer);
				}
				else if (masterList.get(customer).getBusNumber() == 2)
				{
					System.out.println("Current Seat Map for Deluxe Bus: ");
					for (int i = 1; i <= 10; i++)
					{
						System.out.print("Seat " + i + ": ");
						if (deluxeArray[i-1] == true)
						{
							System.out.println("[Filled]");
						}
						else
						{
							System.out.println("[ ]");
						}
					}
					System.out.println("Press Enter To Return To Menu.");
					textSelection = scan.nextLine();
					textSelection = scan.nextLine();
					LoggedInMenu(customer);
				}
			} 
			else if (selection == 4) // Cancel Reservation
			{
				System.out.println("\nAre You Sure You Wish to Cancel Your Reservation?");
				System.out.println("\n1) Yes, I Am Sure");
				System.out.println("2) No, I Don't Want to Cancel My Reservation\n");
				
				System.out.print("Please Enter an Option: ");
				
				selection = scan.nextInt();
				if (selection == 1)
				{
					if (masterList.get(customer).getBusNumber() == 1)
					{
						rocketArray[masterList.get(customer).getSeatNumber() - 1] = false;
					} else if (masterList.get(customer).getBusNumber() == 2)
					{
						deluxeArray[masterList.get(customer).getSeatNumber() - 1] = false;
					}
					masterList.replace(customer, null);
					System.out.println("Reservation has been cancelled");
					textSelection = scan.nextLine();
					LoggedInMenu(customer);
				}
				else if (selection == 2)
				{
					LoggedInMenu(customer);
				}
				else 
				{
					System.out.println("Invalid Selection, Please Try Again");
					LoggedInMenu(customer);
				}
			} 
			else if (selection == 5) // Log out
			{
				System.out.println("Have a good day!\n");
				OpeningMenu();
			} 
			else
			{
				System.out.println("Invalid Selection, Please Try Again");
				LoggedInMenu(customer);
			}
			
			
			
			
		}
		
		System.out.println("Returning To Opening Menu at End of LoggedInMenu");
		OpeningMenu();
	}
	
	public static void CreateReservation(Customer customer)
	{
		// STEP ONE: Entering the Date
		int tempMonth, tempDay, tempYear;
		System.out.println("Step 1) Entering the Date of the Trip\n\n");
		System.out.print("Please Enter the Month in numeric form: ");
		tempMonth = scan.nextInt();
		// validate that
		
		System.out.print("\nPlease Enter the Day in numeric form: ");
		tempDay = scan.nextInt();
		// validate that
		
		System.out.print("\nPlease Enter the Year: ");
		tempYear = scan.nextInt();
		// validate that
		
		Date entryDate = new Date(tempYear - 1900, tempMonth - 1, tempDay);
		
		//STEP TWO: Select Bus
		int numVacancies = 10;
		int busNum = 0;
		System.out.println("Step 2) Select Your Bus\n\n");
		
		for (int i = 1; i <= 10; i++)
		{
			if (rocketArray[i-1] == true)
			{
				numVacancies -= 1;
			}
		}
		
		System.out.println("1) Rocket Bus (" + numVacancies + " Vacancies Left)");
		
		numVacancies = 10;
		for (int i = 1; i <= 10; i++)
		{
			if (deluxeArray[i-1] == true)
			{
				numVacancies -= 1;
			}
		}
		System.out.println("2) Deluxe Bus (" + numVacancies + " Vacancies Left)");
		
		System.out.print("\nPlease Enter an Option: ");
		busNum = scan.nextInt();
		
		while (busNum != 1 && busNum != 2)
		{
			System.out.println("Invalid Option, please try again.");
			textSelection = scan.nextLine();
			System.out.println("Step 2) Select Your Bus\n\n");
			
			numVacancies = 10;
			for (int i = 1; i <= 10; i++)
			{
				if (rocketArray[i-1] == true)
				{
					numVacancies -= 1;
				}
			}
			
			System.out.println("1) Rocket Bus (" + numVacancies + " Vacancies Left)");
			
			numVacancies = 10;
			for (int i = 1; i <= 10; i++)
			{
				if (deluxeArray[i-1] == true)
				{
					numVacancies -= 1;
				}
			}
			System.out.println("2) Deluxe Bus (" + numVacancies + " Vacancies Left)");
			
			System.out.print("\nPlease Enter an Option: ");
			busNum = scan.nextInt();
		}
		
		// STEP THREE: Select Fare Type
		int fare = 0;
		do
		{
			fare = 0;
			System.out.println("Step 3) Select Fare Type\n\n");
			
			if (busNum == 1)
			{
				System.out.println("1) Premium Fare  ($50)");
				System.out.println("2) Business Fare ($35)");
				System.out.println("3) Coach Fare    ($20)");
			} else if (busNum == 2)
			{
				System.out.println("1) Premium Fare  ($100)");
				System.out.println("2) Business Fare ($70)");
				System.out.println("3) Coach Fare    ($50)");
			}
			
			System.out.print("\nPlease Enter an Option: ");
			
			fare = scan.nextInt();
		} while (fare != 1 && fare != 2 && fare != 3);
		
		// STEP FOUR: Choose A Seat
		
		int seat = 0;
		System.out.println("Step 4) Select Seat Number\n\n");
		if (busNum == 1)
		{
			do 
			{
				System.out.println("Current Seat Map for Rocket Bus: ");
				for (int i = 1; i <= 10; i++)
				{
					System.out.print("Seat " + i + ": ");
					if (rocketArray[i-1] == true)
					{
						System.out.println("[Filled]");
					}
					else
					{
						System.out.println("[ ]");
					}
				}
				
				System.out.print("Please Select an Unfilled Seat: ");
				seat = scan.nextInt();
				if (seat >= 1 && seat <= 10 && rocketArray[seat-1] == true)
				{
					System.out.println("Invalid Seat, Please Try Again");
					textSelection = scan.nextLine();
					seat = 0;
				}
			} while (seat < 1 && seat > 10);
			rocketArray[seat - 1] = true;
		}
		else if (busNum == 2)
		{
			do 
			{
				System.out.println("Current Seat Map for Deluxe Bus: ");
				for (int i = 1; i <= 10; i++)
				{
					System.out.print("Seat " + i + ": ");
					if (deluxeArray[i-1] == true)
					{
						System.out.println("[Filled]");
					}
					else
					{
						System.out.println("[ ]");
					}
				}
				
				System.out.print("Please Select an Unfilled Seat: ");
				seat = scan.nextInt();
				if (seat >= 1 && seat <= 10 && deluxeArray[seat-1] == true)
				{
					System.out.println("Invalid Seat, Please Try Again");
					textSelection = scan.nextLine();
					seat = 0;
				}
			} while (seat < 1 || seat > 10);
			deluxeArray[seat - 1] = true;
			
		}
		
		//STEP FIVE: Choose Number of Checked Bags
		
		int bags = -1;
		
		do 
		{
			System.out.println("\nStep 5) Choose Number of Checked Bags\n\n");
			
			System.out.print("How Many Bags? ");
			bags = scan.nextInt();
			
			if (bags < 0)
			{
				System.out.println("Invalid Number of Bags, Please Try Again");
				textSelection = scan.nextLine();
			}
			
		} while (bags < 0);
		
		// Create New Trip
		
		Trip newTrip = new Trip(customer, entryDate, busNum, seat, fare, bags);
		
		masterList.replace(customer, newTrip);
		
		masterList.get(customer).displayTripDetails();  
		
		LoggedInMenu(customer);
	}
	
	public static void ChangeReservation(Customer customer) 
	{
		System.out.println("\n\n1) Change Date");
		System.out.println("2) Change Bus");
		System.out.println("3) Change Seat");
		System.out.println("4) Change Fare Type");
		System.out.println("5) Change Number of Bags");
		System.out.println("6) Exit");
		
		System.out.print("\nPlease Enter an Option: ");
		selection = scan.nextInt();
		
		if (selection == 1) // Change Date
		{
			int tempMonth, tempDay, tempYear;
			System.out.println("Step 1) Entering the Date of the Trip\n\n");
			System.out.print("Please Enter the Month in numeric form: ");
			tempMonth = scan.nextInt();
			// validate that
			
			System.out.print("\nPlease Enter the Day in numeric form: ");
			tempDay = scan.nextInt();
			// validate that
			
			System.out.print("\nPlease Enter the Year: ");
			tempYear = scan.nextInt();
			// validate that
			
			Date entryDate = new Date(tempYear - 1900, tempMonth - 1, tempDay);
			masterList.get(customer).setTripDate(entryDate);
			ChangeReservation(customer);
		}
		else if (selection == 2) // Change Bus
		{
			
			int seat = 0;
			if (masterList.get(customer).getBusNumber() == 1)
			{
				rocketArray[masterList.get(customer).getSeatNumber() - 1] = false;
				masterList.get(customer).setBusNumber(2);
				System.out.println("You have been placed on the Deluxe Bus!");
				System.out.println("Now you must choose a new seat.");
				do 
				{
					System.out.println("Current Seat Map for Deluxe Bus: ");
					for (int i = 1; i <= 10; i++)
					{
						System.out.print("Seat " + i + ": ");
						if (deluxeArray[i-1] == true)
						{
							System.out.println("[Filled]");
						}
						else
						{
							System.out.println("[ ]");
						}
					}
					
					System.out.print("Please Select an Unfilled Seat: ");
					seat = scan.nextInt();
					if (seat >= 1 && seat <= 10 && deluxeArray[seat-1] == true)
					{
						System.out.println("Invalid Seat, Please Try Again");
						textSelection = scan.nextLine();
						seat = 0;
					}
				} while (seat < 1 || seat > 10);
				deluxeArray[seat - 1] = true;
			}
			else if (masterList.get(customer).getBusNumber() == 2)
			{
				deluxeArray[masterList.get(customer).getSeatNumber() - 1] = false;
				masterList.get(customer).setBusNumber(1);
				System.out.println("You have been placed on the Rocket Bus!");
				System.out.println("Now you must choose a new seat.");
				do 
				{
					System.out.println("Current Seat Map for Rocket Bus: ");
					for (int i = 1; i <= 10; i++)
					{
						System.out.print("Seat " + i + ": ");
						if (rocketArray[i-1] == true)
						{
							System.out.println("[Filled]");
						}
						else
						{
							System.out.println("[ ]");
						}
					}
					
					System.out.print("Please Select an Unfilled Seat: ");
					seat = scan.nextInt();
					if (seat >= 1 && seat <= 10 && rocketArray[seat-1] == true)
					{
						System.out.println("Invalid Seat, Please Try Again");
						textSelection = scan.nextLine();
						seat = 0;
					}
				} while (seat < 1 || seat > 10);
				rocketArray[seat - 1] = true;
			}
			masterList.get(customer).setSeatNumber(seat);
			masterList.get(customer).updateTotalPrice();
			ChangeReservation(customer);
		}
		else if (selection == 3) // Change Seat
		{
			int seat = 0;
			System.out.println("You must now choose a new sea\n\n");
			if (masterList.get(customer).getBusNumber() == 1)
			{
				do 
				{
					System.out.println("You must now choose a new seat on the Rocket Bus.");
					rocketArray[masterList.get(customer).getSeatNumber() - 1] = false;
					System.out.println("Current Seat Map for Rocket Bus: ");
					for (int i = 1; i <= 10; i++)
					{
						System.out.print("Seat " + i + ": ");
						if (rocketArray[i-1] == true)
						{
							System.out.println("[Filled]");
						}
						else
						{
							System.out.println("[ ]");
						}
					}
					
					System.out.print("Please Select an Unfilled Seat: ");
					seat = scan.nextInt();
					if (seat >= 1 && seat <= 10 && rocketArray[seat-1] == true)
					{
						System.out.println("Invalid Seat, Please Try Again");
						textSelection = scan.nextLine();
						seat = 0;
					}
				} while (seat < 1 || seat > 10);
				rocketArray[seat - 1] = true;
			}
			else if (masterList.get(customer).getBusNumber() == 2)
			{
				do 
				{
					System.out.println("You must now choose a new seat on the Deluxe Bus.");
					deluxeArray[masterList.get(customer).getSeatNumber() - 1] = false;
					System.out.println("Current Seat Map for Deluxe Bus: ");
					for (int i = 1; i <= 10; i++)
					{
						System.out.print("Seat " + i + ": ");
						if (deluxeArray[i-1] == true)
						{
							System.out.println("[Filled]");
						}
						else
						{
							System.out.println("[ ]");
						}
					}
					
					System.out.print("Please Select an Unfilled Seat: ");
					seat = scan.nextInt();
					if (seat >= 1 && seat <= 10 && deluxeArray[seat-1] == true)
					{
						System.out.println("Invalid Seat, Please Try Again");
						textSelection = scan.nextLine();
						seat = 0;
					}
				} while (seat < 1 || seat > 10);
				deluxeArray[seat - 1] = true;
				
			}
			masterList.get(customer).setSeatNumber(seat);
			ChangeReservation(customer);
		}
		else if (selection == 4) // Change Fare Type
		{
			int fare = 0;
			do
			{
				fare = 0;
				System.out.println("Select Fare Type\n\n");
				
				if (masterList.get(customer).getBusNumber() == 1)
				{
					System.out.println("1) Premium Fare  ($50)");
					System.out.println("2) Business Fare ($35)");
					System.out.println("3) Coach Fare    ($20)");
				} else if (masterList.get(customer).getBusNumber() == 2)
				{
					System.out.println("1) Premium Fare  ($100)");
					System.out.println("2) Business Fare ($70)");
					System.out.println("3) Coach Fare    ($50)");
				}
				
				System.out.print("\nPlease Enter an Option: ");
				
				fare = scan.nextInt();
			} while (fare != 1 && fare != 2 && fare != 3);
			masterList.get(customer).setFareType(fare);
			masterList.get(customer).updateTotalPrice();
			ChangeReservation(customer);
		}
		else if (selection == 5) // Change Number of Bags
		{
			int bags = -1;
			
			do 
			{
				System.out.println("\nChoose Number of Checked Bags\n\n");
				
				System.out.print("How Many Bags? ");
				bags = scan.nextInt();
				
				if (bags < 0)
				{
					System.out.println("Invalid Number of Bags, Please Try Again");
					textSelection = scan.nextLine();
				}
				
			} while (bags < 0);
			
			masterList.get(customer).setNumBags(bags);
			masterList.get(customer).updateTotalPrice();
			ChangeReservation(customer);
		}
		else if (selection == 6) // Exit
		{
			LoggedInMenu(customer);
		}
		else 
		{
			System.out.println("Invalid Entry, Please Try Again");
			ChangeReservation(customer);
		}
		
		
	}

	public static void main(String[] args) {
		
		OpeningMenu();
		
	}

}
