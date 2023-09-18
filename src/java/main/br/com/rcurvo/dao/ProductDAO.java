package br.com.rcurvo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rcurvo.dao.jbdc.ConnectionFactory;
import br.com.rcurvo.domain.Product;

public class ProductDAO implements IProductDAO {

	@Override
	public Integer register(Product product) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO TB_PRODUCT (ID, CODE, NAME, PRICE) VALUES(nextval('SQ_PRODUCT'),?,?, ?)";
			stm = connection.prepareStatement(sql);
			stm.setString(1, product.getCode());
			stm.setString(2, product.getName());
			stm.setFloat(3, product.getPrice());
			return stm.executeUpdate();
			} catch (Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, null);
		}
	}

	@Override
	public Product search(String code) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Product product = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM TB_PRODUCT WHERE CODE = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, code);
			rs = stm.executeQuery();
			if(rs.next()) {
				product = new Product();
				product.setId(rs.getLong("id"));
				product.setCode(rs.getString("code"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
			}
			return product;
			} catch (Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, null);
		}
	}

	@Override
	public List<Product> searchAll() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Product product = null;
		List<Product> list = new ArrayList<>();
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "select * from tb_product";
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				product = new Product();
				product.setId(rs.getLong("id"));
				product.setCode(rs.getString("code"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getFloat("price"));
				list.add(product);
				}
			return list;
			} catch (Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, rs);
		}
	}

	@Override
	public Integer delete(Product product) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "delete from tb_product where code = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, product.getCode());
			return stm.executeUpdate();
			} catch (Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, null);
		}
	}

	@Override
	public Integer update(Product product) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "UPDATE TB_PRODUCT SET NAME = ?, CODE = ?, PRICE = ? WHERE ID = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, product.getName());
			stm.setString(2, product.getCode());
			stm.setFloat(3, product.getPrice());
			stm.setLong(4, product.getId());
			return stm.executeUpdate();
			} catch (Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, null);
		}
	}
	
	private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
			if (stm != null && !stm.isClosed()) {
				stm.close();
			}
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
