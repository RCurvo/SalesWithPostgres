package br.com.rcurvo.dao;

import java.util.List;
import br.com.rcurvo.domain.Product;

public interface IProductDAO {
	
	public Integer register(Product product) throws Exception;

	public Product search(String code) throws Exception;
	
	public List<Product> searchAll() throws Exception;

	public Integer delete(Product product) throws Exception;
	
	public Integer update(Product product) throws Exception;

}
