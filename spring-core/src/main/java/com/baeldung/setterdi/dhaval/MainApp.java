package com.baeldung.setterdi.dhaval;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("com/baeldung/setterdi/dhaval/Beans.xml");

		Subject sub = (Subject) context.getBean("subject");
		sub.beginStudy();

		context.close();
	}
}
