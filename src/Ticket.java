public class Ticket {
    private int row;
    private int seat;
    private double price;
    Person person;

    // Constructor
    public Ticket(int row, int seat, double price, Person Person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = Person;
    }

    // method to print all the information of a ticket
    public void print() {
        System.out.println("Person name: " + person.getName());
        System.out.println("Person surname: " + person.getSurname());
        System.out.println("Person email: " + person.getEmail());
        System.out.println("Row number: " + row);
        System.out.println("Seat number: " + seat);
        System.out.println("Ticket Price: £" + price);
        System.out.println();
    }

    public int getRow() {
        return this.row;
    }

    public int getSeat() {
        return this.seat;
    }

    public double getPrice() {
        return this.price;
    }

    public Person getPerson() {
        return this.person;
    }

}
