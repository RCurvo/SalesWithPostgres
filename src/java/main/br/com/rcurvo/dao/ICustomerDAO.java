package br.com.rcurvo.dao;

import java.util.List;

import br.com.rcurvo.domain.Customer;

public interface ICustomerDAO {
	
	public Integer register(Customer customer) throws Exception;

	public Customer search(String code) throws Exception;
	
	public List<Customer> searchAll() throws Exception;

	public Integer delete(Customer customer) throws Exception;
	
	public Integer update(Customer customer) throws Exception;

}
