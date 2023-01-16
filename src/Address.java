public class Address {
    private String city;
    private String postalCode;
    private String street;
    private int houseNumber;

    public Address(String city, String postalCode, String street, int houseNumber) {
        this.setCity(city);
        this.setPostalCode(postalCode);
        this.setStreet(street);
        this.setHouseNumber(houseNumber);
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return this.street;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getHouseNumber() {
        return this.houseNumber;
    }

}
