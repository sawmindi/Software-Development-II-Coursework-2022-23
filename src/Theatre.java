import java.io.*;
import java.util.*;
import java.io.FileNotFoundException;

public class Theatre {
    static Scanner input = new Scanner(System.in);
    private static final int Row1 = 12;       //  Row1 assigns integers to first row of two-dimensional array seats.
    private static final int Row2 = 16;      //  Row2 assigns integers to second row of two-dimensional array seats.
    private static final int Row3 = 20;     //  Row3 assigns integers to third row of two-dimensional array seats.
    private static int[][] seats = new int[3][];   //Create a 2D array to represent the theater seats.
    private static final List<Ticket> tickets = new ArrayList<>();  // the ArrayList for ticket information was created


    static {                                 // Set the correct number of seats in each row when initializing the seats array.

        seats[0] = new int[Row1];
        seats[1] = new int[Row2];
        seats[2] = new int[Row3];
    }

    public static void main(String[] args) {             //The main method contains a while loop that runs until the user inputs 0 to quit

        System.out.println("\nWelcome to the New Theatre");

        int option = 1;
        while (option != 0) {                                 //The case for choice 0 simply displays a goodbye message and breaks out of the while loop.
            System.out.println("\n---------------------------------------\n");
            System.out.println("Please select an option:");   // the 8 options are displayed to the user
            System.out.println("1) Buy a ticket");
            System.out.println("2) Print seating area");
            System.out.println("3) Cancel ticket");
            System.out.println("4) List available seats");
            System.out.println("5) Save to file");
            System.out.println("6) Load from file");
            System.out.println("7) Print ticket information and total price");
            System.out.println("8) Sort tickets by price");
            System.out.println("\t0) Quit\n");
            System.out.print("\nEnter option: ");       // user can enter the preferred option
            try {
                option = input.nextInt();
                if (option > 9){
                    System.out.println("Please enter invalid number");      // this message is displayed to the user if they enter a value out of the options 0 to 8
                    input.next();
                }
            }catch (InputMismatchException e){    //
                System.out.println("Please enter integer again");   // this message will be displayed if the user enters another character instead of an integer
                input.next();
                continue;
            }

            switch (option){
                case 1:
                    buy_ticket();
                    break;
                case 2:
                    print_seating_area();
                    break;
                case 3:
                    cancel_ticket(seats);
                    break;
                case 4:
                    show_available();
                    break;
                case 5:
                    save(seats);
                    break;
                case 6:
                    load();
                    break;
                case 7:
                    show_tickets_info();
                    break;
                case 8:
                    sort_tickets();
                    break;
            }
        }
    }
    public static void buy_ticket(){               //This method, buy ticket(), begins by printing a message to show that the purchase ticket option has been chosen:
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter the row number: ");  // asks the user to enter the row number that they prefer
            int row = input.nextInt();
            if (row < 4 && row > 0) {        // makes sure that the row number they entered is a number within the range 0-3 (excluding 0 and including 3)
                System.out.print("Please enter the seat number: ");       // asks the user to enter the seat number that they prefer
                int seat = input.nextInt();
                if (seat<=seats[row-1].length && seat>0) {   // makes sure that the seat number entered by the user is within the range of the maximum number of seats for each row (Row1- 12 seats, Row2- 16 seats, Row3- 20 seats)
                    for (int i = 0; i < seats.length; i++) {     // a for count is made to repeat the same process for all the three rows
                        if (row == i + 1 && seat <=seats[i].length) {   // checks whether the seat number and the row number entered by the user is valid
                            if (seats[i][seat - 1] == 1) {    // if the particular seat number entered by the user holds the value(1) in the array it means that the seat has already been occupied
                                System.out.println("Seat occupied");   // this message will be displayed to the user so that he can book another seat
                            }else if (seats[i][seat - 1] == 0) {     // if the array holds the value 0 (means that the seat is not yet occupied) then the user is allowed to book the seat after getting his/ her ticket information
                                System.out.print("Please enter the name: ");    // the user is asked to enter the name
                                String name = input.next();

                                System.out.print("Please enter the surname: ");// the user is asked to enter the surname
                                String surname = input.next();

                                System.out.print("Please enter the email: "); // the user is asked to enter the email
                                String email = input.next();

                                Person person = new Person(name, surname, email);
                                System.out.print("Please enter the price of a ticket: £ ");
                                double price = input.nextDouble();
                                Ticket ticket = new Ticket(row, seat, price, person);
                                tickets.add(ticket);   // the ticket consisting of all the user information is added to the ArrayList
                                seats[i][seat - 1] = 1;  // now the array value in the seat booked by the user is changed to 1 to indicate that the seat is now being occupied.

                            }
                        }
                    }
                } else{
                    System.out.println("Please enter a valid seat number.");    // the message is displayed to the user indicating that he/ she is entering a seat value that is out of the range
                }
            } else {
                System.out.println("Error: This row number does not exist. Please select 1-3.");    // the message is displayed to the user indicating that he/ she is entering a row value that is out of the range
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter an integer");  // an error message will be displayed if the user enter another character instead of an integer.
        }
    }
    public static void print_seating_area(){
        System.out.print("     ");
        for(int i=0;i<=10;++i){
            System.out.print("*");
        }
        System.out.println(" ");
        System.out.println("     *  STAGE  *");
        System.out.print("     ");
        for(int i=0;i<=10;i++){
            System.out.print("*");
        }
        System.out.println(" ");

        for (int i = 0; i < seats.length; i++) {         //initializes a loop that iterates through each row of the seats 2D array.
            if( i== 0){
                System.out.print("    ");
            }
            if (i == 1) {
                System.out.print("  ");
            }
            if(i == 2){
                System.out.print("");
            }//it loops through the 2D array seats using two nested for loops
            for (int j = 0; j < seats[i].length; j++) {                   //initializes another loop that iterates through each element in the current row of the seats 2D array.

                if(j==seats[i].length/2){    //checks whether the current column index j is equal to half the length of the current row. If it is, it prints a single space.
                    System.out.print(" ");
                }

                if(seats[i][j]==0){   //check the value of the current element in the seats array.
                    System.out.print("O");    //If it is 0, the code prints "O"
                }else{
                    System.out.print("X");    //If it is 1, the code prints "x"
                }
            }
            System.out.println();
        }
    }
    public static void ticketRemoval(int row, int seat){
        Ticket removeTicket = null;
        for (Ticket ticket : tickets) {
            if (ticket.getRow() == row && ticket.getSeat() == seat) {   // checks for the presence of the row and seat in each ticket
                removeTicket = ticket;
                break;
            }
        }if (removeTicket != null) {
            tickets.remove(removeTicket);  // if the row and seat are both their in t=the ticket then the ticket is removed as requested by the user
        }
    }
    public static void cancel_ticket(int[][] seats) {
        System.out.println("----Cancellation----");
        System.out.println();
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Please enter the row number: ");  // the user is asked to enter the row number that they want to cancel
            int row = input.nextInt();
            System.out.print("Please enter the seat number: "); // the user is asked for the seat number that they want to cancel.
            int seat = input.nextInt();
            if (row < 4 && row > 0) {   // checks whether the cancelling row is within the range 0-3
                if (seat<=seats[row-1].length && seat>0) {
                    for (int i = 0; i < 3; i++) {
                        if (row == i + 1 && seat < seats[i].length && seats[i][seat - 1] == 1) {    // checks whether the seat has been occupied
                            seats[i][seat - 1] = 0;   // the arrays value is changed back to 0 to indicate that it is again empty
                            ticketRemoval(row, seat);   // the appropriate ticket according to the information given by the user will be cancelled
                        }
                        else if (row == i + 1 && seat < seats[i].length && seats[i][seat - 1] == 0){
                            System.out.println("Please re-enter. This seat has not been occupied"); // this message will be displayed indicating that the seat has not yet been occupied
                        }
                    }
                } else {
                    System.out.println("Incorrect entry. Please re-enter a valid seat");
                }
            }else {
                System.out.println("Incorrect entry. Please re-enter a valid row");
            }
            System.out.println("----successfully Cancelled----");
        } catch(IndexOutOfBoundsException e){
            System.out.println("Please enter an integer.");
        }
    }
    public static void show_available(){
        System.out.println("Available seats");
        System.out.print("-------------------------\n");

        for (int row = 0; row< 3; row++) {                             // the seats in the current row of the seats 2D array.
            System.out.print("Seats available in row " + (row+1) + ": ");
            for (int seat = 0; seat < seats[row].length; seat++) {
                if (seats[row][seat] == 0) {       //if statement checks is the current seat is available,
                    System.out.print(seat + 1+", ");
                }
            }
            System.out.println();
        }
    }
    private static void save(int[][] seats){
        try{
            FileWriter saveFile= new FileWriter("Save.txt");
            PrintWriter rowsPrint = new PrintWriter(saveFile);
            for (int row=0; row < seats.length; row++) {
                rowsPrint.println("");
                rowsPrint.print("Row " +(row+1)+": ");
                for (int seat=0; seat<seats[row].length; seat++) {
                    rowsPrint.print(seats[row][seat]); // the arrays information for each row is being stored in a text file
                }
            }
            saveFile.close();
            System.out.println("\n----successfully saved----");
        } catch (IOException e){
            System.out.println("Error occurred while writing to a file.");
            e.printStackTrace();
        }
    }
    public static void load(){
        try {
            File file= new File("save.txt");                                   //denotes a new file object named 'save.txt'
            Scanner file_reader = new Scanner(file);                                    //create scanner named 'file_reader'
            while (file_reader.hasNextLine()) {                                         //start read the file line by line
                String data = file_reader.nextLine();
                System.out.println(data); // the arrays information for each row stored in the text file is being displayed.
            }
            file_reader.close();

            System.out.println("\n----Successfully restore the data----");

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void show_tickets_info() {
        double TotalPriceTicket = 0.0;
        for (Ticket ticket : tickets) {
            ticket.print();
            TotalPriceTicket += ticket.getPrice();  // the ticket prices are added up
            System.out.println("\n-------------------------------------------------");
        }
        System.out.println("Total price of tickets: £ "+TotalPriceTicket);  // the total price of all the tickets are displayed  after displaying all the ticket information
    }
    private static void sort_tickets() {
        int bottom = tickets.size() - 2;            //integer variable bottom and initializes it to the index of the second-to-last element in the tickets list.
        Ticket temp;
        boolean exchanged = true;    // a boolean variable exchanged and initializes it to true
        while (exchanged) {   // bubble sort algorithm
            exchanged = false;
            for (int i = 0; i <= bottom; i++) {        //iterate over each element in the tickets list up to and including bottom
                if (tickets.get(i).getPrice() > tickets.get(i+1).getPrice()) {
                    temp = tickets.get(i);                       // the ticket at index i in the temp variable.
                    tickets.set(i,tickets.get(i+1));             //replaces the ticket at index i with the ticket at index i+1.
                    tickets.set(i+1,temp);                    //replaces the ticket at index i+1 with the ticket that was originally at index i
                    exchanged = true;
                }
            }
            bottom--;
        }
        for (Ticket ticket : tickets) {
            ticket.print();   // the tickets  are displayed according to the prices in ascending order.
        }
    }
}


/* references
 * https://www.w3schools.com/java/java_methods.asp
 * https://www.tutorialspoint.com/java/java_loop_control.htm
 * https://www.tutorialspoint.com/java/index.htm
 * https://www.w3schools.com/java/default.asp*/