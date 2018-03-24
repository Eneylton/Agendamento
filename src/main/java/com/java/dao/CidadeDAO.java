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
import com.java.modelo.Estado;

public class CidadeDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Connection con;

	public Cidade retornarCidadePorID(Long id) throws ClassNotFoundException, SQLException {

		String sql = "select id,descricao,idestado from cidade where id = ? ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, id);

		ResultSet rs = stmt.executeQuery();

		Cidade cidade = null;

		while (rs.next()) {

			cidade = new Cidade();

			cidade.setId(rs.getLong("id"));

			cidade.setDescricao(rs.getString("descricao"));

			Estado estado = new Estado();
			estado.setId(rs.getLong("id"));
			cidade.setEstado(estado);

		}

		stmt.close();
		con.close();

		return cidade;

	}

	public List<Cidade> buscarPorEstado(Long idestado) throws ClassNotFoundException, SQLException {

		String sql = "select id,descricao,idestado from cidade where idestado = ? ";

		con = new ConnectionFactory().getConnection();

		List<Cidade> lista = new ArrayList<Cidade>();

		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setLong(1, idestado);

		ResultSet rs = stmt.executeQuery();

		Cidade cidade = null;

		while (rs.next()) {

			cidade = new Cidade();

			cidade.setId(rs.getLong("id"));

			cidade.setDescricao(rs.getString("descricao"));

			cidade.setId(rs.getLong("idestado"));

		}

		stmt.close();
		con.close();

		return lista;

	}

	public List<Cidade> listarTodos() throws SQLException {

		List<Cidade> lista = new ArrayList<Cidade>();

		String sql = "select id,descricao,idestado from cidade order by id desc ";

		con = new ConnectionFactory().getConnection();

		PreparedStatement stmt = con.prepareStatement(sql);

		ResultSet rs = stmt.executeQuery();

		Cidade cidade = null;

		while (rs.next()) {

			cidade = new Cidade();

			cidade.setId(rs.getLong("id"));

			cidade.setDescricao(rs.getString("descricao"));

			Estado estado = new Estado();
			estado.setId(rs.getLong("id"));
			cidade.setEstado(estado);

			lista.add(cidade);

		}

		stmt.close();
		con.close();

		return lista;

	}

	public void incluir(Cidade cidade) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "INSERT INTO cidade (descricao,idestado) " + "VALUES (?,?)";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, cidade.getDescricao());
		stmt.setLong(2, cidade.getEstado().getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void alterar(Cidade cidade) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "update cidade set descricao =?, idcidade = ? where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setString(1, cidade.getDescricao());
		stmt.setLong(2, cidade.getEstado().getId());
		stmt.setLong(3, cidade.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

	public void excluir(Cidade cidade) throws SQLException {

		con = new ConnectionFactory().getConnection();

		String sql = "delete from cidade where id = ?";

		PreparedStatement stmt;

		stmt = con.prepareStatement(sql);

		stmt.setLong(1, cidade.getId());

		stmt.execute();
		stmt.close();
		con.close();

	}

}
