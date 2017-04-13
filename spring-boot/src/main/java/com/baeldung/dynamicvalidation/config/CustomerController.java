package com.baeldung.dynamicvalidation.config;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.baeldung.dynamicvalidation.model.Customer;

@Controller
public class CustomerController {

    @GetMapping("/customer")
    public String getCustomerPage(Model model) {
        model.addAttribute("contactInfoType", System.getProperty("contactInfoType"));
        return "customer";
    }

    @PostMapping("/customer")
    public String validateCustomer(@Valid final Customer customer, final BindingResult result, final Model model) {
        if (result.hasErrors()) {
            model.addAttribute("message", "The information is invalid!");
        } else {
            model.addAttribute("message", "The information is valid!");
        }
        return "customer";
    }
}
