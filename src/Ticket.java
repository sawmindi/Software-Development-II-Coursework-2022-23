public class Ticket {       //This declares a new class called Ticket which implements the Comparable interface, with Ticket as its generic parameter.
    private final int rowNO;
    private final int seatNO;
    final double price;
    private final Person user;   //This declares an instance variable person of type Person, which will hold the information about the person who purchased the ticket.
    public Ticket(int rowNO , int seatNO , double price , Person user){
        //This assigns the value of the parameter to the  instance variable.
        this.rowNO = rowNO;
        this.seatNO = seatNO;
        this.price = price;
        this.user = user;

    }

    public int getRow() {
        return rowNO; }
    public int getSeat() {
        return seatNO; }

    public double getPrice() {
        return price;
    }
    public void print(){
        System.out.print("\nName: " + user.getName());
        System.out.print("\nSurname: " + user.getSurname());
        System.out.print("\nEmail: " + user.getEmail());
        System.out.print("\nRow-no: " + getRow());
        System.out.print("\nSeat_no: " + getSeat());
        System.out.print("\nPrice: " + price);

    }


}