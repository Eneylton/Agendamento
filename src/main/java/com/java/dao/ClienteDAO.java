package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Cidade;
import com.java.modelo.Cliente;

public class ClienteDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Cliente retornarClientePorID(Long id) throws ClassNotFoundException, SQLException {
		

		String sql = "SELECT id,nome, cidade_id FROM cliente  where id = ? ";
		

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Cliente cliente = null;

		while (rs.next()) {

			cliente = new Cliente();
			
			Cidade cidade = new Cidade();
			cidade.setId(rs.getLong("id"));
			cliente.setCidade(cidade);
			cliente.setId(rs.getLong("id"));
			cliente.setNome(rs.getString("nome"));

		}

		stmt.close();
		con.close();

		return cliente;

	}
	
	
	
	public List<Cliente> listarTodos() throws SQLException {

		List<Cliente> lista = new ArrayList<Cliente>();

		String sql = "select id,nome,cidade_id from cliente order by id desc ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Cliente cliente = null;

		while (rs.next()) {

			cliente = new Cliente();

			cliente.setId(rs.getLong("id"));
			cliente.setNome(rs.getString("nome"));
			
			
			Cidade cidade = new Cidade();
			cidade.setId(rs.getLong("id"));
			cliente.setCidade(cidade);
			cliente.setId(rs.getLong("id"));
			cliente.setNome(rs.getString("nome"));

			lista.add(cliente);

		}

		stmt.close();
		con.close();

		return lista;

	}
	
	
	

	public void incluir(Cliente cliente) throws SQLException {

		con = new ConnectionFactory().getConnection();

		
		String sql = "INSERT INTO cliente (nome,cidade_id) " + "VALUES (?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, cliente.getNome());
		stmt.setLong(2, cliente.getCidade().getId());
	

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(Cliente cliente) throws SQLException {

		con = new ConnectionFactory().getConnection();

		

		String sql = "update cliente set " + "nome = ?, cidade_id = ?  where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, cliente.getNome());

		stmt.setLong(2, cliente.getCidade().getId());

		stmt.setLong(3, cliente.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(Cliente cliente) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from cliente where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, cliente.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}
