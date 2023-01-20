package bakery;

import bakery.Address;

public class Client {
    Integer id;
    private String name;
    private String surname;
    private Address address;

    public Client(Integer id, String name, String surname, Address address) {
        this.setId(id);
        this.setName(name);
        this.setSurname(surname);
        this.address = address;
    }

    public Client(Integer id, String name, String surname, String city, String postalCode, String street, int houseNumber) {
        this.setId(id);
        this.setName(name);
        this.setSurname(surname);
        this.setAddress(city, postalCode, street, houseNumber);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setAddress(String city, String postalCode, String street, int houseNumber) {
        this.address = new Address(city, postalCode, street, houseNumber);
    }

    public Address getAddress() {
        return this.address;
    }


}
