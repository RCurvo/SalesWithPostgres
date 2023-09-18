package br.com.rcurvo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import br.com.rcurvo.dao.IProductDAO;
import br.com.rcurvo.dao.ProductDAO;
import br.com.rcurvo.domain.Product;

public class ProductTest {
	
	@Test
	public void registerTest() throws Exception {
		IProductDAO dao = new ProductDAO();
		Product product = new Product();
		product.setCode("03");
		product.setName("TV LED 55 inches");
		product.setPrice(1999.99f);
		Integer result = dao.register(product);
		assertTrue(result == 1);
		
		Product productDB = dao.search(product.getCode());
		assertNotNull(productDB);
		assertNotNull(productDB.getId());
		assertEquals(productDB.getCode(), productDB.getCode());
		assertEquals(productDB.getName(), productDB.getName());
		assertEquals(productDB.getPrice(), productDB.getPrice());
		
		Integer resultDel = dao.delete(productDB);
		assertNotNull(resultDel);
	}
	
	@Test
	public void updateTest() throws Exception {
		IProductDAO dao = new ProductDAO();
		Product product = new Product();
		product.setCode("03");
		product.setName("TV LED 55 inches");
		product.setPrice(1999.99f);
		Integer resultRegister = dao.register(product);
		assertTrue(resultRegister == 1);
		
		Product productDB = dao.search(product.getCode());
		assertNotNull(productDB);
		assertNotNull(productDB.getId());
		assertEquals(product.getCode(), productDB.getCode());
		assertEquals(product.getName(), productDB.getName());
		
		productDB.setName("Update");
		productDB.setCode("06");
		productDB.setPrice(2000f);
		Integer resultUpdate = dao.update(productDB);
		assertTrue(resultUpdate == 1);
		
		
		Integer resultDel = dao.delete(productDB);
		assertNotNull(resultDel);
	}
	
	@Test
	public void searchAllTest() throws Exception {
		IProductDAO dao = new ProductDAO();
		Product product1 = new Product();
		List<Product> productList = null;
		product1.setCode("03");
		product1.setName("MICROWAVE");
		product1.setPrice(350f);
		Integer resultRegister1 = dao.register(product1);
		assertTrue(resultRegister1 == 1);
		
		Product product2 = new Product();
		product2.setCode("05");
		product2.setName("REFRIGERATOR");
		product2.setPrice(600f);
		Integer resultRegister2 = dao.register(product2);
		assertTrue(resultRegister2 == 1);
		
		productList = dao.searchAll();
		assertNotNull(productList);
		assertNotNull(productList.get(0));
		assertEquals(productList.get(0).getName(), product1.getName());
		
		
		assertNotNull(productList.get(1));
		assertEquals(productList.get(1).getCode(), product2.getCode());
		
		Integer resultDel1 = dao.delete(productList.get(0));
		assertNotNull(resultDel1);
		Integer resultDel2 = dao.delete(productList.get(1));
		assertNotNull(resultDel2);
	}

}
