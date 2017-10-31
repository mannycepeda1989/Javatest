package com.baeldung.creationaldp.abstractfactory;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractPatternDriverTest {
    @Test
    public void givenAbstractFactory_whenGettingObjects_thenSuccessful() {
        AbstractFactory abstractFactory;
        
        //creating a brown toy dog
        abstractFactory = FactoryProvider.getFactory("Toy");
        Toy toy = abstractFactory.getToy("Dog");
        
        abstractFactory = FactoryProvider.getFactory("Color");
        Color color = abstractFactory.getColor("Brown");
        
        String result = "A " + toy.getType() + " with " + color.getColor() + " color " + toy.makeSound();
        assertEquals("A Dog with brown color Barks", result);
    }

}
