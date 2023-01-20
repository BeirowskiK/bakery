package Tests;
import bakery.Client;
import bakery.Address;
import org.junit.Test;
import org.junit.Assert;

import java.util.Optional;

public class ClientTest {
    Address address = new Address("Miasto", "12-345", "Ulica", 12);
    Address address2 = new Address("City", "22-222", "Street", 123);
    Integer id  = 154;
    Client client = new Client(id, "Test", "Testowy", address);
    Client client2 = new Client(id, "Test", "Testowy", "City", "22-222", "Street", 123);


    @Test
    public void getIdTest() {
        Assert.assertEquals(this.id, this.client.getId());
    }

    @Test
    public void getNameTest() {
        Assert.assertEquals("Test", this.client.getName());
    }

    @Test
    public void getSurnameTest() {
        Assert.assertEquals("Testowy", this.client.getSurname());
    }

    @Test
    public void getAddressTest() {
        Assert.assertEquals(address, this.client.getAddress());
    }

    @Test
    public void getIdTest2() {
        Assert.assertEquals(this.id, this.client2.getId());
    }

    @Test
    public void getNameTest2() {
        Assert.assertEquals("Test", this.client2.getName());
    }

    @Test
    public void getSurnameTest2() {
        Assert.assertEquals("Testowy", this.client2.getSurname());
    }

    @Test
    public void getAddressTest2() {
        Assert.assertEquals(address2.getCity(), this.client2.getAddress().getCity());
        Assert.assertEquals(address2.getPostalCode(), this.client2.getAddress().getPostalCode());
        Assert.assertEquals(address2.getStreet(), this.client2.getAddress().getStreet());
        Assert.assertEquals(address2.getHouseNumber(), this.client2.getAddress().getHouseNumber());
    }
}
