package com.spring.customer;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class CustomerDAO {
	
	@Autowired
	private HibernateTemplate template;
	
	public void saveCustomer(Customer c){  
	   template.save(c);
	   System.out.println("Done");
	}  
	
	public void updateCustomer(Customer c){  
	    template.update(c);
	}  
	 
	
	public void deleteCustomer(Customer c){  
	    template.delete(c);
	} 
	
	public Customer getCustomerByFirstName(int s){ 
		List<Customer> customer=new ArrayList<Customer>();
		customer=(List<Customer>) template.findByNamedParam("from Customer as  c where c.id= :id", "id", s);
	    Customer c=customer.get(0);
		return c;
	} 
	
	public List<Customer> getCustomers(){    
		List<Customer> customer=new ArrayList<Customer>();
		customer=(List<Customer>) template.find("from Customer");
		
		return customer;
	}
	}  