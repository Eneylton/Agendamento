package com.java.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.conexao.ConnectionFactory;
import com.java.modelo.Estado;

public class EstadoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Estado retornarEstadoPorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select id,descricao from estado where id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Estado estado = null;

		while (rs.next()) {

			estado = new Estado();

			estado.setId(rs.getLong("id"));

			estado.setDescricao(rs.getString("descricao"));

		}

		stmt.close();
		con.close();

		return estado;

	}

	public List<Estado> listarTodos() throws SQLException {

		List<Estado> lista = new ArrayList<Estado>();

		String sql = "select id,descricao from estado order by id desc ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Estado estado = null;

		while (rs.next()) {

			estado = new Estado();

			estado.setId(rs.getLong("id"));

			estado.setDescricao(rs.getString("descricao"));

			lista.add(estado);

		}

		stmt.close();
		con.close();

		return lista;

	}

	public void incluir(Estado estado) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "INSERT INTO estado (descricao) " + "VALUES (?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, estado.getDescricao());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(Estado estado) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "update estado set descricao =? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, estado.getDescricao());
		stmt.setLong(2, estado.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(Estado estado) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from estado where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, estado.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}
