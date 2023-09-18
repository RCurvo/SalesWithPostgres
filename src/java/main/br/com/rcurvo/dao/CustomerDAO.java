package br.com.rcurvo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.rcurvo.dao.jbdc.ConnectionFactory;
import br.com.rcurvo.domain.Customer;

public class CustomerDAO implements ICustomerDAO{
	
	public Integer register(Customer customer) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "INSERT INTO TB_CUSTOMER (ID, CODE, NAME) VALUES(nextval('SQ_CUSTOMER'),?,?)";
			stm = connection.prepareStatement(sql);
			stm.setString(1, customer.getCode());
			stm.setString(2, customer.getName());
			return stm.executeUpdate();
			} catch (Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, null);
		}
	}

	@Override
	public Customer search(String code) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Customer customer = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "select * from tb_customer where code = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, code);
			rs = stm.executeQuery();
			if (rs.next()) {
				customer = new Customer();
				customer.setId(rs.getLong("id"));
				customer.setCode(rs.getString("code"));
				customer.setName(rs.getString("name"));
				}
			return customer;
			} catch (Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, rs);
		}
	}

	@Override
	public Integer delete(Customer customer) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "delete from tb_customer where code = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, customer.getCode());
			return stm.executeUpdate();
			} catch (Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, null);
		}
	}

	@Override
	public Integer update(Customer customer) throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "UPDATE TB_CUSTOMER SET NAME = ?, CODE = ? WHERE ID = ?";
			stm = connection.prepareStatement(sql);
			stm.setString(1, customer.getName());
			stm.setString(2, customer.getCode());
			stm.setLong(3, customer.getId());
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

	@Override
	public List<Customer> searchAll() throws Exception {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		Customer customer = null;
		List<Customer> list = new ArrayList<>();
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "select * from tb_customer";
			stm = connection.prepareStatement(sql);
			rs = stm.executeQuery();
			while (rs.next()) {
				customer = new Customer();
				customer.setId(rs.getLong("id"));
				customer.setCode(rs.getString("code"));
				customer.setName(rs.getString("name"));
				list.add(customer);
				}
			return list;
			} catch (Exception e) {
			throw e;
		} finally {
			closeConnection(connection, stm, rs);
		}
	}
}
