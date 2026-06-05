package models;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String phoneNumber;

    public User(String phoneNumber, String zipcode, String state, String city, String address, String password, String email, String lastName, String firstName) {
        this.phoneNumber = phoneNumber;
        this.zipcode = zipcode;
        this.state = state;
        this.city = city;
        this.address = address;
        this.password = password;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
