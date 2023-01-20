package Tests;

import bakery.Address;
import org.junit.Test;
import org.junit.Assert;

public class AddressTest {
    Address address = new Address("Miasto", "12-345", "Ulica", 12);

    @Test
    public void cityGetTest() {
    Assert.assertEquals("Miasto", this.address.getCity());
    }

    @Test
    public void postalCodeGetTest() {
        Assert.assertEquals("12-345", this.address.getPostalCode());
    }

    @Test
    public void streetGetTest() {
        Assert.assertEquals("Ulica", this.address.getStreet());
    }

    @Test
    public void houseNumberGetTest() {
        Assert.assertEquals(12, this.address.getHouseNumber());
    }
}
