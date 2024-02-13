import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Theatre {
    // 2D Array to store the seat information
    static int[][] seats = new int[3][];
    // This static block is used to initialize the seats array. It executed automatically when the class is loaded in memory
    // reference from :- https://www.educative.io/answers/what-are-static-blocks-in-java
    static {
        seats[0] = new int[12];
        seats[1] = new int[16];
        seats[2] = new int[20];
    }

    // Scanner object for user input
    static Scanner input = new Scanner(System.in);

    // boolean variable with an initial value of "true". this exit variable can use to control the termination of the program.
    static boolean exit = true;

    // Arraylist to save all the Tickets
    static ArrayList<Ticket> ticketList = new ArrayList<>();

    // main method - this will execute until exit variable is "false". in here I call 3 methods.
    public static void main(String[] args) {
        System.out.println("Welcome to the New Theatre");
        while (exit) {
            printMenu();
            int option = inputChecker();
            optionManager(option);
        }
    }

    // Method to print menu.
    public static void printMenu() {
        System.out.println("-------------------------------------------------");
        System.out.println("Please select an option:");
        System.out.println("1) Buy a ticket");
        System.out.println("2) Print seating area");
        System.out.println("3) Cancel ticket");
        System.out.println("4) List available seats");
        System.out.println("5) Save to file");
        System.out.println("6) Load from file");
        System.out.println("7) Print ticket information and total price");
        System.out.println("8) Sort tickets by price");
        System.out.println("0) Quit");
        System.out.println("-------------------------------------------------");
    }

    // Method to check userInput option. in here it read user input and validate it before returning an integer option.
    public static int inputChecker() {
        while (true) {
            System.out.print("Enter option: ");
            try {
                int data = input.nextInt();
                if (0 <= data && data <= 8) {
                    return data;
                } else {
                    System.out.println("Invalid option. Please enter a value between 0 and 8");
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
                input.nextLine();
            }
        }
    }

    // Method to manage option. in here it perform the appropriate action based on the user's chosen option.
    public static void optionManager(int option) {
        switch (option) {
            case 1:
                buy_ticket(seats);
                break;
            case 2:
                print_seating_area();
                break;
            case 3:
                cancel_ticket(seats);
                break;
            case 4:
                show_available(seats);
                break;
            case 5:
                save(seats);
                break;
            case 6:
                load(seats);
                break;
            case 7:
                show_tickets_info(ticketList);
                break;
            case 8:
                sort_tickets(ticketList);
                break;
            case 0:
                System.out.println("Goodbye!");
                exit = false;
                break;
        }
    }

    // Method to buy ticket. in here I validate row number, seat number, name, surname, price, and also I checked the seat is already booked or not. after that I add the ticket which I bought to the arraylist.
    public static void buy_ticket(int[][] seats) {
        int row;
        while (true) {
            System.out.print("Enter row number (1-3): ");
            try {
                row = input.nextInt() - 1;
                if (0 <= row && row < seats.length) {
                    break;
                } else {
                    System.out.println("Invalid row number, Please enter a value between 1 and 3");
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
                input.nextLine();
            }
        }

        int seat;
        while (true) {
            System.out.print("Enter seat number (1-" + seats[row].length + "): ");
            try {
                seat = input.nextInt() - 1;
                if (0 <= seat && seat < seats[row].length) {
                    break;
                } else {
                    System.out.println("Invalid seat number, Please enter a value between 1 and " + seats[row].length);
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
                input.nextLine();
            }
        }

        if (seats[row][seat] == 1) {
            System.out.println("This seat is already occupied.");
            return;
        }

        int row_num = row + 1;
        int seat_num = seat + 1;

        double ticketPrice;
        while (true) {
            System.out.print("Enter ticket price: ");
            try {
                ticketPrice = input.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input");
                input.nextLine();
            }
        }

        String name;
        while (true) {
            System.out.print("Enter your name: ");
            name = input.next();
            if (name.matches(("^[a-zA-Z]+$"))) {
                break;
            } else {
                System.out.println("Invalid input");
                input.nextLine();
            }
        }

        String surname;
        while (true) {
            System.out.print("Enter your surname: ");
            surname = input.next();
            if (surname.matches(("^[a-zA-Z]+$"))) {
                break;
            } else {
                System.out.println("Invalid input");
                input.nextLine();
            }
        }

        System.out.print("Enter your email: ");
        String email = input.next();

        Person newPerson = new Person(name, surname, email);

        Ticket newTicket = new Ticket(row_num, seat_num, ticketPrice, newPerson);
        ticketList.add(newTicket);

        // make the seat booked
        seats[row][seat] = 1;
        System.out.println("Ticket bought successfully!");
    }

    // Method to print the seating area
    public static void print_seating_area() {
        // I used java text blocks to print the stage area.
        // reference from :- https://www.baeldung.com/java-text-blocks
        System.out.println("""
                    Seating area:
                         ***********
                         *  STAGE  *
                         ***********""");

        // this code for align the display such that shows the seats in the correct location
        for (int i = 0; i < seats.length; i++) {
            if(i==0) {
                System.out.print("    ");
            } if(i==1) {
                System.out.print("  ");
            }
            for (int j = 0; j < seats[i].length; j++) {
                if ((i == 0 && j == 6 ) || (i == 1 && j == 8 ) || (i == 2 && j == 10 )) {
                    System.out.print(" ");
                }
                if (seats[i][j] == 1) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }
            }
            System.out.println();
        }
    }

    // Method to cancel ticket. in here I validate row number, seat number, and also I checked the seat is already booked or not. then if that seat is booked I remove that from arraylist.
    public static void cancel_ticket(int[][] seats) {
        int row;
        while (true) {
            System.out.print("Enter row number (1-3): ");
            try {
                row = input.nextInt() - 1;
                if (0 <= row && row < seats.length) {
                    break;
                } else {
                    System.out.println("Invalid row number, Please enter a value between 1 and 3");
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
                input.nextLine();
            }
        }

        int seat;
        while (true) {
            System.out.print("Enter seat number (1-" + seats[row].length + "): ");
            try {
                seat = input.nextInt() - 1;
                if (0 <= seat && seat < seats[row].length) {
                    break;
                } else {
                    System.out.println("Invalid seat number, Please enter a value between 1 and " + seats[row].length);
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
                input.nextLine();
            }
        }

        if (seats[row][seat] == 0) {
            System.out.println("This seat is not occupied.");
            return;
        }

        int row_num = row + 1;
        int seat_num = seat + 1;

        for (int i = 0; i < ticketList.size(); i++) {
            Ticket ticket = ticketList.get(i);
            if (ticket.getRow() == row_num && ticket.getSeat() == seat_num) {
                ticketList.remove(i);
                break;
            }
        }

        // Make the seat available
        seats[row][seat] = 0;
        System.out.println("The ticket for row " + row_num + ", seat " + seat_num + " has been cancelled.");
    }

    // Method to print available seats
    public static void show_available(int[][] seats) {
        System.out.println("Available seats:");
        for (int row = 0; row < seats.length; row++) {
            System.out.print("Seats available in row " + (row + 1) + ": ");
            for (int seat = 0; seat < seats[row].length; seat++) {
                if (seats[row][seat] == 0) {
                    System.out.print((seat + 1) + " ");
                }
            }
            System.out.println();
        }
    }

    // Method to save
    public static void save(int[][] seats) {
        try {
            FileWriter writer = new FileWriter("seats.txt");
            for (int row = 0; row < seats.length; row++) {
                for (int seat = 0; seat < seats[row].length; seat++) {
                    writer.write(seats[row][seat] + " ");
                }
                writer.write("\n");
            }
            writer.close();
            System.out.println("Seats saved to file.");
        } catch (IOException e) {
            System.out.println("Error while saving the seats to file.");
        }
    }

    // Method to load
    public static void load(int[][] seats) {
        try {
            File file = new File("seats.txt");
            // scanner object to read from the file
            Scanner file_reader  = new Scanner(file);
            int row = 0;
            // to read each line of the file I used hasNextLine and nextLine methods of the Scanner object.
            while (file_reader.hasNextLine()) {
                //  I used the split method to split each line into an array of string, it uses a single space character as the delimiter.
                // reference from :- https://www.geeksforgeeks.org/split-string-java-examples/
                String[] values = file_reader.nextLine().split(" ");
                for (int seat = 0; seat < values.length; seat++) {
                    // I used Integer.parseInt to convert each String in the array to an integer
                    // reference from :- https://blog.hubspot.com/website/parseint-java
                    seats[row][seat] = Integer.parseInt(values[seat]);
                }
                row++;
            }
            file_reader.close();
            System.out.println("Seats loaded from file.");
        } catch (IOException e) {
            System.out.println("Error while loading the seats from file.");
        }
    }

    // Method to show tickets info
    public static void show_tickets_info(ArrayList<Ticket> tickets) {
        System.out.println("Ticket information:");
        for (int i = 0; i < tickets.size(); i++) {
            System.out.println("Ticket " + (i + 1) + ":");
            Ticket ticket = tickets.get(i);
            ticket.print();
        }

        double totalPrice = 0;
        for (int i = 0; i < ticketList.size(); i++) {
            totalPrice += ticketList.get(i).getPrice();
        }
        System.out.println("Total ticket price: £" + totalPrice);
    }

    // Method to sort tickets
    public static void sort_tickets(ArrayList<Ticket> sortedTickets) {
        System.out.println("Sorted ticket information:");
        int minIndex;
        for (int start = 0; start < sortedTickets.size() - 1; start++) {
            minIndex = start;
            for (int i = start + 1; i < sortedTickets.size(); i++) {
                if (sortedTickets.get(i).getPrice() < sortedTickets.get(minIndex).getPrice()) {
                    minIndex = i;
                }
            }
            Ticket temp = sortedTickets.get(start);
            sortedTickets.set(start, sortedTickets.get(minIndex));
            sortedTickets.set(minIndex, temp);
        }
        for (int i = 0; i < sortedTickets.size(); i++) {
            System.out.println("Ticket " + (i + 1) + ":");
            Ticket ticket = sortedTickets.get(i);
            ticket.print();
        }
    }
}





