package Tests;
import bakery.Pie;
import org.junit.Test;
import org.junit.Assert;

public class PieTest {
    Integer id1 = 5;
    Pie standardPie = new Pie(id1, "Test Pie", 1.3, 60.0);

    Integer id2 = 54;
    Pie fitPie = new Pie(id2, "Test Fit Pie", 1.1, 65.0, true, false);

    Integer id3 = 154;
    Pie veganPie = new Pie(id3, "Test Vegan Pie", 0.7, 50.0, false, true);

    @Test
    public void getIdTest() {
        Assert.assertEquals(id1, this.standardPie.getId());
        Assert.assertEquals(id2, this.fitPie.getId());
        Assert.assertEquals(id3, this.veganPie.getId());
    }

    @Test
    public void getIsVeganTest() {
        Assert.assertFalse(this.standardPie.getIsVegan());
        Assert.assertFalse(this.fitPie.getIsVegan());
        Assert.assertTrue(this.veganPie.getIsVegan());
    }

    @Test
    public void getIsFitTest() {
        Assert.assertFalse(this.standardPie.getIsFitOffer());
        Assert.assertTrue(this.fitPie.getIsFitOffer());
        Assert.assertFalse(this.veganPie.getIsFitOffer());
    }
}
