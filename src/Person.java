public class Person {
    private String name;
    private String surname;
    private String email;

    // Constructor
    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    
    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getEmail() {
        return this.email;
    }
}
