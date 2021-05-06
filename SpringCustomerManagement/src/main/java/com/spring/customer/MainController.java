package com.spring.customer;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Null;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.*;

@Controller
public class MainController {
    
	List<Customer> customerList = new ArrayList<Customer>();
	public static Logger log = Logger.getLogger(MainController.class);
	
	@Autowired
	private CustomerDAO customerDAO;
	
	public MainController() {
	    
		customerList.add(new Customer(1,"Suresh", "Sharma", "Suresh@gmail.com", 4145784578l));
		customerList.add(new Customer(2,"Nitesh", "Singh", "Nitesh@gmail.com", 4175784578l));
		customerList.add(new Customer(3,"Ritesh", "Kumar", "Ritesh@gmail.com", 4185784578l));
		customerList.add(new Customer(4,"Himesh", "Kant", "Himesh@gmail.com", 4142784578l));
		customerList.add(new Customer(5,"Haresh", "Mehta", "Haresh@gmail.com", 4115784578l));
	    System.out.println(customerList);
	} 
	
	@RequestMapping(value="/home",method = RequestMethod.GET)
	public String home() {
//		String string=null;
//		string.length();
		log.info("Home Page Initiated");
		return "test";	}
	
	@RequestMapping("/exception_practice")
	public String exceptionPractice() {
		String string="Java is the best";
		Integer.parseInt(string);
		return "index";
	}
	
	
	@RequestMapping("/login")
	public String SignIn(@ModelAttribute("customer") Customer customer) {
		log.info("Login Page Initiated");
		return "login";
	}
	
	@RequestMapping("/uservalidation")
	public String userValidation(Model model, @Valid @ModelAttribute("customer") Customer customer, Errors errors  ) {
		if(errors.hasErrors()) {
			log.warn("Invalid Login");
			return "login";
		}
		customerList.add(customer);
		customerDAO.saveCustomer(customer);
		model.addAttribute("Name", customer.getFirstname());
		log.info("Login Successful");
			return "redirect:/viewcustomer";
		}
	
	
	@RequestMapping(path = "/details/{name}")
	public String studentProfile(@PathVariable("name") String user, Model model) {
		log.info("Customer Profile Accessed");
		for (Customer customer : customerList) {
			if (customer.getFirstname().equals(user)) {
				model.addAttribute("usern", customer);
			}
		}
		
		return "customer";
	}
	
	@RequestMapping("/viewcustomer")    
    public String viewemp(Model m){    
        List<Customer> list=customerDAO.getCustomers();    
        m.addAttribute("list",list);  
        return "viewcustomer";    
    }    
	
	@RequestMapping(path = "/editcustomer/{id}")    
    public String viewemp(@PathVariable(name = "id") int id,Model m){    
        Customer customer = customerDAO.getCustomerByFirstName(id);   
        m.addAttribute("command",customer);  
        return "editcustomer";
    }    
	
	@RequestMapping(path = "/updatecustomer")    
    public String viewemp(@ModelAttribute Customer customer,Model m){    
        customerDAO.updateCustomer(customer);  
        List<Customer> list=customerDAO.getCustomers();    
        m.addAttribute("list",list); 
        return "viewcustomer";    
    }   
	
	
	
//	@ExceptionHandler(value=NullPointerException.class)
//	public String CustomExceptionHandler(Model m) {
//		m.addAttribute("message", "Null Point Exception Has Occured");
//		return "null_pointer";
//	}
//	
//	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler(value=Exception.class)
//	public String GlobalExceptionHandler(Model m) {
//		m.addAttribute("message2", "Exception Has Occured");
//		return "exception";
//	}
}
