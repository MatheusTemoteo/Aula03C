package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Cliente;

public class ClienteDAO {
	public int criar(Cliente cliente) {
		String sqlInsert = "INSERT INTO cliente(pais, populacao, area) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, cliente.getPais());
			stm.setLong(2, cliente.getPopulacao());
			stm.setDouble(3, cliente.getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					cliente.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente.getId();
	}

	public void atualizar(Cliente cliente) {
		String sqlUpdate = "UPDATE cliente SET pais=?, populacao=?, area=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, cliente.getPais());
			stm.setLong(2, cliente.getPopulacao());
			stm.setDouble(3, cliente.getArea());
			stm.setInt(4, cliente.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM cliente WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, id);
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Cliente carregar(int id) {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		String sqlSelect = "SELECT pais, populacao, area FROM cliente WHERE cliente.id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, cliente.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					cliente.setPais(rs.getString("pais"));
					cliente.setPopulacao(rs.getLong("populacao"));
					cliente.setArea(rs.getDouble("area"));
				} else {
					cliente.setId(-1);
					cliente.setPais(null);
					cliente.setPopulacao(null);
					cliente.setArea(null);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return cliente;
	}

	public ArrayList<Cliente> listarTodos() {
		ArrayList<Cliente> clientes = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, fone, email FROM cliente";
		Cliente cliente;

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					cliente = new Cliente();
					cliente.setId(rs.getInt("id"));
					cliente.setPais(rs.getString("pais"));
					cliente.setPopulacao(rs.getLong("populacao"));
					cliente.setArea(rs.getDouble("area"));
					clientes.add(cliente);
				} 
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return clientes;
	}

}