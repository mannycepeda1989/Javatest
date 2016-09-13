package org.baeldung.equalshashcode.entities;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Test;

public class SquareClassTest {

    @Test
    public void testEqualsAndHashcodes() {

        Square aObject = new Square(10, Color.BLUE);
        Square bObject = new Square(10, Color.BLUE);

        Square dObject = new Square(20, Color.BLUE);

<<<<<<< HEAD
        Assert.assertTrue(aObject.equals(bObject) && bObject.equals(aObject));

        Assert.assertTrue(aObject.hashCode() == bObject.hashCode());

=======
        // equals()
        Assert.assertTrue(aObject.equals(bObject) && bObject.equals(aObject));
        // hashCode()
        Assert.assertTrue(aObject.hashCode() == bObject.hashCode());
        // non-equal objects are not equals() and have different hashCode()
>>>>>>> 25a939665971866fadd0b1b78e09dd732d736193
        Assert.assertFalse(aObject.equals(dObject));
        Assert.assertFalse(aObject.hashCode() == dObject.hashCode());
    }

}
