package br.com.rcurvo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.rcurvo.dao.CustomerDAO;
import br.com.rcurvo.dao.ICustomerDAO;
import br.com.rcurvo.domain.Customer;

public class CustomerTest {
	
	@Test
	public void registerTest() throws Exception {
		ICustomerDAO dao = new CustomerDAO();
		Customer customer = new Customer();
		customer.setCode("03");
		customer.setName("Renan JAVA");
		Integer result = dao.register(customer);
		assertTrue(result == 1);
		
		Customer customerDB = dao.search(customer.getCode());
		assertNotNull(customerDB);
		assertNotNull(customerDB.getId());
		assertEquals(customer.getCode(), customerDB.getCode());
		assertEquals(customer.getName(), customerDB.getName());
		
		Integer resultDel = dao.delete(customerDB);
		assertNotNull(resultDel);
	}
	
	@Test
	public void updateTest() throws Exception {
		ICustomerDAO dao = new CustomerDAO();
		Customer customer = new Customer();
		customer.setCode("03");
		customer.setName("Renan JAVA");
		Integer resultRegister = dao.register(customer);
		assertTrue(resultRegister == 1);
		
		Customer customerDB = dao.search(customer.getCode());
		assertNotNull(customerDB);
		assertNotNull(customerDB.getId());
		assertEquals(customer.getCode(), customerDB.getCode());
		assertEquals(customer.getName(), customerDB.getName());
		
		customerDB.setName("Update");
		customerDB.setCode("06");
		Integer resultUpdate = dao.update(customerDB);
		assertTrue(resultUpdate == 1);
		
		
		Integer resultDel = dao.delete(customerDB);
		assertNotNull(resultDel);
	}
	
	@Test
	public void searchAllTest() throws Exception {
		ICustomerDAO dao = new CustomerDAO();
		Customer customer1 = new Customer();
		List<Customer> customerList = null;
		customer1.setCode("03");
		customer1.setName("Renan JAVA");
		Integer resultRegister1 = dao.register(customer1);
		assertTrue(resultRegister1 == 1);
		
		Customer customer2 = new Customer();
		customer2.setCode("04");
		customer2.setName("Uncle Bob");
		Integer resultRegister2 = dao.register(customer2);
		assertTrue(resultRegister2 == 1);
		
		customerList = dao.searchAll();
		assertNotNull(customerList);
		assertNotNull(customerList.get(0));
		assertEquals(customerList.get(0).getName(), customer1.getName());
		
		
		assertNotNull(customerList.get(1));
		assertEquals(customerList.get(1).getCode(), customer2.getCode());
		
		Integer resultDel1 = dao.delete(customerList.get(0));
		assertNotNull(resultDel1);
		Integer resultDel2 = dao.delete(customerList.get(1));
		assertNotNull(resultDel2);
	}

}
