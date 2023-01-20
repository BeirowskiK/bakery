package Tests;
import org.junit.Test;
import org.junit.Assert;
import bakery.Cake;

public class CakeTest {

    Cake standardCake = new Cake();
    Cake customCake = new Cake("My text");
    Cake specialCake = new Cake(7.3, 115.0, "My special text");

    @Test
    public void standardCakeGetNameTest() {
        Assert.assertEquals("Standard b-day cake", this.standardCake.getName());
    }

    @Test
    public void standardCakeGetWeightTest() {
        Assert.assertEquals(2.0,this.standardCake.getWeight(), 0.1);
    }
    @Test
    public void standardCakeGetVFKTest() {
        Assert.assertEquals(100.0, this.standardCake.getValueForKg(), 0.1);
    }

    @Test
    public void standardCakeGetCustomMessageTest() {
        Assert.assertEquals("Happy Birthday", this.standardCake.getCustomMessage());
    }

    @Test
    public void customCakeGetNameTest() {
        Assert.assertEquals("Custom b-day cake", this.customCake.getName());
    }

    @Test
    public void customCakeGetWeightTest() {
        Assert.assertEquals(2.0,this.customCake.getWeight(), 0.1);
    }
    @Test
    public void customCakeGetVFKTest() {
        Assert.assertEquals(110.0, this.customCake.getValueForKg(), 0.1);
    }

    @Test
    public void customCakeGetCustomMessageTest() {
        Assert.assertEquals("My text", this.customCake.getCustomMessage());
    }

    @Test
    public void specialCakeGetNameTest() {
        Assert.assertEquals("Special cake", this.specialCake.getName());
    }

    @Test
    public void specialCakeGetWeightTest() {
        Assert.assertEquals(7.3,this.specialCake.getWeight(), 0.1);
    }
    @Test
    public void specialCakeGetVFKTest() {
        Assert.assertEquals(115.0, this.specialCake.getValueForKg(), 0.1);
    }

    @Test
    public void specialCakeGetCustomMessageTest() {
        Assert.assertEquals("My special text", this.specialCake.getCustomMessage());
    }

}
